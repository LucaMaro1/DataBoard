import java.util.*;

//      SECONDA IMPLEMENTAZIONE

public class SecondDataBoard<E extends Data> implements DataBoard<E> {

    /*
        AF(c) = < c.password, {c.categories.get(i) | 0 <= i < c.categories.size()}, {<c.categories.get(i), {c.friendsForCategories.get(c.categories.get(i)).get(j)}> | 0 <= i < c.friendsForCategories.size() & 0 <= j < c.friendForCategories.get(i).size()},
                  {<c.categories.get(i), {c.dataForCategories.get(c.categories.get(i).get(j)}> | 0 <= i < c.dataForCategories.size() & 0 <= j < c.dataForCategories.get(c.categories.get(i)).size()},
                  for all 0 <= i < c.dataForCategories.size() & for all 0 <= j < c.dataForCategories.get(c.categories.get(i)).size() ==> categoryOfData(c.dataForCategories.get(categories.get(i)).get(j)) = c.categories.get(i),
                  for all 0 <= i < c.friendsForCategories.size() & for all 0 <= j < c.friendsForCategories.get(c.categories.get(i)).size() ==> friendsOfCategories(c.categories.get(i)) = {c.friendForCategories.get(c.categories.get(i)).get(j)},
                  for all 0 <= i < c.friendLikedData.size() & for all 0 <= j < c.friendLikedData.get(categories.get(i)).size() & for all 0 <= k < c.friendLikedData.get(categories.get(i)).get(c.dataForCategories.get(categories.get(i)).get(j)).size() ==>
                  ==> likes(c.dataForCategories.get(categories.get(i)).get(j)) = {c.friendLikedData.get(categories.get(i)).get(c.dataForCategories.get(categories.get(i)).get(j)).get(k)} >

        IR(c) = - c.password != null & c.categories != null & c.friendForCategories != null & c.dataForCategories != null & c.friendLikedData != null;

                - c.categories.size() = c.friendsForCategories.size() = c.dataForCategories.size() = c.friendLikedData.size();

                - for all i. 0 <= i < c.categories.size() ==> (c.categories.get(i) != null & c.friendsForCategories.get(c.categories.get(i)) != null & c.dataForCategories.get(c.categories.get(i)) != null & c.friendLikedData.get(c.categories.get(i)));

                - for all i. 0 <= i < c.categories.size() & for all j. 0 <= j < c.friendsForCategories.get(c.categories.get(i)).size() ==> c.friendsForCategories.get(c.categories.get(i)).get(j) != null;

                - for all i. 0 <= i < c.categories.size() & for all j. 0 <= j < c.dataForCategories.get(c.categories.get(i)).size() ==> c.dataForCategories.get(c.categories.get(i)).get(j) != null;

                - for all i. 0 <= i < c.categories.size() & for all j. 0 <= j < c.friendsLikedData.get(c.categories.get(i)).size() ==> c.friendsLikedData.get(c.categories.get(i)).get(j) != null;

                - for all i. 0 <= i < c.categories.size() & for all j. 0 <= j < c.friendsLikedData.get(c.categories.get(i)).size() & for all k. 0 <= k < c.friendsLikedData.get(categories.get(i)).get(dataForCategories.get(c.categories.get(i)).size() ==>
                  ==> c.friendsLikedData.get(c.category.get(i)).get(c.dataForCategories.get(categories.get(i).get(j)).get(k) != null;

                - c.numOfData = [Sommatoria su i che va da 0 a (c.dataCategories.size() - 1) di: (c.dataCategories.get(categories.get(i)).size())];

                - for all i. 0 <= i < c.categories.size() & for all j. i < j < c.categories.size() ==> (c.categories.get(i).equals(c.categories.get(j)) == false);

                - for all i. 0 <= i < c.categories.size() & for all j. 0 <= j < c.friendsForCategories.get(c.categories.get(i)).size() & for all k. j < k < c.friendsForCategories.get(c.categories.get(i)).size() ==>
                  ==> (c.friendsForCategories.get(categories.get(i)).get(j).equals(c.friendsForCategories.get(categories.get(i)).get(k)) == false);

                - for all i1. 0 <= i1 < c.categories.size() & for all i2. 0 <= i2 < c.categories.size() & for all j. 0 <= j < c.dataForCategories.get(c.categories.get(i1)).size() & for all k. j < k < c.dataForCategories.get(c.categories.get(i2)).size() ==>
                  ==> (c.dataForCategories.get(categories.get(i1)).get(j).equals(c.dataForCategories.get(categories.get(i2)).get(k)) == false);

                - for all i. 0 <= i < c.categories.size() & for all j1. 0 <= j1 < c.friendLikedData.get(categories.get(i)).size() & for all j2. 0 <= j2 < c.friendLikedData.get(categories.get(i)).size() &
                  & for all k1. 0 <= k1 < c.friendLikedData.get(categories.get(i)).get(dataForCategories.get(categories.get(i)).get(j1)).size() & for all k2. k1 < k2 < c.friendLikedData.get(categories.get(i)).get(dataForCategories.get(categories.get(i)).get(j2)).size() ==>
                  ==> (c.friendLikedData.get(categories.get(i)).get(dataForCategories.get(categories.get(i)).get(j1)).get(k1).equals(c.friendLikedData.get(categories.get(i)).get(dataForCategories.get(categories.get(i)).get(j2)).get(k2)) == false);

                - c.friendsForCategories.keySet().equals(c.dataForCategories.keySet()) == true & c.friendsForCategories.keySet().equals(c.friendLikedData.keySet()) == true & c.friendsForCategories.keySet().equals(c.categories) == true;

                - for all i. 0 <= i < c.categories.size() ==> c.friendLikedData.get(i).keySet().equals(c.dataForCategories.get(categories.get(i))) == true;

                - for all i. 0 <= i < c.categories.size() & for all key in c.friendLikedData.get(categories.get(i)). key ∈ c.dataForCategories.get(categories.get(i));

                - for all i. 0 <= i < c.categories.size() & for all j. 0 <= j < c.friendLikedData.get(categories.get(i)).size() & for all k. 0 <= k < c.friendLikedData.get(categories.get(i)).get(c.dataForCategories.get(categories.get(i)).get(j)).size() ==>
                  ==> c.friendLikedData.get(categories.get(i)).get(c.dataForCategories.get(categories.get(i)).get(j)).get(k) ∈ c.friendsForCategories.get(categories.get(i)).
     */

