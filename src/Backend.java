public class Backend implements Observer<boolean[][]>{

    //das Array, welches das Feld darstellt, wird erstellt
    private  boolean[][] field;

    private final StateManager stateManager;


    //ein neues Backend wird mit angepasster Feldhöhe und Feldbreite erstellt, indem am Anfang alle Zellen auf "false"
    //gesetzt werden
    public Backend(int feldbreite, int feldhoehe, StateManager stateManager){

        this.stateManager = stateManager;

        field = new boolean[feldbreite][feldhoehe];

        stateManager.fieldManager.setField(field);

        for(int i = 0; i < feldhoehe; i++) {
            for (int j = 0; j < feldbreite; j++) {

                setZelle(j, i, false);
            }
        }
    }


    @Override
    public void update(boolean[][] field){


    }


    //der zufünftige Zustand einer bestimmten Zelle wird ausgegeben
    public boolean getLiveOrDie(int feldbreiteWert, int feldhoeheWert){

        int anzahlNachbar = getNachbar(feldbreiteWert, feldhoeheWert);

        boolean zustand = getZelle(feldbreiteWert, feldhoeheWert);

        if (!zustand && anzahlNachbar == 3) {
            return true;
        }

        if (zustand && (anzahlNachbar == 2 || anzahlNachbar == 3)){
            return true;
        }

        return false;
    }


    //alle Zellen werden nach ihrem zufünftigen Zustand gefragt
    public void zellCheck(){

        boolean[][] newFeld = new boolean[feldbreite][feldhoehe];

        for(int i = 0; i < feldhoehe; i++){
            for(int j = 0; j < feldbreite; j++){

                newFeld[j][i] = getLiveOrDie(j, i);
            }
        }

        setFeld(newFeld);
    }




    //zum Testen

    //das aktuelle Feld wird in Nullen und Einsen ausgegeben
    public void anzeigen(){

        for(int i = 0; i < feldhoehe; i++) {
            for (int j = 0; j < feldbreite; j++) {

                if(getZelle(j, i)){
                    System.out.print("1");
                }
                else{
                    System.out.print("0");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }
}
