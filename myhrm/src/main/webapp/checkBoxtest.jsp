<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript">
        function chgAll(t){//第一个复选框选中或取消选中，则下面的复选框为全选或取消全选；
        $("input[name='id']").attr('checked',t.checked);//改变name名为id的input标签内的复选框的checked属性
    }
    function chg(){//当下面的复选框全部选中时，则将第一个复选框设置为选中，当下面的复选框中有一个没有被选中时，则第一个复选框取消选中；
        var ids = $.makeArray($("input[name='id']"));
        for(var i in ids){
            if(ids[i].checked==false){//如果所有的复选框只要有一个未选中，则第一个复选框不会选中
                $("input[name='ids']").attr('checked', false);
                return;
            }
        }
        $("input[name='ids']").attr('checked', true);//全部选中的情况下，则第一个复选框选中
    }
    function deleteBatch(){//将下面的复选框的id值传递给Controller层，组成id数组，拼接url到controller层，调用批量删除方法（deleteBatch())方法
        var ids = $.makeArray($("input[name='id']:checked"));//通过$.makeArray将id放在数组中
        var url = '${pageContext.request.contextPath}/deleteBatch.do';//此url指向controller层的deleteBatch方法，需要id属性
        var flag = true;
        for(var i in ids){//遍历数组
            if(i == 0){
                url += "?id=" + ids[i].value;//第一个id属性前加？拼接
                flag = false;
            } else {
                url += "&id=" + ids[i].value;//后面的id属性前加&拼接
                flag = false;
            }
        }
        if(flag){//如果没有选中商品
            alert("请选中商品！");
            return;
        }
        if(confirm("确定删除记录吗？")){
            window.location.href = url;//把拼接好的id数组传给页面
        }
    }
    </script>
</head>
<body>
<form action="#" method="post">
    <table id="cTable">

        <tr>
            <td><input type="checkbox" name="id"  onchange="chgAll(this)" /></td>
            <td>商品编号</td>
            <td>商品标题</td>
        </tr>

        <tr>
            <td><input type="checkbox"  name="id" value="1" onchange="chg()"/></td>
            <td>001</td>
            <td>生活用品</td>
        </tr>
        <tr>
            <td><input type="checkbox"  name="id" value="2" onchange="chg()"/></td>
            <td>002</td>
            <td>棉袄</td>
        </tr>
        <tr>
            <td><input  type="button" onclick="deleteBatch()" value="批量删除" /></td>
        </tr>
    </table>
</form>
</body>
</html>