    //variabili di istanza
    private String password;
    int numOfData;
    private List<String> categories;
    private HashMap<String, ArrayList<String>> friendsForCategories;
    private HashMap<String, ArrayList<E>> dataForCategories;
    private HashMap<String, HashMap<E, ArrayList<String>>> friendLikedData;

    public SecondDataBoard(String pass){
        password = pass;
        categories = new ArrayList<String>();
        friendsForCategories = new HashMap<String, ArrayList<String>>();
        dataForCategories = new HashMap<String, ArrayList<E>>();
        friendLikedData = new HashMap<String, HashMap<E, ArrayList<String>>>();
        numOfData = 0;
    }

    @Override
    public void createCategory(String category, String passw) throws WrongPassException {
        System.out.println("Trying to add category " + category + "...");
        if(category == null || passw == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException

        if(friendsForCategories.containsKey(category)){
            //si sta tendando di aggiungere una categoria già esistente
            throw new IllegalArgumentException("Already existent category: " + category + ".");
        }
        //categoria insesistente, posso aggiungerla
        categories.add(category);
        friendsForCategories.put(category, new ArrayList<String>());
        dataForCategories.put(category, new ArrayList<E>());
        friendLikedData.put(category, new HashMap<E, ArrayList<String>>());
        System.out.println("Category " + category + " correctly added.");
        printCategoriesList(categories);
    }

    @Override
    public void removeCategory(String category, String passw) throws WrongPassException, EmptyListException {
        System.out.println("Trying to remove category " + category + "...");
        if(category == null || passw == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(categories.size() == 0)
            throw new EmptyListException("Impossible to remove category " + category + " because there are no categories.");

        if(friendsForCategories.containsKey(category)){
            //controllo se ci sono dati associati alla categoria
            if(dataForCategories.get(category).size() == 0){
                categories.remove(category);
                friendsForCategories.remove(category);
                dataForCategories.remove(category);
                friendLikedData.remove(category);
                System.out.println("Category " + category + " correctly removed.");
                printCategoriesList(categories);
                return;
            }
            else{
                throw new IllegalArgumentException("Impossible to remove category " + category + " because there are some data associated.");
            }
        }
        //categoria inesistente
        throw new IllegalArgumentException("Nonexistent category: " + category + ".");
    }

    @Override
    public void addFriend(String category, String passw, String friend) throws WrongPassException, EmptyListException {
        System.out.println("Trying to add friend " + friend + " for category " + category +"...");
        if(category == null || passw == null || friend == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(categories.size() == 0)
            throw new EmptyListException("Impossible to add friend " + friend + " because there are no categories.");

        if(friendsForCategories.containsKey(category)) {
            for(int i = 0 ; i < friendsForCategories.get(category).size() ; i++){
                if(friend.equals(friendsForCategories.get(category).get(i))){
                    //si sta tendando di aggiungere un amico già esistente
                    throw new IllegalArgumentException("Already existent friend:" + friend + ".");
                }
            }
            //amico inesistente nella categoria scelta, posso aggiungerlo
            friendsForCategories.get(category).add(friend);
            System.out.println("Friend " + friend + " correctly added.");
            printFriendList(friendsForCategories.get(category));
            return;
        }
        //categoria inesistente
        throw new IllegalArgumentException("Nonexistent category: " + category + ".");
    }

    @Override
    public void removeFriend(String category, String passw, String friend) throws WrongPassException, EmptyListException {
        System.out.println("Trying to remove friend " + friend + " for category " + category +"...");
        if(category == null || passw == null || friend == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(categories.size() == 0)
            throw new EmptyListException("Impossible to remove friend " + friend + " because there are no categories.");

        if(friendsForCategories.containsKey(category)) {
            if(friendsForCategories.get(category).size() == 0)
                throw new EmptyListException("Impossible to remove friend " + friend + " because there are no friends for this category.");


            for(int j = 0 ; j < friendsForCategories.get(category).size() ; j++){
                if(friend.equals(friendsForCategories.get(category).get(j))){
                    friendsForCategories.get(category).remove(j);
                    //elimino i like che friend aveva messo ai post di category
                    for(int k = 0 ; k < friendLikedData.get(category).size() ; k++){
                        friendLikedData.get(category).get(dataForCategories.get(category).get(k)).remove(friend);
                    }
                    System.out.println("Friend " + friend + " correctly removed.");
                    printFriendList(friendsForCategories.get(category));
                    return;
                }
            }
            //amico inesistente
            throw new IllegalArgumentException("Nonexistent friend: " + friend + ".");
        }
        //categoria inesistente
        throw new IllegalArgumentException("Nonexistent category: " + category + ".");
    }

    @Override
    public boolean put(String passw, E dato, String category) throws WrongPassException {
        if(dato == null){
            System.out.println("Trying to add data null...");
            throw new NullPointerException();
        }
        System.out.println("Trying to add data " + dato.getTitle() + "...");
        if(passw == null || category == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException

        //controllo che non siano stati inseriti dati uguali a dato
        for(int i = 0 ; i < dataForCategories.size() ; i++){
            for(int j = 0 ; j < dataForCategories.get(categories.get(i)).size() ; j++){
                if(dato.equals(dataForCategories.get(categories.get(i)).get(j))){
                    //dato già esistente, esito negativo
                    System.out.println("Unsuccessful operation, data " + dato.getTitle() + " already existent.\n");
                    return false;
                }
            }
        }

        if(!dataForCategories.containsKey(category)){
            //aggiungo la categoria e lo notifico all'utente
            System.out.println("Nonexistent category: " + category + ", let's add it.\n");
            createCategory(category, passw);
        }
        //ci sono le condizioni per aggiungere il dato
        dataForCategories.get(category).add(dato);
        friendLikedData.get(category).put(dato, new ArrayList<String>());
        numOfData++;
        System.out.println("Data " + dato.getTitle() + " correctly added.");
        //per l'output
        List<Data> dataList = new ArrayList<Data>();
        for(int i = 0 ; i < dataForCategories.size() ; i++){
            dataList.addAll(dataForCategories.get(categories.get(i)));
        }
        printDataList(dataList);
        return true;
    }

    @Override
    public E get(String passw, E dato) throws WrongPassException, EmptyListException {
        if(dato == null){
            System.out.println("Trying to get data null...");
            throw new NullPointerException();
        }
        System.out.println("Trying to get data " + dato.getTitle() + "...");
        if(passw == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(numOfData == 0)
            throw new EmptyListException("Impossible to get the selected data because there are no data.");

        for(int i = 0 ; i < categories.size() ; i++){
            for(int j = 0 ; j < dataForCategories.get(categories.get(i)).size() ; j++){
                if(dato.equals(dataForCategories.get(categories.get(i)).get(j))){
                    E retVal = (E) dataForCategories.get(categories.get(i)).get(j).clone(); //creo una copia
                    System.out.println("Data " + dato.getTitle() + " correctly got.\n");
                    return retVal;
                }
            }
        }
        //dato non trovato, restituisco esito negativo
        System.out.println("Unsuccessful operation, data " + dato.getTitle() + " nonexistent.");
        return null;
    }

    @Override
    public E remove(String passw, E dato) throws WrongPassException, EmptyListException {
        if(dato == null){
            System.out.println("Trying to remove data null...");
            throw new NullPointerException();
        }
        System.out.println("Trying to remove data " + dato.getTitle() + "...");
        if(passw == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(numOfData == 0)
            throw new EmptyListException("Impossible to get the selected data because there are no data.");

        for(int i = 0 ; i < categories.size() ; i++){
            for(int j = 0 ; j < dataForCategories.get(categories.get(i)).size() ; j++){
                if(dato.equals(dataForCategories.get(categories.get(i)).get(j))){
                    E retVal = dataForCategories.get(categories.get(i)).get(j);
                    dataForCategories.get(categories.get(i)).remove(j);
                    friendLikedData.get(categories.get(i)).remove(dato);
                    numOfData--;
                    System.out.println("Data " + dato.getId() + " correctly removed.");
                    //per l'output
                    List<Data> dataList = new ArrayList<Data>();
                    for(int k = 0 ; k < dataForCategories.size() ; k++){
                        dataList.addAll(dataForCategories.get(categories.get(k)));
                    }
                    printDataList(dataList);
                    return retVal;
                }
            }
        }
        //dato non trovato, restituisco esito negativo
        System.out.println("Unsuccessful operation, data " + dato.getTitle() + " nonexistent.");
        return null;
    }

    @Override
    public List<E> getDataCategory(String passw, String category) throws WrongPassException, EmptyListException {
        System.out.println("Trying to get data from category " + category + "...");
        if(passw == null || category == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException
        if(categories.size() == 0)
            throw new EmptyListException("Impossible to get data from category " + category + " because there are no categories.");

        if(dataForCategories.containsKey(category)){
            List<E> retList = new ArrayList<E>();
            for(int i = 0 ; i < dataForCategories.get(category).size() ; i++) {
                //inserisco delle copie per evitare che i dato possano essere modificati
                retList.add((E) dataForCategories.get(category).get(i).clone());
            }
            System.out.println("All data from category " + category + " correctly got.\n");
            return retList;
        }
        //categoria inesistente
        throw new IllegalArgumentException("Nonexistent category: " + category + ".");
    }

    @Override
    public void insertLike(String friend, E data) throws EmptyListException {
        if(data == null){
            System.out.println("Trying to insert like to data null for friend " + friend + "...");
            throw new NullPointerException();
        }
        System.out.println("Trying to insert like to data " + data.getTitle() + " for friend " + friend + "...");
        if(friend == null){
            throw new NullPointerException();
        }
        if(numOfData == 0)
            throw new EmptyListException("Impossible to set like to the selected data because there are no data.");


        for(int i = 0 ; i < friendLikedData.size() ; i++){
            if(friendLikedData.get(categories.get(i)).containsKey(data)){
                //eseguo questo controllo sui like solo se è stato messo almeno un like
                for(int j = 0 ; j < friendLikedData.get(categories.get(i)).get(data).size() ; j++){
                    if(friend.equals(friendLikedData.get(categories.get(i)).get(data).get(j))){
                        //se l'amico ha già messo like non faccio nulla
                        System.out.println("Friend " + friend + " already set like to data " + data.getTitle() + ".\n");
                        return;
                    }
                }
                //controllo se friend può mettere like ai dati di quella categoria
                for(int j = 0 ; j < friendsForCategories.get(categories.get(i)).size() ; j++){
                    if(friend.equals(friendsForCategories.get(categories.get(i)).get(j))){
                        friendLikedData.get(categories.get(i)).get(data).add(friend);
                        System.out.println("Like set correctly.");
                        printFriendList(friendLikedData.get(categories.get(i)).get(data));
                        return;
                    }
                }
                throw new IllegalArgumentException("Friend " + friend + " has no permission for category " + categories.get(i) + ".");
            }
        }
        //dato inesistente
        throw new IllegalArgumentException("Nonexistent data: " + data.getTitle() + ".");
    }

    @Override
    public Iterator<E> getIterator(String passw) throws WrongPassException {
        System.out.println("Trying to get Iterator on data...");
        if(passw == null){
            throw new NullPointerException();
        }
        passwordCheck(passw, this.password); //solleva WrongPassException

        List<E> retData = new ArrayList<E>();
        List<Integer> numOfLikesPerData = new ArrayList<Integer>();
        for(int i = 0 ; i < dataForCategories.size() ; i++){
            retData.addAll(dataForCategories.get(categories.get(i)));
            for(int j = 0 ; j < friendLikedData.get(categories.get(i)).size() ; j++){
                numOfLikesPerData.add(friendLikedData.get(categories.get(i)).get(dataForCategories.get(categories.get(i)).get(j)).size());
            }
        }

        //ordino i dati (IMPLEMENTARE CON MERGESORT!!!)
        for(int i = 0 ; i < retData.size(); i++){
            for(int j = 0 ; j < retData.size() - 1 ; j++){
                //ordina in modo decrescente
                if(numOfLikesPerData.get(j) < numOfLikesPerData.get(j + 1)){
                    E tempData = retData.get(j);
                    Integer tempNum = numOfLikesPerData.get(j);

                    retData.set(j, retData.get(j +1));
                    numOfLikesPerData.set(j , numOfLikesPerData.get(j + 1));

                    retData.set(j + 1, tempData);
                    numOfLikesPerData.set(j + 1, tempNum);
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

        List<String> permittedCategories = new ArrayList<String>();
        for(int i = 0 ; i < friendsForCategories.size() ; i++){
            for(int j = 0 ; j < friendsForCategories.get(categories.get(i)).size() ; j++){
                if(friend.equals(friendsForCategories.get(categories.get(i)).get(j))){
                    permittedCategories.add(categories.get(i));
                }
            }
        }

        int i = 0;
        int j = 0;
        List<E> retList = new ArrayList<E>();
        while(j < permittedCategories.size()){
            if(categories.get(i).equals(permittedCategories.get(j))){
                retList.addAll(dataForCategories.get(categories.get(i)));
                j++;
            }
            i++;
        }

        Iterator<E> it = new MyIterator(retList);
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
