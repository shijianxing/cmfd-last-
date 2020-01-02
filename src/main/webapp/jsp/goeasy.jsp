<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/escherts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/escherts/china.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
</head>
<body>
<div id="main" style="width: 600px;height:400px;"></div>

</body>
<script>
    var goEasy = new GoEasy({
        host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BS-172bf1a3efa1482d9364f61e2923bb62", //替换为您的应用appkey
    });


    var myChart = echarts.init($("#main")[0]);
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts  统计七天的用户注册量'
        },
        tooltip: {},
        legend: {
            data:['人数']
        },
        xAxis: {
            data: ["星期一","星期二","星期三","星期四","星期五","星期六","星期天"]
        },
        yAxis: {},
        series: [{
            name: '人数',
            type: 'bar'
        }]
    };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    $(function () {
        $.ajax({
            url:"${pageContext.request.contextPath}/goeasy/week",
            datatype:"json",
            type:"POST",
            success:function (reult) {
                myChart.setOption({
                    series:[{
                        data:reult
                    }
                    ]
                })

            }
        })
    })

    goEasy.subscribe({
        channel: "gurueasy", //替换为您自己的channel
        onMessage: function (message) {
            /*alert("Channel:" + message.channel + " content:" + message.content);*/
            console.log(message.content);
            var parse = JSON.parse(message.content);
            myChart.setOption({
                series: [{
                    data:parse
                }]
            })
        }
    });

</script>
</html>