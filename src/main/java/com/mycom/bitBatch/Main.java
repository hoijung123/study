package com.mycom.bitBatch;

import java.io.IOException;

import javax.inject.Inject;

import com.mycom.myapp.dao.TickerDAO;
import com.mycom.myapp.vo.TickerStaticVO;


public class Main {
	@Inject
	private TickerDAO tickerDAO;

	public void main() throws InterruptedException, IOException {

		Tran tran = new Tran();
		int i = 0;
		int pos = 0;

		// BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR
		String arrayCurrency[] = { Constants.CURRENCY_BTC, Constants.CURRENCY_ETH, Constants.CURRENCY_DASH,
				Constants.CURRENCY_LTC, Constants.CURRENCY_ETC, Constants.CURRENCY_XRP, Constants.CURRENCY_BCH,
				Constants.CURRENCY_XMR };
		while (1 > i) {
			Thread.sleep(5000);
			try {
				System.out.println(arrayCurrency[pos]);
				tran.tickerInsert(arrayCurrency[pos]);

			} catch (Exception e) {
				e.printStackTrace();
			}
			pos++;
			if (pos >= arrayCurrency.length) {
				pos = 0;
			}
		}

	}
	
	public void tranJob() {
		TickerStaticVO vo = new TickerStaticVO();
		tickerDAO.registerTickerStaticList(vo);
		System.out.println("arrayCurrency[pos]");
	}

}
