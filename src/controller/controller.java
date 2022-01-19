package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.GetArt;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Objects;

@Controller
public class controller {


    //获取文章Url  --第一版，目前未使用
    @RequestMapping(value = "GetArtUrl", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String GetArtUrl(@RequestParam("ArtName") String ArtName) throws SQLException {
        return GetArt.GetArt(ArtName).toString();
    }


    //根据文章名以及获取参数Url，返回对应md文件
    @RequestMapping(value = "GetMd", method = RequestMethod.GET)
    public void GetMd(@RequestParam("Url") String Url, @RequestParam("ArtName") String ArtName, HttpServletResponse response) throws SQLException, IOException {

        //设置响应头
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(ArtName, StandardCharsets.UTF_8));

        //获取文件路径
        String filePath = null;
        if (Objects.equals(Url, "ArtUrl")) {
            filePath = GetArt.GetArt(ArtName).getArtUrl();
        } else {
            filePath = GetArt.GetArt(ArtName).getComUrl();
        }

        //获取文件
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);

        //返回文件流
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

    //接受文本，保存为md文件,存入文件夹（getDataPath()），写入数据库
//    {
//        ArtName:文章名字;
//        ArtText:文章内容
//    }
//    todo：数据库新建数据，保存文章内容为md文件，存入getDataPath()/Art文件夹，在getDataPath()/Com文件夹下新建一个新的空的md文件（存放评论的）。
}
