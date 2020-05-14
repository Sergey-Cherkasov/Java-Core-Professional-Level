package homework.three;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class TaskTwo {

    public static void main(String[] args) throws IOException {
        File file = new File("hw03/task02.tsk");
        file.createNewFile();

        ArrayList<InputStream> listStream = new ArrayList<>();
        listStream.add(new FileInputStream("hw03/task02_01.tsk"));
        listStream.add(new FileInputStream("hw03/task02_02.tsk"));
        listStream.add(new FileInputStream("hw03/task02_03.tsk"));
        listStream.add(new FileInputStream("hw03/task02_04.tsk"));
        listStream.add(new FileInputStream("hw03/task02_05.tsk"));

        SequenceInputStream seqStream = new SequenceInputStream(Collections.enumeration(listStream));

        FileOutputStream fos = new FileOutputStream(file);
        byte[] bytes = new byte[512];
        int x;
        while ((x = seqStream.read(bytes)) != -1) {
            fos.write(x);
        }
        seqStream.close();
        fos.close();

    }

}
