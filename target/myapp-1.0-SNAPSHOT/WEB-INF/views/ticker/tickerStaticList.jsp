<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
 
<body>
<table border="1">
<tr>
	<td>currency</td>
	<td>max_closing_price</td>
	<td>min_closing_price</td>
	<td>avg_closing_price</td>
	<td>std_closing_price</td>
	<td>std_avg_closing_price</td>
	<td>date</td>
<c:forEach var="tickerStaticList" items="${tickerStaticList}">
<tr>
    <td>${tickerStaticList.currency} </td>
    <td>${tickerStaticList.max_closing_price}</td>
    <td>${tickerStaticList.min_closing_price}</td>
    <td>${tickerStaticList.avg_closing_price}   </td>
    <td>${tickerStaticList.std_closing_price} </td>
    <td>${tickerStaticList.std_avg_closing_price}</td>
    <td>${tickerStaticList.date}
</tr>    
</c:forEach>
</table>
<br>
<a href="tickerList">tickerList</a><br>
<a href="tickerStaticList">tickerStaticList</a><br>
<a href="tranConfigList">tranConfigList</a>
</body>