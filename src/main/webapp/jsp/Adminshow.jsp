<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="app"/>
<!doctype html>
<html lang="en">
<head>
    <style>
        .panel panel-default{border-top-left-radius: 243px;}
    </style>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%-- 引入bootstrap 依赖--%>
    <link rel="stylesheet" href="${app}/boot/css/bootstrap.css">
    <%--  映入 jquery 相关的css--%>
    <link rel="stylesheet" href="${app}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%-- 引入--%>
    <script src="${app}/boot/js/jquery-2.2.1.min.js"></script>
    <script src="${app}/boot/js/bootstrap.js"></script>
    <%--5.jqgrid相关js--%>
    <script src="${app}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${app}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>

    <%--上传--%>
    <script src="${app}/boot/js/ajaxfileupload.js"></script>
    <%--<script>
        $(function () {
           /*  判断对象是否为空*/
            var admin=${admin}
            alert(admin)
            if(admin==null){
                window.location="${app}/jsp/login.jsp";
            }
        })

    </script>--%>
    <script>
        $(function () {
        $("#poiOut").click(function () {
            $.ajax({
                url:"${app}/poi/adminOUTpoi",
                datatype:"json",
                type:"POST",
                success:function (result) {
                    if ("ok"==result){
                        alert("文件导出成功");
                    }else {
                        alert("文件导出失败");
                    }
                }

            })
        })

        })


    </script>

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2 aa">
            <%-- 头部--%>
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header ">
                        <a class="navbar-brand" href="#">持明法洲管理系统</a>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">欢迎：<strong>${admin.username}</strong></a></li>
                            <li><a href="#">
                                退出登录
                                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                                </a></li>
                            <li><a href="javascript:void(0)" ><span id="poiOut" class="glyphicon glyphicon-floppy-save">
                                <strong > 文件导出</strong>
                            </span> </a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <br/> <br/> <br/>
        <%-- 主体--%>
        <div class="col-sm-10 aa">
            <div class="panel-group " id="parent">
                <!--
                    用户管理
                -->
                <%-- 手风琴--%>
                <div class="col-sm-2">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a href="#second" data-toggle="collapse" data-parent="#parent">用户管理</a>
                            </h3>
                        </div>

                        <div class="panel-collapse collapse" id="second">
                            <div class="panel-body">
                                <ul class="list-group">
                                    <li class="list-group-item"><a href="">asd</a></li>
                                    <li class="list-group-item"><a href="javascript:$('#context').load('Address.jsp')">用户地图的位置</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                        <%--
                        上师管理
                        --%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a href="#second1" data-toggle="collapse" data-parent="#parent">上师管理</a>
                            </h3>
                        </div>

                        <div class="panel-collapse collapse" id="second1">
                            <div class="panel-body">
                                <ul class="list-group">
                                    <li class="list-group-item"><a href="">asd</a></li>
                                    <li class="list-group-item"><a href="javascript:$('#context').load('goeasy.jsp')">一个星期注册的数量</a></li>
                                    <li class="list-group-item"><a href="javascript:$('#context').load('Mongoeasy.jsp')">月份折线图</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                        <%--
                        文章管理
                        --%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a href="#second2" data-toggle="collapse" data-parent="#parent">文章管理</a>
                            </h3>
                        </div>

                        <div class="panel-collapse collapse" id="second2">
                            <div class="panel-body">
                                <ul class="list-group">
                                    <li class="list-group-item"><a href="javascript:$('#context').load('Article.jsp')">文章列表</a></li>

                                </ul>
                            </div>
                        </div>
                    </div>
                    <%--
                    专辑管理
                    --%>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a href="#second3" data-toggle="collapse" data-parent="#parent">专辑管理</a>
                            </h3>
                        </div>

                        <div class="panel-collapse collapse" id="second3">
                            <div class="panel-body">
                                <ul class="list-group">
                                    <li class="list-group-item"><a href="javascript:$('#context').load('album.jsp')">专辑展示</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                        <%--
                        轮播图管理
                        --%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a href="#second4" data-toggle="collapse" data-parent="#parent">轮播图管理</a>
                            </h3>
                        </div>

                        <div class="panel-collapse collapse" id="second4">
                            <div class="panel-body">
                                <ul class="list-group">
                                    <li class="list-group-item"><a href="javascript:$('#context').load('Bannerfist.jsp')">轮播图</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
                </div>
                    <%-- 巨幕--%>
                <div class="col-sm-10" id="context">
                    <div class="jumbotron">
                        <h3>欢迎来到持法明州后台管理系统</h3>
                    </div>

                    <%--
                         轮播图
                    --%>
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <img src="${app}/img/shouye.jpg" alt="...">
                                <div class="carousel-caption">
                                    ...
                                </div>
                            </div>
                            <div class="item">
                                <img style="height: 332px;width: 1109px" src="${app}/img/timg.jfif" alt="">
                                <div class="carousel-caption">
                                    ...
                                </div>
                            </div>

                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%-- 底部 导航栏--%>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container -align-center" >
       <%-- <p></p>--%>

           <div class="col-md-6 col-sm-offset-4">
               <div class="copyright">
                   © 持名法度后台管理@北京柏芝有限公司
               </div>
           </div>
    </div>
<%--  模态框--%>
    <div class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">文件导出</h4>
                </div>
                <div class="modal-body">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</nav>
</body>
</html>