package com.mycom.myapp.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;

public abstract class GenericDAOImpl<E, K> implements GenericDAO<E, K> {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.mycom.myapp.dao.DbMapper";
	
	@Override
	public K getTime() {
		return sqlSession.selectOne(namespace + ".getNow");
	}

	@Override
	public void register(E vo) {
		sqlSession.insert(namespace + ".register", vo);
	}

	@Override
	public E get(K userid) {
		return sqlSession.selectOne(namespace + ".get", userid);
	}

	@Override
	public List<E> getList() {
		return sqlSession.selectList(namespace + ".getList");
	}

}// class
