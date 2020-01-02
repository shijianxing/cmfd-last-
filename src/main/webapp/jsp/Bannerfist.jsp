<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script>
    $(function () {
        $("#tableList").jqGrid({
            url:"${pageContext.request.contextPath}/banner/pager",
            editurl:"${pageContext.request.contextPath}/banner/edit",
            datatype:"json",
            styleUI:"Bootstrap",
            colNames:["id","title","create_date","status","img"],
            pager:"bannerpage",
            rowNum:2,
            toolbar:[true,"top"],
            mtype: 'POST',
            rowsList:[2,4,8],
            viewrecords:true,
            autowidth:true,
            caption:"轮播图管理",
            multiselect:true,
            height:'400px',
            colModel:[
                {name:"id"},
                {name:"title",editable:true},
                {name:"create_date",editable:true,edittype:'date'},
                {name:"status",editable:true,edittype:"select",editoptions:{
                        value:"展示:展示;不展示:不展示"
                    }},
                {name:"img",editable:true,edittype:'file',
                    formatter:function (cellvalue, options, rowObject) {
                    return "<img style='width:100%;height:100px' src='${pageContext.request.contextPath}/img/"+cellvalue+"'/>";
                    }
                }
            ]
        }).jqGrid("navGrid","#bannerpage",{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},
            {
                /*
                * 修改
                * */
                closeAfterEdit:true,
                afterSubmit:function (response) {

                    var id = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/bannerUpload",
                        fileElementId:'img',
                        data:{bannerId:id},
                        type:"post",
                        success:function () {
                            $("#tableList").trigger("reloadGrid");// 刷新gqgrid的表格
                            $("#updateDiv").show();
                            setTimeout(function () {
                                $("#updateDiv").hide();
                            },3000)
                            /*window.location.reload(); 刷新页面*/
                        }
                    })
                    return response;
                }

            },
        {
            /*
            * 添加
            * */
            closeAfterAdd:true,     //关闭添加的模态框
            afterSubmit:function (response) {

                var id = response.responseJSON.bannerId;
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/banner/bannerUpload",
                    fileElementId:'img',
                    data:{bannerId:id},
                    type:"post",
                    success:function () {
                        $("#tableList").trigger("reloadGrid");
                        $("#msgDiv").show();
                        setTimeout(function () {
                            $("#msgDiv").hide();
                        },3000)
                    }
                })
                return response;
            }


        },
            {

                /*
                * 删除
                * */

            }
        );
        //添加按钮
       $("#t_tableList").html("<button class=\"btn btn-success\" type=\"button\" onclick=\"Easypoi()\"><span class=\"glyphicon glyphicon-floppy-save\">导出</span></button>"+
           "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<button class=\"btn btn-success\" type=\"button\" onclick=\"EasyInpoi()\"><span class=\"glyphicon glyphicon-floppy-save\">导入</span></button>"
       )

    })
        function Easypoi() {
        var ok="ok";
            //导出
            $.ajax({
                url:"${pageContext.request.contextPath}/poi/easyOutpoi",
                type:"POST",
                datatype:"json",
                success:function (data) {
                    if (data==(ok)){
                        alert("导入成功");
                    }else {
                        alert("导入失败");
                    }
                }
            })
        }

        function EasyInpoi() {
           /* 模态展示*/
            $("#mymodal").modal("toggle");
        }

        function Inpoi() {

            //导入
            $.ajax({
                url:"${pageContext.request.contextPath}/poi/easyInpoi",
                data:$("#myfrom").serialize(),
                type:"POST",
                datatype:"json",
                success:function (data) {
                    if (data=="ok"){
                        //导入成功
                            alert(data);
                    } else {
                        //导入失败

                    }
                }
            })
        }
    </script>
</head>
<body>
    <%-- 导航页--%>
    <div>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">轮播图展示</a></li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="home">
                <table id="tableList" ></table>
            </div>
        </div>
    </div>

    <div style="height: 50px" id="bannerpage"></div>
    <div class="alert alert-success" style="display:none" id="msgDiv">
        添加成功
    </div>
    <div class="alert alert-success" style="display:none" id="updateDiv">
        修改成功
    </div>

    <%--
     导入模态框
    --%>
    <div class="modal fade"  role="dialog" id="mymodal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">excle导入</h4>
                </div>

                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/poi/easyInpoi" method="post" id="myfrom" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="exampleInputFile">File input</label>
                        <input type="file" name="myexcle" id="exampleInputFile">
                    </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="Inpoi()" data-dismiss="modal"><span class="glyphicon glyphicon-log-in">Submit</span></button>
                </div><%-- onclick="Inpoi()"--%>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


</body>
</html>