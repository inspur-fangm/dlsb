package com.dc.dlsb.staticval;

/**
 * @author wanggang
 * @description 电量上报常量配置
 * @date 2019/2/14
 */
public enum DlsbEnum {

     PAGE_DLSB_INDEX("dlsb/dlsbindex","电量上报index页面"),
     PAGE_DLSB_SEARCHINDEX("dlsb/dlsbsearchindex","电量上报searchindex页面"),
     PAGE_DLSB_SEARCH_INDEX("dlsb/search/index","电量上报查看index页面"),
     PAGE_DLSB_EDIT_PAGE("dlsb/edit/editpage","电量上报中调修改数据页面"),

     PAGE_DLSB_DC_CONFIG_INDEX("dlsb/dc/dcconfigindex","电量上报电厂配置页面");
     private final String key;
     private final String value;
     DlsbEnum(String key,String value){
          this.key=key;
          this.value=value;
     }

     public static DlsbEnum getEnumByKey(String key) {
          if (null == key) {
               return null;
          }
          for (DlsbEnum temp : values()) {
               if (temp.getKey().equals(key)) {
                    return temp;
               }
          }
          return null;
     }
     public String getKey() {
          return this.key;
     }

     public String getValue() {
          return this.value;
     }
}
