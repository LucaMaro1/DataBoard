import java.util.*;

public class Test {
    public static void main(String[] args){
        FirstDataBoard<Data> db = new FirstDataBoard<Data>("qfrt22LmOnp#5"); // -> testa la prima implementazione
        //SecondDataBoard<Data> db = new SecondDataBoard<Data>("qfrt22LmOnp#5");    // -> testa la seconda implementazione

        //creo dati che mi serviranno
        Data theDarkSideOfTheMoon = new MyData("The Dark Side Of The Moon", "Pink Floyd");
        Data backInBlack = new MyData("Back In Black", "AC/DC");
        Data warAndPeace = new MyData("War And Peace", "Lev Tolstoj");
        Data narnia = new MyData("The Chronicles of Narnia", "Clive Staples Lewis");
        Data schism = new MyData("Schism", "Tool");
        Data rush = new MyData("Rush", "Ron Howard");
        Data orwell1984 = new MyData("1984", "George Orwell");
        Data cormenAlgo = new MyData("Introduction to Algorithms", "Thomas H. Cormen");
        Data dijkstra = new MyData("Dijkstra's algorithm", "Edsger Wybe Dijkstra");
        Data endgame = new MyData("Avengers: Endgame", "Anthony & Joe Russo");
        Data facebook = new MyData("Facebook", "Mark Zuckerberg");
        Data starlight = new MyData("Starlight", "Muse");

        //provo a rimuovere un dato prima di averne inseriti
        try {
            db.remove("qfrt22LmOnp#5", warAndPeace);
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //provo a inserire like ad un dato prima di averne inseriti
        try{
            db.insertLike("Giovanni", starlight);
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //provo a rimuovere una categoria prima di averne create
        try {
            db.removeCategory("informatica", "qfrt22LmOnp#5");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //provo ad ottenere i dati di una categoria prima di aver creato categorie
        try{
            db.getDataCategory("qfrt22LmOnp#5","cinema");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //provo a togliere l'accesso ad una categoria per un amico prima di aver creato categorie
        try {
            //l'eccezione viene gestita nello stesso modo per il metodo addFriend
            db.removeFriend("informatica", "qfrt22LmOnp#5", "Andrea");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //provo a togliere l'accesso ad una categoria (a cui nessuno ha accesso) per un amico
        try {
            //creo la categoria e provo a cercare di nuovo l'amico
            db.createCategory("informatica", "qfrt22LmOnp#5");
            db.removeFriend("informatica", "qfrt22LmOnp#5", "Andrea");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        try{
            //in questo blocco NON verranno sollevate eccezioni

            //creo altre categorie
            db.createCategory("musica", "qfrt22LmOnp#5");
            db.createCategory("cinema", "qfrt22LmOnp#5");
            db.createCategory("sport", "qfrt22LmOnp#5");
            db.createCategory("filosofia", "qfrt22LmOnp#5");
            db.createCategory("letteratura", "qfrt22LmOnp#5");

            //inserisco amici
            db.addFriend("informatica", "qfrt22LmOnp#5", "Luca");
            db.addFriend("informatica", "qfrt22LmOnp#5", "Mirco");
            db.addFriend("cinema", "qfrt22LmOnp#5", "Giulia");
            db.addFriend("sport", "qfrt22LmOnp#5", "Luca");
            db.addFriend("sport", "qfrt22LmOnp#5", "Davide");
            db.addFriend("sport", "qfrt22LmOnp#5", "Mirco");
            db.addFriend("musica", "qfrt22LmOnp#5", "Luca");
            db.addFriend("musica", "qfrt22LmOnp#5", "Giovanni");
            db.addFriend("cinema", "qfrt22LmOnp#5", "Giorgia");
            db.addFriend("letteratura", "qfrt22LmOnp#5", "Giorgia");
            db.addFriend("letteratura", "qfrt22LmOnp#5", "Maria");
            db.addFriend("letteratura", "qfrt22LmOnp#5", "Giulia");
            db.addFriend("informatica", "qfrt22LmOnp#5", "Maria");

            //inserisco tutti i dati
            db.put("qfrt22LmOnp#5", theDarkSideOfTheMoon, "musica");
            db.put("qfrt22LmOnp#5", backInBlack, "musica");
            db.put("qfrt22LmOnp#5", schism, "musica");
            db.put("qfrt22LmOnp#5", cormenAlgo, "informatica");
            db.put("qfrt22LmOnp#5", dijkstra, "informatica");
            db.put("qfrt22LmOnp#5", rush, "sport");
            db.put("qfrt22LmOnp#5", endgame, "cinema");
            db.put("qfrt22LmOnp#5", warAndPeace, "letteratura");
            db.put("qfrt22LmOnp#5", orwell1984, "letteratura");
            db.put("qfrt22LmOnp#5", narnia, "letteratura");
            db.put("qfrt22LmOnp#5", starlight, "musica");
            //provo ad inserire un dato già esistente
            db.put("qfrt22LmOnp#5", orwell1984, "sport");

            //inserisco i like
            db.insertLike("Luca", theDarkSideOfTheMoon);
            db.insertLike("Giovanni", backInBlack);
            db.insertLike("Giovanni", schism);
            db.insertLike("Luca", schism);
            db.insertLike("Luca", cormenAlgo);
            db.insertLike("Mirco", dijkstra);
            db.insertLike("Davide", rush);
            db.insertLike("Luca", rush);
            db.insertLike("Giorgia", endgame);
            db.insertLike("Giulia", orwell1984);
            db.insertLike("Maria", orwell1984);
            db.insertLike("Giorgia", orwell1984);
            db.insertLike("Giorgia", narnia);
            db.insertLike("Giulia", narnia);
            //provo ad inserire per la seconda volta un like con lo stesso amico allo stesso dato
            db.insertLike("Luca", theDarkSideOfTheMoon);

            //rimuovo un dato
            db.remove("qfrt22LmOnp#5", backInBlack);
            //rimuovo un amico da una categoria e tutti i like che ha messo
            db.removeFriend("informatica", "qfrt22LmOnp#5", "Mirco");

            //mi faccio restituire dei dati e li stampo
            db.get("qfrt22LmOnp#5", theDarkSideOfTheMoon).display();
            db.get("qfrt22LmOnp#5", dijkstra).display();
            //richiedo un dato che non c'è
            if(db.get("qfrt22LmOnp#5", facebook) == null){
                System.out.println("null\n");
            }

            //inserisco un dato e gli associo una categoria non esistente
            db.put("qfrt22LmOnp#5", facebook, "social network");

            //mi faccio restituire tutti i dati ai quali ho associato la categoria musica e li stampo
            List<Data> musicData = new ArrayList<Data>(db.getDataCategory("qfrt22LmOnp#5", "letteratura"));
            for (Data e: musicData) {
                e.display();
            }

            //test degli iteratori
            Iterator<Data> it1 = db.getIterator("qfrt22LmOnp#5");
            /*
                1984                        -> 3 likes
                Schism                      -> 2 likes
                Rush                        -> 2 likes
                Narnia                      -> 2 likes
                The Dark Side of The Moon   -> 1 like
                Cormen                      -> 1 like
                Endgame                     -> 1 like
                Djikstra                    -> 0 likes
                War and Peace               -> 0 likes
                Starlight                   -> 0 likes
                Facebook                    -> 0 likes
             */
            while(it1.hasNext()){
                System.out.println(it1.next().toString());
            }

            Iterator<Data> it2 = db.getFriendIterator("Luca");
            //informatica, musica e sport
            while(it2.hasNext()){
                System.out.println(it2.next().toString());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }


        //TEST ECCEZIONI

        //provo a chiamare un metodo a cui passo un parametro null che non sia il dato
        //l'eccezione viene gestita con lo stesso principio per TUTTI i metodi di FirstDataBoard
        try{
            db.addFriend("letteratura", "qfrt22LmOnp#5", null);
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //provo ad eseguire un metodo passando la password sbagliata
        //ogni metodo che deve gestire il controllo della password lo fa eseguendo le STESSE istruzioni
        try {
            db.createCategory("medicina", "qwerty");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //provo ad aggiungere per la seconda volta la stessa categoria
        try {
            db.createCategory("cinema", "qfrt22LmOnp#5");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //provo a rimuovere delle categorie
        try{
            db.removeCategory("filosofia", "qfrt22LmOnp#5");
            //lancio l'eccezione
            db.removeCategory("sport", "qfrt22LmOnp#5");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //provo a rimuovere una categoria inseistente
        try{
            db.removeCategory("cucina", "qfrt22LmOnp#5");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //aggiungo un amico ad una categoria inseistente
        try{
            db.addFriend("cucina", "qfrt22LmOnp#5", "Bruno");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //provo ad aggiungere lo stesso amico ad una categoria per la seconda volta
        try{
            db.addFriend("informatica", "qfrt22LmOnp#5", "Maria");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //rimuovo un amico da una categoria inesistente
        try{
            db.removeFriend("meccanica", "qfrt22LmOnp#5", "Mirco");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //rimuovo un amico che non ho ancora inserito nella categoria
        try{
            db.removeFriend("informatica", "qfrt22LmOnp#5", "Alberto");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //provo ad inserire un dato nullo
        try{
            db.put("qfrt22LmOnp#5", null, "sport");
        }
        catch (Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //provo a farmi restituire un dato null
        try{
            db.get("qfrt22LmOnp#5", null);
        }
        catch (Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //provo a rimuovere un dato nullo
        try{
            db.remove("qfrt22LmOnp#5", null);
        }
        catch (Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //provo ad ottenere tutti i dati associati ad una categoria inseistente
        try{
            db.getDataCategory("qfrt22LmOnp#5", "storia");
        }
        catch (Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //provo ad inserire like ad un dato nullo
        try{
            db.insertLike("Luca", null);
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //provo ad inserire like ad un dato inesistente
        try{
            db.insertLike("Giorgia", new MyData("temporaneo", "nessuno"));
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //provo ad inserire un like ad un dato di una categoria al quale l'amico non può accedere
        try{
            db.insertLike("Giulia", dijkstra);
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("FINE");
    }
}
