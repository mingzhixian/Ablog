package service;

import dao.DBtool;

import java.io.File;

public class DelMd {
    public static void DelMd(String title) {
        new File(DBtool.getDataPath() + "Art/" + title + ".md").delete();
        new File(DBtool.getDataPath() + "Com/" + title + ".md").delete();
    }
}
