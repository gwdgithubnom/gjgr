package tools;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;


public class XmlUtil {

	private static Logger log = Logger.getLogger(XmlUtil.class);
	 /** 
    * 将对象直接转换成String类型的 XML输出 
    *  
    * @param obj 
    * @return 
    */  
	
   public static String convertToXml(Object obj) {  
       // 创建输出流  
       StringWriter sw = new StringWriter();  
       try {  
           // 利用jdk中自带的转换类实现  
           JAXBContext context = JAXBContext.newInstance(obj.getClass());  
 
           Marshaller marshaller = context.createMarshaller();  
           // 格式化xml输出的格式  
           marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,  
                   Boolean.TRUE);  
           // 将对象转换成输出流形式的xml  
           marshaller.marshal(obj, sw);  
       } catch (JAXBException e) {  
           e.printStackTrace();  
       }  
       return sw.toString();  
   }  
 
   /** 
    * 将对象根据路径转换成xml文件 
    *  
    * @param obj 
    * @param path 
    * @return 
    */  
   public static void convertToXml(Object obj, String path) {  
   	try {
			// 利用jdk中自带的转换类实现
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			// 格式化xml输出的格式
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
			// 将对象转换成输出流形式的xml
			// 创建输出流
		//	FileWriter fw = null;
			OutputStreamWriter isr = null;
			try {
			//	fw = new FileWriter(path);
				isr = new OutputStreamWriter(new FileOutputStream (path),
						"UTF-8");	
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(XmlUtil.convertToXml(obj));
			marshaller.marshal(obj, isr);
		
		} catch (JAXBException e) {
			e.printStackTrace();
		}
   }  
 
   @SuppressWarnings("unchecked")  
   /** 
    * 将String类型的xml转换成对象 
    */  
   public static Object convertXmlStrToObject(Class clazz, String xmlStr) {  
       Object xmlObject = null;  
       try {  
           JAXBContext context = JAXBContext.newInstance(clazz);  
           // 进行将Xml转成对象的核心接口  
           Unmarshaller unmarshaller = context.createUnmarshaller();  
           StringReader sr = new StringReader(xmlStr);  
           xmlObject = unmarshaller.unmarshal(sr);  
       } catch (JAXBException e) {  
           e.printStackTrace();  
       }  
       return xmlObject;  
   }  
 
   @SuppressWarnings("unchecked")  
   /** 
    * 将file类型的xml转换成对象 
    */  
   public static Object convertXmlFileToObject(Class clazz, String xmlPath) {  
   	Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			FileReader fr = null;
			InputStreamReader isr = null;
			try {
				// fr = new FileReader(xmlPath);
				isr = new InputStreamReader(new FileInputStream(xmlPath),
						"UTF-8");				
		    	System.out.println("convertXmlFileToObject Xml InputStreamReader：");
				System.out.println(isr.toString());
               xmlObject = unmarshaller.unmarshal(isr);
			} catch (UnsupportedEncodingException e) {
			//	log.fatal("the Encode does not exist!" + e.getMessage());
				return null;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//log.fatal("the file does not exist!" + e.getMessage());
				return null;
			}
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	//	System.out.println("this is the content xml正文内容:");
		//System.out.println(XmlUtil.convertToXml(xmlObject));
		return xmlObject;
   }
}
