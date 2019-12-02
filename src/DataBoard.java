import java.util.*;

public interface DataBoard<E extends Data> {

    /*
        Overview: Tipo modificabile che rappresenta una bacheca di dati ai quali vengono associate categorie, alle quali possono essere associati degli amici.
                  Gli amici possono mettere un like ai dati che hanno associate le categorie alle quali hanno accesso.

        Typical element: <password, {data}, {categories}, {friends}, categoryOfData : d ∈ {data} -> c ∈ {categories}, friendsOfCategories : c ∈ {categories} -> F ⊆ {friends}, likes : d ∈ {data} -> L ⊆ friendsForCategories(categoryForData(d))>
                        dove:
                            - password: una stringa che rappresenta la parola d'ordine con la quale si identifica il proprietario della Databoard;
                            - {data}: insieme dei dati presenti nella Databoard;
                            - {categories}: insieme delle categorie presenti nella Databoard;
                            - {friends}: insieme di amici che ha accesso alla Databoard;
                            - categoryForData: funzione che associa una categoria di {categories} ad ogni dato di {data};
                            - friendsForCategories: funzione che associa un'insieme di amici (che è sottinsieme di {friends}) ad ogni categoria di {categories}. Corrisponde all'insieme di amici che hanno accesso ad una particolare categoria;
                            - likes: funzione che associa ad ogni dato di {data} un insieme di amici, il quale è sottinsieme degli amici associati alla categoria del dato. Corrisponde all'insieme di amici che hanno messo like ad un particolare dato.
     */

    public void createCategory(String category, String passw) throws WrongPassException;
    /*
        REQUIRES: -category != null;
                  -passw != null;
                  -password corretta;
                  -category non appartiene alle categorie già inserite.
        MODIFIES: this
        EXCEPTION: -NullPointerException (unchecked): se category = null o se passw = null;
                   -WrongPassException (checked): se passw non corrisponde alla password reale;
                   -IllegalArgumentException (unchecked): se category è già stata inserita nell'insieme delle categorie.
        EFFECTS: Aggiunge category dall'insieme delle categorie.
     */

    public void removeCategory(String category, String passw) throws WrongPassException, EmptyListException;
    /*
        REQUIRES: -category != null;
                  -passw != null;
                  -password corretta;
                  -category appartiene alle categorie già inserite;
                  -non ci sono dati associati alla categoria.
        MODIFIES: this
        EXCEPTION: -NullPointerException (unchecked): se category = null o se passw = null;
                   -WrongPassException (checked): se passw non corrisponde alla password reale;
                   -EmptyListException (checked): se non sono state inserite categorie;
                   -IllegalArgumentException (unchecked): se category non compare nell'insieme delle categorie o se ci sono
                                                          dati associati alla categoria.
        EFFECTS: Rimuove category dall'insieme delle categorie.
     */

    public void addFriend(String category, String passw, String friend) throws WrongPassException, EmptyListException;
    /*
        REQUIRES: -category != null;
                  -passw != null;
                  -friend != null;
                  -password corretta;
                  -category non appartiene alle categorie già inserite;
                  -friend non appartiene agli amici che posso vedere i contenuti di category.
        MODIFIES: this
        EXCEPTION: -NullPointerException (unchecked): se category = null o se passw = null o se friend = null;
                   -WrongPassException (checked): se passw non corrisponde alla password reale;
                   -EmptyListException (checked): se non sono state inserite categorie;
                   -IllegalArgumentException (unchecked): se friend è già tra gli amici che possono accedere ai dati di category
                                                          o se category non compare nell'insieme delle categorie.
        EFFECTS: Aggiunge friend all'insieme degli amici che possono accedere ai contenuti di category.
     */

    public void removeFriend(String category, String passw, String friend) throws WrongPassException, EmptyListException;
    /*
        REQUIRES: -category != null;
                  -passw != null;
                  -friend != null;
                  -password corretta;
                  -category non appartiene alle categorie già inserite;
                  -friend appartiene agli amici che posso vedere i contenuti di category.
        MODIFIES: this
        EXCEPTION: -NullPointerException (unchecked): se category = null o se passw = null o se friend = null;
                   -WrongPassException (checked): se passw non corrisponde alla password reale;
                   -EmptyListException (checked): se non sono state inserite categorie o se non ci sono amici che possono
                                                  accedere ai dati di category;
                   -IllegalArgumentException (unchecked): se friend non compare nell'insieme degli amici che possono accedere
                                                          ai contenuti di category o se category non compare nell'insieme delle categorie.
        EFFECTS: Rimuove friend dall'insieme degli amici che possono accedere ai contenuti di category e rimuove tutti i
                 like che friend aveva messo ai post di category.
     */

