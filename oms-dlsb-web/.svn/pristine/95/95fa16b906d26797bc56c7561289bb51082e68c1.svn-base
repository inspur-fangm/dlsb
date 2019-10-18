<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>中调修改上报数据页面</title>
    <base href="${request.contextPath}">
    <script> var base = "${request.contextPath}";</script>
    <link rel="stylesheet" href="${request.contextPath}/resources/js/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" href="${request.contextPath}/resources/js/easyui/themes/icon.css" />
    <link rel="stylesheet" href="${request.contextPath}/resources/js/easyui/themes/icon.css"/>
    <link rel="stylesheet" href="${request.contextPath}/static/layui/css/layui.css"/>
    <link rel="stylesheet" href="${request.contextPath}/static/dlsb/common/drc_report.css"/>
    <script type="text/javascript" src="${request.contextPath}/resources/js/jquery/jquery-2.1.4.min.js"></script>
    <script type="text/javascript"
            src="${request.contextPath}/resources/js/jquery-validation/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <#--<script type="text/javascript" src="${request.contextPath}/static/awyxgl/common/common.js?v=20181024"></script>
    <script type="text/javascript" src="${request.contextPath}/static/awyxgl/form/addform.js?v=20181024"></script>-->
</head>
<body class="easyui-layout" style="background:#eee;overflow: auto;height: 99.5%"  >
<div data-options="region:'center'">
    <form method="post" id="editForm" name="editForm">
        <table id="sectionEdit" style="width: 100%" class="tablegrid_flat" align="center" cellspacing="0" border="0" cellpadding="0">
            <thead>
                <tr class="datagrid-header-row">
                    <th style="display: none">上报电厂id</th>
                    <th style="width: 25%">电厂名称</th>
                    <th style="display: none">数据日期</th>
                    <th style="width: 25%">电厂类型</th>
                    <th style="display: none">上报机组id</th>
                    <th style="width: 25%">机组名称</th>
                    <th style="width: 25%">发电量(万Kwh)</th>
                    <th style="display: none">数据记录日期</th>
                </tr>
            </thead>
            <tbody>
                <#if list?exists>
                    <#list list as data>
                        <tr class="datagrid-row shujudata" attr="shujudata">
                            <td style="display: none">
                                <input class="fluid-input" readonly="readonly" name="sbdcid" value="<#if data.sbdcid??>${data.sbdcid}</#if>">
                            </td>
                            <td class="data_cal">
                                <input class="fluid-input" readonly="readonly" name="sbdcmc" value="<#if data.sbdcmc??>${data.sbdcmc}</#if>">
                            </td>
                            <td style="display: none" class="data_cal">
                                <input class="fluid-input" readonly="readonly" name="shujurq" value="<#if data.shujurq??>${data.shujurq}</#if>">
                            </td>
                            <td class="data_cal">
                                <input class="fluid-input" readonly="readonly" name="dclx" value="<#if data.dclx??>${data.dclx}</#if>">
                            </td>
                            <td style="display: none" class="data_cal">
                                <input class="fluid-input" readonly="readonly" name="sbjzbh" value="<#if data.sbjzbh??>${data.sbjzbh}</#if>">
                            </td>
                            <td class="data_cal">
                                <input class="fluid-input" readonly="readonly" name="sbjzmc" value="<#if data.sbjzmc??>${data.sbjzmc}<#else >-</#if>">
                            </td>
                            <td class="data_cal">
                                <input class="fluid-input" data-name="sbshuju" name="sbshuju" value="<#if data.sbshuju??>${data.sbshuju}</#if>">
                            </td>
                            <td class="data_cal" style="display: none">
                                <input class="fluid-input" readonly="readonly" name="jlsj" value="<#if data.jlsj??>${data.jlsj}<#else ></#if>">
                            </td>
                        </tr>
                    </#list>
                </#if>
            <tr>
                <td attr="GDJ_NAME" colspan="3">合计</td>
                <td class="data_cal"><div class="autoCal level0" id="ljxddl_total" attr="ljxddl" func="sum_table_col,sectionEdit,sbshuju,2"></div></td>
            </tr>
            </tbody>
        </table>
    </form>
    <br>
    <div style="text-align: center">
        <button onclick="saveData()" class="layui-btn layui-btn-xs layui-bg-green">保存</button>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $(".fluid-input").on("change",function(){
            calLevel0();
        });
    });
    calLevel0();
    function saveData(){
        var rows = getTableRows();
        var resultRows = addSumColum(rows);
        console.log(resultRows);
        var rowstr = JSON.stringify(resultRows);
        $.ajax({
            type : "POST",
            url : getUrl.get_dlsb_savedata(),
            contentType: "application/json",
            data : rowstr,
            dataType : "json",
            success : function(r) {
                if(r.msg=='ok'){
                    easyuiAlert("提示",'保存上报成功',"info");
                }else {
                    easyuiAlert("提示",r.msg,"info");
                }

            },
            error : function(request) {
                easyuiAlert("提示",'系统发生异常，请联系维护人员!',"warning");
            }
        });
    }
    var getTableRows = function () {
        var datas = document.getElementsByClassName("shujudata");
        var resultdata = [];
        if(datas){
            for(var i=0;i<datas.length;i++){
                var tdArr = datas[i].children;
                var jsonData = {};
                if(tdArr){
                    for(var j=0;j<tdArr.length;j++){
                        var td = tdArr[j];
                        var name = td.getElementsByTagName("input")[0].name;
                        var value = td.getElementsByTagName("input")[0].value;
                        jsonData[name]=value;
                    }
                }
                resultdata.push(jsonData);
            }
        }
        return resultdata;
    }

    var addSumColum = function(rows){
        var rows = rows;
        var shujurq = $("#sdate").val();
        var sbdcid = null;
        var sbdcname = null;
        var dclx = null;
        var jlsj = null;
        var total = 0;
        var len = rows.length - 1;
        var objectArray = [];
        $.each(rows, function (i, item) {//循环所有行记录
            if (!isNull(item.sbjzbh)) {
                objectArray.push(item);
            }
            /*console.log(item.sbjzmc);
            console.log(item.jlsj);*/
            var sum = 0;
            //计算合计的sum
            if (!isNull(item.sbshuju)) {
                sum = item.sbshuju;
            } else {
                sum = 0;
            }
            /*if (!isNull(item.jlsj)) {
                jlsj = item.jlsj;
            }else{
                jlsj = null;
            }
            if (!isNull(item.dclx)){
                dclx = item.dclx;
            } else{
                dclx = null;
            }*/
            if (!sbdcid) {//判断是还是是当前区域id，如果不是赋值当前记录的area_id给变量
                sbdcid = item.sbdcid;
                sbdcname = item.sbdcmc;
                dclx = item.dclx;
                jlsj = item.jlsj;
            }
            if (sbdcid == item.sbdcid) {//如果是当前的区域id，计数合计
                total += Number(sum);
            } else {//不是当前的，添加datagrid的一条行数据
                objectArray.push({
                    sbdcid: sbdcid,
                    sbdcmc: sbdcname,
                    dclx: dclx,
                    sbshuju: total.toFixed(2),
                    shujurq: shujurq,
                    sbjzbh : null,
                    sbjzmc: null,
                    sbomsdcid: null,
                    jlsj: jlsj
                });
                sbdcid = item.sbdcid;
                sbdcname = item.sbdcmc;
                jlsj = item.jlsj;
                dclx = item.dclx;
                total = Number(sum);
            }
            if (i == len) {//最后一行
                objectArray.push({
                    sbdcid: sbdcid,
                    sbdcmc: sbdcname,
                    sbshuju: total.toFixed(2),
                    shujurq: shujurq,
                    dclx:dclx,
                    sbjzbh : null,
                    sbjzmc: null,
                    sbomsdcid: null,
                    jlsj: jlsj
                });
            }

        });
        return objectArray;
    }
</script>
</body>
</html>