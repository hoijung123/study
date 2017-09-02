package com.mycom.bitBatch.biz.dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class BitDao implements UserDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public void Connect() {
		
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/bitsum?serverTimezone=UTC";
		
		try {
			
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "testuser", "1111");
			
		} catch(Exception e) {
			
			System.out.println(e);
		}
	}
	
	public void Disconnect() {

		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
			
		if(conn != null) {
			
			try {
				conn.close();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public boolean com_insert(ComEntity ce) {
		try {
			
			Connect();
			String sql = "INSERT INTO t_ticker(currency,date,status,opening_price,closing_price,min_price,max_price,average_price,units_traded,volume_1day,volume_7day,buy_price,sell_price)"
                       + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			
			int i = 0;
			pstmt.setString(++i, ce.getCurrency());
			pstmt.setString(++i, ce.getDate());
			pstmt.setString(++i, ce.getStatus());
			pstmt.setLong(++i, ce.getOpening_price());
			pstmt.setLong(++i, ce.getClosing_price());
			pstmt.setLong(++i, ce.getMin_price());
			pstmt.setLong(++i, ce.getMax_price());
			pstmt.setLong(++i, ce.getAverage_price());
			pstmt.setLong(++i, ce.getUnits_traded());
			pstmt.setLong(++i, ce.getVolume_1day());
			pstmt.setLong(++i, ce.getVolume_7day());
			pstmt.setLong(++i, ce.getBuy_price());
			pstmt.setLong(++i, ce.getSell_price());

			pstmt.executeUpdate();
			
			return true;
			
		} catch(Exception e) {
			System.out.println(e);
			return false;
		} finally {
			Disconnect();
		}
	}
	
}
