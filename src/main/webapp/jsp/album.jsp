<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .ui-jqgrid .ui-userdata {
            padding: 13px 94px;
            overflow: hidden;
            min-height: 32px;
        }
    </style>
<script>
    /*  jqGrid*/
    $(function () {

        $("#albumList").jqGrid({
            url:"${app}/album/pagerAll",
            editurl:"${app}/admin/edit",
            styleUI:"Bootstrap",
            mtype:"POST",
            height:"400",
            multiselect:true,
            datatype:"json",
            autowidth:true,
            records:true,
            viewrecords:true,
            rowNum:3,
            rowList:[3,6,9],
            caption:"专辑展示",
            pager:"#albumDiv",
            colNames:[
                "ID","标题","图片","分数","作者","播音员","章节数","专辑简介","上传时间","状态"
            ],
            colModel:[
                {name:"id"},
                {name:"title",editable:true},
                {name:"img",editable:true,edittype:'file',
                    formatter:function (cellvalue, options, rowObject) {
                        return "<img style=\"width: 100%;height:60px\" src='${pageContext.request.contextPath}/img/"+cellvalue+"'/>";
                    }
                },
                {name:"score",editable:true},
                {name:"author",editable:true},
                {name:"broadcaster",editable:true},
                {name:"count",editable:true},
                {name:"brief",editable:true},
                {name:"create_date",editable:true,edittype:'date'},
                {name:"status",editable:true,edittype:"select",editoptions:{
                        value:"展示:展示;不展示:不展示"
                    }},
            ],
            subGrid:true,               //开启子表格
            subGridRowExpanded:function (subGridId,albumId) {
                //添加子表格的方法
                addSubGrid(subGridId,albumId);
            }
        }).jqGrid("navGrid","#albumDiv")
    })

    //添加子表格的方法
    function addSubGrid(subGridId,albumId) {
        //动态 table id
        var TableId=subGridId+"table";
        //动态 div id名
        var  DivId=albumId+"div";
        //动态 播放id


       /* var music=albumId+"music";*/
        //动态添加子表格
        $("#"+subGridId).html(
            "<table id='"+TableId+"'></table>"+
            "<div id='"+DivId+"' style='height: 50px'></div>"
        )
        //子表格的创建
        $("#"+TableId).jqGrid({
            url:"${app}/chapter/pagerAll?id="+albumId,
            editurl:"${app}/chapter/editChapter?albumid="+albumId,
            styleUI:"Bootstrap",
            toolbar:[true,"top"],
            datatype:"json",
            autowidth:true,
            records:true,
            height:"450px",
            viewrecords:true,
            rowNum:3,
            multiselect:true,
            rowList:[3,6,9],
            caption:"专辑管理",
            pager:"#"+DivId,
            colNames:[
                "ID","标题","大小","时长","音频","状态"

            ],
            colModel:[
                {name:"id"},
                {name:"title",editable:true},
                {name:"duration"},
                {name:"size"},
                {name:"src",editable:true,edittype:'file',
                    formatter:function (cellvalue, options, rowObject) {
                    return cellvalue;
                        /*var ss=Math.floor(Math.random()*10+1);//"+music+"
                        return "<audio src='/audio/"+cellvalue+"' controls='controls' id='"+ss+"'></audio>";*/
                    }
                },
                {name:"status",editable:true,edittype:"select",editoptions:{
                        value:"展示:展示;不展示:不展示"
                    }},
            ]
        }).jqGrid("navGrid","#"+DivId,{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},
            {
                /*
                * 修改
                * */
                closeAfterEdit:true,
                afterSubmit:function (response) {

                    var id = response.responseJSON.chapterId;
                    $.ajaxFileUpload({
                        url:"${app}/chapter/chapterUpload",
                        fileElementId:'src',
                        data:{chapterId:id},
                        type:"post",
                        success:function () {
                            $("#albumList").trigger("reloadGrid");// 刷新gqgrid的表格
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

                    var id = response.responseJSON.chapterId;
                    $.ajaxFileUpload({
                        url:"${app}/chapter/chapterUpload",
                        fileElementId:'src',
                        data:{chapterId:id},
                        type:"post",
                        success:function () {
                            $("#albumList").trigger("reloadGrid");//刷新表格
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
        $("#t_"+TableId).html("<button class='btn btn-danger' onclick=\"play('"+TableId+"')\">播放 <span class='glyphicon glyphicon-play'></span></button>"+
            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
            "<button class='btn btn-danger' onclick=\"upcc('"+TableId+"')\">下载 <span class='glyphicon glyphicon-arrow-down'></span></button>"+
            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
            +"<button class='btn btn-danger' onclick=\"stopau('"+TableId+"')\">停止 <span class='glyphicon glyphicon-pause'></span></button>"
        )
    }
    //播放
    function play(TableId) {

        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
        var gr = $("#"+TableId).jqGrid('getGridParam', 'selrow');
        if(gr == null){
            alert("请选中要播放的音频");
        }else{
            //1.请求后台
            //2.jqgrid 提供的方法 根据id拿到对应的值
            var data = $("#"+TableId).jqGrid('getRowData', gr);
            //获得src的值
            var src=data.src;
            //展示模态框
            $('#myModal').modal('toggle');
            //添加 音频的地址
            $("#Myaudio").attr("src","${app}/audio/"+src);
        }
    }

    //停止
    function stopau(TableId) {
        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
        var gr = $("#"+TableId).jqGrid('getGridParam', 'selrow');
        if(gr == null){
            alert("请选中要停止的音频");
        }else{
            //1.请求后台
            //2.jqgrid 提供的方法 根据id拿到对应的值
            var data = $("#"+TableId).jqGrid('getRowData', gr);
            var byId = document.getElementById("Myaudio");

            byId.pause();
        }
    }
    //下载
    function upcc(TableId) {
        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
        var gr = $("#"+TableId).jqGrid('getGridParam', 'selrow');
        if(gr == null){
            alert("请选中要停止的音频");
        }else{
            //1.请求后台
            //2.jqgrid 提供的方法 根据id拿到对应的值
            var data = $("#"+TableId).jqGrid('getRowData', gr);
            var mysrc =data.src;
            alert(mysrc);
            //发送请求
            location.href="${app}/chapter/filedown?name="+mysrc;
        }
    }
        //离开时停止播放
    function stopaudio() {
        var byId = document.getElementById("Myaudio");
        byId.pause();
    }



</script>
</head>

<body>

<%--
    页头
--%>

<div class="page-header">
    <h3>专辑秀</h3>
</div>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑管理</a></li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="home">
            <table id="albumList"></table>
        </div>
    </div>
</div>

<div style="height:50px" id="albumDiv"></div>
<div class="alert alert-success" style="display:none" id="msgDiv">
    添加成功
</div>



<%--
        音乐播放器
--%>
<div class="modal fade" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">音乐播放</h4>
            </div>
            <div class="modal-body">
               <audio src="" controls="controls" currentTime autoplay id="Myaudio"></audio>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="stopaudio()" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>