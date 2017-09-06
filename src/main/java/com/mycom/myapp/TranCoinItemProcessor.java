package com.mycom.myapp;

import java.util.HashMap;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.kiss.util.SendMail;
import com.mycom.bitBatch.Api_Client;
import com.mycom.bitBatch.Constants;
import com.mycom.bitBatch.Tran;
import com.mycom.myapp.dao.TranConfigDAO;
import com.mycom.myapp.vo.TranConfigVO;

public class TranCoinItemProcessor implements ItemProcessor<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(TranCoinItemProcessor.class);
	private static final int SLEEP_TIME = 200;

	@Inject
	private TranConfigDAO tranConfigDAO;

	@Override
	public String process(String item) throws Exception {
		this.tranCoin(Constants.CURRENCY_XRP);
		Thread.sleep(SLEEP_TIME);
		this.tranCoin(Constants.CURRENCY_LTC);
		Thread.sleep(SLEEP_TIME);
		this.tranCoin(Constants.CURRENCY_ETH);
		return item;
	}

	public String tranCoin(String sCurrency) {
		SendMail sendMail = new SendMail();
		Api_Client api = new Api_Client(Constants.API_KEY, Constants.SECRET_KEY);

		HashMap<String, String> rgParams = new HashMap<String, String>();
		rgParams.put("order_currency", sCurrency);
		rgParams.put("payment_currency", "KRW");
		rgParams.put("currency", sCurrency);

		Tran tran = new Tran();

		TranConfigVO vo = new TranConfigVO();
		vo.setCurrency(sCurrency);
		vo.setTran_type("B");
		TranConfigVO tranConfigBuyVO = tranConfigDAO.getTranConfig(vo);

		vo = new TranConfigVO();
		vo.setCurrency(sCurrency);
		vo.setTran_type("S");
		TranConfigVO tranConfigSellVO = tranConfigDAO.getTranConfig(vo);

		Float lBuyPrice = tranConfigBuyVO.getTarget_price();
		String sTranBuyYn = tranConfigBuyVO.getTran_yn();
		Float lBuyUnits = tranConfigBuyVO.getUnits();
		String sBuyStatus = tranConfigBuyVO.getStatus();

		Float lSellPrice = tranConfigSellVO.getTarget_price();
		Float lSellUnits = tranConfigSellVO.getUnits();
		String sTranSellYn = tranConfigSellVO.getTran_yn();
		String sSellStatus = tranConfigSellVO.getStatus();

		try {
			String result = api.callApi("/public/ticker/" + sCurrency, rgParams);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

			JSONObject jsonObj2 = (JSONObject) jsonObj.get("data");

			String price = (String) jsonObj2.get("closing_price");
			System.out.println(sCurrency + " Price =============> " + price);

			if ("N".equals(sBuyStatus)) {
				if (new Float(price).intValue() <= lBuyPrice) {
					float krw_balance = tran.krw_balance().floatValue();
					if (tran.krw_balance().floatValue() > lBuyUnits * 10) {
						if ("Y".equals(sTranBuyYn)) {
							tran.market_buy(sCurrency, new Float((krw_balance / new Float(price).floatValue()) * 0.97),
									price);
							vo = new TranConfigVO();
							vo.setCurrency(sCurrency);
							vo.setStatus("Y");
							vo.setTran_type("B");
							tranConfigDAO.updateTranConfig(vo);

							vo = new TranConfigVO();
							vo.setCurrency(sCurrency);
							vo.setStatus("N");
							vo.setTran_type("S");
							tranConfigDAO.updateTranConfig(vo);
							sendMail.sendMail("Bitsum Buy", sCurrency + "/" + " Buy " + "/" + " Unit:" + lBuyUnits + "/" + " Price:" + price);
						} else {
							System.out.println("Tran is Not Setting");
						}
					} else
					{
						System.out.println("tran.krw_balance().floatValue() ==>" + tran.krw_balance().floatValue());
					}
				}
			}

			// lSellPrice = lBuyPrice.longValue() + 5;
			System.out.println(sCurrency + " SellTargetPrice ==========================> " + lSellPrice);

			if ("N".equals(sSellStatus)) {
				if (new Float(price).intValue() > lSellPrice) {
					float currency_balance = tran.currency_balance(sCurrency).floatValue();
					if (currency_balance > lSellUnits.floatValue()) {
						if ("Y".equals(sTranSellYn)) {
							tran.market_sell(sCurrency, lSellUnits, price);

							vo = new TranConfigVO();
							vo.setCurrency(sCurrency);
							vo.setStatus("Y");
							vo.setTran_type("S");
							tranConfigDAO.updateTranConfig(vo);

							vo = new TranConfigVO();
							vo.setCurrency(sCurrency);
							vo.setStatus("N");
							vo.setTran_type("B");
							tranConfigDAO.updateTranConfig(vo);
							
							sendMail.sendMail("Bitsum Sell", sCurrency + "/" + " Sell " + "/" + " Unit:" + lBuyUnits + "/" + " Price:" + price);
						} else {
							System.out.println("Tran is Not Setting");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("tranCoin --> " + sCurrency);
		return "item";
	}

}