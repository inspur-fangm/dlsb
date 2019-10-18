/**
 * @type {{add_awgl_data: (function(): string), form_add_html: (function(): string)}}
 */
var getUrl = {
    get_dlsb_dataenum: function () {
        return base + "/dlsb/getDateEnumBySbOmsOrgid.do";
    },
    get_dlsb_savedata:function () {
        return base + "/dlsb/saveSbData.do";
    },
    check_sbdata_date:function () {
        return base + "/dlsb/checkIsCanSbData.do";
    },
    get_indexurl_dclx:function (dclx) {
        return base + "/dlsb/goIndexByDclx.hmle?dclx="+dclx;
    },

    get_indexurl_dcconfig:function (dcId) {
        return base + "/dlsb/goIndexByDclx.hmle?dclx="+dclx;
    },

    get_searchindex_data:function () {
        return base + "/dlsb/getSbDataByDclx.do";
    },
    get_editPage_url:function (sbdcid,date) {
        return base + "/dlsb/goEditPage.do?sbdcid="+sbdcid+"&date="+date;
    },
    get_ExportExcel_url:function (date,dclx) {
        return base + "/dlsb/exportExcel.do?date="+date+"&dclx="+dclx;
    }
};

/**
 * 消息提示
 * @param title 标题
 * @param content 内容
 * @param level 级别warning;info
 */
var easyuiAlert = function (title, content, level) {
    $.messager.progress("close");
    if (level == undefined) {
        level = "warning";
    }
    $.messager.alert(title, content, level);
};
/**
 *
 *
 *
 *
 *
 *
 * 消息提示
 * @param title 标题
 * @param content 内容
 * @param time 超时
 */
var esayuiShow = function (title, content, time) {
    $.messager.show({
        title: title,
        msg: content,
        timeout: time,
        showType: 'slide'
    });

};
var addMainTab = function (title,links,target) {
    if (links == "") return;
    var html = '<iframe src="' + links + '" frameborder="0" style="width: 100%;height:99%;"></iframe>';
    var isexists = $('#'+target).tabs('exists', title);
    if (isexists) {
        var tab = $('#'+target).tabs('getTab', title);
        $("#"+target).tabs('update', {
            tab: tab,
            options: {
                title: title,
                content: html,
                fit: true,
                closable: true,
                selected: true
            }
        });
        $('#'+target).tabs('select', title);
    } else {
        $('#'+target).tabs('add', {
            title: title,
            content: html,
            closable: true,
            fit: true
        });
    }
}




/**
 * uuid
 */
var getUUID = function () {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
};

var toolTips = {
    /* 显示流程记录 */
    showInfo: function (element) {
        $("#showLcjl").show();
        var uniqueRow = element.siblings().eq(0).text();
        if (uniqueRow.length == 32) {
            $.ajax({
                url: getUrl.bpm_getlcjl(uniqueRow),
                type: "POST",
                dataType: "json",
            }).done(function (res) {
                var list = res.rows;
                $("#lcjl_timeline").html("");
                var lines = "";
                var todo = "";
                var done = "";
                for (var i = 0; i < list.length; i++) {
                    if (list[i].endTime == null) {
                        todo = "<li class=\"layui-timeline-item\">" +
                            "<i class=\"layui-icon layui-timeline-axis\"><span class=\"layui-badge layui-bg-red\">待</span></i>" +
                            "<div class=\"layui-timeline-content layui-text\">" + list[i].activityName + "<br>" +
                            "待办人【" + list[i].assignee + "】" + "<br>开始时间【" + list[i].startTime + "】";
                        "</div>" +
                        "</li>";
                    } else {
                        done = "<li class=\"layui-timeline-item\">" +
                            "<i class=\"layui-icon layui-timeline-axis\"><span class=\"layui-badge layui-bg-green\">已</span></i>" +
                            "<div class=\"layui-timeline-content layui-text\">" + list[i].activityName + "<br>" +
                            "处理人【" + list[i].assignee + "】" + "<br>" + "处理时间【" + list[i].endTime + "】";
                        "</div>" +
                        "</li>";
                    }
                }
                lines += done + todo;

                $("#lcjl_timeline").append(lines);


            });
            $(document).mousemove(function (e) {
                var xPos = parseInt(e.pageX + 12) + "px";
                var yPos = e.pageY + "px";
                $("#showLcjl").css("left", xPos).css("display", "block");
                $("#showLcjl").css("top", yPos);
                $("#showLcjl").css("position", "fixed");
            });
        }
    },
    /* 隐藏流程记录 */
    hideInfo: function () {
        $(document).mousemove(function (e) {
            var xPos = parseInt(e.pageX + 12) + "px";
            var yPos = e.pageY + "px";
            $("#showLcjl").css("left", xPos).css("display", "none");
            $("#showLcjl").css("top", yPos);

        });
    }
};
function doCal(obj, scrpt,no) {
    var aa = eval(scrpt);
    var bb = aa;
    var item_lx = $(obj).attr("item_lx");
    if (!isNull(item_lx)){
        var bb = bb+"%";
    }
    $(obj).text(bb);
    return no;
}

