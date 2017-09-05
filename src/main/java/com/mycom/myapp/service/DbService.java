package com.mycom.myapp.service;
 
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.dao.DbMapper;
import com.mycom.myapp.vo.TickerStaticVO;
import com.mycom.myapp.vo.TickerVO;

 
@Service
public class DbService {
 
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.mycom.myapp.dao.DbMapper";
    /* select dual */
    public List<TickerVO> getTickerList() throws Exception{
        return sqlSession.selectList(namespace + ".getTicker");
    }    
    
    public List<TickerStaticVO> getTickerStaticList() throws Exception{
        return sqlSession.selectList(namespace + ".getTickerList");
    }     
 
}

