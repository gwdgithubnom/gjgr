/**
 * 文 件 名 :
 * CopyRright (c) 1949-xxxx:
 * 文件编号：
 * 创 建 人：龚文东
 * 日    期：Nov 19, 2015
 * 修 改 人：root
 * 日   期：
 * 修改备注： 
 * 描   述：
 * 版 本 号：
 */
package tools;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * This class is used for ... ClassName: JSONHelper
 * 
 * @Description: TODO
 * @author 龚文东 root
 * @version Nov 19, 2015 8:16:14 AM
 */
public class JSONTool {

	/**
	 * Description : 带参数构造函数, 初始化模式名,名称和数据源类型
	 * 
	 * @params schema
	 *            ： 模式名
	 * @params name
	 *            ： 名称
	 * @params type
	 *            ： 数据源类型
	 *            <p>
	 *            Description:
	 *            </p>
	 */
	public JSONTool() {
		// TODO Auto-generated constructor stub
	}
	
	private static Gson gson = null;  
    
    static {  
        if (gson == null) {  
            gson = new Gson();  
        }  
    }  
	
	/** 
     * 对象转换成json字符串 
     * @param obj  
     * @return  
     */  
    public static String toJson(Object obj) {  
        gson = new Gson();  
        return gson.toJson(obj);  
    }  
  
    /** 
     * json字符串转成对象 
     * @param str   
     * @param type 
     * @return  
     */  
    public static <T> T fromJson(String str, Type type) {  
        gson = new Gson();  
        return gson.fromJson(str, type);  
    }  
  
    /** 
     * json字符串转成对象 
     * @param str   
     * @param type  
     * @return  
     */  
    public static <T> T fromJson(String str, Class<T> type) {  
        gson = new Gson();  
        return gson.fromJson(str, type);  
    }  
  
	/**
	 * 
	  * 函 数 名 : to reverse the hbase data to object
	  * 功能描述：
	* 输入参数:   @param str
	* 输入参数:   @return
	* 返 回 值:  - 类型 <说明>  Map<String,HashSet<String>>
	* 异    常：<按照异常名字的字母顺序>
	* 创 建 人:龚文东
	* 日    期:Nov 22, 2015
	* 修 改 人:root
	* 日    期:
	*@throws:@param str
	*@throws:@return
	 */
	public static Map<String,HashSet<String>> JsonStringToMap(String str){
        gson=new Gson(); 
        Map<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
        map=(Map<String, HashSet<String>>) gson.fromJson(str, map.getClass());
        return map;
	}
	
	
	/** 
     * 将对象转换成json格式(并自定义日期格式) 
     *  
     * @param ts 
     * @return 
     */  
    public static String objectToJsonDateSerializer(Object ts,  
            final String dateformat) {  
        String jsonStr = null;  
        gson = new GsonBuilder()  
                .registerTypeHierarchyAdapter(Date.class,  
                        new JsonSerializer<Date>() {  
                            public JsonElement serialize(Date src,  
                                    Type typeOfSrc,  
                                    JsonSerializationContext context) {  
                                SimpleDateFormat format = new SimpleDateFormat(  
                                        dateformat);  
                                return new JsonPrimitive(format.format(src));  
                            }  
                        }).setDateFormat(dateformat).create();  
        if (gson != null) {  
            jsonStr = gson.toJson(ts);  
        }  
        return jsonStr;  
    }  
    /** 
     * 将json格式转换成list对象 
     *  
     * @param jsonStr 
     * @return 
     */  
    public static List<?> jsonToList(String jsonStr) {  
        List<?> objList = null;  
        if (gson != null) {  
            Type type = new com.google.gson.reflect.TypeToken<List<?>>() {
            }.getType();
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }


    public static Set<?> jsonToSet(String jsonStr){
    	Set<?> objSet=null;
    	if (gson != null) {
            Type type = new com.google.gson.reflect.TypeToken<Set<?>>() {
            }.getType();
            objSet = gson.fromJson(jsonStr, type);
        }
        return objSet;
    }


    /**
     * 将json格式转换成list对象，并准确指定类型
     * @param jsonStr
     * @param type
     * @return
     */
    public static List<?> jsonToList(String jsonStr, Type type) {
        List<?> objList = null;
        if (gson != null) {
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }

    /**
     * 将json格式转换成Set对象，并准确指定类型
     * @param jsonStr
     * @param type
     * @return
     */
    public static Set<?> jsonToSet(String jsonStr, Type type) {
    	Set<?> objSet = null;
        if (gson != null) {
            objSet = gson.fromJson(jsonStr, type);
        }
        return objSet;
    }


    /**
     * 将json格式转换成map对象
     *
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr) {
        Map<?, ?> objMap = null;
        if (gson != null) {
            Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
            }.getType();  
            objMap = gson.fromJson(jsonStr, type);  
        }  
        return objMap;  
    }  
  
    /** 
     * 将json转换成bean对象 
     *  
     * @param jsonStr 
     * @return 
     */  
    public static Object jsonToBean(String jsonStr, Class<?> cl) {  
        Object obj = null;  
        if (gson != null) {  
            obj = gson.fromJson(jsonStr, cl);  
        }  
        return obj;  
    }  
  
    /** 
     * 将json转换成bean对象 
     *  
     * @param jsonStr 
     * @param cl 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T jsonToBeanDateSerializer(String jsonStr, Class<T> cl,  
            final String pattern) {  
        Object obj = null;  
        gson = new GsonBuilder()  
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {  
                    public Date deserialize(JsonElement json, Type typeOfT,  
                            JsonDeserializationContext context)  
                            throws JsonParseException {  
                        SimpleDateFormat format = new SimpleDateFormat(pattern);  
                        String dateStr = json.getAsString();  
                        try {  
                            return format.parse(dateStr);  
                        } catch (ParseException e) {  
                            e.printStackTrace();  
                        }  
                        return null;  
                    }  
                }).setDateFormat(pattern).create();  
        if (gson != null) {  
            obj = gson.fromJson(jsonStr, cl);  
        }  
        return (T) obj;  
    }  
  
    /** 
     * 根据 
     *  
     * @param jsonStr 
     * @param key 
     * @return 
     */  
    public static Object getJsonValue(String jsonStr, String key) {  
        Object rulsObj = null;  
        Map<?, ?> rulsMap = jsonToMap(jsonStr);  
        if (rulsMap != null && rulsMap.size() > 0) {  
            rulsObj = rulsMap.get(key);  
        }  
        return rulsObj;  
    }  
    
    
}
