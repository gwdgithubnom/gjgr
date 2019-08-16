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
 * ClassName: Week
 * @Description: TODO
 * @author 龚文东 root
 * @version Nov 22, 2015 11:59:11 AM   
 */
public enum Week {
	MONDAY("星期一", "Monday", "Mon.", 1),  
    TUESDAY("星期二", "Tuesday", "Tues.", 2),  
    WEDNESDAY("星期三", "Wednesday", "Wed.", 3),  
    THURSDAY("星期四", "Thursday", "Thur.", 4),  
    FRIDAY("星期五", "Friday", "Fri.", 5),  
    SATURDAY("星期六", "Saturday", "Sat.", 6),  
    SUNDAY("星期日", "Sunday", "Sun.", 7);  
      
    String name_cn;  
    String name_en;  
    String name_enShort;  
    int number;  
      
    Week(String name_cn, String name_en, String name_enShort, int number) {  
        this.name_cn = name_cn;  
        this.name_en = name_en;  
        this.name_enShort = name_enShort;  
        this.number = number;  
    }  
      
    public String getChineseName() {  
        return name_cn;  
    }  
  
    public String getName() {  
        return name_en;  
    }  
  
    public String getShortName() {  
        return name_enShort;  
    }  
  
    public int getNumber() {  
        return number;  
    }  
}
