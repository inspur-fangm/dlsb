<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>电量上报index界面</title>
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
    <style>
        .datagrid-row-selected {
            background: #00bbee;
            color: #fff;
        }
        .user-ul li {
            display: inline-block;
            margin: 5px;
            border: gainsboro;
        }
        .removebtn {
            line-height: 22px;
            text-decoration: none;
            opacity: 0.8;
            filter: alpha(opacity=80);
            padding: 3px 10px;
            border: 1px solid #dfe2e8;
            background-color: #fff;
        }
        .removebtn:hover {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body class="easyui-layout" data-options="fit:true">
    <div id="queryDiv" data-options="region:'north',split:true" style="height:10%;text-align: left; padding: 5px 0 5px 0;background-color: #f5f5f5;font-size: 14px">
        <table style="width: 100%">
            <tr>
                <td style="width: 25%;text-align: right">
                    <label for="sdate">日期:</label>
                </td>
                <td style="width: 30%;text-align: left">
                    <input type="text" style="width: 100%" name="sdate" id="sdate" value="<#if sdate??>${sdate }</#if>"
                       size="15" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly">
                </td>
                <td style="width: 45%;text-align: center">
                    <input type="hidden" id="dclx" value="<#if dclx??>${dclx}</#if>" >
                    <button onclick="sbDataSearch()"
                        class="layui-btn layui-btn-xs layui-bg-green">查询
                    </button>
                    <button onclick="exportThisExcel()"
                            class="layui-btn layui-btn-xs layui-bg-green">导出Excel
                    </button>
                </td>
            </tr>
        </table>
    </div>
    <div id="reportDiv" data-options="region:'center'"
        style="overflow-y:auto;background-color: #f5f5f5; text-align: left; padding: 0 1px 0 1px;">
        <table id="dlsb-data"></table>
    </div>
    <div id="dd">Dialog Content.</div>
    <div id="opDiv"></div>
</body>
</html>