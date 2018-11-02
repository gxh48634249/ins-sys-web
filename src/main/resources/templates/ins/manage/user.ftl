<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册页面</title>
    <script src="/webjars/jquery/3.3.1-1/jquery.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.bundle.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <script src="/js/jquery.growl.js"></script>
    <link rel="stylesheet" href="/css/jquery.growl.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap-grid-jsf.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap-grid.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap-jsf.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap-reboot-jsf.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css">
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
<script type="text/javascript">
    var ip = window.location.host;
</script>
<div class="container">
    <h2 class="text-center">登录信息</h2>
    <form id="logininfo" action="/user/insertUser" method="post">
        <div class="form-group col-10 offset-1">
            <label for="userAccount"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="/img/user.png"></span>
                </div>
                <input type="text" class="form-control" id="userAccount" name="userAccount" placeholder="账户（登录使用）" required>
            </div>
        </div>
        <div class="form-group col-10 offset-1">
            <label for="userPwd"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="/img/user.png"></span>
                </div>
                <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="密码（登录使用）" required>
            </div>
        </div>
        <div class="form-group col-10 offset-1">
            <label for="roleIds"></label>
            <select multiple class="form-control" id="userPwd" name="userPwd">
                <#list role as item>
                     <option id="${item.roleId}">${item.roleId}</option>
                </#list>
            </select>
        </div>
        <div class="form-group col-10 offset-1">
            <label for="userName"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="/img/pwd.png"></span>
                </div>
                <input type="text" class="form-control" id="userName" name="userName" placeholder="姓名" required>
            </div>
        </div>
        <div class="form-group col-10 offset-1">
            <label for="phone"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="/img/pwd.png"></span>
                </div>
                <input type="number" class="form-control" id="phone" name="phone" placeholder="手机号" required>
            </div>
        </div>
        <div class="form-group col-10 offset-1">
            <label for="mail"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="/img/pwd.png"></span>
                </div>
                <input type="email" class="form-control" id="mail" name="mail" placeholder="邮箱" required>
            </div>
        </div>
        <div class="form-group col-10 offset-1">
            <label for="wechat"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="/img/pwd.png"></span>
                </div>
                <input type="text" class="form-control" id="wechat" name="wechat" placeholder="微信" required>
            </div>
        </div>
        <div class="form-group col-10 offset-1">
            <label for="address"></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><img src="/img/pwd.png"></span>
                </div>
                <input type="text" class="form-control" id="address" name="address" placeholder="地址" required>
            </div>
        </div>

        <button type="submit" class="btn btn-primary col-4" id="login" >提&ensp;&ensp;&ensp;&ensp;交</button>
    </form>
</div>
</body>
</html>