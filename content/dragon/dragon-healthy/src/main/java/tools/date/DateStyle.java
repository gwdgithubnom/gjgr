/**
* 文 件 名 :
    * CopyRright (c) 1949-xxxx:
* 文件编号：
* 创 建 人：龚文东
* 日    期：Nov 22, 2015
* 修 改 人：root
* 日   期：
* 修改备注： 
* 描   述：
* 版 本 号：
*/ 
package tools.date;

/**  
 * This class is used for ...  
 * ClassName: DateStyle
 * @Description: TODO
 * @author 龚文东 root
 * @version Nov 22, 2015 11:58:33 AM   
 */
public enum DateStyle {
	YYYYMM("yyyyMM",false),
	YYYYMMDD("yyyMMdd",false),
	YYYY_MM("yyyy-MM", false),  
    YYYY_MM_DD("yyyy-MM-dd", false),  
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm", false),  
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", false),  
      
    YYYY_MM_EN("yyyy/MM", false),  
    YYYY_MM_DD_EN("yyyy/MM/dd", false),  
    YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm", false),  
    YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss", false),  
      
    YYYY_MM_CN("yyyy年MM月", false),  
    YYYY_MM_DD_CN("yyyy年MM月dd日", false),  
    YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm", false),  
    YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss", false),  
      
    HHMM("hhmm",true),
    HHMMSS("hhmmss",true),
    HH_MM("HH:mm", true),  
    HH_MM_SS("HH:mm:ss", true),  
      
    MM_DD("MM-dd", true),  
    MM_DD_HH_MM("MM-dd HH:mm", true),  
    MM_DD_HH_MM_SS("MM-dd HH:mm:ss", true),  
      
    MM_DD_EN("MM/dd", true),  
    MM_DD_HH_MM_EN("MM/dd HH:mm", true),  
    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss", true),  
      
    MM_DD_CN("MM月dd日", true),  
    MM_DD_HH_MM_CN("MM月dd日 HH:mm", true),  
    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss", true);  
      
    private String value;  
      
    private boolean isShowOnly;  
      
    DateStyle(String value, boolean isShowOnly) {  
        this.value = value;  
        this.isShowOnly = isShowOnly;  
    }  
      
    public String getValue() {  
        return value;  
    }  
      
    public boolean isShowOnly() {  
        return isShowOnly;  
    }  
 
}
