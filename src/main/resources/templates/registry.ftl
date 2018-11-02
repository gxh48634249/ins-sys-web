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
    <h2 class="text-center">新用户注册</h2>
    <form id="logininfo" action="/ins/insertUser">
        <div class="form-group">
            <label for="account"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/user.png"></span>
                </div>
                <input type="text" class="form-control" id="account" name="userAccount" placeholder="登录账户" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/pwd.png"></span>
                </div>
                <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="登录密码" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/pwd.png"></span>
                </div>
                <input type="text" class="form-control" id="userName" name="userName" placeholder="您的姓名" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/pwd.png"></span>
                </div>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="手机号" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/pwd.png"></span>
                </div>
                <input type="text" class="form-control" id="mail" name="mail" placeholder="邮箱" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/pwd.png"></span>
                </div>
                <input type="text" class="form-control" id="wechat" name="wechat" placeholder="微信" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/pwd.png"></span>
                </div>
                <input type="text" class="form-control" id="idCard" name="idCard" placeholder="身份证号" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/pwd.png"></span>
                </div>
                <input type="text" class="form-control" id="address" name="address" placeholder="住址" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/pwd.png"></span>
                </div>
                <input type="text" class="form-control" id="qq" name="qq" placeholder="QQ" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="img/pwd.png"></span>
                </div>
                <input type="text" class="form-control" id="gender" name="gender" placeholder="性别" required>
            </div>
        </div>
        <button type="submit" class="btn btn-primary col-4" id="login" >注册</button>
        <button class="btn btn-info col-4">登录</button>
    </form>
</div>
</body>
</html>