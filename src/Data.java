public interface Data extends Cloneable {
    public void display();

    public String getTitle();

    public String getAuthor();

    public int getId();

    public Data clone();
}
