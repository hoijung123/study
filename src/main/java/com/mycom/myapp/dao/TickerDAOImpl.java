package com.mycom.myapp.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycom.myapp.vo.TickerStaticVO;
import com.mycom.myapp.vo.TickerVO;
import com.mycom.myapp.vo.TranConfigVO;

@Repository
public class TickerDAOImpl extends GenericDAOImpl<TickerVO, String> implements TickerDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.mycom.myapp.dao.DbMapper";
	@Override
	public List<TickerVO> getTickerList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".getTickerList");
	}
	
	@Override
	public List<TickerStaticVO> getTickerStaticList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".getTickerStaticList");
	}

	@Override
	public void registerTickerStatic(TickerStaticVO vo) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".registerTickerStatic", vo);
	}

	@Override
	public void registerTickerStaticList(TickerStaticVO vo) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".registerTickerStaticList", vo);
		
	}

	@Override
	public TranConfigVO getTranConfig(String currency) {
		// TODO Auto-generated method stub
		return null;
	}
}
