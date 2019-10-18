$(function () {
    $.ajaxSetup({
        cache: false
    });
    tablePure.iniTable(getUrl.get_searchindex_data());
});
var tablePure = {
    iniTable: function (url) {
        $("#dlsb-data").datagrid({
            columns: [[
                {
                    field: 'sbdcid', title: '序号', align: 'center',hidden:true
                },
                {
                    field: 'sbdcmc', title: '电厂名称', width: '25%', align: 'center'
                },
                {
                    field: 'dclx', title: '电厂类型', width: '25%', align: 'center'
                },
                {
                    field: 'sbshuju', title: '发电量（万Kwh）', width: '20%', align: 'center',editor: { type: 'numberbox', options: {min:0,precision:2} }
                },
                {
                    field: 'jlsj', title: '记录时间', width: '20%', align: 'center'
                },
                {
                    field: 'cz', title: '操作', width: '10%', align: 'center',formatter:function (value,row,index) {
                        return '<a  href="javascript:void(0)" onclick="editThisData(\''
                            +row.sbdcid+'\')">修改</a>';
                    }
                }
            ]],
            toolbar: [{

            }],
            url: url,
            queryParams:{
                dclx : $("#dclx").val(),
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

            },
            onSelect:function(index, row){//去除选中高亮
                $('#dlsb-data').datagrid('unselectRow',index);
            },
            onDblClickCell: function (index, field, value) {
                /*checkSbDataisSubmit(index).done(function (r) {
                    if (r.msg == "ok"){
                        //双击开启编辑行
                        if (groupeditRow != undefined) {
                            $('#dlsb-data').datagrid("endEdit", groupeditRow);
                            groupeditRow = undefined;
                            $("#dlsb-data").datagrid("autoMergeCells", ['sbdcmc']);
                        }
                        if (groupeditRow == undefined) {
                            $('#dlsb-data').datagrid("beginEdit", index);
                            groupeditRow = index;
                            $("#dlsb-data").datagrid("autoMergeCells", ['sbdcmc']);
                        }
                    }else{
                        easyuiAlert("提示",'该数据在该时间段无法修改',"info");
                    }
                }).error(function () {
                    easyuiAlert("提示",'系统发生异常，请联系维护人员!',"info");
                })*/
            },
        });
    },
    reloadTable: function () {
        $("#dlsb-data").datagrid("reload");
    },
}
var sbDataSearch = function () {
    $("#dlsb-data").datagrid({
        queryParams: {
            dclx : $("#dclx").val(),
            date : $("#sdate").val()
        }
    });
}
var editThisData = function (sbdcid) {
    var date = $("#sdate").val();
    $('#dd').dialog({
        title: '修改',
        width: '60%',
        height: '80%',
        closed: false,
        cache: false,
        href: getUrl.get_editPage_url(sbdcid,date),
        modal: true,
        onClose: function () {
            tablePure.reloadTable();
        }
    });
}
var exportThisExcel = function () {
    var date = $("#sdate").val();
    var dclx = $("#dclx").val();
    var b = new Base64();
    var rows = $("#dlsb-data").datagrid('getRows');
    var rowsjson = JSON.stringify(rows)
    var form="<form id='tmpForm' method='post' target='_blank'><textarea id='content' name='content'>";
    form+=b.encode(rowsjson)+"</textarea></form>";
    $("#opDiv").append(form);
    $("#tmpForm").attr("action",getUrl.get_ExportExcel_url(date,dclx));
    $("#tmpForm").submit();
    $("#tmpForm").remove();
}


