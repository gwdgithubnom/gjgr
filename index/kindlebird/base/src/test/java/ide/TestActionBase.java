package ide; /**
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used for ... ClassName: ide.TestActionBase
 * 
 * @Description: TODO
 * @author 龚文东 root
 * @version Nov 19, 2015 9:55:29 AM
 */
public class TestActionBase {
	private static HandlerMapping handlerMapping;
	private static HandlerAdapter handlerAdapter;
	private static final Log log = LogFactory.getLog(TestActionBase.class);
    protected MockMvc mockMvc;


	
	/**
	 * Description : 带参数构造函数, 初始化模式名,名称和数据源类型
	 * 
	 *            <p>
	 *            Description:
	 *            </p>
	 */
	public TestActionBase() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 读取配置文件
	 */
	@BeforeClass
	public static void setUp() {
		if (handlerMapping == null) {
			String[] configs = { "file:src/main/resources/application-context.xml","file:src/main/resources/spring-servlet.xml"};
			XmlWebApplicationContext context = new XmlWebApplicationContext();
			context.setConfigLocations(configs);
			MockServletContext msc = new MockServletContext();
			context.setServletContext(msc);
			context.refresh();
			msc.setAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
					context);// TODO
			handlerMapping = (HandlerMapping) context
					.getBean(RequestMappingHandlerMapping.class);

			handlerAdapter = (HandlerAdapter) context
					.getBean(context
							.getBeanNamesForType(RequestMappingHandlerAdapter.class)[0]);

			// handlerAdapter = (HandlerAdapter) context.getBean(context
			// .getNamespace());
		}
	}
	@Before
	public void testBefore() {
		log.info("@Before:");
	}

	@After
	public void testAfter() {
		log.info("@After:");
	}

	@AfterClass
	public static void afterTest() {
		log.info("@AfterClass");
	}
	 /** 
     * 执行request请求的action 
     *  
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
    public ModelAndView excuteAction(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        // 这里需要声明request的实际类型，否则会报错  
        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true); 
        
        HandlerExecutionChain chain = handlerMapping.getHandler(request);  
        log.debug("##Debug:*******"+request+response+"chain:"+chain+"HandleMapping:"+handlerMapping+"handleMapping:"+handlerAdapter);
        ModelAndView model = null;  
        try {  
            model = handlerAdapter  
                    .handle(request, response, chain.getHandler());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return model;  
    }  

}
