<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
 
<body>
<table border="1">
<tr>
	<td>currency</td>
	<td>target_price</td>
	<td>tran_yn</td>
	<td>status</td>
	<td>closing_price</td>
	<td>closing_price</td>
	<td>date</td>
<c:forEach var="tranConfigList" items="${tranConfigList}">
<tr>
    <td>${tranConfigList.currency} </td>
    <td>${tranConfigList.target_price}</td>
    <td>${tranConfigList.tran_yn}</td>
    <td>${tranConfigList.status}   </td>
    <td>${tranConfigList.currency} </td>
    <td>${tranConfigList.currency}</td>
    <td>${tranConfigList.date}
</tr>    
</c:forEach>
</table>
<br>
<a href="tickerList">tickerList</a><br>
<a href="tickerStaticList">tickerStaticList</a><br>
<a href="tranConfigList">tranConfigList</a>
</body>