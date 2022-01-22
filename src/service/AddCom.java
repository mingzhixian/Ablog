package service;

import dao.DBtool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddCom {
    public static void AddCom(String artName, String comText) throws IOException {
        File comFile = new File(DBtool.getDataPath() + "Com/" + artName + ".md");
        FileWriter comWriter = new FileWriter(comFile, true);
        comWriter.write("- " + comText + "\n");
        comWriter.flush();
        comWriter.close();
    }
}
