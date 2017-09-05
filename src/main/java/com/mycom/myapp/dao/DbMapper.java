package com.mycom.myapp.dao;

import java.util.List;

import com.mycom.myapp.vo.TickerStaticVO;
import com.mycom.myapp.vo.TickerVO;

public interface DbMapper {
    /* DB Select  */
    public String getDual() throws Exception;
    
    public List<TickerVO> getTickerList() throws Exception;
    
    public List<TickerStaticVO> getTickerStaticList() throws Exception;
}

