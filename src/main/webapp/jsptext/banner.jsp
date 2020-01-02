<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<script>
    $(function () {
        $("#bannerList").jqGrid({
            url:"${pageContext.request.contextPath}/banner/pager",
            editurl:"${pageContext.request.contextPath}/banner/bannerEdit",
            datatype:"json",
            styleUI:"Bootstrap",
            colNames:["id","name","img_src"],
            pager:"#bannerPager",               //分页
            rowNum:2,                            //每页显示多少条
            rowList:[2,4,8],
            viewrecords:true,                   //是否显示总记录数
            autowidth:true,                     //自适应父容器
            multiselect:true,                   //是否多选
            height:'400px',
            colModel:[
                {name:"id"},
                {name:"name",'editable':true},
                {name:"img_src",editable:true,edittype:'file',
                    formatter:function(cellvalue, options, rowObject){
                        return "<img style='width:100%;height:100px' src='${pageContext.request.contextPath}/img/"+cellvalue+"'/>";
                    }
                }
            ]
        }).jqGrid("navGrid","#bannerPager",{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},
            {
                /*
                * 修改
                * */

            },
            {
                /*
                * 添加
                * */
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    var id = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/bannerUpload",
                        fileElementId:'img_src',
                        data:{bannerId:id},
                        type:"post",
                        success:function () {
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
    })
</script>


<table id="bannerList"></table>
<div id="bannerPager" style="height: 50px"></div>
<div class="alert alert-success" style="display:none" id="msgDiv">
    添加成功
</div>