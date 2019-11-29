public class MyData implements Data {

    /*
        AF(c) = <c.id, c.title, c.author>

        IR(c) = -c.id != null & c.title != null & c.author != null
                -c.id > 0;
     */

    private static int createdData = 0;
    private int id;
    private String title;
    private String author;

    public MyData(String title, String author){
        this.id = createdData;
        this.title = title;
        this.author = author;
        createdData++;
    }

    public void display(){
        System.out.println("Id: " + this.id);
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author + "\n");
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + "\nTitle: " + this.title + "\nAuthor: " + this.author + "\n";
    }

    public Data clone(){
        try {
            return (Data) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
