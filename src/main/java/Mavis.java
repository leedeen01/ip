import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Mavis {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));        
        pw.println("Hello! I'm Mavis\nWhat can I do for you?");
        pw.flush();
        ArrayList <String> shelf = new ArrayList<>();
        while(true) {
            String inputs = br.readLine();            
            if (inputs.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inputs.equals("list")){
                if (shelf.isEmpty()) {
                    pw.println("The shelf is empty.");
                } else {
                    for (int i = 0; i < shelf.size(); i++) {
                        pw.println((i + 1) + ". " + shelf.get(i));
                    }
                    pw.flush();
                }
            } else {
                shelf.add(inputs);
                pw.println("added: " + inputs);
                pw.flush();
            }
        }
        pw.flush();
        pw.close();

    }
}
