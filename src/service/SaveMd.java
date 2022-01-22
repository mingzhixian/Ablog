package service;

import dao.DBtool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveMd {
    public static void SaveMd(String artName, String artText, String type) throws IOException, SQLException, ClassNotFoundException {
        //新文章
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String artUrl = DBtool.getDataPath() + "Art/" + artName + ".md";
        String comUrl = DBtool.getDataPath() + "Com/" + artName + ".md";
        String sql = String.format("insert into Art ('ArtName', 'ArtUrl', 'ComUrl','Type','Date')" +
                "values ('%s', '%s', '%s','%s','%s');", artName, artUrl, comUrl, type, date);
        File artfile = new File(artUrl);
        File comFile = new File(comUrl);

        //新建文件
        artfile.getParentFile().mkdirs();
        comFile.getParentFile().mkdirs();

        //写入数据
        FileWriter artWriter = new FileWriter(artfile);
        FileWriter comWriter = new FileWriter(comFile);

        //初始评论
        if (!comFile.exists()) {
            comWriter.write("- 欢迎来打脸\n");
        }
        artWriter.write(artText);
        artWriter.flush();
        comWriter.flush();
        artWriter.close();
        comWriter.close();

        //添加数据库记录
        DBtool.excute("delete from Art where ArtName='" + artName + "';");
        DBtool.excute(sql);
    }
}
