package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@Controller
public class controller {

    //获取文章Url  --第一版，目前未使用
    @RequestMapping(value = "GetArtUrl", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String GetArtUrl(@RequestParam("ArtName") String ArtName) throws SQLException {
        return GetArt.GetArt(ArtName).toString();
    }


    //根据文章名以及获取参数Url，返回对应md文件
    @RequestMapping(value = "GetArt", method = RequestMethod.GET)
    public void GetMd(@RequestParam("Url") String Url, @RequestParam("ArtName") String ArtName, HttpServletResponse response) throws SQLException, IOException {
        //设置响应头
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(ArtName, StandardCharsets.UTF_8));

        //返回文件流
        InputStream inputStream = GetMd.GetMd(ArtName, Url);
        ServletOutputStream outputStream = response.getOutputStream();
        int len;
        byte[] bytes = new byte[1024];
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        // 关闭资源
        inputStream.close();
        outputStream.close();
    }


    //根据提交的表单生成md文件，并添加数据库
    @RequestMapping(value = "SaveArt", method = RequestMethod.POST)
    public void SaveArt(@RequestParam("ArtName") String artName, @RequestParam("ArtText") String artText, @RequestParam("Type") String type, HttpServletResponse response, HttpServletRequest request) throws IOException,
            SQLException, ClassNotFoundException {
        //区分修改还是新建
        String Url = request.getRequestURI();
        if (Url.contains("?article=")) {
            String title = Url.substring(Url.indexOf("?article=") + "?article=".length());
            DelMd.DelMd(title);
        }
        //保存文件
        SaveMd.SaveMd(artName, artText, type);
        ListToMd.ListToMd();
        //返回信息
        response.getWriter().write("{\"SaveArt\":\"success\"}");
    }


    //返回所有的type类型
    @RequestMapping(value = "GetType", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String GetType() throws SQLException {
        return GetType.GetType();
    }

    @RequestMapping(value = "DelArt", method = RequestMethod.POST)
    public void DelArt(@RequestParam("ArtName") String artName, HttpServletResponse response) throws SQLException,
            IOException, ClassNotFoundException {
        DelMd.DelMd(artName);
        ListToMd.ListToMd();
        //返回信息
        response.getWriter().write("{\"DelArt\":\"success\"}");
    }
}
