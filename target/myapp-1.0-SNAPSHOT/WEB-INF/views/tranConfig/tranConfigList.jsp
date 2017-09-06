<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<body>

	<table border="1" id="tranConfigList">
		<tr>
			<td>currency</td>
			<td>tran_type</td>
			<td>target_price</td>
			<td>status</td>
			<td>tran_yn</td>
			<td>units</td>
			<c:forEach var="tranConfigList" items="${tranConfigList}">
				<tr>
					<td id="currency">${tranConfigList.currency}</td>
					<td>${tranConfigList.tran_type}</td>
					<td>${tranConfigList.target_price}</td>
					<td>${tranConfigList.status}</td>
					<td>${tranConfigList.tran_yn}</td>
					<td>${tranConfigList.units}</td>
				</tr>
			</c:forEach>
	</table>
	<br>
	<form name="cmmnClCode" method="post" action="tranConfigSave">
		<table>
			<tr>
				<td>currency</td>
				<td><input type="text" id="currency" name="currency" /></td>
			</tr>
			<tr>
				<td>tran_type</td>
				<td><input type="text" id="tran_type" name="tran_type" /></td>
			</tr>
			<tr>
				<td>target_price</td>
				<td><input type="text" id="target_price" name="target_price" />
				</td>
			</tr>
			<tr>
				<td>status</td>
				<td><select name="status" id="status">
						<option value="Y">Y</option>
						<option value="N">N</option>
				</select></td>
			</tr>
			<tr>
				<td>tran_yn</td>
				<td><select name="tran_yn" id="tran_yn">
						<option value="Y">Y</option>
						<option value="N">N</option>
				</select></td>
			</tr>
			<tr>
				<td>units</td>
				<td><input type="text" id="units" name="units" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save"></td>
			</tr>
		</table>
	</form>
	<br>
	<a href="tickerList">tickerList</a>
	<br>
	<a href="tickerStaticList">tickerStaticList</a>
	<br>
	<a href="tranConfigList">tranConfigList</a>
</body>

<script>

	// 테이블의 Row 클릭시 값 가져오기
	$("#tranConfigList tr").click(function() {

		var str = ""
		var tdArr = new Array(); // 배열 선언

		// 현재 클릭된 Row(<tr>)
		var tr = $(this);
		var td = tr.children();

		// tr.text()는 클릭된 Row 즉 tr에 있는 모든 값을 가져온다.
		console.log("클릭한 Row의 모든 데이터 : " + tr.text());

		// 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
		td.each(function(i) {
			tdArr.push(td.eq(i).text());
		});

		console.log("배열에 담긴 값 : " + tdArr);

		// td.eq(index)를 통해 값을 가져올 수도 있다.
		var currency = td.eq(0).text();
		var tran_type = td.eq(1).text();
		var target_price = td.eq(2).text();
		var status = td.eq(3).text();
		var tran_yn = td.eq(4).text();
		var units = td.eq(5).text();

		$('input[name="currency"]').val(currency);
		$('input[name="tran_type"]').val(tran_type);
		$('input[name="target_price"]').val(target_price);
		$("#status").val(status);
		$("#tran_yn").val(tran_yn);
		$('input[name="units"]').val(units);
	});
</script>
