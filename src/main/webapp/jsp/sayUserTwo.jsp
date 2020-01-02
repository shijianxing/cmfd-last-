<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<script src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
<script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
<script>
    $(function () {
        /*  准备富文本的初始化*/
        KindEditor.ready(function (K) {
            window.editor = K.create('#editor_id', {
                items: [
                    'emoticons', 'subscript', 'superscript', 'forecolor', 'fontname', 'fontsize', 'hilitecolor'
                ]
            });
        });
        /*  goeasy*/
        var goEasy = new GoEasy({
            host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-b121a256bfeb4d0e874c71b5a744d092", //替换为您的应用appkey
        });

        //接受发布的信息
        goEasy.subscribe({
            channel: "aaa", //替换为您自己的channel
            onMessage: function (message) {
                var m = new Date().getMinutes();
                var s = new Date().getSeconds();
                var h = new Date().getHours();
                var time = h + ":" + m + ":" + s;
                /*alert("Channel:" + message.channel + " content:" + message.content);*/
                $("#talk").append("<p style=\"margin-left: 120px;color:  hotpink\">苦哈哈"+time+"</p>"+
                    "<p style=\"margin-left: 160px;color: #1b6d85\">"+message.content+"</p>")
            }
        });
        /* 发布消息*/
        $("#submit1").click(function () {
            var m = new Date().getMinutes();
            var s = new Date().getSeconds();
            var h = new Date().getHours();
            var time = h + ":" + m + ":" + s;
            html = editor.html();
            $("#talk").append("<p id=\"3\" style=\"margin-left: 580px;color: #00bbff\">我"+time+"</p>"+
                "<p style=\"margin-left: 620px;color: #1b6d85\">"+html+"</p>")
            goEasy.publish({
                channel: "bbb", //替换为您自己的channel
                message: html //替换为您想要发送的消息内容
            });
            editor.html("");
        })
    })




</script>
<body>
<div>
    <div style="margin-left: 300px">
        <%-- 准备对话框--%>
        <div style="border: 1px #9A9A9A solid;width:700px;height:300px; overflow-y:auto" id="talk">
        </div>
            <%-- 富文本--%>
        <textarea id="editor_id" name="content" style="width:700px;height:100px;">

            </textarea>
        <br/>
            <%-- 发送按钮--%>
        <button class="" style="margin-left: 650px" id="submit1">发送</button>
    </div>
</div>

</body>
</html>