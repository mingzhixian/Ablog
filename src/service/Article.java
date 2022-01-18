package service;

public class Article {
    private String ArtName = "404";
    private String ArtUrl = "404";
    private String ComUrl = "404";

    public Article() {
    }

    public Article(String artName, String artUrl, String comUrl) {
        ArtUrl = artUrl;
        ComUrl = comUrl;
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

    public String getArtName() {
        return ArtName;
    }

    public void setArtName(String artName) {
        ArtName = artName;
    }

    @Override
    public String toString() {
        return "{" +
                "\"ArtName\":\"" + ArtName + '\"' +
                ", \"ArtUrl\":\"" + ArtUrl + '\"' +
                ", \"ComUrl\":\"" + ComUrl + '\"' +
                '}';
    }
}
