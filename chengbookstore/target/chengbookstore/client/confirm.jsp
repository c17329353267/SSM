<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="ccheng" uri="http://cheng.login.tage" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- <p:user /> -->
	<ccheng:login/>
	<!-- 确认支付form -->
	<%--<form action="https://www.yeepay.com/app-merchant-proxy/node" method="post">--%>
	<form action="${pageContext.request.contextPath}/client/order/pay.do" method="post">
		<h3>订单号：${order.id},付款金额 ：${order.money}</h3>
		<input type="hidden" name="id" value="${order.id }" /> <input
			type="hidden" name="money" value="${order.money }" /> <input
			type="hidden" name="name" value="${order.id }" /> <input
			type="hidden" name="p2_Order" value="${p2_Order }" /> <input
			type="hidden" name="p3_Amt" value="${p3_Amt }" /> <input
			type="hidden" name="p4_Cur" value="${p4_Cur }" /> <input
			type="hidden" name="p5_Pid" value="${p5_Pid }" /> 
			<input type="hidden" name="p6_Pcat" value="${p6_Pcat }" /> 
			<input type="hidden" name="p7_Pdesc" value="${p7_Pdesc }" /> 
			<input type="hidden" name="p8_Url" value="${p8_Url }" /> 
			<input type="hidden" name="p9_SAF" value="${p9_SAF }" /> 
			<input type="hidden" name="pa_MP" value="${pa_MP }" /> 
			<input type="hidden" name="pr_NeedResponse" value="${pr_NeedResponse }" /> 
			<input type="hidden" name="hmac" value="${hmac }" /> 
			<input type="submit" value="确认支付" />
	</form>
</body>
</html>