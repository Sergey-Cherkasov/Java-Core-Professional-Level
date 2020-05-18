package homework.three;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TaskOne {

    public static void main(String[] args) {

        File file = new File("hw03/task01.tsk");
        FileInputStream fis = null;
        InputStreamReader isr = null;
        int x;
        try {
        fis = new FileInputStream(file);
        isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        while ((x = isr.read()) > -1){
            System.out.print((char) x);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
