public class MyData implements Data {

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
        System.out.println("Id dato: " + this.id);
        System.out.println("titolo: " + this.title);
        System.out.println("autore: " + this.author + "\n");
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
        return "Id dato: " + this.id + "\ntitolo: " + this.title + "\nautore: " + this.author + "\n";
    }

    public Data clone(){
        try {
            return (Data) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
