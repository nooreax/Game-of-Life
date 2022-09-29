public class Field {

    protected boolean[][] field;


    public int getFieldWidth(){

        return field.length;
    }


    public int getFieldHeight(){

        return field[0].length;
    }


    public boolean[][] getField(){

        return field;
    }


    public boolean getZelle(int feldbreiteWert, int feldhoeheWert){

        if (feldbreiteWert < 0 || feldbreiteWert >= field.length || feldhoeheWert < 0 || feldhoeheWert >= field[0].length){
            return false;
        }

        return field[feldbreiteWert][feldhoeheWert];
    }


    public int getNachbar(int feldbreiteWert, int feldhoeheWert){

        int anzahlNachbar = 0;

        if (getZelle(feldbreiteWert-1, feldhoeheWert-1)){
            anzahlNachbar++;
        }

        if (getZelle(feldbreiteWert-1, feldhoeheWert)){
            anzahlNachbar++;
        }

        if (getZelle(feldbreiteWert-1, feldhoeheWert+1)){
            anzahlNachbar++;
        }

        if (getZelle(feldbreiteWert, feldhoeheWert-1)){
            anzahlNachbar++;
        }

        if (getZelle(feldbreiteWert, feldhoeheWert+1)){
            anzahlNachbar++;
        }

        if (getZelle(feldbreiteWert+1, feldhoeheWert-1)){
            anzahlNachbar++;
        }

        if (getZelle(feldbreiteWert+1, feldhoeheWert)){
            anzahlNachbar++;
        }

        if (getZelle(feldbreiteWert+1, feldhoeheWert+1)){
            anzahlNachbar++;
        }
        return anzahlNachbar;
    }

}
