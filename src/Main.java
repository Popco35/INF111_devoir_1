
/**
 * Jeu Jotto fait dans le cadre du cours INF111
 *
 * @author Olivier Bachand-Martinez, Loic Higgins, William Desgagne, Kevin Hua
 * @version H2023
 *
 */

public class Main {
    //tableau contenant toutes les lettres de l'alphabet
    public static final char[] TABLEAU_LETTRES = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
            'r','s','t','u','v','w','x','y','z'};
    public static final int NB_LETTRES_ALPHABET = 26;   // nombre de lettres total dans l'alphabet

    public static final int DECALAGE_ASCII = 97;    //decalage entre la valeur ascii de 'a' et 0

    public static final int NB_LETTRES_MIN = 3;     // nombre minimum de lettres dans un mot

    /*le point a partir du quel un nouvel algorithme de generation
    de mot devient plus rapide
    */
    public  static final int POINT_OPTIMISATION_MOT = 15;
    public static void main(String[] args) {
        //init les stats du jeu
        StatistiquesJeu stats = new StatistiquesJeu();

        int nbLettres; // nb de lettres dans le mot

        boolean finJeu = false; // drapeau signalant la fin du jeu
        while(!finJeu){

            nbLettres = UtilitaireEntreesSorties.lireInt("Entrez un nombre entier entre " + NB_LETTRES_MIN +
                    " et " + NB_LETTRES_ALPHABET,NB_LETTRES_MIN,NB_LETTRES_ALPHABET);

            // si l'utilisateur ne quitte pas, jouer la partie. Sinon quitter le jeu
            if(!UtilitaireEntreesSorties.utilisateurAnnule()){

                jouerPartieJotto(nbLettres, stats);
            } else {

                finJeu = true;
            }

        }

        UtilitaireEntreesSorties.afficherStats(stats);
        System.out.println("\nMerci d'avoir jouer a Jotto!\n");

    }

    public static void jouerPartieJotto(int nbLettres, StatistiquesJeu stats){

        char[] mot; // mot genere par le programme
        String motUtilisateur;  // mot entre par le joueur


        boolean finPartie = false;  //drapeau signalant la fin de la partie

        while(!finPartie){

            mot = genererMot(nbLettres);

            boolean motEstDevine = false;   //drapeau signalant que le mot a ete devine
            boolean quitterPartie = false;  // drapeau signalant la demande d'abandon du joueur

            while(!motEstDevine && !quitterPartie){

                int nbDoublons; //nb de lettres identiques entre deux mots
                motUtilisateur = demanderMot(nbLettres);

                // si le joueur n'abandonne pas, analyser son mot. Sinon, abandonner cette partie
                if(!UtilitaireEntreesSorties.utilisateurAnnule()) {

                    nbDoublons = nbDoublons(mot, motUtilisateur);

                    //si les mots sont identiques, l'utilisateur a gagne la partie
                    if (equals(mot, motUtilisateur)) {
                        /*
                        mettre a jour les statistiques lors d'une victoire et activer le drapeau signalant le mot
                        comme etant devine
                         */
                        stats.nbParties += 1;
                        stats.nbEssaisTotal += 1;
                        stats.nbReussite += 1;
                        motEstDevine = true;

                        //impression d'une message de victoire
                        System.out.println("Bravo, vous avec devine le mot!");

                    } else {
                        /*
                        en cas de mot non gagnant, dire au joueur le nombre de lettres qui se retrouvent dans le mot
                        a deviner et actualiser le nombre d'essais des statistiques
                         */
                        stats.nbEssaisTotal += 1;
                        System.out.println("Vous avez " + nbDoublons + " lettres qui sont dans les deux mots");
                    }
                } else {
                    // activer le drapeau pour quitter la partie et actualiser le nombre de parties
                    quitterPartie = true;
                    stats.nbParties +=1;
                }


            }




                finPartie = !demandeUtilisateurAutrePartie();

        }


    }

    /**
     * Demande a l'utilisateur s'il veux jouer une autre partie avec le meme nombre de lettres
     * @return retourne true si l'utilisateur veux garder le meme nombre de lettre
     */
    public static boolean demandeUtilisateurAutrePartie(){
        boolean reponseValide = false;
        boolean reponse = false;
        while(!reponseValide){
            String demande = UtilitaireEntreesSorties.lireString("Voulez jouer une autre partie avec le "+
                    "meme nombre de lettres? [oui/non]: ",false);
            if(demande.toLowerCase().equals("oui")){
                reponse = true;
                reponseValide = true;
            } else if(demande.toLowerCase().equals("non")){

                reponseValide = true;
            } else{
                System.out.println("La reponse que vous avec entre n'est pas valide");
            }
        }

        return reponse;
    }


    /**
     * Determine si une lettre est dans un tableau
     * @param lettre lettre a chercher dans le tableau
     * @param tableau tableau sur lequel on effectue la recherche
     * @return true si la lettre est dans le tableau
     */
    public static boolean lettreEstDansTableau(char lettre, char[] tableau){

        //loop sur le tableau pour trouver la lettre
        boolean doublon = false;	// flag pour determiner si la lettre a ete trouvee dans le tableau
        for(int i =0;i < tableau.length;i++){
            //si la lettre est trouvee, arreter la recherche et mettre vrai dans doublon
            if(tableau[i] == lettre){
                doublon = true;
                break;
            }
        }
        return doublon;
    }


    /**
     * Determine le nombre de lettres qui sont dans le char[] et dans le String
     * @param mot mot qui doit etre devine
     * @param motUtilisateur mot que l'utilisateur a entre en essai
     * @return retourne le nombre de lettre dans mot qui sont aussi dans motUtilisateur
     */
    public static int nbDoublons(char[] mot, String motUtilisateur){

        int sommeDoublons =0;	//nombre total de lettres dans les deux mots
        //mettres les lettres en minuscule pour ne pas avoir de probleme avec la conversion de leur nombre ascii
        motUtilisateur = motUtilisateur.toLowerCase();
        //tableau pour determiner le nombre de fois que chaque lettre est presente dans les deux mots
        int[] tableauDoublons = new int[NB_LETTRES_ALPHABET];

        //permet de filtrer les motUtilisateurs qui ont plus d'une fois la meme lettre
        for(int i =0; i < motUtilisateur.length();i++){
            tableauDoublons[(int)(motUtilisateur.charAt(i))- DECALAGE_ASCII] = 1;
        }
        //utiliser chaque lettre comme index (decalle) dans le tableau et indiquer sa presence en ajoutant 1
        for(int i =0; i< mot.length;i++){
            tableauDoublons[(int)(mot[i])- DECALAGE_ASCII] +=1;
        }

        for(int i =0; i< tableauDoublons.length;i++){
            //Si une case du tableau contient une valeur superieur a 1, cela veut dire que cette lettre etait presente
            // dans les deux mots
            sommeDoublons += tableauDoublons[i]>=2?1:0;
        }

        return sommeDoublons;
    }

    /**
     * Genere un mot n'ayant aucune lettre lettre en double
     * @param nbLettres le nombre de lettres souhaite dans le mot
     * @return retourne un mot de nbLettres
     */
    public static char[] genererMot(int nbLettres){
		/*
			Sous 15 lettres, generateMotAleatoire() produit un mot plus rapidement que generateMotEchange().
			c'est le contraire quand nbLettres devient egal ou superieur a 15 lettres. Je prends donc la fonction
			la plus rapide.
		*/
        return (nbLettres<POINT_OPTIMISATION_MOT)? genererMotAleatoire(nbLettres):
                genererMotEchange(nbLettres);
    }

    /**
     * Genere un mot d'un certain nombre de lettres sans caracteres doublons
     * @param nbLettres determine le nombre de lettres que le mot genere doit avoir
     * @return retourne le mot genere sous la forme de char[]
     */
    private static char[] genererMotAleatoire(int nbLettres){
		 /*
		 	genere une nouvelle lettre aleatoire tant qu'elle est deja dans le mot incomplet. Une fois une lettre
		 	unique trouvee, l'ajouter au mot et continuer avec la prochaine lettre si le mot n'est pas complet
		 */
        char[] mot = new char[nbLettres];
        // generer la premiere lettre. Elle ne peut pas avoir de doublon
        mot[0] = genererLettre();

        for(int i =1;i<nbLettres;i++){
            boolean lettreValide = false; // flag pour les lettres valides
            char letter;	// lettre qui va etre genere

            while(!lettreValide){
                letter = genererLettre();
                //si la lettre est valide (pas dans le tableau), l'ajouter au mot et passer a la lettre suivante
                if(!lettreEstDansTableau(letter,mot)){
                    lettreValide = true;
                    mot[i] = letter;
                }
            }
        }
        return mot;
    }



    /**
     * Genere un mot aleatoire sans lettre qui se repete
     * @param nbLettres nombre de lettres que le mot genere doit avoir
     * @return retourne le mot sous forme de char[]
     */
    public static char[] genererMotEchange(int nbLettres){
		 /*
		 	prendre une lettre aleatoire et l'ajouter au mot. remettre cette lettre aleatoire a la fin du tableau et
		 	reduire de 1 le nombre de lettres significatives. reprendre une lettre aleatoire ne depassant pas le nombre
		 	de lettres significatives jusqu'a ce que le mot soit complet
		 */
        char[] mot = new char[nbLettres]; // initialiser le mot a generer
        int indiceSignificatif = NB_LETTRES_ALPHABET -1;	// indice de la derniere lettre significative et encore valide
        // tableau contenant toutes les lettres
        char[] tabLettres = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
                'r','s','t','u','v','w','x','y','z'};

        int num; // numero d'indice genere pas le nombre aleatoire
        for(int i =0; i < nbLettres;i++){
            // genere un nombre aleatoire entre [0,indiceSignificatif]
            num = UtilitaireFonctions.alea(0,indiceSignificatif);
            // ajouter cette lettre au mot
            mot[i] = tabLettres[num];
            // mettre cette lettre a la fin des lettres significatives et reduire de 1 l'indice des lettres
            // significatives
            echangerChar(tabLettres, num, indiceSignificatif);
            indiceSignificatif--;
        }



        return mot;
    }

    /**
     * echange deux char dans un tableau char[]
     * @param tab tableau sur lequel effectuer le l'echange
     * @param index1 indice du premier element
     * @param index2 indice du second element
     */
    public static void echangerChar(char[] tab, int index1, int index2){

        char tmp = tab[index1]; // variable pour garder en memoire l'element qui se fait ecraser
        // echanger les deux elements
        tab[index1] = tab[index2];
        tab[index2] = tmp;
    }


    public static boolean equals(char[] mot, String str){

        boolean result = true;
        str = str.toLowerCase();
        if(mot.length == str.length()){
            for(int i =0;i < mot.length;i++){
                if(mot[i] != str.charAt(i)){
                    result = false;
                    break;
                }
            }
        } else{
            result = false;
        }

        return result;
    }

    /**
     * genere un char aleatoire
     * @return un char
     */
    public static char genererLettre(){

        return TABLEAU_LETTRES[UtilitaireFonctions.alea(0, TABLEAU_LETTRES.length-1)];
    }

    /**
     * Demande un mot valide a l'utilisateur
     * @param nbLettres le nombre de lettres que doit contenir le mot
     * @return le mot ou NULL si l'utilisateur decide de quitter
     */
    public static String demanderMot(int nbLettres){
		 /*
		 demander un mot tant qu'il n'est pas valide ou qu'il decide de quitter.
		 */
        boolean motValide = false;
        String mot = null;
        while(!motValide){
            mot = UtilitaireEntreesSorties.lireString("entrer un mot de "
                    +nbLettres+" lettres",true);
            if(UtilitaireEntreesSorties.utilisateurAnnule()){
                break;
            } else if (mot.length()==nbLettres){
                motValide = true;

            }


        }

        return mot;
    }
}

