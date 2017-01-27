package constitution;

import java.util.LinkedList;

/**
 * Created by Gosia on 2016-12-11.
 */
public class Constitution {
    private LinkedList<Article> articles = new LinkedList<>();
    private LinkedList<Chapter> chapters = new LinkedList<>();

    public void readConstitution(String source) {
        Parser parser = new Parser();
        parser.readFile(source, articles, chapters);
    }

    public String returnArt(int n) {
        if (n == 0)
            return articles.get(n).getArtText() + "\n";
        else return ("Art." + String.valueOf(n) + "\n" + articles.get(n).getArtText() + "\n");
    }

    public String returnSectionTitle(int n) {
        return articles.get(n).getCaption();
    }


    public String returnChapter(int n) {
        String res = "Rozdział " + String.valueOf(n)  + "\n" + chapters.get(n).getCaption() + "\n";
        for(int i = chapters.get(n).getFirstArt(); i<=chapters.get(n).getLastArt(); i++)
            res += returnSectionTitle(i)+returnArt(i);
        return res;
    }
}