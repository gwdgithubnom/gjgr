package edu.bigdata.core.controller;

import edu.bigdata.core.service.BillBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gwd on 2016/4/16.
 */
@Controller
@RequestMapping("/billboard")
public class BillboardController {
    @Autowired
    public BillBoardService billBoardService;
    @RequestMapping(value="/all",method= RequestMethod.GET)
    public ModelAndView getBillboards(){
        ModelAndView view=new ModelAndView("/html/billboard");
        view.addObject("model",billBoardService.getBillboards());
        return view;
    }

}
