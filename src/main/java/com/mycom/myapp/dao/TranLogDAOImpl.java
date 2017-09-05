package com.mycom.myapp.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycom.myapp.vo.TranConfigVO;
import com.mycom.myapp.vo.TranLogVO;

@Repository
public class TranLogDAOImpl extends GenericDAOImpl<TranLogVO, String> implements TranLogDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.mycom.myapp.dao.DbMapper";
	@Override
	public TranLogVO get(String userid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TranLogVO getTranLog(TranLogVO vo) {
		// TODO Auto-generated method stub
		sqlSession.selectOne(namespace + ".getTranLog", vo);
		return null;
	}
	@Override
	public void updateTranLog(TranLogVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".updateTranLog", vo);
	}
	@Override
	public void registerTranLog(TranLogVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".registerTranLog", vo);
	}
	@Override
	public List<TranLogVO> getTranConfigList() {
		// TODO Auto-generated method stub
		return null;
	}
}
