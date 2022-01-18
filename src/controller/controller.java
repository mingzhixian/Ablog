package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.GetArt;

import java.sql.SQLException;

@Controller
public class controller {
    @RequestMapping(value = "GetArt", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String index(@RequestParam("ArtName") String ArtName) throws SQLException {
        return GetArt.GetArt(ArtName).toString();
    }
}
