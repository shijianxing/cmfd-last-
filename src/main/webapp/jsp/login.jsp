<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Login Form Template</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
    <script src="../boot/js/jquery-3.3.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.backstretch.min.js"></script>
    <script src="assets/js/scripts.js"></script>
    <%--<script src="../boot/js/jquery.validate.min.js"></script>
    <script src="../boot/js/jquery.validate.min.js"></script>--%>
    <script src="${pageContext.request.contextPath}/dist/js/bootstrapValidator.min.js"></script>
    <link href="${pageContext.request.contextPath}/dist/css/bootstrapValidator.min.css" rel="stylesheet" />


    <script>
       $(function () {
           $("#codeImage").click(function () {
               $("#codeImage").prop("src","${pageContext.request.contextPath}/code/getCode?time="+new Date().getTime())
           });

           $("#loginButtonId").click(function () {
               $.ajax({
                   url: "${pageContext.request.contextPath}/admin/login",
                   data: $("#loginForm").serialize(),
                   datatype: "json",
                   success:function (date) {
                       var error="ok";
                       if (date==error){
                           window.location="${pageContext.request.contextPath}/jsp/Adminshow.jsp";
                       } else{
                           alert(date);
                       }
                   }
               })
           })
           $("#loginForm").bootstrapValidator({
               message: 'This value is not valid',
               feedbackIcons: {
                   valid: 'glyphicon glyphicon-ok',
                   invalid: 'glyphicon glyphicon-remove',
                   validating: 'glyphicon glyphicon-refresh'
               },
               fields: {
                   username: {
                       message: '用户名验证失败',
                       validators: {
                           notEmpty: {
                               message: '用户名不能为空'
                           },
                           stringLength: {
                               min: 4,
                               max: 18,
                               message: '用户名长度必须在6到18位之间'
                           },
                           regexp: {
                               regexp:  /^[\u2E80-\u9FFF\w-]{4,20}$/, /*/^[a-zA-Z0-9_]+$/*/
                               message: '用户名仅支持中文、字母、数字、“-”“_”的组合，4-20个字符'
                           }
                       }
                   }
               },
               password: {
                   validators: {
                       notEmpty: {
                           message: '密码不能为空'
                       },
                       stringLength: {
                           min: 6,
                           max: 18,
                           message: '用户名长度必须在6到18位之间'
                       }
                   }

               }
                   /*.on('success.form.bv', function(e) {//点击提交之后
                       e.preventDefault();
                       var $form = $(e.target);
                       var bv = $form.data('bootstrapValidator');

                       // Use Ajax to submit form data 提交至form标签中的action，result自定义
                       $.post($form.attr('action'), $form.serialize(), function (result) {
                           //恢复submit按钮disable状态。
                           $('#loginForm').bootstrapValidator('disableSubmitButtons', false);
                           //do something...
                       })*/
                   })
                   /*.on('error.form.bv', function(e) {
                       var $form= $(e.target),
                           bootstrapValidator = $form.data('bootstrapValidator');
                       if (!bootstrapValidator.isValidField('password')) {
                           // The captcha is not valid
                           // Regenerate the captcha
                           $('#loginForm').bootstrapValidator('disableSubmitButtons', false);
                       }
                   })*/
                   /*.on('success.form.bv', function(e) {
                       // Prevent submit form
                       e.preventDefault();

                       var $form     = $(e.target),
                           validator = $form.data('bootstrapValidator');
                       $form.find('.alert').html('Thanks for signing up. Now you can sign in as ' + validator.getFieldElements('username').val()).show();
                   })*/




           })



    </script>
</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>CMFZ</strong> Login Form</h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>CMFZ</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top" style="width: 450px">
                        <div class="form-top-left">
                            <h3>Login to showall</h3>
                            <p>Enter your username and password to log on:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">
                        <form role="form" action="${pageContext.request.contextPath}/admin/login" method="post" class="login-form" id="loginForm">
                            <span id="msgDiv"></span>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="username" placeholder="请输入用户名..." required
                                       class="form-username form-control" id="form-username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="password" placeholder="请输入密码..." required pattern="[a-zA-Z0-9]{6,10}"
                                       class="form-password form-control" id="form-password">
                            </div>
                            <div class="form-group">
                                <img id="codeImage" style="height: 48px" class="captchaImage"
                                     src="${pageContext.request.contextPath}/code/getCode">
                                <input style="width: 289px;height: 50px;border:3px solid #ddd;border-radius: 4px;"
                                       type="test" name="enCode" id="form-code">
                            </div>
                            <input type="submit" style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;" id="loginButtonId"  value="登录">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


</body>

</html>