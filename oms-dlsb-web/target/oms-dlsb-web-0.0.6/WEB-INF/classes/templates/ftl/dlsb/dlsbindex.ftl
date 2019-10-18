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
    <script type="text/javascript" src="${request.contextPath}/resources/js/jquery/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/dlsb/common/common.js?v=20181118"></script>
    <script type="text/javascript" src="${request.contextPath}/static/dlsb/grid/dlsbindex.js?v=20181118"></script>
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
<div id="queryDiv" data-options="region:'north',split:true"
     style="height:10%;text-align: left; padding: 5px 0 5px 0;background-color: #f5f5f5;font-size: 14px">
    <table style="width: 100%">
        <tr>
            <td style="width: 15%;text-align: right">
                <label for="czName">单位名称:</label>
            </td>
            <td style="width: 20%;text-align: left">
                <input type="text" style="width: 100%" name="czName" id="czName"
                       value="<#if dlsb.czName??>${dlsb.czName}<#else>  </#if>" size="15" readonly="readonly">
                <input type="hidden" name="ownerOrgid" id="ownerOrgid" value="<#if dlsb.ORGID??>${dlsb.ORGID }</#if>">
                <input type="hidden" name="receiptOrgid" id="receiptOrgid"
                       value="<#if dlsb.MORGID??>${dlsb.MORGID }</#if>">
            </td>
            <td style="width: 15%;text-align: right">
                <label for="sdate">日期:</label>
            </td>
            <td style="width: 20%;text-align: left">
                <input type="text" style="width: 100%" name="sdate" id="sdate" value="<#if dlsb.sdate??>${dlsb.sdate }</#if>"
                       size="15" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly">
            </td>
            <td style="width: 30%;text-align: center">
                <button onclick="searchSbData()"
                        class="layui-btn layui-btn-xs layui-bg-green">查询
                </button>
            <#--<a href="javascript:preDay();" class="easyui-linkbutton" data-options="plain: true,iconCls:'icon-arrow-left'"></a>
            <a href="javascript:nextDay();" class="easyui-linkbutton" data-options="plain: true,iconCls:'icon-arrow-right'"></a>
            <a href="javascript:query();" class="easyui-linkbutton" data-options="plain: true,iconCls:'icon-search'">查询</a>
                <a id="btn-next" class="easyui-linkbutton" onclick="bpm.startBpm()" data-options="iconCls:'easy-icon-search'">查询</a>-->
            </td>
        </tr>
    </table>
</div>
<div id="reportDiv" data-options="region:'center'"
     style="overflow-y:auto;background-color: #f5f5f5; text-align: left; padding: 0 1px 0 1px;">
    <table id="dlsb-data-table"></table>
</div>
<div data-options="region:'west',split:true" style="width: 10%">
    填报说明：
</div>
</body>
</html>