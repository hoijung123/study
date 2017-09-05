package com.mycom.myapp.dao;

import java.util.List;

import com.mycom.myapp.vo.TickerStaticVO;
import com.mycom.myapp.vo.TickerVO;
import com.mycom.myapp.vo.TranConfigVO;

public interface TickerDAO extends GenericDAO<TickerVO, String> {
	public List<TickerVO> getTickerList();
	public List<TickerStaticVO> getTickerStaticList();
	public void registerTickerStatic(TickerStaticVO vo);
	public void registerTickerStaticList(TickerStaticVO vo);
	public TranConfigVO getTranConfig(String currency);
}