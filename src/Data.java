public interface Data extends Cloneable {

    //Overview: Tipo non modificabile che rappresenta un dato.

    //Typical element: <id, title, data>

    public void display();
    //EFFECTS: stampa i valori dei campi di this, ognuno su una riga.

    public String getTitle();
    //EFFECTS: restituisce il titolo del dato.

    public int getId();
    //EFFECTS: restituisce l'id del dato.

    public Data clone();
    //crea una copia di this e la restituisce.
}
