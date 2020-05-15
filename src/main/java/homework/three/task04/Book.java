package homework.three.task04;

import java.io.Serializable;

public class Book implements Serializable {

    int id;
    String name;
    String author;

    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public String getInfo(){
        return String.format("Книга: %sб автор: %s", name, author);
    }

}
