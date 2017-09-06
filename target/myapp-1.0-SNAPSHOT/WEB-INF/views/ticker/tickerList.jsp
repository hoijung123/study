<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
 
<body>
<table border="1">
<tr>
	<td>currency</td>
	<td>closing_price</td>
	<td>closing_price</td>
	<td>closing_price</td>
	<td>closing_price</td>
	<td>closing_price</td>
	<td>date</td>
<c:forEach var="tickerList" items="${tickerList}">
<tr>
    <td>${tickerList.currency} </td>
    <td>${tickerList.closing_price}</td>
    <td>${tickerList.closing_price}</td>
    <td>${tickerList.closing_price}   </td>
    <td>${tickerList.closing_price} </td>
    <td>${tickerList.closing_price}</td>
    <td>${tickerList.date}
</tr>    
</c:forEach>
</table>
<br>
<a href="tickerList">tickerList</a><br>
<a href="tickerStaticList">tickerStaticList</a><br>
<a href="tranConfigList">tranConfigList</a>
</body>

<script langauge="javascript">
var counter = 0;
window.setInterval("refreshDiv()", 5000);
function refreshDiv(){
	location.reload();
}
</script>