function Sum(list) {
    var s = 0;
    for(var i=0;i<list.length;i++){
        s = s + list[i];
    }
    return s;
}
function abs(list) {
    if (list != null && list.length===1) {
        return list[0];
    }else if (list != null && list.length>1){
        var s = list[0];
        for(var i=1;i<list.length;i++){
            s = s - list[i];
        }
        return Math.abs(s);
    }else{
        return 0;
    }

}
function Float() {
    var s = 0;
    s = arguments[0];
    if (isNaN(s) || !isFinite(s)) {
        s=0;
    }
    var r = s.toFixed(arguments[1]);
    return r;
}
function isNull(obj){
    if(obj == null || typeof obj == 'undefined' || obj == ''){
        return true;
    }
    return false;
}
function calLevel0(){
    var len=$(".autoCal.level0[func!='']").length;
    var no=0;
    $(".autoCal.level0[func!='']").each(function(i,item){
        var cal=null;
        var func=$(item).attr("func");
        if(func.startsWith("sum_col")){
            args=func.split(",");
            formula=sum_col(args[1],args[2]);
            no = doCal($(item),formula,no);
            //no=doCal($(item),formula,no);
        }
        else if(func.startsWith("sum_table_col")){
            args=func.split(",");
            formula=sum_table_col(args[1],args[2],args[3]);
            no = doCal($(item),formula,no);
            //no=doCal($(item),formula,no);
        }
        if(++no==len){
            level0_ready=true;
            calLevel1(1);
        }
    });
}
function calLevel1(level){
    var search=".autoCal.level"+level+"[func!='']";
    var len=$(search).length;
    var no=0;
    $(search).each(function(i,item){
        var cal;
        var func=$(item).attr("func");
        if(func.startsWith("sum_col")){
            args=func.split(",");
            formula=sum_col(args[1],args[2]);
            no = doCal($(item),formula,no);
            //no=doCal($(item),formula,no);
        }
        else if (func.startsWith("Float")) {
            result = func.match(/@{.*?}/g);
            for(var i=0;result!=null&&i<result.length;i++){
                if($(item).attr('id')=='debug'){
                    console.log("debug");
                    debugger;
                }
                var vbName=result[i].replace("@{","").replace("}","");
                if($("#"+vbName).is("input")){
                    var v=$("#"+vbName).val().replace(/(^\s*)|(\s*$)/g,"");
                    if(v=="")v=0;
                    func=func.replaceAll(result[i],v);
                }
                else if($("#"+vbName).is("div")){
                    var v=$("#"+vbName).text().replace(/(^\s*)|(\s*$)/g,"");
                    func=func.replaceAll(result[i],v);
                }
            }
            no = doCal($(item),func,no);
            //no=doCal($(item),func,no);
        }
        else if(func.startsWith("Abs")){
            args=func.split(",");
            formula=sub_col(args[1],args[2]);
            no = doCal($(item),formula,no)
        }
        if(++no==len){
            calLevel1(level+1);
        }

    });
}

function sum_col(field,fix){
    var scrpt="";
    if(fix){
        scrpt="Float(";
    }
    scrpt+="Sum([";
    var hasData=0;
    var fields = field.split("+")
    for(var i=0;fields!=null&&i<fields.length;i++){
        if(fields[i]){
            if($("#"+fields[i]).is("input")){
                if(hasData==1)scrpt+=",";
                if($("#"+fields[i]).val()=="")scrpt+="0"
                else scrpt+=$("#"+fields[i]).val();
                hasData=1;
            }else{
                if(hasData==1)scrpt+=",";
                if($("#"+fields[i]).text()=="")scrpt+="0"
                else scrpt+=$("#"+fields[i]).text();
                hasData=1;
            }
        }
    }
    scrpt+="])";
    if(fix){
        scrpt+=","+fix+")";
    }
    return scrpt;
}
function sum_table_col(divKey,col_num,fix){
    var scrpt="";
    var data=$("#"+divKey+" input[data-name='"+col_num+"']");
    if(fix){
        scrpt="Float(";
    }
    scrpt+="Sum([";
    var hasData=0;
    for(var i=0;data!=null&&i<data.length;i++){
        if(data[i]){
            if(hasData==1)scrpt+=",";
            if($(data[i]).val()=="")scrpt+="0"
            else scrpt+=$(data[i]).val();
            hasData=1;
        }
    }
    scrpt+="])";
    if(fix){
        scrpt+=","+fix+")";
    }
    return scrpt;
}
function sub_col(field,fix){
    var scrpt="";
    if(fix){
        scrpt="Float(";
    }
    scrpt+="abs([";
    var hasData=0;
    var fields = field.split("-")
    for(var i=0;fields!=null&&i<fields.length;i++){
        if(fields[i]){
            if($("#"+fields[i]).is("input")){
                if(hasData==1)scrpt+=",";
                if($("#"+fields[i]).val()=="")scrpt+="0"
                else scrpt+=$("#"+fields[i]).val();
                hasData=1;
            }else{
                if(hasData==1)scrpt+=",";
                if($("#"+fields[i]).text()=="")scrpt+="0"
                else scrpt+=$("#"+fields[i]).text();
                hasData=1;
            }
        }
    }
    scrpt+="])";
    if(fix){
        scrpt+=","+fix+")";
    }
    return scrpt;
}
function valueformatter(value){
    if(value==null||value==undefined){
        return '';
    }else{
        return value;
    }
}

function Base64() {

    // private property
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    // public method for encoding
    this.encode = function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = _utf8_encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
                _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
                _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
        }
        return output;
    }

    // public method for decoding
    this.decode = function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (i < input.length) {
            enc1 = _keyStr.indexOf(input.charAt(i++));
            enc2 = _keyStr.indexOf(input.charAt(i++));
            enc3 = _keyStr.indexOf(input.charAt(i++));
            enc4 = _keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
        }
        output = _utf8_decode(output);
        return output;
    }

    // private method for UTF-8 encoding
    _utf8_encode = function (string) {
        string = string.replace(/\r\n/g, "\n");
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }
        return utftext;
    }

    // private method for UTF-8 decoding
    _utf8_decode = function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while (i < utftext.length) {
            c = utftext.charCodeAt(i);
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if ((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i + 1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i + 1);
                c3 = utftext.charCodeAt(i + 2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
        return string;
    }
}