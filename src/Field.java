public class Field {

    protected boolean[][] field;


    //die Breite des Feldes wird ausgegeben
    public int getFieldWidth(){

        return field.length;
    }


    //die Höhe des Feldes wird ausgegeben
    public int getFieldHeight(){

        return field[0].length;
    }


    //das Feld wird ausgegeben
    public boolean[][] getField(){

        return field;
    }


    //der Wert einer bestimmten Zelle wird ausgegeben
    public boolean getZelle(int feldbreiteWert, int feldhoeheWert){

        if (feldbreiteWert < 0 || feldbreiteWert >= field.length || feldhoeheWert < 0 || feldhoeheWert >= field[0].length){
            return false;
        }

        return field[feldbreiteWert][feldhoeheWert];
    }


    //die Anzahl der Nachbarn einer bestimmten Zelle wird ausgegeben
    public int getNachbar(int feldbreiteWert, int feldhoeheWert){

        int anzahlNachbar = 0;

        //erstes Feld wird überprüft
        if (getZelle(feldbreiteWert-1, feldhoeheWert-1)){
            anzahlNachbar++;
        }

        //zweites Feld wird überprüft
        if (getZelle(feldbreiteWert-1, feldhoeheWert)){
            anzahlNachbar++;
        }

        //drittes Feld wird überprüft
        if (getZelle(feldbreiteWert-1, feldhoeheWert+1)){
            anzahlNachbar++;
        }

        //vierstes Feld wird überprüft
        if (getZelle(feldbreiteWert, feldhoeheWert-1)){
            anzahlNachbar++;
        }

        //fünftes Feld wird überprüft
        if (getZelle(feldbreiteWert, feldhoeheWert+1)){
            anzahlNachbar++;
        }

        //sechstes Feld wird überprüft
        if (getZelle(feldbreiteWert+1, feldhoeheWert-1)){
            anzahlNachbar++;
        }

        //siebtes Feld wird überprüft
        if (getZelle(feldbreiteWert+1, feldhoeheWert)){
            anzahlNachbar++;
        }

        //achtes Feld wird überprüft
        if (getZelle(feldbreiteWert+1, feldhoeheWert+1)){
            anzahlNachbar++;
        }
        return anzahlNachbar;
    }

}
