package constitution;

/**
 * Created by Gosia on 2016-12-05.
 */
public class Article {
    protected int artNr;
    protected String artText = "";
    protected String caption = "";

    public Article(int artNr, String pre) {
        this.artNr = artNr;
        this.caption = pre;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getArtNr() {
        return artNr;
    }

    public String getArtText() {
        return artText;
    }

    public void setArtNr(int artNumber) {
        this.artNr = artNumber;
    }

    public void updateArtText(String newArtText) {
        this.artText += newArtText;
    }

}