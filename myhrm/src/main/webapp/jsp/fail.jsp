<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <script language="JavaScript" type="text/javascript">
        $(function(){
            $("#returnButton").click(){

            }
        })
    </script>
</head>
<body>

<h3 align="center" style="margin-top: auto">${fail}</h3>
<%--<input align="center" type="button" value="返回" id="returnButton" onclick="JavaScript:history.back(-1);">--%>
<input align="center" type="button" value="返回" id="returnButton" onclick="javascript:history.back(-1)">


</body>
</html>
