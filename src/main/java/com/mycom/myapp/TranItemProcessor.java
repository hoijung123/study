package com.mycom.myapp;

import javax.inject.Inject;

import org.springframework.batch.item.ItemProcessor;

import com.mycom.bitBatch.Main;
import com.mycom.bitBatch.biz.dao.BitDao;
import com.mycom.myapp.dao.TickerDAO;
import com.mycom.myapp.dao.TranConfigDAO;
import com.mycom.myapp.vo.TickerStaticVO;

public class TranItemProcessor implements ItemProcessor<String, String> {

	@Inject
	private TickerDAO tickerDAO;
	@Inject
	private TranConfigDAO tranConfigDAO;
	
	@Override
	public String process(String item) throws Exception {
		TickerStaticVO vo = new TickerStaticVO();
		tickerDAO.registerTickerStaticList(vo);
		System.out.println("Processing..." + item);
		return item;
	}

}