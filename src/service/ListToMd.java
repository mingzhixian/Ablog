package service;

import dao.DBtool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ListToMd {
    private static HashMap<String, StringBuilder> Types = new HashMap<String, StringBuilder>();

    public static boolean ListToMd() throws SQLException, IOException, ClassNotFoundException {
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
        String Url = Get.GetArtShowUrl() + "?article=" + article.getArtName();
        Types.get(article.getType()).append(String.format(str, article.getArtName(), Url, article.getDate()));
    }

    //新增Type
    private static void AddType(String type) {
        StringBuilder one = new StringBuilder();
        one.append("# ").append(type).append("\n");
        Types.put(type, one);
    }

    private static void ToSaveMd() throws SQLException, IOException, ClassNotFoundException {
        StringBuilder str = new StringBuilder();
        Types.forEach((key, value) -> {
            str.append(value.toString());
        });
        //博客首页欢迎语
        SaveMd.SaveMd("欢迎来到LJ与鸣之弦的博客", str.toString(), "index");
    }
}
