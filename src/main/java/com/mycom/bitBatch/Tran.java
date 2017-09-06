package com.mycom.bitBatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mycom.bitBatch.biz.dao.BitDao;
import com.mycom.bitBatch.biz.dao.ComEntity;
import com.mycom.myapp.dao.TranConfigDAO;
import com.mycom.myapp.vo.TranConfigVO;


public class Tran {
	@Inject
	private TranConfigDAO tranConfigDAO;
	
	public void market_buy(String currency, float cnt, String price) throws ParseException, IOException {
//		{
//		    "status"    : "0000",
//		    "order_id"  : "1429500241523",
//		    "data": [
//		        {
//		            "cont_id"   : "15364",
//		            "units"     : "0.16789964",
//		            "price"     : "270000",
//		            "total"     : 45333,
//		            "fee"       : "0.00016790"
//		        },
//		        {
//		            "cont_id"   : "15365",
//		            "units"     : "0.08210036",
//		            "price"     : "289000",
//		            "total"     : 23727,
//		            "fee"       : "0.00008210"
//		        }
//		    ]
//		}
//		[Returned Value Description]
//				Key Name	Description
//				status	결과 상태 코드 (정상 : 0000, 정상이외 코드는 에러 코드 참조)
//				order_id	주문 번호
//				cont_id	체결 번호
//				units	총 구매 수량(수수료 포함)
//				price	1Currency당 KRW 시세 (BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR)
//				total	구매 KRW
//				fee	구매 수수료		
		
		// [Request Parameters]
		// Parameter Name Data Type Description
		// apiKey String apiKey
		// secretKey String scretKey
		// units Float 주문 수량
		//
		// - 1회 최소 수량 (BTC: 0.001 | ETH: 0.01 | DASH: 0.01 | LTC: 0.1 | ETC: 0.1 | XRP:
		// 10 | BCH: 0.01 | XMR: 0.01)
		// - 1회 거래 한도 : 1억원
		// currency String BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR (기본값: BTC)		

		Api_Client api = new Api_Client(Constants.API_KEY, Constants.SECRET_KEY);

		HashMap<String, String> rgParams = new HashMap<String, String>();
		rgParams.put("currency", currency);
		rgParams.put("payment_currency", "KRW");
		Float fCnt = (float) Math.floor(cnt);
		rgParams.put("units", fCnt.toString());

		String result = api.callApi("/trade/market_buy", rgParams);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

		System.out.println(jsonObj);

		System.out.println("Buy Cnt ===> " + fCnt.toString());
		System.out.println("Buy Price ===> " + price);
	}

	public void market_sell(String currency, float cnt, String price) throws ParseException, IOException {
		// https://api.bithumb.com/trade/market_sell
		// apiKey String apiKey
		// secretKey String scretKey
		// units Float 占쌍뱄옙 占쏙옙占쏙옙
		//
		// - 1회 占쌍쇽옙 占쏙옙占쏙옙 (BTC: 0.001 | ETH: 0.01 | DASH: 0.01 | LTC: 0.1 | ETC: 0.1
		// | XRP: 10 | BCH: 0.01)
		// - 1회 占신뤄옙 占싼듸옙 : 1占쏙옙占�
		// currency String BTC, ETH, DASH, LTC, ETC, XRP, BCH (占썩본占쏙옙: BTC)

		Api_Client api = new Api_Client(Constants.API_KEY, Constants.SECRET_KEY);

		HashMap<String, String> rgParams = new HashMap<String, String>();
		rgParams.put("currency", currency);
		rgParams.put("payment_currency", "KRW");
		Float fCnt = (float) Math.floor(cnt);
		rgParams.put("units", fCnt.toString());

		String result = api.callApi("/trade/market_sell", rgParams);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

		System.out.println(jsonObj);

		//System.out.println("Sell Cnt ===> " + fCnt.toString());
		//System.out.println("Sell Price ===> " + price);
	}

