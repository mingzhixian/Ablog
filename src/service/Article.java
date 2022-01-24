package service;

public class Article {
    private String ArtName = "404";
    private String ArtUrl = "404";
    private String ComUrl = "404";
    private String Type = "404";
    private String Date = "404";
    private int Browse = 0;

    public Article() {
    }

    public Article(String artName, String artUrl, String comUrl, String type, String date, int browse) {
        ArtName = artName;
        ArtUrl = artUrl;
        ComUrl = comUrl;
        Type = type;
        Date = date;
        Browse = browse;
    }

    public String getArtName() {
        return ArtName;
    }

    public void setArtName(String artName) {
        ArtName = artName;
    }

    public String getArtUrl() {
        return ArtUrl;
    }

    public void setArtUrl(String artUrl) {
        ArtUrl = artUrl;
    }

    public String getComUrl() {
        return ComUrl;
    }

    public void setComUrl(String comUrl) {
        ComUrl = comUrl;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getBrowse() {
        return Browse;
    }

    public void setBrowse(int browse) {
        Browse = browse;
    }

    @Override
    public String toString() {
        return "{" +
                "\"ArtName\":\"" + ArtName + '\"' +
                ", \"ArtUrl\":\"" + ArtUrl + '\"' +
                ", \"ComUrl\":\"" + ComUrl + '\"' +
                ", \"Type\":\"" + Type + '\"' +
                ", \"Date\":\"" + Date + '\"' +
                ", \"Browse\":\"" + Browse + '\"' +
                '}';
    }
}
