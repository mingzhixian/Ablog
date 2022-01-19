package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.GetArt;
import service.GetMd;
import service.SaveMd;

import javax.servlet.ServletOutputStream;
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
    public void SaveArt(@RequestParam("ArtName") String artName, @RequestParam("ArtText") String artText, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        //保存文件
        SaveMd.SaveMd(artName, artText);
        //返回信息
        response.getWriter().write("{\"SaveMd\":\"success\"}");
    }

}
