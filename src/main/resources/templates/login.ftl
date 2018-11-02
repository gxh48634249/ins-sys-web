<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>INS 后台管理系统登陆</title>
    <script src="webjars/jquery/3.3.1-1/jquery.js"></script>
    <script src="webjars/bootstrap/4.1.1/js/bootstrap.bundle.js"></script>
    <script src="webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <script src="js/jquery.growl.js"></script>
    <link rel="stylesheet" href="css/jquery.growl.css">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap-grid-jsf.css">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap-grid.css">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap-jsf.css">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap-reboot-jsf.css">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap.css">
    <style>
        .container{
            display: block;
            text-align: center;
        }
        .btn{
            margin-top: 3%;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center">登录信息</h2>
    <form id="logininfo" action="/login" method="post">
        <div class="form-group col-10 offset-1">
            <label for="account"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/user.png"></span>
                </div>
                <input type="text" class="form-control" id="account" name="username" placeholder="请输入登录账户" required>
            </div>
        </div>
        <div class="form-group col-10 offset-1">
            <label for="pwd"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/pwd.png"></span>
                </div>
                <input type="password" class="form-control" id="pwd" name="password" placeholder="请输入账户密码" required>
            </div>
        </div>
        <button type="submit" class="btn btn-primary col-4" id="login" >登&ensp;&ensp;&ensp;&ensp;录</button>
        <button type="submit" class="btn btn-primary col-4" id="registry" >注&ensp;&ensp;&ensp;&ensp;册</button>
        <button class="btn btn-info col-4">忘记密码</button>
    </form>
</div>
</body>
</html>