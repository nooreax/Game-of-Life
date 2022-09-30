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


    public int getNeighbor(int fieldWidthValue, int fieldHeightValue){

        int valueNeighbor = 0;

        if (getZelle(fieldWidthValue-1, fieldHeightValue-1)){
            valueNeighbor++;
        }

        if (getZelle(fieldWidthValue-1, fieldHeightValue)){
            valueNeighbor++;
        }

        if (getZelle(fieldWidthValue-1, fieldHeightValue+1)){
            valueNeighbor++;
        }

        if (getZelle(fieldWidthValue, fieldHeightValue-1)){
            valueNeighbor++;
        }

        if (getZelle(fieldWidthValue, fieldHeightValue+1)){
            valueNeighbor++;
        }

        if (getZelle(fieldWidthValue+1, fieldHeightValue-1)){
            valueNeighbor++;
        }

        if (getZelle(fieldWidthValue+1, fieldHeightValue)){
            valueNeighbor++;
        }

        if (getZelle(fieldWidthValue+1, fieldHeightValue+1)){
            valueNeighbor++;
        }
        return valueNeighbor;
    }

}
