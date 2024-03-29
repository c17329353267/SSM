<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="ccheng" uri="http://cheng.login.tage" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />

<script>
	/*数量(可增加可减少)，库存，id*/
	function changeProductNum(count, totalCount, id) {
		count = parseInt(count);
		totalCount = parseInt(totalCount);
		//如果数量为0，判断是否要删除商品
		if (count == 0) {
			var flag = window.confirm("确认删除商品吗?");

			if (!flag) {
				count = 1;
			}
		}

		if (count > totalCount) {
			alert("已达到商品最大购买量");
			count = totalCount;
		}

		location.href = "${pageContext.request.contextPath}/client/cart/changeCart.do?id="
				+ id + "&count=" + count;
	}
</script>
</head>

<body class="main">
	<%-- <p:user/> --%>
	<ccheng:login/><%--对用户做是否登陆检查--%>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<img src="${pageContext.request.contextPath}/client/ad/page_ad.jpg" width="900" height="89" />
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td>
											<img src="${pageContext.request.contextPath}/client/images/buy1.gif" width="635" height="38" />
										</td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">序号</td>
													<td width="30%">商品名称</td>
													<td width="10%">价格</td>
													<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</td>
													<td width="10%">库存</td>
													<td width="10%">小计</td>
													<td width="10%">取消</td>
												</tr>
											</table>
											<c:set var="total" value="0" /> 
											<c:forEach items="${cart}" var="entry" varStatus="vs"><%--varStatus是做序号--%>
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td width="10%">${vs.count}</td>
														<td width="30%">${entry.key.name }</td>
														<td width="10%">${entry.key.price }</td>
														<td width="20%">
															<input type="button" value='-' style="width:20px"
															onclick="changeProductNum('${entry.value-1}','${entry.key.pnum}','${entry.key.id}')">

															<input name="text" type="text" value="${entry.value}" style="width:40px;text-align:center" />
															<input type="button" value='+' style="width:20px"
															onclick="changeProductNum('${entry.value+1}','${entry.key.pnum}','${entry.key.id}')">

														</td>
														<td width="10%">${entry.key.pnum}</td>
														<td width="10%">${entry.key.price*entry.value}</td><%--小计计算=数量*价格--%>
														<td width="10%">
															<a href="${pageContext.request.contextPath}/client/cart/changeCart.do?id=${entry.key.id}&count=0"
															style="color:#FF0000; font-weight:bold">X</a>
														</td>
													</tr>
												</table>
												<c:set value="${total+entry.key.price*entry.value}" var="total" />
											</c:forEach>

											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align:right; padding-right:40px;">
														<font style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;${total}元</font>
													</td>
												</tr>
											</table>
											<div style="text-align:right; margin-top:10px">
												<a href="${pageContext.request.contextPath}/client/product/findProductByCategory.do">
													<img src="${pageContext.request.contextPath}/client/images/gwc_jx.gif" border="0" />
												</a>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="${pageContext.request.contextPath}/client/order.jsp">
													<img src="${pageContext.request.contextPath}/client/images/gwc_buy.gif" border="0" />
												</a>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>
