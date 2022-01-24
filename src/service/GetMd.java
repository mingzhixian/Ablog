package service;

import dao.DBtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;

public class GetMd {
    public static InputStream GetMd(String ArtName, String Url) throws SQLException, IOException, ClassNotFoundException {
        //获取文件路径
        String filePath = null;
        Article article = GetArt.GetArt(ArtName);
        if (Objects.equals(Url, "ArtUrl")) {
            filePath = article.getArtUrl();

            //增加浏览次数
            DBtool.excute(String.format("update Art set Browse='%s' where ArtName ='%s';", article.getBrowse() + 1, article.getArtName()));
        } else {
            filePath = article.getComUrl();
        }
        //获取文件
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        return inputStream;
    }
}
