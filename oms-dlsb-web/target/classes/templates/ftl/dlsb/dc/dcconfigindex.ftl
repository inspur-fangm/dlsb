<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>电厂配置页面</title>
    <base href="${request.contextPath}">
    <script> var base = "${request.contextPath}"</script>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="${request.contextPath}/static/layui/css/layui.css"/>
    <link rel="stylesheet" href="${request.contextPath}/resources/js/easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" href="${request.contextPath}/resources/js/easyui/themes/icon.css"/>

    <link rel="stylesheet" href="${request.contextPath}/resources/js/easyui/themes/icon.css"/>
    <link rel="stylesheet" href="${request.contextPath}/static/dlsb/common/drc_report.css"/>
    <script type="text/javascript" src="${request.contextPath}/resources/js/jquery/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/dlsb/common/common.js?v=20181122"></script>
    <script type="text/javascript" src="${request.contextPath}/static/dlsb/grid/searchindex.js?v=20181122"></script>
    <script type="text/javascript" src="${request.contextPath}/static/dlsb/grid/dcconfigindex.js"></script>
    <style>
        .textbox{
            height: 20px;
            margin: 0;
            padding: 0 2px;
            box-sizing: content-box;
        }
    </style>
</head>
<body>

<div id="tb" style="padding:5px;">
    <div style="margin-bottom:5px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" style="display:none;" id="save" onclick="">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" style="display:none;" id="redo" onclick="">取消编辑</a>
    </div>
    <div style="padding:0 0 0 7px;color:#333;">
        根据dlldb_id查询数据：<input type="text" class="textbox" name="dlldbId" style="width:110px;margin-left:10px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search();">查询</a>
    </div>
</div>
    <#--数据展示-->
<div>
    <table id="box"></table>
</div>
</body>
</html>