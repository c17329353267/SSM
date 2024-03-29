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
	 var error="${param.error}";
		if(error=="paysucess"){

		alert("租金支付成功，可在“已缴租金”中查看详情！");
		}
	</script>
	
</head>
<body>
<div>
<div class="result-title">
<h1>已缴租金列表</h1>
</div>
<div class="search-wrap">
            <div class="search-content">
                <form action="${pageContext.request.contextPath}/paid/findmypaid.action" method="post" name="myform">
                    <table class="search-tab">
                        <tr>
                           
                            <th width="70">起始日期:</th>
                            <td><input class="common-text" name="fromdate" placeholder="请选择应缴日期" value="${vo.fromdate}" id="fromdate" type="text" readonly></td>
                            <th width="70">终止日期:</th>
                            <td><input class="common-text" placeholder="请选择应缴日期" name="todate" value="${vo.todate}" id="todate" type="text" readonly></td>
                             <input type="hidden" id="page" name="page" value="">
                           
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                           
                        </tr>
                    </table>
                </form>
            </div>
        </div>
	
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
									
									<td>租金</td>
									<td>租金应缴日期</td>
									<td>租金实缴日期</td>
									<td>租客姓名</td>
									
									<td>状态</td>
									<td>操作</td>
								
									
								</tr>
								<c:forEach items="${paid}" var="paid">
									<tr
										style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
										<td>${paid.house_id }</td>

										<td>${paid.address}</td>
										
										<td>${paid.price}</td>
										<td>${paid.date}</td>
										<td>${paid.paydate}</td>
										<td>${paid.name}</td>
										<td>${paid.status}</td>
										<td>
										
													<a class="link-update"
											href="${pageContext.request.contextPath}/paid/zukedeletepaid.action?id=${paid.id}"
											onclick="return window.confirm('确定删除吗？')">删除</a>
											&nbsp;&nbsp; 
												
										
											
									</td>		
										
										
									</tr>

								</c:forEach>

							</tbody>
						</table>
						</div>
					

						<tr>
						<tr>
						<div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right; margin-top:10px">
						以上共缴租金：<B style="color:red">${sum} </B>元
						</div>
								<%--<div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right; margin-top:10px">--%>
									<div>
								
									<%--共[<B>${p.total}</B>]条记录，共[<B>${p.pages}</B>]页
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
													 submitUrl="${pageContext.request.contextPath}/paid/findmypaid.action?pageIndex={0}"></cheng:pager>

									
								</div>
							</span>
						
						</tr>
						</tbody>
					
					

					
						</tbody>
				

	
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