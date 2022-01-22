package service;

import dao.DBtool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ListToMd {
    private static HashMap<String, StringBuilder> Types = new HashMap<>();
    private static HashMap<String, StringBuilder> AblogManager = new HashMap<>();

    public static boolean ListToMd() throws SQLException, IOException, ClassNotFoundException {
        Types.clear();
        AblogManager.clear();
        String sql = "select * from Art;";
        List<Article> articles = DBtool.GetArt(sql);

        for (Article article : articles) {
            if (!Objects.equals(article.getType(), "index")) {
                AddList(article);
            }
        }

        ToSaveMd();

        return true;
    }

    //增加记录
    private static void AddList(Article article) {
        //如果无Type,则新增记录
        if (!Types.containsKey(article.getType())) {
            AddType(article.getType());
        }
        //增加记录
        String str = "- [%s](%s)(最后更新日期：%s)\n";
        String Url = "./article.html?article=" + article.getArtName();
        Types.get(article.getType()).append(String.format(str, article.getArtName(), Url, article.getDate()));
        String Url2 = "./editor.html?article=" + article.getArtName();
        AblogManager.get(article.getType()).append(String.format(str, article.getArtName(), Url2, article.getDate()));
    }

    //新增Type
    private static void AddType(String type) {
        StringBuilder one = new StringBuilder();
        one.append("# ").append(type).append("\n");
        Types.put(type, one);
        StringBuilder two = new StringBuilder();
        two.append("# ").append(type).append("\n");
        AblogManager.put(type, two);
    }

    private static void ToSaveMd() throws SQLException, IOException, ClassNotFoundException {
        StringBuilder str = new StringBuilder();
        Types.forEach((key, value) -> {
            str.append(value.toString());
        });
        //博客首页欢迎语
        SaveMd.SaveMd("欢迎来到LJ与鸣之弦的博客", str.toString(), "index");

        StringBuilder str2 = new StringBuilder();
        AblogManager.forEach((key, value) -> {
            str2.append(value.toString());
        });
        //博客首页欢迎语
        SaveMd.SaveMd("AblogManager", str2.toString(), "index");
    }
}
