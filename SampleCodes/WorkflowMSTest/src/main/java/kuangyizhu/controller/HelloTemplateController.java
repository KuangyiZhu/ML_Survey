package kuangyizhu.controller;

import jdk.jfr.DataAmount;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@ResponseBody
@RequestMapping(value = "/templateTest")
public class HelloTemplateController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(Model model) {
        //return "templates/hello.html";
        model.addAttribute("target", "Japan");
        return "hello";
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public ModelAndView hello2() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("target", "America");
        mav.setViewName("hello");
        return mav;
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    public ModelAndView hello3(@RequestParam (value = "name", required = false) String name) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("target", (name == null ? "NoName" : name));
        mav.setViewName("hello");
        return mav;
    }

    @RequestMapping("/hello4/{name}")
    public String hello4(@PathVariable("name") String name, Model model) {
        model.addAttribute("target", name);
        return "hello";
    }

    @RequestMapping(value = "/hello5")
    public String hello5(@ModelAttribute SampleForm sampleForm) {
        return "hello5";
    }
    @RequestMapping(value = "/hello6")
    public String hello6() {
        return "hello6";
    }

    @RequestMapping(value = "/hello7")
    public String hello7() {
        return "hello7";
    }

    @RequestMapping(value = "/scmlabel")
    public ModelAndView hello8(@RequestParam(value = "no", required = false) String no) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("target", (no == null ? "12345": no));
        mav.setViewName("hello8");
        return mav;
        
    }

    @Data
    public class SampleForm {
        private String name;
        private Integer age;
    }
}
