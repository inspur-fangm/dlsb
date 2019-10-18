
var groupeditRow = undefined;
var tableJzPure = {
    iniTable: function (url) {
        $("#dlsb-data-table").datagrid({
            columns: [[
                {
                    field: 'sbdcid', title: '序号', align: 'center',hidden:true
                },
                {
                    field: 'sbdcmc', title: '电厂名称', width: '25%', align: 'center'
                },
                {
                    field: 'sbjzbh', title: '机组编号', hidden:true, align: 'center'
                },
                {
                    field: 'sbjzmc', title: '机组', width: '25%', align: 'center'
                },
                {
                    field: 'sbshuju', title: '发电量（万Kwh）', width: '25%', align: 'center',editor: { type: 'numberbox', options: {min:0,precision:2} }
                },
                {
                    field: 'jlsj', title: '记录时间', width:'25%', align: 'center'
                }
            ]],
            toolbar: [{
                text: '电量填报',
            }, '-', {
                text: '保存并上报',
                iconCls: 'easy-icon-redo',
                handler: function () {
                    saveSbData(getUrl.get_dlsb_savedata());
                }
            }],
            url: url,
            queryParams:{
                orgid : $("#ownerOrgid").val(),
                date : $("#sdate").val()
            },
            collapsible: true,
            singleSelect: true,
            checkOnSelect: true,
            selectOnCheck: true,
            nowrap: false,
            rownumbers: true,
            striped: true,
            border: false,
            fit: true,
            fitColumns: false,
            loadMsg: "正在加载，请稍后......",
            onLoadSuccess: function (data) {
                $(this).datagrid("autoMergeCells", ['sbdcmc']);
            },
            onSelect:function(index, row){//去除选中高亮
                $('#dlsb-data-table').datagrid('unselectRow',index);
            },
            onDblClickCell: function (index, field, value) {
                checkSbDataisSubmit(index).done(function (r) {
                    if (r.msg == "ok"){
                        //双击开启编辑行
                        if (groupeditRow != undefined) {
                            $('#dlsb-data-table').datagrid("endEdit", groupeditRow);
                            groupeditRow = undefined;
                            $("#dlsb-data-table").datagrid("autoMergeCells", ['sbdcmc']);
                        }
                        if (groupeditRow == undefined) {
                            $('#dlsb-data-table').datagrid("beginEdit", index);
                            groupeditRow = index;
                            $("#dlsb-data-table").datagrid("autoMergeCells", ['sbdcmc']);
                        }
                    }else{
                        easyuiAlert("提示",'该数据在该时间段无法修改',"info");
                    }
                }).error(function () {
                    easyuiAlert("提示",'系统发生异常，请联系维护人员!',"info");
                })
            },
        });
    },
    reloadTable: function () {
        $("#dlsb-data-table").datagrid("reload");
    },
}
var saveSbData = function(url){
    $("#dlsb-data-table").datagrid('endEdit', groupeditRow);
    var rows = $("#dlsb-data-table").datagrid('getRows');
    console.log(rows);
    var sumrows = addSumColum();
    console.log(sumrows);
    /*var resultrows = rows.concat(sumrows);*/
    var rowstr = JSON.stringify(sumrows);
    $.ajax({
        type : "POST",
        url : url,
        contentType: "application/json",
        data : rowstr,
        dataType : "json",
        success : function(r) {
            if(r.msg=='ok'){
                easyuiAlert("提示",'保存上报成功',"info");
            }else {
                easyuiAlert("提示",r.msg,"info");
            }
            tableJzPure.reloadTable();
        },
        error : function(request) {
            easyuiAlert("提示",'系统发生异常，请联系维护人员!',"warning");
        }
    });
    groupeditRow = undefined;
}

var checkSbDataisSubmit = function (index) {
    var date = $("#sdate").val();
    return $.ajax({
        type : "POST",
        url : getUrl.check_sbdata_date(),
        data :{
            "date": date
        },
        dataType : "json",
        success : function(request) {
            if (request.msg == "ok") {
                flag = true;
            }
        }
    });
}
/**
 * 根据电厂分组添加机组电量合计行数据
 */
var addSumColum = function(){
    $("#dlsb-data-table").datagrid("autoMergeCells", ['sbdcmc']);
    var rows = $('#dlsb-data-table').datagrid('getRows');
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
var isNull = function(obj){
    if(obj == null || typeof obj == 'undefined' || obj == ''){
        return true;
    }
    return false;
}
/**
 * 查询对应日期上报数据
 */
var searchSbData = function(){
    $("#dlsb-data-table").datagrid({
        queryParams: {
            orgid : $("#ownerOrgid").val(),
            date : $("#sdate").val()
        }
    });
}
/*var viewAwgl = function (id) {
    var closeCallBack = {
        cancel: function () {
            tablePure.reloadTable();
            return true;
            //return false 开启该代码可禁止点击该按钮关闭
        }
    };
    openDialog(getUrl.form_view_html(id), "查看运行说明", closeCallBack);
}*/
$(function () {
    $.ajaxSetup({
        cache: false
    });
    tableJzPure.iniTable(getUrl.get_dlsb_dataenum());
});








//添加分组
$.extend($.fn.datagrid.methods, {
    autoMergeCells: function (jq, fields) {
        return jq.each(function () {
            var target = $(this);
            if (!fields) {
                fields = target.datagrid("getColumnFields");
            }
            var rows = target.datagrid("getRows");
            var i = 0,
                j = 0,
                temp = {};
            for (i; i < rows.length; i++) {
                var row = rows[i];
                j = 0;
                for (j; j < fields.length; j++) {
                    var field = fields[j];
                    var tf = temp[field];
                    if (!tf) {
                        tf = temp[field] = {};
                        tf[row[field]] = [i];
                    } else {
                        var tfv = tf[row[field]];
                        if (tfv) {
                            tfv.push(i);
                        } else {
                            tfv = tf[row[field]] = [i];
                        }
                    }
                }
            }
            $.each(temp, function (field, colunm) {
                $.each(colunm, function () {
                    var group = this;

                    if (group.length > 1) {
                        var before,
                            after,
                            megerIndex = group[0];
                        for (var i = 0; i < group.length; i++) {
                            before = group[i];
                            after = group[i + 1];
                            if (after && (after - before) == 1) {
                                continue;
                            }
                            var rowspan = before - megerIndex + 1;
                            if (rowspan > 1) {
                                target.datagrid('mergeCells', {
                                    index: megerIndex,
                                    field: field,
                                    rowspan: rowspan
                                });
                            }
                            if (after && (after - before) != 1) {
                                megerIndex = after;
                            }
                        }
                    }
                });
            });
        });
    }
});
//添加编辑器
$.extend($.fn.datagrid.methods,{
    addEditor : function(jq,param) {
        if(param instanceof Array){
            $.each(param,function(index,item){
                var e = $(jq).datagrid('getColumnOption',item.field);
                e.editor = item.editor;
            });
        }else {
            var e = $(jq).datagrid('getColumnOption',param.field);
            e.editor = param.editor;
        }
    },
    removeEditor : function (jq,param) {
        if(param instanceof Array) {
            $.each(param, function(index,item){
                var e = $(jq).datagrid('getColumnOption',item);
                e.editor = {};
            });
        }else {
            var e = $(jq).datagrid('getColumnOption',param);
            e.editor = {};
        }
    }
});
