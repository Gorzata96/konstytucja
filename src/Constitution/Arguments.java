package constitution;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gosia on 2016-12-11.
 */
public class Arguments {
    private String filePath;
    private List<SingleArg> arguments = new LinkedList<>();
    private Type option;

    public Arguments(String[] args){
        if(args.length < 2) {
            System.err.println("Za mało argumentów.");
            System.exit(1);
        }
        if (args.length > 4) {
            System.err.println("Za dużo argumentów.");
            System.exit(1);
        }

        filePath = args[0];

        for(int i=1; i<args.length; i++) {
            if(Character.isDigit(args[i].charAt(0))){
                if(args[i].contains(":")) {
                    String[] g = args[i].split(":");
                    if(Integer.parseInt(g[0]) > 243 || Integer.parseInt(g[1]) > 243) {
                        System.err.println("Za duży numer artykułu.");
                        System.exit(1);
                    }
                    if(Integer.parseInt(g[1]) < Integer.parseInt(g[0])) {
                        System.err.println("Numer pierwszego artykułu musi być mniejszy od numeru drugiego.");
                        System.exit(1);
                    }
                    option = Type.range;
                    arguments.add(new SingleArg(Integer.parseInt(g[0]), Integer.parseInt(g[1]), option));
                }
                else {
                    if(Integer.parseInt(args[1]) > 243) {
                        System.err.println("Za duży numer artykułu.");
                        System.exit(1);
                    }
                    option = Type.article;
                    arguments.add(new SingleArg(Integer.parseInt(args[1]), option));
                }
            }
            else if (Character.isLetter(args[i].charAt(0))) {
                option = Type.chapter;
                arguments.add(new SingleArg(roman(args[i]), option));
            }
            else {
                System.err.println("Niepoprawny argument.");
                System.exit(1);
            }
        }
    }

    public List<SingleArg> getArg() {
        return arguments;
    }

    public String getFilePath() {
        return filePath;
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
            default: throw new IllegalArgumentException("Niepoprawny argument.");
        }
    }

    protected class SingleArg {
        protected int nr1;
        protected int nr2;
        protected Type type;

        public SingleArg(int nr1, Type type) {
            this.nr1 = nr1;
            this.type = type;
        }

        public SingleArg(int nr1, int nr2, Type type) {
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

        public Type getType() {
            return type;
        }
    }
}