package edu.bigdata.core.controller;

import edu.bigdata.core.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gwd on 2016/4/15.
 */
@Controller
@RequestMapping(value="/activity")
public class AcitvityController {

    @Autowired
    private ActivityService activityService;
    @RequestMapping(value="/index",method= RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView view=new ModelAndView("/html/test");
        view.addObject("model",activityService.getAcitvityByID(1));
        return view;
    }

}
