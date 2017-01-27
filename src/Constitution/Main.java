package constitution;

/**
 * Created by Gosia on 2016-12-11.
 */
public class Main {
    public static void main(String[] args) {
        Arguments arguments = new Arguments(args);
        Constitution constitution = new Constitution();
        constitution.readConstitution(arguments.getFilePath());
        for(Arguments.SingleArg argument : arguments.getArg()) {
            switch(argument.getType()) {
                case article: System.out.print(constitution.returnArt(argument.getNr1()));
                    break;
                case chapter: System.out.print(constitution.returnChapter(argument.getNr1()));
                    break;
                case range:
                    for(int i = argument.getNr1(); i <= argument.getNr2(); i++)
                        System.out.print(constitution.returnArt(i));
                    break;
            }
        }
    }
}