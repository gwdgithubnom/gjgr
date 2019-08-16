package controller;

import ide.TestActionBase;
import org.junit.Ignore;
import  org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gwd on 2016/4/15.
 */

public class TestController extends TestActionBase{
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @Ignore
    public static void domain(String args[])  {
   
    }


    @Ignore
    public static void testDemo(){
        String json = "{key:test,tableName:{test:a,a:aa},familyName:{aa:aaa,1:2,3:4}}";
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setServletPath("/admin/init");
        // request.getSession().setAttribute("USERID", "201601");
        request.setContentType("application/json");
        request.setMethod("post");
       // ModelAndView mav = this.excuteAction(request, response);
        request.setServletPath("/admin/create/table");

       // logger.debug("OutPut:" + a.toString());
        request.setContent(json.getBytes());
        // request.addParameter("datas", JSONHelper.mapJson(map));
        // request.setAttribute("map", JSONHelper.mapJson(map));

        request.setContentType("application/json");
        request.setMethod("post");
        logger.debug("request entity:*********" + request.toString());
        // request.setAttribute("msg", "测试action成功");
        // request.getSession().setAttribute("USERID", "2015");

    //    mav = this.excuteAction(request, response);
      //  logger.debug("Out:*******" + mav);
    }


    @Test
    public void testIt() throws Exception {
        logger.debug("....");
        String json = "{key:test}";
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setServletPath("/customer/all");
        // request.getSession().setAttribute("USERID", "201601");
        request.setContentType("application/json");
        request.setMethod("post");
        ModelAndView mav = this.excuteAction(request, response);
        // logger.debug("OutPut:" + a.toString());
        if(mav!=null)
        logger.debug(mav.toString());
        else
        logger.error("mav is null");
        request.setContent(json.getBytes());
        // request.addParameter("datas", JSONHelper.mapJson(map));
        // request.setAttribute("map", JSONHelper.mapJson(map));

        request.setContentType("application/json");
        request.setMethod("post");
        logger.debug("request entity:*********" + request.toString());
    }

}
