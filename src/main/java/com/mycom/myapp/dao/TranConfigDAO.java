package com.mycom.myapp.dao;

import java.util.List;

import com.mycom.myapp.vo.TranConfigVO;

public interface TranConfigDAO extends GenericDAO<TranConfigVO, String> {
	public List<TranConfigVO> getTranConfigList();
	public TranConfigVO getTranConfig(TranConfigVO vo);
	public void updateTranConfig(TranConfigVO vo);
}