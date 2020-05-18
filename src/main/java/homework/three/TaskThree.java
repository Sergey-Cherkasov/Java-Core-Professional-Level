package homework.three;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TaskThree {
    public static void main(String[] args) throws IOException {
        File file = new File("hw03/task03_01.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        byte[] bytes = new byte[1800];
        int offset = 0;
        int lengthBytes = bytes.length;
        int i = 0;
        long time = System.currentTimeMillis();

        while (raf.read(bytes) != -1){
            String text = new String(bytes,0, lengthBytes, StandardCharsets.UTF_8);
            System.out.print(text);
            offset += 1800;
            raf.seek(offset);
            i++;
            bytes = new byte[1800];
//            System.out.println("Для продолжения нажме Enter..." + System.in.read());
        }
        System.out.println(System.currentTimeMillis() - time);
        raf.close();
    }
}
