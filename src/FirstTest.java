import java.util.Iterator;

public class FirstTest {
    public static void main(String[] args){
        FirstDataBoard db1 = new FirstDataBoard("qfrt22LmOnp#5");

        //creo dati che mi serviranno
        Data theDarkSideOfTheMoon = new MyData("The Dark Side Of The Moon", "Pink Floyd");
        Data backInBlack = new MyData("Back In Black", "AC/DC");
        Data warAndPeace = new MyData("War And Peace", "Lev Tolstoj");
        Data schism = new MyData("Schism", "Tool");
        Data rush = new MyData("Rush", "Ron Howard");
        Data orwell1984 = new MyData("1984", "George Orwell");
        Data cormenAlgo = new MyData("Introduction to Algorithms", "Thomas H. Cormen");
        Data dijkstra = new MyData("Dijkstra's algorithm", "Edsger Wybe Dijkstra");
        Data endgame = new MyData("Avengers: Endgame", "Anthony & Joe Russo");
        Data facebook = new MyData("Facebook", "Mark Zuckerberg");

        //provo a rimuovere prima di aver inserito
        try {
            db1.removeCategory("informatica", "qfrt22LmOnp#5");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        try {
            db1.removeFriend("informatica", "qfrt22LmOnp#5", "Andrea");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        try {
            //creo la categoria e provo a cercare di nuovo l'amico
            db1.createCategory("informatica", "qfrt22LmOnp#5");
            db1.removeFriend("informatica", "qfrt22LmOnp#5", "Andrea");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        try {
            db1.remove("qfrt22LmOnp#5", warAndPeace);
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }



        try{
            //in questo blocco NON verranno sollevate eccezioni

            //creo altre categorie
            db1.createCategory("musica", "qfrt22LmOnp#5");
            db1.createCategory("cinema", "qfrt22LmOnp#5");
            db1.createCategory("sport", "qfrt22LmOnp#5");
            db1.createCategory("filosofia", "qfrt22LmOnp#5");
            db1.createCategory("letteratura", "qfrt22LmOnp#5");

            //inserisco amici
            db1.addFriend("informatica", "qfrt22LmOnp#5", "Luca");
            db1.addFriend("informatica", "qfrt22LmOnp#5", "Mirco");
            db1.addFriend("cinema", "qfrt22LmOnp#5", "Giulia");
            db1.addFriend("sport", "qfrt22LmOnp#5", "Luca");
            db1.addFriend("sport", "qfrt22LmOnp#5", "Davide");
            db1.addFriend("musica", "qfrt22LmOnp#5", "Luca");
            db1.addFriend("musica", "qfrt22LmOnp#5", "Giovanni");
            db1.addFriend("cinema", "qfrt22LmOnp#5", "Giorgia");
            db1.addFriend("letteratura", "qfrt22LmOnp#5", "Giorgia");
            db1.addFriend("letteratura", "qfrt22LmOnp#5", "Maria");
            db1.addFriend("letteratura", "qfrt22LmOnp#5", "Giulia");

            //inserisco tutti i dati
            db1.put("qfrt22LmOnp#5", schism, "musica");
            db1.put("qfrt22LmOnp#5", theDarkSideOfTheMoon, "musica");
            db1.put("qfrt22LmOnp#5", backInBlack, "musica");
            db1.put("qfrt22LmOnp#5", cormenAlgo, "informatica");
            db1.put("qfrt22LmOnp#5", dijkstra, "informatica");
            db1.put("qfrt22LmOnp#5", rush, "sport");
            db1.put("qfrt22LmOnp#5", endgame, "cinema");
            db1.put("qfrt22LmOnp#5", warAndPeace, "letteratura");
            db1.put("qfrt22LmOnp#5", orwell1984, "letteratura");
            //provo ad inserire un dato già esistente
            db1.put("qfrt22LmOnp#5", orwell1984, "sport");


            //inserisco i like
            db1.insertLike("Luca", theDarkSideOfTheMoon);
            db1.insertLike("Giovanni", backInBlack);
            db1.insertLike("Giovanni", schism);
            db1.insertLike("Luca", schism);
            db1.insertLike("Luca", cormenAlgo);
            db1.insertLike("Mirco", dijkstra);
            db1.insertLike("Davide", rush);
            db1.insertLike("Luca", rush);
            db1.insertLike("Giorgia", endgame);
            db1.insertLike("Giulia", orwell1984);
            db1.insertLike("Maria", orwell1984);
            db1.insertLike("Giorgia", orwell1984);

            //mi faccio restituire dei dati e li stampo
            db1.get("qfrt22LmOnp#5", theDarkSideOfTheMoon).display();
            db1.get("qfrt22LmOnp#5", dijkstra).display();
            //richiedo un dato che non c'è
            if(db1.get("qfrt22LmOnp#5", facebook) == null){
                System.out.println("null\n");
            }

            //inserisco un dato e gli associo una categoria non esistente
            db1.put("qfrt22LmOnp#5", facebook, "social network");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //rimuovo delle categorie
        try{
            db1.removeCategory("filosofia", "qfrt22LmOnp#5");
            //lancio l'eccezione
            db1.removeCategory("sport", "qfrt22LmOnp#5");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //provo a rimuovere una categoria inseistente
        try{
            db1.removeCategory("cucina", "qfrt22LmOnp#5");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //aggiungo un amico ad una categoria inseistente
        try{
            db1.addFriend("cucina", "qfrt22LmOnp#5", "Bruno");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //provo ad aggiungere lo stesso amico ad una categoria per la seconda volta
        try{
            db1.addFriend("informatica", "qfrt22LmOnp#5", "Mirco");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //rimuovo un amico da una categoria inesistente
        try{
            db1.removeFriend("meccanica", "qfrt22LmOnp#5", "Mirco");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }
        //rimuovo un amico che non ho ancora inserito nella categoria
        try{
            db1.removeFriend("informatica", "qfrt22LmOnp#5", "Alberto");
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

        //FINE TEST: iteratori e getDataCategory !!!!!!


        /*
        try {
            db1.put("qfrt22LmOnp#5", data1, "lusso");
            db1.put("qfrt22LmOnp#5", data2, "sport");
            db1.put("qfrt22LmOnp#5", data3, "informatica");
            db1.put("qfrt22LmOnp#5", data4, "lusso");


            db1.insertLike("Imbruttito", data1);
            db1.insertLike("Luca", data1);
            db1.insertLike("Luca", data4);
            db1.insertLike("Rama", data2);

            Iterator it1 = db1.getIterator("qfrt22LmOnp#5");
            while(it1.hasNext()){
                System.out.println(it1.next().toString());
            }

            Iterator it2 = db1.getFriendIterator("Luca");
            while(it2.hasNext()){
                System.out.println(it2.next().toString());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage() + "\n");
        }

         */
    }
}
