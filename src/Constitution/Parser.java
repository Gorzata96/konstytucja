package constitution;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gosia on 2017-01-27.
 */
public class Parser {
    String Line;
    private List<String> lines = new LinkedList<>();

    public void readFile(String source, LinkedList<Article> articles, LinkedList<Chapter> chapters) {
        FileReader fr = null;
        String line = "";

        //otwieranie pliku
        try {
            fr = new FileReader(source);
        } catch (FileNotFoundException e) {
            System.err.print("Nie znaleziono pliku.");
            System.exit(1);
        }

        BufferedReader bfr = new BufferedReader(fr);

        //pomijamy to, czego nie chcemy, resztę zapisujemy
        try {
            while((Line = bfr.readLine())!=null){
                if(!Line.contains("Kancelaria Sejmu") && !Line.equals("2009-11-16") && Line.length()>1)
                    lines.add(Line);
            }

            int preambleLines = preambleHandling(articles, chapters);
            contentHandling(preambleLines, articles, chapters);
        } catch (IOException e) {
            System.err.print("Błąd odczytu");
            System.exit(2);
        }
    }


    public int preambleHandling(LinkedList<Article> articles, LinkedList<Chapter> chapters) {
        int count=0;
        articles.add(new Article(0," "));
        chapters.add(new Chapter(0));
        for(String currentLine : lines) {
            if(currentLine.startsWith("Rozdzia"))
                break;
            currentLine = currentLine.trim();
            currentLine = endOfLineCheck(currentLine);
            articles.getLast().updateArtText(currentLine);
            count++;
        }
        return count;
    }

    public  void contentHandling(int startLine, LinkedList<Article> articles, LinkedList<Chapter> chapters) {
        String currentLine;
        String sectionTitle = "";
        for(int i = startLine; i<lines.size(); i++) {
            currentLine = lines.get(i);
            if(currentLine.startsWith("Rozdzia")) {
                chapters.getLast().setLastArt(articles.getLast().getArtNr());
                chapters.add(new Chapter(articles.getLast().getArtNr()+1));
            }
            else if(currentLine.startsWith("Art")) {
                articles.add(new Article(Integer.parseInt(currentLine.replaceAll("\\D+", "")), sectionTitle));
                sectionTitle="";
            }
            else if(currentLine.equals(currentLine.toUpperCase())){
                if(lines.get(i-1).startsWith("Rozdzia"))
                    chapters.getLast().setCaption(currentLine);
                else
                    sectionTitle = currentLine + "\n";
            }
            else {
                currentLine = currentLine.trim();
                currentLine = nrListCheck(currentLine, articles);
                currentLine = endOfLineCheck(currentLine);
                articles.getLast().updateArtText((currentLine));
            }
        }
        chapters.getLast().setLastArt(articles.getLast().getArtNr());
    }

    private String nrListCheck(String line, LinkedList<Article> articles) {
        if((line.matches("[0-9]+\\..*") || (line.matches("(\\d+)[)].*"))) && !articles.getLast().getArtText().equals(""))
            line = "\n" +line;
        return line;
    }
    private String endOfLineCheck(String line) {
        if (line.endsWith("-"))
            line = line.substring(0, line.length() - 1);
        else
            line += " ";
        return line;
    }
}