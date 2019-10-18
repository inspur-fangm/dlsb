$(function () {



    // /*点击事件，按条件提交参数查询*/
    // obj = {
    //     editRow : undefined,
    //     /*查询*/
    //     search:function () {
    //         $('#box').datagrid('load',{
    //             user : $.trim($('input[name="user"]').val()),
    //             date_from : $('input[name="date_from"]').val(),
    //             date_to : $('input[name="date_to"]').val()
    //         });
    //     },
    //
    //     /*添加  appendRow:末尾追加*/
    //     add : function () {
    //         /*点击添加时显示保存和取消编辑按钮*/
    //         $('#save,#redo').show();
    //         if(this.editRow = undefined){
    //             /*添加一行*/
    //             $('#box').datagrid('insertRow',{
    //                 index : 0, /*添加的索引位置*/
    //                 rows : {
    //                     user : 'user1',
    //                     email : 'www.baidu.com',
    //                     date : '2019-08-05'
    //                 }
    //             });
    //             /*将第一行设置为可编辑状态*/
    //             $('#box').datagrid('beginEdit',0);
    //
    //             /*
    //                 editor ：{
    //                         type : 'text',
    //                         options : {
    //                             required : true,
    //                         },
    //                     },
    //             * */
    //
    //             this.editRow = 0;
    //         }
    //
    //         /*当前行或者页面结尾添加*/
    //         $('#box').datagrid('appendRow', {
    //             user : 'user1',
    //             email : 'www.baidu.com',
    //             date : '2019-08-05'
    //         });
    //     },
    //
    //     /*保存函数*/
    //     save : function () {
    //         /*将第一行设置为结束编辑状态*/
    //         $('#box').datagrid('endEdit',this.editRow);
    //
    //         // /*隐藏*/
    //         // $('#save,#redo').hide();
    //         // /*取消之后改为false状态*/
    //         // this.editRow = false;
    //     },
    //
    //    /*取消编辑函数*/
    //     redo : function () {
    //         /*隐藏*/
    //         $('#save,#redo').hide();
    //         /*取消之后改为false状态*/
    //         this.editRow = undefined;
    //         /*回滚到编辑之前的状态*/
    //         $('#box').datagrid('rejectChanges');
    //     },
    //
    //     /*点击修改按钮执行修改*/
    //     edit : function(){
    //         /*获取选中的行数*/
    //         var rows = $('#box').datagrid('getSelections');
    //
    //         /*当size为1时才可以修改*/
    //         if(rows._size() == 1){
    //
    //             if(this.editRow != undefined){
    //                 $('#box').datagrid('endEdit',this.editRow);
    //             }
    //
    //             if(this.editRow = undefined){
    //                 /*得到当前选中行的索引*/
    //                 var index = $('#box').datagrid('getRowIndex',rows[0]);
    //                 $('#save,#redo').show();
    //                 $('#box').datagrid('beginEdit',index);
    //                 this.editRow = index;
    //                 /*取消当前行*/
    //                 $('#box').datagrid('onselectRow',index);
    //             }
    //         }else {
    //             $.messager.alert('警告','修改只能选中一行！！！','warning');
    //         }
    //
    //     },
    //
    //     /*删除 需要在列中加上一栏id且加上CheckBox：true*/
    //     remove : function(){
    //         /*获取选中的行数*/
    //         var rows = $('#box').datagrid('getSelections');
    //
    //         if(rows._size() > 0){
    //             /*删除操作友情提示*/
    //             $.messager.confirm('确定操作','您确定要删除所选的记录吗，一经删除不可以恢复！！！',function (flag) {
    //                 /*删除*/
    //                 if(flag){
    //                     var ids = [];
    //                     for (var i = 0;i<rows.length;i++){
    //                         ids.push(rows[i].id);
    //                     }
    //                     console.log(ids.join(','));
    //
    //                     /*请求后台删除数据 ajax请求*/
    //                     $.ajax( {
    //                         type : 'POST',
    //                         url : '',
    //                         data : {
    //
    //                         },
    //                         beforeSend : function () {
    //                             /*加载过程*/
    //                             $('#box').datagrid('loading');
    //                         },
    //                         success : function (data) {
    //                             if(data){
    //                                 $('#box').datagrid('loaded');
    //                                 /*刷新删除过后的记录*/
    //                                 $('#box').datagrid('load');
    //                                 /*取消选中的所有行*/
    //                                 $('#box').datagrid('unselectAll');
    //
    //                                 /*右下角提示*/
    //                                 $.messager.show({
    //                                     title : '提示',
    //                                     msg : data +'条记录被删除！！！',
    //                                 });
    //                             }
    //                         }
    //                     });
    //                 }
    //             });
    //
    //         }else{
    //             $.messager.alert('提示','请选中需要删除的记录！！！','info');
    //         }
    //     },
    //
    //
    //     /*双击修改函数 位置在列表函数中（待移动）*/
    //     onDblClickRow : function (rowIndex,rowData) {
    //         if(obj.editRow != undefined){
    //             $('#box').datagrid('endEdit',obj.editRow);
    //         }
    //
    //         /*双击开始修改*/
    //         if(obj.editRow = undefined){
    //             $('#save,#redo').show();
    //             $('#box').datagrid('beginEdit',rowIndex);
    //             obj.editRow = rowIndex;
    //         }
    //
    //     }
    // }

    obj = {
        editRow : undefined,
        /*根据dlldbid查询电厂配置*/
        search : function () {
            $('#box').datagrid('load',{
                dlldbId : $.trim($('input[name="dlldbId"]').val())
            });
        },
        add : function () {
            $('#save,#redo').show();

        }
    }



    /*主要的查询函数*/
    $('#box').datagrid({
        width : '100%',
        height : 600,
        url : base + "/dlsb/findCfgData.do",
        title: '电厂配置列表',
        type : 'POST',
        iconCls : 'icon-search',
        striped : true,
        nowrap : true,
        rownumbers : true,
        fitColumns : true,
        columns : [[
            {
                field : 'id',
                title : '编号',
                sortable : true,
                width : 100,
                checkbox : true,
            },
            {
                field : 'czpxtId',
                title : 'czpxtId',
                sortable : true,
                width : 100,
                /*必填项验证*/
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'dlldbId',
                title : 'dlldbId',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                        validType : 'email',
                    },
                },
            },
            {
                field : 'fhycId',
                title : 'fhycId',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'wdsNewId',
                title : 'wdsNewId',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'scadaId',
                title : 'scadaId',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'omsId',
                title : 'omsId',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'name',
                title : '电厂名称',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'cfgType',
                title : '电厂类型',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'mqNewId',
                title : 'mqNewId',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'px',
                title : 'px',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'isStop',
                title : 'isStop',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'wdsRtuid',
                title : 'wdsRtuid',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'qjnyPowerId',
                title : 'qjnyPowerId',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'omsOrgid',
                title : '部门ID',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'sbOmsOrgid',
                title : '上报部门ID',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'omsDcId',
                title : '电厂ID',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'dlsbPx',
                title : 'dlsbPx',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
            {
                field : 'tjId',
                title : '统计ID',
                sortable : true,
                width : 100,
                editor : {
                    type : 'validatebox',
                    options : {
                        required : true,
                    },
                },
            },
        ]],
        toolbar : '#tb',
        pagination : true,
        pageSize : 10,
        pageList : [10, 20, 30],
        pageNumber : 1,
        sortName : 'dlsbPx',
        sortOrder : 'ASC',
        loadMsg: "正在加载，请稍后......",
        page:true,
        limit:15,
        queryParams: {
            dlldbId: 'dlldbId',
        },
        reloadTable: function () {
            $("#dlsb-data-table").datagrid("reload");
        }
    });

});
