package com.mycom.myapp.dao;

import java.util.List;

import com.mycom.myapp.vo.TranConfigVO;
import com.mycom.myapp.vo.TranLogVO;

public interface TranLogDAO extends GenericDAO<TranLogVO, String> {
	public List<TranLogVO> getTranConfigList();
	public TranLogVO getTranLog(TranLogVO vo);
	public void updateTranLog(TranLogVO vo);
	public void registerTranLog(TranLogVO vo);
}