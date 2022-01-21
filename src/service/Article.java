package service;

public class Article {
    private String ArtName = "404";
    private String ArtUrl = "404";
    private String ComUrl = "404";
    private String Type = "404";
    private String Date = "404";

    public Article() {
    }

    public Article(String artName, String artUrl, String comUrl, String type, String date) {
        ArtName = artName;
        ArtUrl = artUrl;
        ComUrl = comUrl;
        this.Type = type;
        this.Date = date;
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
        this.Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "ArtName:'" + ArtName + '\'' +
                ", ArtUrl:'" + ArtUrl + '\'' +
                ", ComUrl:'" + ComUrl + '\'' +
                ", Type:'" + Type + '\'' +
                ", Date:'" + Date + '\'' +
                '}';
    }
}
