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
    option = {
        title : {
            text: '用户地理分布图',
            left: 'center'
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['女士','男士']
        },
        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text:['高','低'],           // 文本，默认为数值文本
            calculable : true
        },
        toolbox: {
            show: true,
            orient : 'vertical',
            left: 'right',
            top: 'center',
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        series : [
            {
                name: '男士',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
            }
        ]
    };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    /*$(function () {
        $.ajax({
            url:"/goeasy/week",
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
    })*/

    goEasy.subscribe({
        channel: "address", //替换为您自己的channel
        onMessage: function (message) {
            /*alert("Channel:" + message.channel + " content:" + message.content);*/
            var parse = JSON.parse(message.content);
            myChart.setOption({
                series: [{
                    data:message.content
                }]
            })
        }
    });

    $.ajax({
        url:"${pageContext.request.contextPath}/user/addressUser",
        datatype:"json",
        type:"POST",
        success:function (data) {
            console.log(data);
            myChart.setOption({
                series: [{
                    data:data
                }]
            })
        }
    })
</script>
</html>