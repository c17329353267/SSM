<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="cheng" uri="http://cc.pager-tags" %>
<%--<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>房屋租赁系统</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pager.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-datepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css"/>

	<style type="text/css">
	.sum{
	float:right;
	}
	
	</style>
	<script type="text/javascript">
	 $().ready(function() {
	        // 在键盘按下并释放及提交后验证提交表单
	        $("#fromdate").datepicker();
	        $("#todate").datepicker();
	 });
		
	</script>
	
</head>
<body>
<div>
<div class="result-title">
<h1>待缴租金列表</h1>
</div>
                <form action="${pageContext.request.contextPath}/paid/mytopaidlist.action" method="post" name="myform">
						 <div class="result-title">
                    <div class="result-list">
                      
                        
                    </div>
                </div>

					<div class="result-content">
						<table id=grid
							class="result-tab" width="100%">
							<tbody>
								<tr
									style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
									<td>房屋id</td>
									<td>地址</td>
									
									<td>应缴租金</td>
									<td>租金应缴日期</td>
									
									<td>租客姓名</td>
									
									<td>状态</td>
									<td>操作</td>
								
									
								</tr>
								<c:forEach items="${topaid}" var="topaid">
									<tr
										style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
										<td>${topaid.house_id }</td>

										<td>${topaid.address}</td>
										
										<td>${topaid.price}</td>
										<td>${topaid.date}</td>
										
										<td>${topaid.name}</td>
										<td>${topaid.status}</td>
										
										<td><a class="link-update"
											<%--href="${pageContext.request.contextPath}/paid/gotopay.action?id=${topaid.id }"--%>
											   href="${pageContext.request.contextPath}/paid/pay.action?id=${topaid.id}&price=${topaid.price}"
											onclick="return window.confirm('确定要支付吗?')">支付租金</a>
											&nbsp;&nbsp; </td>
										
									</tr>

								</c:forEach>

							</tbody>
						</table>
						</div>
				

						<tr>
						<tr>
						
								<%--<div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right; margin-top:10px">--%>
									<div>
								<%--
									共[<B>${p.total}</B>]条记录，共[<B>${p.pages}</B>]页
									,

									<c:if test="${ p.pageNum > 1 }">
													[<A href="javascript:to_page(${p.prePage})">前一页</A>]
												</c:if>
										<input type="hidden" name="page" id="page" value=""/>
									第<B>${p.pageNum}</B>页

									<c:if test="${ p.pageNum < p.pages }">
													[<A href="javascript:to_page(${p.nextPage})">后一页</A>] 
												</c:if>--%>
									<cheng:pager pageIndex="${pageModel.pageIndex}"
												 pageSize="${pageModel.pageSize}"
												 recordCount="${pageModel.recordCount}"
												 submitUrl="${pageContext.request.contextPath}/paid/mytopaidlist.action?pageIndex={0}"></cheng:pager>
									
								</div>
							</span>
						
						</tr>
						</tbody>
					
					

					
						</tbody>
				
	</form>
	
</div>
 <script language=javascript>
	// 提交分页的查询的表单
	function to_page(page) {
		if (page) {
			$("#page").val(page);
		}
		document.myform.submit();
	}
</script>
</body>
</html>