	public Long krw_balance() throws ParseException, IOException {
		// status 占쏙옙占� 占쏙옙占쏙옙 占쌘듸옙 (占쏙옙占쏙옙 : 0000, 占쏙옙占쏙옙占싱울옙 占쌘듸옙占� 占쏙옙占쏙옙 占쌘듸옙 占쏙옙占쏙옙)
		// total_{currency} 占쏙옙체 Currency (btc, eth, dash, ltc, etc, xrp, bch)
		// total_krw 占쏙옙체 KRW
		// in_use_{currency} 占쏙옙占쏙옙占� Currency (btc, eth, dash, ltc, etc, xrp, bch)
		// in_use_krw 占쏙옙占쏙옙占� KRW
		// available_{currency} 占쏙옙占� 占쏙옙占쏙옙 Currency (btc, eth, dash, ltc, etc, xrp,
		// bch)
		// available_krw 占쏙옙占� 占쏙옙占쏙옙 KRW
		// misu_{currency} 占신울옙킹占� Currency (btc, eth, dash, ltc, etc, xrp, bch)
		// misu_krw 占신울옙킹占� KRW
		// xcoin_last bithumb 占쏙옙占쏙옙占쏙옙 占신뤄옙체占쏙옙 占쌥억옙
		// misu_depo_krw 占싱쇽옙 占쏙옙占신깍옙

		Long ret = new Long(0);

		Api_Client api = new Api_Client(Constants.API_KEY, Constants.SECRET_KEY);

		HashMap<String, String> rgParams = new HashMap<String, String>();
		rgParams.put("currency", "LTC");
		rgParams.put("payment_currency", "KRW");

		String result = api.callApi("/info/balance", rgParams);
		System.out.println("result===> " + result);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

		JSONObject jsonObj2 = (JSONObject) jsonObj.get("data");

		Long available_krw = (Long) jsonObj2.get("available_krw");

		return available_krw;
	}

	public Float currency_balance(String sCurrency) throws ParseException, IOException {
		// status 占쏙옙占� 占쏙옙占쏙옙 占쌘듸옙 (占쏙옙占쏙옙 : 0000, 占쏙옙占쏙옙占싱울옙 占쌘듸옙占� 占쏙옙占쏙옙 占쌘듸옙 占쏙옙占쏙옙)
		// total_{currency} 占쏙옙체 Currency (btc, eth, dash, ltc, etc, xrp, bch)
		// total_krw 占쏙옙체 KRW
		// in_use_{currency} 占쏙옙占쏙옙占� Currency (btc, eth, dash, ltc, etc, xrp, bch)
		// in_use_krw 占쏙옙占쏙옙占� KRW
		// available_{currency} 占쏙옙占� 占쏙옙占쏙옙 Currency (btc, eth, dash, ltc, etc, xrp,
		// bch)
		// available_krw 占쏙옙占� 占쏙옙占쏙옙 KRW
		// misu_{currency} 占신울옙킹占� Currency (btc, eth, dash, ltc, etc, xrp, bch)
		// misu_krw 占신울옙킹占� KRW
		// xcoin_last bithumb 占쏙옙占쏙옙占쏙옙 占신뤄옙체占쏙옙 占쌥억옙
		// misu_depo_krw 占싱쇽옙 占쏙옙占신깍옙

		Long ret = new Long(0);

		Api_Client api = new Api_Client(Constants.API_KEY, Constants.SECRET_KEY);

		HashMap<String, String> rgParams = new HashMap<String, String>();
		rgParams.put("currency", sCurrency.toLowerCase());
		rgParams.put("payment_currency", "KRW");

		String result = api.callApi("/info/balance", rgParams);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
		
		System.out.println("currency_balance====> " + result);
		

		JSONObject jsonObj2 = (JSONObject) jsonObj.get("data");

		Float available_currency = new Float((String) jsonObj2.get("available_" + sCurrency.toLowerCase()));

		return available_currency;

	}

	public Long buyPrice() throws IOException {
		Long lBuyPrice = new Long(100);
		File fBuyPrice = new File("x_buy_price.log");

		BufferedReader brBuy = new BufferedReader(new FileReader(fBuyPrice));
		String strBuyLine = null, tmpBuy;

		if ((tmpBuy = brBuy.readLine()) != null) {
			strBuyLine = tmpBuy;
			lBuyPrice = new Long(strBuyLine);
			System.out.println("XRP BuyTargetPrice =============> " + lBuyPrice);
		}
		brBuy.close();

		return lBuyPrice;
	}

