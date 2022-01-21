package service;

import dao.DBtool;

import java.sql.SQLException;
import java.util.List;

public class GetType {
    public static String GetType() throws SQLException {
        List<String> strings = DBtool.GetType();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[\n");
        for (int i = 0; i < strings.size() - 1; i++) {
            String string = strings.get(i);
            stringBuilder.append("{\"type\":\"").append(string).append("\"},");
        }
        stringBuilder.append("{\"type\":\"").append(strings.get(strings.size() - 1)).append("\"}");
        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }
}
