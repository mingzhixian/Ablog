package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class controller {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView hello = new ModelAndView("WEB-INF/web/index.jsp");
        hello.addObject("hello", "Ablog");
        return hello;
    }
}
