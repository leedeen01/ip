import java.io.*;
import java.util.HashMap;

public class Mavis {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));        
        pw.println("Hello! I'm Mavis\nWhat can I do for you?");
        pw.flush();
        HashMap <Integer, String> shelf = new HashMap<>();
        HashMap <String, Boolean> doneMap = new HashMap<>();
        while(true) {
            String[] inputs = br.readLine().split(" ");            
            if (inputs[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inputs[0].equals("list")){
                if (shelf.isEmpty()) {
                    pw.println("The shelf is empty.");
                } else {
                    for (int i = 0; i < shelf.size(); i++) {
                        Boolean x = doneMap.get(shelf.get(i));
                        if (x == true) {
                            pw.println((i + 1) + ". "+ "[" + "X" + "] " + shelf.get(i));
                        } else {
                            pw.println((i + 1) + ". "+ "[" + " " + "] " + shelf.get(i));
                        }
                    }
                }
            } else if (inputs[0].equals("mark")) {
                doneMap.put(inputs[1], true);
                pw.println("Nice! I've marked this task as done:\r\n[X] " + inputs[1]);
            } else if (inputs[0].equals("unmark")) {
                doneMap.put(inputs[1], false);
                pw.println("OK, I've marked this task as not done yet:\r\n[ ] " + inputs[1]);
            } else {
                String recombined = String.join(" ", inputs);  // Join the array elements with a space
                shelf.put(shelf.size(), recombined);
                doneMap.put(recombined, false);
                pw.println("added: " + recombined);
            }
            pw.flush();

        }
        pw.flush();
        pw.close();

    }
}
