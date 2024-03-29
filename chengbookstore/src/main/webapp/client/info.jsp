<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>电子书城</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
	<script>
		function checkpnum(pnum,pid) {
			pnum = parseInt(pnum);
			if(pnum<=0){
			    alert("该商品库存不足！！");
			}else{
			    window.location.href="${pageContext.request.contextPath}/client/cart/addCart.do?id="+pid;
			}
        }
	</script>
</head>

<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/client/product/findProductByCategory.do?category=${p.category}">&nbsp;${p.category}</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${p.name}
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td><img src="${pageContext.request.contextPath }/client/ad/page_ad.jpg" width="900" height="84" />
								<table width="100%%" border="0" cellspacing="0">
									<tr>
										<td width="30%">
											<div class="divbookcover">
												<p>
													<img src="${pageContext.request.contextPath}${p.imgurl}"
														width="213" height="269" border="0" />
												</p>
											</div>
											<div style="text-align:center; margin-top:25px">
												<%--<a href="${pageContext.request.contextPath}/addCart?id=${p.id}">--%><%--添加购物车--%>
													<a href="#" onclick="checkpnum('${p.pnum}','${p.id}');">
													<img src="${pageContext.request.contextPath }/client/images/buybutton.gif" border="0" /> 
												</a>
											</div>
										</td>
										<td style="padding:20px 5px 5px 5px">
											<img src="${pageContext.request.contextPath }/client/images/miniicon3.gif" width="20" height="13" />
											<font class="bookname">&nbsp;&nbsp;${p.name}</font>
											<hr />售价：<font color="#FF0000">￥${p.price}</font>
											<hr /> 类别：${p.category }
											<hr />
											<p>
												<b>内容简介：</b>
											</p> ${p.description}
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
