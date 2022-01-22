package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;

public class GetMd {
    public static InputStream GetMd(String ArtName, String Url) throws SQLException, FileNotFoundException {
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
        return inputStream;
    }
}
