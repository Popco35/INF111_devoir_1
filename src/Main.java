public class Main {
    public static void main(String[] args) {
        //init les stats du jeu et initializer ses champs a 0
        StatistiquesJeu stats = new StatistiquesJeu();

        int nbLettres;

        boolean finJeu = false;
        while(!finJeu){

            nbLettres = UtilitaireEntreesSorties.lireInt("Entrez un nombre entier entre "
                            +UtilitaireFonctions.nbLettresMin+" et "
                            +UtilitaireFonctions.nbLettresAlphabet+": "
                            ,UtilitaireFonctions.nbLettresMin
                            ,UtilitaireFonctions.nbLettresAlphabet);

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