	public Long sellPrice(Long lBuyPrice) throws IOException {
		File fSellPrice = new File("x_sell_price.log");
		BufferedReader brSell = new BufferedReader(new FileReader(fSellPrice));
		String strSellLine = null, tmpSell;

		Long lSellPrice = new Long(1000);

		if ((tmpSell = brSell.readLine()) != null) {
			strSellLine = tmpSell;
			lSellPrice = new Long(strSellLine);
			//
		}

		brSell.close();

		// lSellPrice = lBuyPrice.longValue() + 5;
		System.out.println("XRP SellTargetPrice =============> " + lSellPrice);
		return lSellPrice;
	}

	public String tranYn() throws IOException {
		File fSellPrice = new File("x_tranYn.log");
		BufferedReader brSell = new BufferedReader(new FileReader(fSellPrice));
		String strSellLine = null, tmpSell;

		String sTranYn = "N";

		if ((tmpSell = brSell.readLine()) != null) {
			strSellLine = tmpSell;
			sTranYn = strSellLine;
			System.out.println("XRP sTranYn =============> " + sTranYn);
		}

		brSell.close();
		return sTranYn;
	}

	public String debugYn() throws IOException {
		File fSellPrice = new File("x_debugYn.log");
		// BufferedReader brSell = new BufferedReader(new
		// FileReader(fSellPrice));
		String strSellLine = null, tmpSell;
		//
		String sDebugYn = "N";

		return sDebugYn;
	}

	public ComEntity get_ticker(String pCurrency) throws ParseException, IOException {
		// "status": "0000",
		// "data": {
		// "opening_price" : "504000",
		// "closing_price" : "505000",
		// "min_price" : "504000",
		// "max_price" : "516000",
		// "average_price" : "509533.3333",
		// "units_traded" : "14.71960286",
		// "volume_1day" : "14.71960286",
		// "volume_7day" : "15.81960286",
		// "buy_price" : "505000",
		// "sell_price" : "504000",
		// "date" : 1417141032622

		Long ret = new Long(0);

		Api_Client api = new Api_Client(Constants.API_KEY, Constants.SECRET_KEY);

		HashMap<String, String> rgParams = new HashMap<String, String>();
		rgParams.put("currency", pCurrency);
		rgParams.put("payment_currency", "KRW");

		String result = api.callApi("/public/ticker/" + pCurrency, rgParams);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

		JSONObject jsonObj2 = (JSONObject) jsonObj.get("data");
		
		System.out.println(jsonObj2);

		ComEntity ent = new ComEntity();
		ent.setCurrency(pCurrency);
		ent.setOpening_price(new Long(Math.round(new Float((String) jsonObj2.get("opening_price")))));
		ent.setClosing_price(new Long(Math.round(new Float((String) jsonObj2.get("closing_price")))));
		ent.setMin_price(new Long(Math.round(new Float((String) jsonObj2.get("min_price")))));
		ent.setMax_price(new Long(Math.round(new Float((String) jsonObj2.get("max_price")))));
		ent.setAverage_price(new Long(Math.round(new Float((String) jsonObj2.get("average_price")))));
		ent.setUnits_traded(new Long(Math.round(new Float((String) jsonObj2.get("units_traded")))));
		ent.setVolume_1day(new Long(Math.round(new Float((String) jsonObj2.get("volume_1day")))));
		ent.setVolume_7day(new Long(Math.round(new Float((String) jsonObj2.get("volume_7day")))));
		ent.setBuy_price(new Long(Math.round(new Float((String) jsonObj2.get("buy_price")))));
		ent.setSell_price(new Long(Math.round(new Float((String) jsonObj2.get("sell_price")))));
		ent.setDate((String) jsonObj2.get("date"));

		return ent;
	}

	public void tickerInsert(String pCurrency) throws ParseException, IOException {
		BitDao bitDao = new BitDao();
		Tran tran = new Tran();
		ComEntity tickerOut = new ComEntity();

		// BTC, ETH, DASH, LTC, ETC, XRP, BCH
			tickerOut = tran.get_ticker(pCurrency);
			bitDao.com_insert(tickerOut);
	}
	
}
