<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>INS 后台管理系统登陆</title>
    <script src="/webjars/jquery/3.3.1-1/jquery.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.bundle.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <script src="/js/jquery.growl.js"></script>
    <script src="/webjars/bootstrap-treeview/1.2.0/dist/bootstrap-treeview.min.js"></script>
    <link rel="stylesheet" href="/css/jquery.growl.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap-grid-jsf.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap-grid.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap-jsf.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap-reboot-jsf.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css">
    <link rel="stylesheet" href="/css/Graphical.css">
    <link rel="stylesheet" href="/webjars/bootstrap-treeview/1.2.0/dist/bootstrap-treeview.min.css">
    <link rel="stylesheet" href="/css/iconfont.css">
    <style>
        i:hover {
            cursor: pointer;
        }
        div {
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!--左侧树-->
        <div class="col-12">
            <div class="row">
                <div class="col-4 offset-1">
                    <a>数据列表</a>
                </div>
                <div class="col-4 offset-3" id="librarymenu">
                    <a>
                        <i class="iconfont icon-bin" style="cursor:hand"></i>
                    </a>
                    <a>
                        <i class="iconfont icon-edit"></i>
                    </a>
                    <a>
                        <i class="iconfont icon-add"></i>
                    </a>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="treeview offset-2"></div>
            </div>
        </div>
        <!--右侧详情-->
        <div class="col-8 offset-1" id="info">
            <div class="row">
                <div class="col-4 offset-1">
                    <a>数据列表</a>
                </div>
                <div class="col-2 offset-5" id="librarymenu">
                    <a id="edit">
                        <i class="iconfont icon-edit"></i>
                    </a>
                </div>
            </div>
            <hr>
            <div class="row offset-2">
                <div class="row">
                    <form class="bs-example bs-example-form" role="form">
                        <div class="input-group center-block">
                            数据字典名称：
                            <input type="text" class="form-control" id="libraryName" disabled>
                        </div>
                        <br>
                        <div class="input-group center-block">
                            数据字典编码：
                            <input type="text" class="form-control" id="libraryCode" disabled>
                        </div>
                        <br>
                        <div class="input-group center-block">
                            字典创建时间：
                            <input type="text" class="form-control" id="createTime" disabled>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    $(document).ready(function () {
        //设置初始状态
        var state = {
            checked: false,
            disabled: false,
            expanded: false,
            selected: false
        };
        //获取node
        function getNodes(item){
            var node = {};
            node["text"] = item.libraryName;
            node["id"] = item.libraryCode;
            node["state"] = state;
            node["tags"] = [item.parentCode,item.createTime.toString()];
            return node;
        }
        //获取树
        function getTree() {
            var data = ${MyData};
            var tree = [];
            data.forEach(function (item, index) {
                var node = getNodes(item);
                if (item.child != null) {
                    var nodes = [];
                    item.child.forEach(function (item, index) {
                        nodes[index] = getNodes(item);
                    })
                    node["nodes"] = nodes;
                }
                tree[index] = node;
            })
            return tree;
        };
        //初始化树
        $('.treeview').treeview({
            data: getTree(),         // data is not optional
            levels: 5,
            injectStyle: true,
            expandIcon: 'iconfont icon-msnui-triangle-right',
            collapseIcon: 'iconfont icon-xiangxia',
            emptyIcon: 'iconfont icon-yuandian',
            onhoverColor: '#F5F5F5',
            selectedColor: '#b1b1b1',
            selectedBackColor: '#428bca',
            searchResultColor: '#D9534F',
            enableLinks: false,
            highlightSelected: true,
            highlightSearchResults: true,
            showBorder: false,
            showIcon: true,
            showCheckbox: true,
            showTags: false,
            multiSelect: false,
            onNodeSelected : function(event,node){

            }
        })
        $('body').on('click','#modify',function () {
            var temp = $('#tree').treeview('getNode', 2);
            alert(temp.text());
            $('.treeview').treeview('expandNode', [ 0, { levels: 2, silent: true } ]);
        })
        $('body').on('nodeSelected','.treeview',function(event,data){
            $('input[id=libraryName]').attr("disabled","disabled");
            $('input[id=libraryCode]').attr("disabled","disabled");
            $('input[id=libraryName]').val(data.text);
            $('input[id=libraryCode]').val(data.id);
            $('input[id=createTime]').val(data.tags[1]);
        })
        $('body').on('click','#edit',function () {
            $('input[id=libraryName]').removeAttr("disabled");
            $('input[id=libraryCode]').removeAttr("disabled");
        })
        $('body').on('keypress','input',function (event) {
            alert(0)
            if(event.which==13){
                alert("修改成功");
            }
        })
    })
</script>
</body>
</html>