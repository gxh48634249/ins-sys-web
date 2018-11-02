<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>INS 后台管理系统登陆</title>
    <script src="webjars/jquery/3.3.1-1/jquery.js"></script>
    <#--<script src="webjars/bootstrap/4.1.1/js/bootstrap.bundle.js"></script>-->
    <script src="webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <script src="js/jquery.growl.js"></script>
    <link rel="stylesheet" href="css/jquery.growl.css">
    <#--<link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap-grid-jsf.css">-->
    <#--<link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap-grid.css">-->
    <#--<link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap-jsf.css">-->
    <#--<link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap-reboot-jsf.css">-->
    <#--<link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap-reboot.css">-->
    <link rel="stylesheet" href="webjars/bootstrap/4.1.1/css/bootstrap.css">
    <link rel="stylesheet" href="/css/iconfont.css">
    <link rel="stylesheet" href="/css/Graphical.css">
    <link rel="stylesheet" href="/webjars/bootstrap-treeview/1.2.0/dist/bootstrap-treeview.min.css">
    <link rel="stylesheet" href="/css/iconfont.css">
    <#assign  user=Session.SPRING_SECURITY_CONTEXT.authentication.principal/>
    <style>
        #userset,#headimg{
            cursor: pointer;
        }
        #logo{
            cursor: pointer;
        }
        i{
            cursor: pointer;
        }
        li>a>i{
            color: #1598e8;
        }
        li>a{
            color: black;
            text-align: center;
            padding-left: 5%;
        }
        .active{
            background-color: #f0f0f0;
        }
        #leftdrive,#logop{
            -moz-box-shadow:5px 0px 5px #f1f1f1, 0px 0px 5px #f1f1f1;
            -webkit-box-shadow:5px 0px 5px #f1f1f1, 0px 0px 5px #f1f1f1;
            box-shadow:5px 0px 5px #f1f1f1, 0px 0px 5px #f1f1f1;
        }
        #pana{
            -moz-box-shadow:0px 5px 5px #f1f1f1, 0px 0px 5px #f1f1f1;
            -webkit-box-shadow:0px 5px 5px #f1f1f1, 0px 0px 5px #f1f1f1;
            box-shadow:0px 5px 5px #f1f1f1, 0px 0px 5px #f1f1f1;
        }
        #search{
            background-color: #e9ecef;
        }
        #work-info{
            background-color: #f0f0f0;
        }
        i:hover {
            cursor: pointer;
        }
        /*div {*/
            /*vertical-align: middle;*/
        /*}*/
    </style>
</head>
<body>
<#-- 顶部导航/logo -->
<nav class="navbar navbar-expand-sm bg-white navbar-dark border-bottom p-0 m-0">
    <div class="col-1 p-0 m-0 h-100 w-100" id="logop">
        <#--左侧LOGO-->
        <div class="m-1 p-1 " id="logo">
            <img class="navbar-brand img-reponsive p-0 m-2" href="#" src="/img/logo.png" style="max-width: 100%;max-height: auto;">
        </div>
    </div>
    <div class="col-11 row p-0 m-0 justify-content-end"id="pana">
        <div class="row col-3 p-0">
            <div class="input-group m-2">
                <input type="text" class="form-control" placeholder="搜索..." id="search" name="search">
                <div class="input-group-append">
                    <span class="input-group-text"><i class="iconfont icon-search"></i></span>
                </div>
            </div>
        </div>
        <div class="row col-5"></div>
        <div class="row col-3 p-0 mr-1">
            <#--消息通知-->
            <div class="col-1 p-3 m-auto justify-content-center">
                <i class="iconfont icon-notification- p-2 m-2"></i>
            </div>
            <#--头像-->
            <div class="col-1 p-0 m-auto" id="headimg">
                <img src="http://static.runoob.com/images/mobile-icon.png" alt="头像" class="rounded-circle img-reponsive p-0 m-0" style="max-width: 100%;max-height: 100%;">
            </div>
            <#--个人中心-->
            <div class="col-2 p-0 m-auto justify-content-center">
                <#-- Dropdown -->
                <div class="nav-item dropdown row">
                    <a id="userset" class="nav-link dropdown-toggle" data-toggle="dropdown">${user.username}</a></i>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">权限查看</a>
                        <a class="dropdown-item" href="#">基本信息</a>
                        <a class="dropdown-item" href="#">安全设置</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row col-1"></div>
    </div>
</nav>

<div class="container-fluid h-100" id="work-space">
    <#--工作空间-->
    <div class="row h-100">
        <#--左侧导航-->
        <div class="col-1 p-0 m-0 h-100" id="leftdrive">
            <ul class="nav flex-column p-0 m-0">
                <li class="nav-item" id="library">
                    <a class="nav-link pl-0 m-0 pt-3 pb-3" href="#"><i class="iconfont icon-palette p-3"></i>数据字典</a>
                </li>
                <li class="nav-item" id="organ">
                    <a class="nav-link pl-0 m-0 pt-3 pb-3" href="#"><i class="iconfont icon-command p-3"></i>机构管理</a>
                </li>
                <li class="nav-item" id="permission">
                    <a class="nav-link pl-0 m-0 pt-3 pb-3" href="#"><i class="iconfont icon-joystick p-3"></i>权限管理</a>
                </li>
                <li class="nav-item" id="user">
                    <a class="nav-link pl-0 m-0 pt-3 pb-3" href="#"><i class="iconfont icon-user p-3"></i>人员管理</a>
                </li>
                <li class="nav-item" id="resource">
                    <a class="nav-link pl-0 m-0 pt-3 pb-3" href="#"><i class="iconfont icon-monitor p-3"></i>资源管理</a>
                </li>
            </ul>

        </div>
        <#--右侧展示1-->
        <div class="col-11 row p-0 m-0" id="work-info">
            <div class="row p-0 m-0">
                <div class="col-10" id="datatree">

                </div>
            </div>
        </div>
    </div>
</div>
<#--欢迎你,${user.username}<br/>-->
<#--您的权限为：<br/>-->
<#--<#list user.authorities as item>-->
    <#--${item}<br/>-->
<#--</#list>-->
<#--<a href="/logout">注销</a>-->
<script>
    $(document).ready(function () {
        $('body').on('click','li[class=nav-item]',function () {
            $('.nav-item').removeClass('active');
            $(this).addClass("active");
        })
        $('body').on('click','#logo',function () {
            $('.nav-item').removeClass('active');
            $('#work-space').clear();
        })
        $('body').on('click','#library',function () {
            $.ajax({
                type: 'POST',
                url: '/ins/manage/library',
                cache: false,
                success: function (data) {
                    $.growl.notice({title:"操作提示",message:'查询成功'});
                    document.getElementById("datatree").innerHTML=data;
                },
                error: function () {
                    $.growl.error({title:"操作提示",message:"查询失败"})
                }
            })
        })

    })
</script>
</body>
</html>