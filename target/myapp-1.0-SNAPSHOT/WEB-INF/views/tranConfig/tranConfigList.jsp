<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
 
<body>
<table border="1">
<tr>
	<td>currency</td>
	<td>tran_type</td>
	<td>target_price</td>
	<td>status</td>
	<td>tran_yn</td>
	<td>units</td>
<c:forEach var="tranConfigList" items="${tranConfigList}">
<tr>
    <td>${tranConfigList.currency} </td>
    <td>${tranConfigList.tran_type}</td>
    <td>${tranConfigList.target_price}</td>
    <td>${tranConfigList.status}   </td>
    <td>${tranConfigList.tran_yn} </td>
    <td>${tranConfigList.units}</td>
</tr>    
</c:forEach>
</table>
<br>
<a href="tickerList">tickerList</a><br>
<a href="tickerStaticList">tickerStaticList</a><br>
<a href="tranConfigList">tranConfigList</a>
</body>