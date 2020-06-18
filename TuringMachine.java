import java.io.*;
import java.nio.charset.Charset;

public class TuringMachine {

    public static void main(String[] args) throws NumberFormatException, IOException {

        //File file=new File("");

        if (args.length == 0) {
            System.out.println("No file given\n");
        }
        InputStream fis = TuringMachine.class.getResourceAsStream("C:\\Users\\Sabari\\Downloads\\Compressed\\secureprog-master\\tasks\\task4\\running_ones.tmprog.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        String readLine;
        int headPosition;
        char[] tape;
        String state = "0";
        readLine = br.readLine();
        headPosition = Integer.parseInt(readLine);
        readLine = br.readLine();
        tape = new char[readLine.length()];
        for (int i = 0; i < readLine.length(); i++)
            tape[i] = readLine.charAt(i);
        Count(state,tape,headPosition,readLine, br);

    }

    public static void Count(String state,char[] tape,int headPosition,String readLine, BufferedReader br) throws IOException {
        int ruleCount = 0;
        String[][] rules = new String[100][5];

        while ((readLine = br.readLine()) != null) {
            if (readLine.trim().isEmpty()) continue;

            String[] tokens = readLine.split(" ");
            for (int i = 0; i < 5; i++) {
                rules[ruleCount][i] = tokens[i];
            }
            ruleCount++;
        }
        br.close();
        display(ruleCount,state,tape,headPosition,rules);
    }

    public static void display(int ruleCount,String state,char[] tape,int headPosition,String rules[][]) {
        while (true) {
            for (int i = 0; i < ruleCount; i++) {
                if (rules[i][0].equals(state) && rules[i][1].charAt(0) == tape[headPosition]) {
                    tape[headPosition] = rules[i][2].charAt(0);
                    if (rules[i][3].charAt(0) == 'L') headPosition--;
                    else headPosition++;
                    state = rules[i][4];
                    System.out.println("");
                    for (int j = 0; j < tape.length; j++) {
                        System.out.print(tape[j]);
                    }
                }
            }
        }

    }
}