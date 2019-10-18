<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>电量上报Searchindex界面</title>
    <base href="${request.contextPath}">
    <script> var base = "${request.contextPath}"</script>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="${request.contextPath}/static/layui/css/layui.css"/>
    <link rel="stylesheet" href="${request.contextPath}/resources/js/easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" href="${request.contextPath}/resources/js/easyui/themes/icon.css"/>

    <link rel="stylesheet" href="${request.contextPath}/resources/js/easyui/themes/icon.css"/>
    <script type="text/javascript" src="${request.contextPath}/resources/js/jquery/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/dlsb/common/common.js?v=20181118"></script>
    <style>
        .menu {width:100%; background:#0099FF; height:100%; font-size:14px; font-family: "微软雅黑";}
        .menu ul {padding:0px; list-style-type:none;}
        .menu ul li {width:100px; height:35px; line-height:35px; text-align:center;}
        .menu ul li a {text-decoration:none; color:#fff; display:block;}
        .menu ul li a:hover {background:#fff; color:#000;}
        .menu ul li a:active {background:#fff; color:#000;}
        .menu ul li a:focus {background:#fff; color:#000;}
    </style>
</head>
<body class="easyui-layout" style="width:99%"  data-options="fit:true">
    <div data-options="region:'west',title:'电厂类型列表',split:true" style="width:12%;font-size: 12px">
        <div id="nav" class="menu">
            <ul>
                <li><a href="javascript:void(0)" onclick="addMainTab('火电',getUrl.get_indexurl_dclx(1),'ttContent')">火电</a></li>
                <li><a href="javascript:void(0)" onclick="addMainTab('直调水电',getUrl.get_indexurl_dclx(2),'ttContent')">直调水电</a></li>
                <li><a href="javascript:void(0)" onclick="addMainTab('统购小水电',getUrl.get_indexurl_dclx(3),'ttContent')">统购小水电</a></li>
                <li><a href="javascript:void(0)" onclick="addMainTab('风电',getUrl.get_indexurl_dclx(4),'ttContent')">风电</a></li>
                <li><a href="javascript:void(0)" onclick="addMainTab('光伏',getUrl.get_indexurl_dclx(5),'ttContent')">光伏</a></li>
                <li><a href="javascript:void(0)" onclick="addMainTab('其它电厂',getUrl.get_indexurl_dclx(6),'ttContent')">其它电厂</a></li>
                <li><a href="javascript:void(0)" onclick="addMainTab('下网电量',getUrl.get_indexurl_dclx(7),'ttContent')">下网电量</a></li>
                <li><a href="javascript:void(0)" onclick="addMainTab('地县购小电',getUrl.get_indexurl_dclx(8),'ttContent')">地县购小电</a></li>
            </ul>
        </div>
    </div>
    <div data-options="region:'center'" style="padding:0px;background:#eee;">
        <div id="ttContent" class="easyui-tabs" data-options="fit:true"></div>
    </div>
</body>
</html>