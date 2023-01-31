import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
        //init les stats du jeu et initializer ses champs a 0
        StatistiquesJeu stats = new StatistiquesJeu();
        stats.nbReussite = 0;
        stats.nbParties = 0;
        stats.nbEssaisTotal = 0;
//--------------------------------------------------------------------------------------------------------------------//
        int nbL = 26;
        char[] uselessWord = new char[nbL];
        int ocunter =0;
        long then = System.nanoTime();


        while(ocunter < 1000000){
            uselessWord = UtilitaireFonctions.generateMotShift(nbL);
            ocunter++;
        }
        long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - then);
        System.out.println("GenerateMot Shift (ms): " + millis); // = something around 1000.

        System.out.println(uselessWord);
//--------------------------------------------------------------------------------------------------------------------//
        ocunter =0;
        String word = "qwertyuiopasdfghjklzxcvbnm";
        int nb =0;
        then = System.nanoTime();

        while(ocunter < 1_000_000){
            nb = UtilitaireFonctions.nbDoublons(uselessWord,word);
            ocunter++;
        }
        millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - then);
        System.out.println("nbDoublons (ms): " + millis); // = something around 1000.
        System.out.println(nb);
//--------------------------------------------------------------------------------------------------------------------//
        ocunter =0;
        then = System.nanoTime();

        while(ocunter < 1_000_000){
            uselessWord = UtilitaireFonctions.generateMot(nbL);
            ocunter++;
        }
        millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - then);
        System.out.println("GenerateMot Mixte (ms): " + millis); // = something around 1000.
        System.out.println(uselessWord);
//--------------------------------------------------------------------------------------------------------------------//

        int nbLettres;

        boolean finPartie = false;
        while(!finPartie){
            nbLettres = UtilitaireEntreesSorties.lireInt("Entrez un nombre entier entre 3 et 26: ",3,26);

            if(!UtilitaireEntreesSorties.utilisateurAnnule()){
                Jotto.playJotto(nbLettres, stats);
            } else{
                finPartie = true;
            }

        }




        UtilitaireEntreesSorties.afficherStats(stats);
        System.out.println("\nMerci d'avoir jouer a Jotto!\n");

    }
}