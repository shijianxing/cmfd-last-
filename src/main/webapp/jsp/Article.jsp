<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor/themes/default/default.css" />
    <script>
        $(function () {
            $("#articleList").jqGrid({
                url:"${pageContext.request.contextPath}/article/pagerAll",
                editurl:"${pageContext.request.contextPath}/article/edit",
                datatype:"json",
                styleUI:"Bootstrap",
                colNames:["id","title","author","content","create_date","status","操作"],
                pager:"articlepage",
                rowNum:2,
                mtype: 'POST',
                rowsList:[2,4,8],
                viewrecords:true,
                autowidth:true,
                caption:"文章表管理",
                multiselect:true,
                height:'400px',
                colModel:[
                    {name:"id"},
                    {name:"title",editable:true},
                    {name:"author",editable:true},
                    {name:"content", hidden:true},
                    {name:"create_date",editable:true,edittype:'date'},
                    {name:"status",editable:true,edittype:"select",editoptions:{
                            value:"展示:展示;不展示:不展示"
                        }},
                    {
                        name: "aa",formatter: function () {
                            return  "<button type=\"button\" class=\"btn btn-success\" id=\"myshow\" onclick=\"show()\" ><span class=\"glyphicon glyphicon-list-alt\"></span></button>";
                        }
                    }
                ]
            }).jqGrid("navGrid","#articlepage",{search:false,addtext:"添加",edittext:"修改",deltext:"删除"}

            ,{
                //修改
                    closeAfterEdit:true,
                },
                {
                    closeAfterAdd:true,     //关闭添加的模态框
                },
                {

                }



            )


            /* 模态框*/
            $("#mymodal").click(function () {
                $('#modal').modal('toggle');
            })

            var k=KindEditor.create('#editor_id',{
                width: "100%",
                height: "400px",
                allowFileManager:true,    //是否展示 图片空间
                filePostName:"img",       //上传是后台接收的名字
                uploadJson:"${pageContext.request.contextPath}/kindededitro/uploadFileImg",     //上传后台的路径
                fileManagerJson:"${pageContext.request.contextPath}/kindededitro/getAllFileImgs",
                afterCreate : function(){ //kindeditor创建后，将编辑器的内容设置到原来的textarea控件里
                    this.sync();
                },
                afterChange: function(){ //编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里
                    this.sync();
                },
                afterBlur : function(){ //编辑器聚焦后，将编辑器的内容设置到原来的textarea控件里
                    this.sync();
                } 

            });

            //离开模态框清空 富文本的值
            $("#close").click(function () {
                k.html(null);
            })

            /*  ajax*/
            $("#submit").click(function () {
                //发送请求
                $.ajax({
                    url:"${pageContext.request.contextPath}/article/add",
                    data:$("#formlist").serialize(),
                    type:"POST",
                    datatype:"json",
                    success:function (result) {
                        //返回
                    }
                    
                })
            })
            //文章内容
            var ky=KindEditor.create('#editor',{
                width: "500px",
                height: "312px",
                allowFileManager:true,    //是否展示 图片空间
                filePostName:"img",       //上传是后台接收的名字
                uploadJson:"${pageContext.request.contextPath}/kindededitro/uploadFileImg",     //上传后台的路径
                fileManagerJson:"${pageContext.request.contextPath}/kindededitro/getAllFileImgs",
                afterCreate : function(){ //kindeditor创建后，将编辑器的内容设置到原来的textarea控件里
                    this.sync();
                },
                afterChange: function(){ //编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里
                    this.sync();
                },
                afterBlur : function(){ //编辑器聚焦后，将编辑器的内容设置到原来的textarea控件里
                    this.sync();
                }

            });
            //文章修改
            var kz=KindEditor.create('#edi',{
                width: "500px",
                height: "312px",
                allowFileManager:true,    //是否展示 图片空间
                filePostName:"img",       //上传是后台接收的名字
                uploadJson:"${pageContext.request.contextPath}/kindededitro/uploadFileImg",     //上传后台的路径
                fileManagerJson:"${pageContext.request.contextPath}/kindededitro/getAllFileImgs",
                afterCreate : function(){ //kindeditor创建后，将编辑器的内容设置到原来的textarea控件里
                    this.sync();
                },
                afterChange: function(){ //编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里
                    this.sync();
                },
                afterBlur : function(){ //编辑器聚焦后，将编辑器的内容设置到原来的textarea控件里
                    this.sync();
                }

            });
            /* 回显 数据库KindEditor的内容*/
         /*   $("#kindmodal").click(function () {
                //获得选中行的数据
                var gr = $("#articleList").jqGrid('getGridParam', 'selrow');
                if(gr == null){
                    alert("请选着您要看的行");
                }else {
                    //1.请求后台
                    //2.jqgrid 提供的方法 根据id拿到对应的值
                    var data = $("#articleList").jqGrid('getRowData', gr);
                    var con=data.content;
                    /!*$(document.getElementById('editor')[0].contentWindow.document.body).html(con);*!/
                    ky.html(null);
                    ky.html(con);
                    //展示模态框
                    $('#kindmodal').modal('toggle')
                }
            });*/
            //修改
            $("#update").click(function () {
                //获得选中行的数据
                var gr = $("#articleList").jqGrid('getGridParam', 'selrow');
                if(gr == null){
                    alert("请选着您要修改的行");
                }else {
                    //文章form修改 清空
                 /*   $("#formupdate").empty();*/
                    //2.jqgrid 提供的方法 根据id拿到对应的值
                    var data = $("#articleList").jqGrid('getRowData', gr);
                    var con=data.content;
                     var title =data.title;
                    //回显数据
                   $("#ids").val(data.id);
                    $("#title1").attr("value",title);
                    $("#author1").val(data.author);
                    $("#create_date1").val(data.create_date);
                    kz.html(null);
                    kz.html(con);
                    //展示模态框
                    $('#kindmodal1').modal('toggle');

                }
            })
            //修改发送ajax submit1
            $("#submit1").click(function () {
                //发送请求
                $.ajax({
                    url:"${pageContext.request.contextPath}/article/updatesubmit",
                    data:$("#formupdate").serialize(),
                    type:"POST",
                    datatype:"json",
                    success:function (result) {
                        //返回
                        $("#articleList").trigger("reloadGrid");
                    }

                })
            })

            /* 关闭 添加模态框*/
            $("#submit").click(function () {
                $("#modal").modal('toggle');
            })

        })

        /* 回显 数据库KindEditor的内容*/
       function  show() {
            //获得选中行的数据
            var gr = $("#articleList").jqGrid('getGridParam', 'selrow');
            if(gr == null){
                alert("请选着您要看的行");
            }else {
                //2.jqgrid 提供的方法 根据id拿到对应的值
                var data = $("#articleList").jqGrid('getRowData', gr);
                var con=data.content;

                /*$('#kindmodal').empty();*/
                //展示内容
                $('#modolshow').html(con);
                //展示模态框
                $('#kindmodal').modal('toggle')
            }
        };
    </script>
