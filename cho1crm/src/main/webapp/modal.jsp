<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

    <script type="text/javascript">

            //  ajax请求
            $.ajax({
                url: "",
                data:{

                },
                dataType: "json",
                type: "get",
                success: function (data) {
                    //在这里处理返回的数据,
                    if (data.success) {

                    } else {

                    }
                }
            })

            //处置表格中所有值
            $("#id值")[0].reset();

            //如果当前窗口不是顶级窗口,设置为顶级窗口
            if(window.top!=window){
                window.top.location=window.location;
            }

    </script>
</head>
<body>


</body>
</html>