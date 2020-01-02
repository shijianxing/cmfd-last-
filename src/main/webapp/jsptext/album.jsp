<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false"%>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<style>
    .ui-jqgrid .ui-userdata {
        padding: 13px 94px;
        overflow: hidden;
        min-height: 32px;
    }
</style>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url:"${app}/json/album.json",
            styleUI:"Bootstrap",
            datatype:"json",
            autowidth:true,
            records:true,
            rowNum:3,
            rowList:[3,6,9],
            height:400,
            caption: "专辑",
            pager:"#albumPager",
            colNames:[
                "id","title","count"
            ],
            colModel:[
                {name:"id"},
                {name:"title"},
                {name:"count"}
            ],
            subGrid:true,         //开启子表格
            subGridRowExpanded:function (subGridId,albumId) {
                //添加子表格的方法
                addSubGrid(subGridId,albumId);
            }
        })
    })

    //添加子表格
    function addSubGrid(subGridId,albumId) {
        //动态table  id
        var subGridTableId = subGridId + "table";
        //动态div id
        var subGridDivId = subGridId + "div";
        //动态添加子表格
        $("#"+subGridId).html("<table id='"+subGridTableId+"'></table>"+
                               "<div id='"+subGridDivId+"' style='height: 50px'></div>"
                              )
        $("#"+subGridTableId).jqGrid({
            url:"${app}/json/chapter.json",
            styleUI:"Bootstrap",
            datatype:"json",
            autowidth:true,
            records:true,
            rowNum:3,
            caption:"章节",
            toolbar:[true,"top"],
            pager:"#"+subGridDivId,
            rowList:[3,6,9],
            colNames: [
                "id","title","size","timelong"
            ],
            colModel: [
                {name:"id"},
                {name:"title"},
                {name:"size"},
                {name:"timelong"}
            ]
        })

        //添加按钮
        $("#t_"+subGridTableId).html("<button class='btn btn-danger' onclick=\"play('"+subGridTableId+"')\">播放 <span class='glyphicon glyphicon-play'></span></button>"+
                                       "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                       "<button class='btn btn-danger'>下载 <span class='glyphicon glyphicon-arrow-down'></span></button>"
                                     )
    }

    //播放
    function play(subGridTableId) {
        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
        var gr = $("#"+subGridTableId).jqGrid('getGridParam', 'selrow');
        if(gr == null){
            alert("请选中要播放的音频");
        }else{
            //1.请求后台
            //2.jqgrid 提供的方法 根据id拿到对应的值
            var data = $("#"+subGridTableId).jqGrid('getRowData', gr);
            console.log(data);
        }
    }
</script>

<table id="albumList" style="height: 200px"></table>
<div id="albumPager" style="height: 50px"></div>