</head>
<body>
<%-- 页头--%>
<div class="page-header">
    <h3>文章展示</h3>
</div>
<%-- 面板--%>
<div class="panel panel-default">
    <%-- 标签页--%>
    <div class="panel-heading">
        <div>
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">文章列表</a></li>
            <li role="presentation" class="active"><a href="#" id="mymodal" aria-controls="home" role="tab" data-toggle="tab">添加文章</a></li>

            <li role="presentation" class="active"><a href="#" id="kind" aria-controls="home" role="tab" data-toggle="tab">文章内容</a></li>
            <li role="presentation" class="active"><a href="#" id="update" aria-controls="home" role="tab" data-toggle="tab">文章修改</a></li>
        </ul>
    </div>

    </div>
        <%-- 表格--%>
        <div class="panel-body">
            <table style="height: 60px" id="articleList"></table>
            <div id="articlepage" style="height: 100px"></div>
        </div>
</div>

<%-- 模态框--%>
<div class="modal fade" id="modal" >
<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">文章添加</h4>
        </div>
        <div class="modal-body">
        <%-- 表单 --%>

            <form action="${pageContext.request.contextPath}/article/add" id="formlist" method="post">
                <div class="form-group">
                    <label for="title">标题 </label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="请输入标题">

                </div>
                <div class="form-group">
                    <label for="author">作者</label>
                    <input type="text" class="form-control" id="author" name="author" placeholder="作者">
                </div>
                <div class="form-group">
                    <label for="create_date">创建日期</label>
                    <input type="date" class="form-control" id="create_date" name="create_date" >
                </div>
                <div class="form-group">
                 <textarea id="editor_id" name="content" style="width:572px;height:300px;">
                 &lt;strong&gt;HTML内容&lt;/strong&gt;
                </textarea>
                </div>
                <select  class="form-control" name="status">
                    <option  value="展示">展示</option>
                    <option  value="不展示">不展示</option>
                </select>
                <button type="button" id="submit" class="btn btn-primary">Submit</button>
                <button type="reset" class="btn btn-default">重置</button>
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" id="close"  data-dismiss="modal">Close</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%-- 文章内容  --%>
<div class="modal fade "   id="kindmodal" >
    <div class="modal-dialog " role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">文章内容</h4>
            </div>
            <div class="modal-body" id="modolshow" >

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="myclose">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

//修改模态框
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="kindmodal1" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改</h4>
            </div>
            <div class="modal-body">

                   <form action="${pageContext.request.contextPath}/article" id="formupdate" method="post">
                       <div class="form-group" style="display:none;">
                           <label for="ids">id</label>
                           <input type="text" class="form-control" id="ids" name="id" value="">

                       </div>

                       <div class="form-group">
                           <label for="title">标题 </label>
                           <input type="text" class="form-control" id="title1" name="title" placeholder="请输入标题" value="">

                       </div>
                       <div class="form-group">
                           <label for="author">作者</label>
                           <input type="text" class="form-control" id="author1" name="author" placeholder="作者" value="">
                       </div>
                       <div class="form-group">
                           <label for="create_date">创建日期</label>
                           <input type="date" class="form-control" id="create_date1" name="create_date" value="" >
                       </div>
                       <div class="form-group">
                   <textarea id="edi" name="content" style="width:800px;height:700px;">
                   </textarea>
                       </div>
                       <select  class="form-control" name="status">
                           <option  value="展示">展示</option>
                           <option  value="不展示">不展示</option>
                       </select>
                       <button type="button" id="submit1" class="btn btn-primary" data-dismiss="modal">Submit</button>
                       <button type="reset" class="btn btn-default">重置</button>
                   </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="myclose1">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>