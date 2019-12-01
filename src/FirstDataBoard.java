import java.util.*;

//      PRIMA IMPLEMENTAZIONE

public class FirstDataBoard<E extends Data> implements DataBoard<E> {

    /*
        AF(c) = < c.password, {c.data.get(i) | 0 <= i < c.data.size()}, {c.dataCategories.get(i) | 0 <= i < c.dataCategories.size()}, {c.categories.get(i) | 0 <= i < c.categories.size()},
                  {c.friendsForCategories.get(i).get(j) | 0 <= i < c.friendsForCategories.size() & 0 <= j < c.friendsForCategories.get(i).size()},
                  {c.hasFriendPutLike.get(i).get(j) | 0 <= i < c.hasFriendPutLike.size() & 0 <= j < c.hasFriendPutLike.get(i).size()} >

        IR(c) = - c.password != null & c.data != null & c.dataCategories != null & c.categories != null & c.friendsForCategories != null & c.hasFriendPutLike != null;

                - c.data.size() = c.dataCategories.size() = c.hasFriendPutLike.size() & c.categories.size() = c.friendsForCategories.size();

                - for all i. 0 <= i < c.data.size() ==> (c.data.get(i) != null & c.dataCategories.get(i) != null);

                - for all i. 0 <= i < c.categories.size() ==> (c.categories.get(i) != null & c.friendForCategories.get(i) != null);

                - for all i. 0 <= i < c.friendsPutLike.size() & for all j. 0 <= j < c.friendsPutLike.get(i) ==> c.friendsPutLike.get(i).get(j) != null;

                - for all i. 0 <= i < c.hasFriendPutLike.size() & for all j. 0 <= j < c.hasFriendPutLike.get(i).size() ==> (c.hasFriendPutLike.get(i).get(j) != null);

                - for all i. 0 <= i < c.data.size() & for all j. i < j < c.data.size() ==> (c.data.get(i).equals(c.data.get(j)) == false);

                - for all i. 0 <= i < c.categories.size() & for all j. i < j < c.categories.size() ==> (c.categories.get(i).equals(c.categories.get(j)) == false);

                - for all i. 0 <= i < c.friendsForCategories.size() & for all j. 0 <= j < c.friendsForCategories.get(i).size() & for all k. j <= k < c.friendsForCategories.get(i).size() ==>
                  ==> (c.friendsForCategories.get(i).get(j).equals(c.friendsForCategories.get(i).get(k)) == false);

                - for all i. 0 <= i < c.hasFriendPutLike.size() & for all j. 0 <= j < c.hasFriendPutLike.get(i).size() & for all k. j <= k < c.hasFriendPutLike.get(i).size() ==>
                  ==> (c.hasFriendPutLike.get(i).get(j).equals(c.hasFriendPutLike.get(i).get(k)) == false);

                - for all i. 0 <= i < c.dataCategories.size() ==> c.dataCategories.get(i) ∈ c.categories;

                - for all i. 0 <= i < c.hasFriendPutLike.size() & for all j. 0 <= j < c.hasFriendPutLike.get(i).size() ==> ( ∃ k. 0 <= k < c.friendsForCategories.size() ==> c.hasFriendPutLike.get(i).get(j) ∈ c.friendsForCategories.get(k) ).
     */

    //variabili di istanza
    private String password;
    private List<E> data;
    private List<String> dataCategories;
    private List<String> categories;
    private List<ArrayList<String>> friendsForCategories;
    private List<ArrayList<String>> hasFriendPutLike;

    public FirstDataBoard(String pass){
        password = pass;
        data = new ArrayList<E>();
        dataCategories = new ArrayList<String>();
        categories = new ArrayList<String>();
        friendsForCategories = new ArrayList<ArrayList<String>>();
        hasFriendPutLike = new ArrayList<ArrayList<String>>();
    }