    public boolean put(String passw, E dato, String category) throws WrongPassException;
    /*
        REQUIRES: -passw != null;
                  -dato != null;
                  -category != null;
                  -password corretta.
        MODIFIES: this
        EXCEPTION: -NullPointerException (unchecked): se dato = null o se passw = null o se category = null;
                   -WrongPassException (checked): se passw non corrisponde alla password reale.
        EFFECTS: Se category non appartiene all'insieme delle cataegorie la inserisce in quell'insieme. Se dato non è
                 contenuto nell'insieme dei dati lo aggiunge, gli associa category e restituisce true, altrimenti false.
     */

    public E get(String passw, E dato) throws WrongPassException, EmptyListException;
    /*
        REQUIRES: -dato != null;
                  -passw != null;
                  -password corretta;
                  -insieme dei dati non vuoto.
        EXCEPTION: -NullPointerException (unchecked): se category = null o se passw = null;
                   -WrongPassException (checked): se passw non corrisponde alla password reale;
                   -EmptyListException (checked): se non sono stati inseriti dati.
        EFFECTS:  Cerca dato nell'insieme dei dati: se lo trova ne restituisce una copia, altrimenti null.
     */

    public E remove(String passw, E dato) throws WrongPassException, EmptyListException;
    /*
        REQUIRES: -dato != null;
                  -passw != null;
                  -password corretta;
                  -insieme dei dati non vuoto.
        MODIFIES: this
        EXCEPTION: -NullPointerException (unchecked): se category = null o se passw = null;
                   -WrongPassException (checked): se passw non corrisponde alla password reale;
                   -EmptyListException (checked): se non sono stati inseriti dati.
        EFFECTS: Cerca dato nell'insieme dei dati: se lo trova lo rimuove e lo restituisce, altrimenti restituisce null.
     */

    public List<E> getDataCategory(String passw, String category) throws WrongPassException, EmptyListException;
    /*
        REQUIRES: -category != null;
                  -passw != null;
                  -password corretta;
                  -category non appartiene alle categorie già inserite.
        EXCEPTION: -NullPointerException (unchecked): se category = null o se passw = null;
                   -WrongPassException (checked): se passw non corrisponde alla password reale;
                   -EmptyListException (checked): se non sono state inserite categorie;
                   -IllegalArgumentException (unchecked): se category non compare nell'insieme delle categorie.
        EFFECTS: Restituisce una lista contenente tutti i dati ai quali è associata la categoria category.
     */

    void insertLike(String friend, E data) throws EmptyListException;
    /*
        REQUIRES: -friend != null;
                  -data != null;
                  -data appartiene all'insieme dei dati;
                  -friend deve avere i permessi per la categoria del dato data.
        MODIFIES: this
        EXCEPTION: -NullPointerException (unchecked): se friend = null o se data = null;
                   -EmptyListException (checked): se non sono stati inseriti dati;
                   -IllegalArgumentException (unchecked): se data non appartiene all' insieme dei dati o se friend non ha
                                                          i permessi per accedere ai dati.
        EFFECTS: Se non è già presente, inserisce friend nell'insieme degli amici che hanno messo like al dato data.
     */

    public Iterator<E> getIterator(String passw) throws WrongPassException;
    /*
        REQUIRES: -passw != null;
                  -password corretta.
        EXCEPTION: -NullPointerException (unchecked): se passw = null;
                   -WrongPassException (checked): se passw non corrisponde alla password reale;
        EFFECTS: Crea un iteratore sull'insieme dei dati, ordinato per numero di like, e lo restituisce.
     */

    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca condivisi con friend
    public Iterator<E> getFriendIterator(String friend);
    /*
        REQUIRES: -friend != null;
        EXCEPTION: -NullPointerException (unchecked): se friend = null;
        EFFECTS: Ritorna un iteratore sull'insieme dei dati ai quali sono associate categorie alle quali friend ha accesso.
     */

    // … altre operazione da definire a scelta
}

class WrongPassException extends Exception{
    public WrongPassException(){
        super();
    }
    public WrongPassException(String s){
        super(s);
    }
}

class EmptyListException extends Exception{
    public EmptyListException(){
        super();
    }
    public EmptyListException(String s){
        super(s);
    }
}