package Constitution;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by Gosia on 2016-12-11.
 */
public class Arguments {
    private String FilePath;
    private List<SingleArg> arguments = new LinkedList<>();

    public Arguments(String[] args){
        if(args.length<2) {
            System.err.println("Za mało argumentów");
            System.exit(1);
        }
        if (args.length > 4) {
            System.err.println("Za dużo argumentów");
            System.exit(1);
        }

        FilePath = args[0];
        for(int i=1; i<args.length; i++) {
            if(Character.isDigit(args[i].charAt(0))){
                if(args[i].contains(":")) {
                    String[] g = args[i].split(":");
                    arguments.add(new SingleArg(Integer.parseInt(g[0]), Integer.parseInt(g[1]), 'r'));
                }
                else {
                    arguments.add(new SingleArg(Integer.parseInt(args[1]), 'a'));
                }
            }
            else if (Character.isLetter(args[i].charAt(0))) {
                arguments.add(new SingleArg(roman(args[i]), 'c'));
            }
            else {
                System.err.println("Niepoprawny argument");
                System.exit(1);
            }
        }

    }



    public List<SingleArg> getArg() {
        return arguments;
    }

    public String getFilePath() {
        return FilePath;
    }

    private int roman(String rom) {
        switch(rom) {
            case "I": return 1;
            case "II": return 2;
            case "III": return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            case "VII": return 7;
            case "VIII": return 8;
            case "IX": return 9;
            case "X": return 10;
            case "XI": return 11;
            case "XII": return 12;
            case "XIII": return 13;
            default: throw new IllegalArgumentException("Niepoprawny argument");
        }
    }

    protected class SingleArg {
        protected int nr1;
        protected int nr2;
        protected char type;

        public SingleArg(int nr1, char type) {
            this.nr1 = nr1;
            this.type = type;
        }

        public SingleArg(int nr1, int nr2, char type) {
            this.nr1 = nr1;
            this.nr2 = nr2;
            this.type = type;
        }

        public int getNr1() {
            return nr1;
        }

        public int getNr2() {
            return nr2;
        }

        public char getType() {
            return type;
        }
    }
}
