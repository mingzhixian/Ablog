package service;

import dao.DBtool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveMd {
    public static void SaveMd(String artName, String artText, String type) throws IOException, SQLException,
            ClassNotFoundException {
        //新文章
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String artUrl = DBtool.getDataPath() + "Art/" + artName + ".md";
        String comUrl = DBtool.getDataPath() + "Com/" + artName + ".md";
        Save(artText, artUrl);
        Save("- 欢迎来打脸\n", comUrl);

        //添加数据库记录
        if (!DBtool.IsHave(artName)) {
            String sql = String.format("insert into Art ('ArtName', 'ArtUrl', 'ComUrl','Type','Date','Browse') values" +
                    " ('%s','%s', '%s','%s','%s','%s');", artName, artUrl, comUrl, type, date, "0");
            DBtool.excute(sql);
        }
    }

    public static void Save(String Text, String Url) throws IOException {
        File file = new File(Url);
        file.getParentFile().mkdirs();
        FileWriter Writer = new FileWriter(file);
        Writer.write(Text);
        Writer.flush();
        Writer.close();
    }
}