    @Override
    public void createCategory(String category, String passw) throws WrongPassException{
        System.out.println("Trying to add category " + category + "...");
        if(category == null || passw == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException

        for(int i = 0 ; i < categories.size() ; i++){
            if(category.equals(categories.get(i))){
                //si sta tendando di aggiungere una categoria già esistente
                throw new IllegalArgumentException("Already existent category: " + category + ".");
            }
        }
        //categoria insesistente, posso aggiungerla
        categories.add(category);
        friendsForCategories.add(new ArrayList<String>());
        System.out.println("Category " + category + " correctly added.");
        printCategoriesList(categories);
    }

    @Override
    public void removeCategory(String category, String passw) throws WrongPassException, EmptyListException{
        System.out.println("Trying to remove category " + category + "...");
        if(category == null || passw == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(categories.size() == 0)
            throw new EmptyListException("Impossible to remove category " + category + " because there are no categories.");

        for(int i = 0 ; i < categories.size() ; i++){
            if(category.equals(categories.get(i))){
                //controllo se ci sono dati associati alla categoria
                boolean find = false;
                for(int j = 0 ; j < dataCategories.size() && !find ; j++){
                    if(dataCategories.get(j).equals(category)){
                        find = true;
                    }
                }

                if(!find){
                    categories.remove(i);
                    friendsForCategories.remove(i);
                    System.out.println("Category " + category + " correctly removed.");
                    printCategoriesList(categories);
                    return;
                }
                else{
                    throw new IllegalArgumentException("Impossible to remove category " + category + " because there are some data associated.");
                }
            }
        }
        //categoria inesistente
        throw new IllegalArgumentException("Nonexistent category: " + category + ".");
    }

    @Override
    public void addFriend(String category, String passw, String friend) throws WrongPassException, EmptyListException{
        System.out.println("Trying to add friend " + friend + " for category " + category +"...");
        if(category == null || passw == null || friend == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(categories.size() == 0)
            throw new EmptyListException("Impossible to add friend " + friend + " because there are no categories.");

        for(int i = 0 ; i < categories.size() ; i++){
            if(category.equals(categories.get(i))){
                for(int j = 0 ; j < friendsForCategories.get(i).size() ; j++){
                    if(friend.equals(friendsForCategories.get(i).get(j))){
                        //si sta tendando di aggiungere un amico già esistente
                        throw new IllegalArgumentException("Already existent friend: " + friend + ".");
                    }
                }
                //amico inesistente nella categoria scelta, posso aggiungerlo
                friendsForCategories.get(i).add(friend);
                System.out.println("Friend " + friend + " correctly added.");
                printFriendList(friendsForCategories.get(i));
                return;
            }
        }
        //categoria inesistente
        throw new IllegalArgumentException("Nonexistent category: " + category + ".");
    }

    @Override
    public void removeFriend(String category, String passw, String friend) throws WrongPassException, EmptyListException{
        System.out.println("Trying to remove friend " + friend + " for category " + category +"...");
        if(category == null || passw == null || friend == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(categories.size() == 0)
            throw new EmptyListException("Impossible to remove friend " + friend + " because there are no categories.");

        for(int i = 0 ; i < categories.size() ; i++){
            if(category.equals(categories.get(i))){
                if(friendsForCategories.get(i).size() == 0)
                    throw new EmptyListException("Impossible to remove friend " + friend + " because there are no friends for this category.");

                for(int j = 0 ; j < friendsForCategories.get(i).size() ; j++){
                    if(friend.equals(friendsForCategories.get(i).get(j))){
                        friendsForCategories.get(i).remove(j);
                        //elimino i like che friend aveva messo ai post di category
                        for(int k = 0 ; k < data.size() ; k++){
                            if(dataCategories.get(k).equals(category)){
                                hasFriendPutLike.get(k).remove(friend);
                            }
                        }
                        System.out.println("Friend " + friend + " correctly removed.");
                        printFriendList(friendsForCategories.get(i));
                        return;
                    }
                }
                //amico inesistente
                throw new IllegalArgumentException("Nonexistent friend: " + friend + ".");
            }
        }
        //categoria inesistente
        throw new IllegalArgumentException("Nonexistent category: " + category + ".");
    }

    @Override
    public boolean put(String passw, E dato, String category) throws WrongPassException{
        if(dato == null){
            System.out.println("Trying to add data null...");
            throw new NullPointerException();
        }
        System.out.println("Trying to add data " + dato.getTitle() + "...");
        if(passw == null || category == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException

        boolean categoryExist = false;
        for(int i = 0 ; i < categories.size() && !categoryExist ; i++){
            if(category.equals(categories.get(i))){
                categoryExist = true;
            }
        }
        if(categoryExist){
            for(int i = 0 ; i < data.size() ; i++){
                if(dato.equals(data.get(i))){
                    //dato già esistente, esito negativo
                    System.out.println("Unsuccessful operation, data " + dato.getTitle() + " already existent.\n");
                    return false;
                }
            }
        }
        else{
            //categoria inesistente, la aggiungo e lo notifico all'utente
            System.out.println("Nonexistent category: " + category + ", let's add it.\n");
            createCategory(category, passw);
        }
        //ci sono le condizioni per aggiungere il dato
        data.add(dato);
        dataCategories.add(category);
        hasFriendPutLike.add(new ArrayList<String>());
        System.out.println("Data " + dato.getTitle() + " correctly added.");
        List<Data> printList = new ArrayList<Data>(data); //per l'output
        printDataList(printList);
        return true;
    }

    @Override
    public E get(String passw, E dato) throws WrongPassException, EmptyListException{
        if(dato == null){
            System.out.println("Trying to get data null...");
            throw new NullPointerException();
        }
        System.out.println("Trying to get data " + dato.getTitle() + "...");
        if(passw == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(data.size() == 0)
            throw new EmptyListException("Impossible to get the selected data because there are no data.");

        for(int i = 0 ; i < data.size() ; i++){
            if(dato.equals(data.get(i))){
                E retData = (E) data.get(i).clone(); //creo una copia
                System.out.println("Data " + dato.getTitle() + " correctly got.\n");
                return retData;
            }
        }
        //dato non trovato, restituisco esito negativo
        System.out.println("Unsuccessful operation, data " + dato.getTitle() + " nonexistent.");
        return null;
    }

    @Override
    public E remove(String passw, E dato) throws WrongPassException, EmptyListException{
        if(dato == null){
            System.out.println("Trying to remove data null...");
            throw new NullPointerException();
        }
        System.out.println("Trying to remove data " + dato.getTitle() + "...");
        if(passw == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(data.size() == 0)
            throw new EmptyListException("Impossible to remove the selected data because there are no data.");

        for(int i = 0 ; i < data.size() ; i++){
            if(dato.equals(data.get(i))){
                //dato trovato, lo rimuovo
                E retVal = data.get(i);
                data.remove(i);
                dataCategories.remove(i);
                hasFriendPutLike.remove(i);
                System.out.println("Data " + dato.getTitle() + " correctly removed.");
                //per l'output
                List<Data> printList = new ArrayList<Data>(data);
                printDataList(printList);
                return retVal;
            }
        }
        //dato non trovato, restituisco esito negativo
        System.out.println("Unsuccessful operation, data " + dato.getTitle() + " nonexistent.");
        return null;
    }

    @Override
    public List<E> getDataCategory(String passw, String category) throws WrongPassException, EmptyListException{
        System.out.println("Trying to get data from category " + category + "...");
        if(passw == null || category == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(categories.size() == 0)
            throw new EmptyListException("Impossible to get data from category " + category + " because there are no categories.");

        boolean categoryExist = false;
        for(int i = 0 ; i < categories.size() && !categoryExist ; i++){
            if(category.equals(categories.get(i))){
                categoryExist = true;
            }
        }
        if(categoryExist){
            List<E> retList = new ArrayList<E>();
            for(int i = 0 ; i < data.size() ; i++){
                if(category.equals(dataCategories.get(i))){
                    //inserisco delle copie per evitare che i dato possano essere modificati
                    retList.add((E) data.get(i).clone());
                }
            }
            System.out.println("All data from category " + category + " correctly got.\n");
            return retList;
        }
        //categoria inesistente
        throw new IllegalArgumentException("Nonexistent category: " + category + ".");
    }

    @Override
    public void insertLike(String friend, E data) throws EmptyListException{
        if(data == null){
            System.out.println("Trying to insert like to data null for friend " + friend + "...");
            throw new NullPointerException();
        }
        System.out.println("Trying to insert like to data " + data.getTitle() + " for friend " + friend + "...");
        if(friend == null){
            throw new NullPointerException();
        }
        if(this.data.size() == 0)
            throw new EmptyListException("Impossible to set like to the selected data because there are no data.");

        boolean dataExist = false;
        int i;
        for(i = 0 ; i < this.data.size() && !dataExist; i++){
            if(data.equals(this.data.get(i))){
                dataExist = true;
                i--;
            }
        }
        if(dataExist){
            int j;
            for(j = 0 ; j < categories.size() ; j++){
                //controllo l'indice sull'array categories della categoria associata a data
                if(categories.get(j).equals(dataCategories.get(i))){
                    //controllo se friend può mettere like ai dati di quella categoria
                    boolean hasPermission = false;
                    for(int k = 0 ; k < friendsForCategories.get(j).size() ; k++){
                        if(friend.equals(friendsForCategories.get(j).get(k))){
                            hasPermission = true;
                        }
                    }
                    if(hasPermission){
                        for(int k = 0 ; k < hasFriendPutLike.get(i).size() ; k++){
                            if(friend.equals(hasFriendPutLike.get(i).get(k))){
                                //se l'amico ha già messo like non faccio nulla
                                System.out.println("Friend " + friend + " already set like to data " + data.getTitle() + ".\n");
                                return;
                            }
                        }
                        hasFriendPutLike.get(i).add(friend);
                        System.out.println("Like set correctly.");
                        printFriendList(hasFriendPutLike.get(i));
                    }
                    else{
                        throw new IllegalArgumentException("Friend " + friend + " has no permission for category " + categories.get(j) + ".");
                    }
                }
            }
        }
        else {
            //dato inesistente
            throw new IllegalArgumentException("Nonexistent data: " + data.getTitle() + ".");
        }
    }

    @Override
    public Iterator<E> getIterator(String passw) throws WrongPassException{
        System.out.println("Trying to get Iterator on data...");
        if(passw == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException

        List<E> retData = new ArrayList<E>();
        List<Integer> likes = new ArrayList<Integer>();
        for(int i = 0 ; i < data.size() ; i++){
            retData.add(data.get(i));
            likes.add(hasFriendPutLike.get(i).size());
        }

        //ordino i dati (IMPLEMENTARE CON MERGESORT!!!)
        for(int i = 0 ; i < retData.size(); i++){
            for(int j = 0 ; j < retData.size() - 1 ; j++){
                //ordina in modo decrescente
                if(likes.get(j) < likes.get(j + 1)){
                    E temp = retData.get(j);
                    Integer tempN = likes.get(j);

                    retData.set(j, retData.get(j + 1));
                    likes.set(j, likes.get(j + 1));

                    retData.set(j + 1, temp);
                    likes.set(j + 1, tempN);
                }
            }
        }

        Iterator<E> it = new MyIterator(retData);
        System.out.println("Iterator on data correctly got.\n");
        return it;
    }

    @Override
    public Iterator<E> getFriendIterator(String friend) {
        System.out.println("Trying to get Iterator on friend " + friend + ".");
        if(friend == null){
            throw new NullPointerException();
        }

        List<E> retList = new ArrayList<E>();
        List<String> categs = new ArrayList<String>();
        for(int i = 0 ; i < friendsForCategories.size() ; i++){
            for(int j = 0 ; j < friendsForCategories.get(i).size() ; j++){
                if(friend.equals(friendsForCategories.get(i).get(j))){
                    categs.add(categories.get(i));
                }
            }
        }
        for(int i = 0 ; i < data.size() ; i++){
            for(int j = 0 ; j < categs.size() ; j++){
                if(dataCategories.get(i).equals(categs.get(j))){
                    retList.add(data.get(i));
                }
            }
        }

        Iterator it = new MyIterator(retList);
        System.out.println("Iterator on friends correctly got.\n");
        return it;
    }


    private static void passwordCheck(String p, String truePass) throws WrongPassException{
        if(!p.equals(truePass))
            throw new WrongPassException("Wrong Password.");
    }

    private static void printFriendList(List<String> list){
        System.out.println(list.toString() + "\n");
    }

    private static void printCategoriesList(List<String> list){
        System.out.println(list.toString() + "\n");
    }

    private static void printDataList (List<Data> list){
        System.out.print('[');
        for(int i = 0 ; i < list.size() - 1 ; i++){
            System.out.print(list.get(i).getTitle() + ", ");
        }
        System.out.println(list.get(list.size() - 1).getTitle() + "]\n");
    }

    private static class MyIterator<E extends Data> implements Iterator {
        //un classico iteratore ma che non supporta il metodo remove

        Iterator<E> it;

        public MyIterator(List<E> list) {
            it = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.it.hasNext();
        }


        @Override
        public Object next() {
            return this.it.next();
        }
    }
}
