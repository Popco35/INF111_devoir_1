public class Main {
    public static void main(String[] args) {
        //init les stats du jeu
        StatistiquesJeu stats = new StatistiquesJeu();

        int nbLettres; // nb de lettres dans le mot

        boolean finJeu = false; // drapeau signalant la fin du jeu
        while(!finJeu){

            nbLettres = UtilitaireEntreesSorties.lireInt("Entrez un nombre entier entre "
                            +UtilitaireFonctions.NB_LETTRES_MIN +" et "
                            +UtilitaireFonctions.NB_LETTRES_ALPHABET +": "
                            ,UtilitaireFonctions.NB_LETTRES_MIN
                            ,UtilitaireFonctions.NB_LETTRES_ALPHABET);

            if(!UtilitaireEntreesSorties.utilisateurAnnule()){

                Jotto.jouerPartieJotto(nbLettres, stats);
            } else{

                finJeu = true;
            }

        }

        UtilitaireEntreesSorties.afficherStats(stats);
        System.out.println("\nMerci d'avoir jouer a Jotto!\n");

    }
}

