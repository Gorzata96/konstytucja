package Constitution;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;



/**
 * Created by Gosia on 2016-12-11.
 */
public class Constitution {
    private LinkedList<Article> articles = new LinkedList<>();
    private LinkedList<Chapter> chapters = new LinkedList<>();
    String Line;

    public void readConstitution(String source) {
        Parser parser = new Parser();
        parser.readFile(source);
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
        String res = "Rozdzial " + String.valueOf(n)  + "\n" + chapters.get(n).getCaption() + "\n";
        for(int i = chapters.get(n).getFirstArt(); i<=chapters.get(n).getLastArt(); i++)
            res += returnSectionTitle(i)+returnArt(i);
        return res;
    }

    public class Parser {
        private List<String> lines = new LinkedList<>();

        public void readFile(String source) {
            FileReader fr = null;
            String line = "";

            //otwieranie pliku
            try {
                fr = new FileReader(source);
            } catch (FileNotFoundException e) {
                System.err.print("Nie znaleziono pliku");
                System.exit(1);
            }

            BufferedReader bfr = new BufferedReader(fr);

            try {
                while((Line = bfr.readLine())!=null){
                    if(!Line.equals("©Kancelaria Sejmu") && !Line.equals("2009-11-16") && Line.length()>1)
                        lines.add(Line);
                }

                int preambleLines = preambleHandling();
                contentHandling(preambleLines);
            } catch (IOException e) {
                System.err.print("Error reading file!");
                System.exit(2);
            }
        }


        public int preambleHandling() {
            int count=0;
            articles.add(new Article(0," "));
            chapters.add(new Chapter(0));
            for(String currentLine : lines) {
                if(currentLine.startsWith("Rozdział"))
                    break;
                currentLine = currentLine.trim();
                currentLine = endOfLineCheck(currentLine);
                articles.getLast().updateArtText(currentLine);
                count++;
            }
            return count;
        }

        public  void contentHandling(int startLine) {
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
                    currentLine = nrListCheck(currentLine);
                    currentLine = endOfLineCheck(currentLine);
                    articles.getLast().updateArtText((currentLine));
                }
            }
            chapters.getLast().setLastArt(articles.getLast().getArtNr());
        }

        private String nrListCheck(String line) {
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


}
