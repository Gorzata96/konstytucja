package Constitution;

/**
 * Created by Gosia on 2016-12-05.
 */
public class Chapter {
    private int firstArt;
    private int lastArt;

    public int getFirstArt() {
        return firstArt;
    }

    public int getLastArt() {
        return lastArt;
    }

    public Chapter(int firstArt){
        this.firstArt = firstArt;
    }

    public void setFirstArt(int firstArt) {
        this.firstArt = firstArt;
    }

    public void setLastArt(int lastArt) {
        this.lastArt = lastArt;
    }
    
    public String getCaption(){
        return caption;
    }

    public void setCaption(){
        this.caption = caption;
    }

}
