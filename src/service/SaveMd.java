package service;

import dao.DBtool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class SaveMd {
    public static void SaveMd(String artName, String artText) throws IOException, SQLException, ClassNotFoundException {
        String artUrl = DBtool.getDataPath() + "/Art/" + artName + ".md";
        String comUrl = DBtool.getDataPath() + "/Com/" + artName + ".md";
        String sql = String.format("insert into Art ('ArtName', 'ArtUrl', 'ComUrl')" +
                "values ('%s', '%s', '%s');", artName, artUrl, comUrl);
        String com="- 欢迎来打脸";
        File artfile = new File(artUrl);
        File comFile = new File(comUrl);

        //新建文件
        artfile.getParentFile().mkdirs();
        comFile.getParentFile().mkdirs();

        //写入数据
        FileWriter writer = new FileWriter(artfile);
        FileWriter comWriter= new FileWriter(comFile);
        comWriter.write(com);
        writer.write(artText);
        writer.flush();
        writer.close();

        //添加数据库记录
        DBtool.excute(sql);
    }
}
