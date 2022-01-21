package service;

import dao.DBtool;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DelMd {
    public static void DelMd(String title) throws SQLException, IOException, ClassNotFoundException {
        new File(DBtool.getDataPath() + "/Art/" + title + ".md").delete();
        new File(DBtool.getDataPath() + "/Com/" + title + ".md").delete();
    }
}
