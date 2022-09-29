public class FieldWriter extends Field{

    public void setField(boolean[][] field){

        this.field = field;
    }


    public void setZelle(int feldbreiteWert, int feldhoeheWert, boolean zustand){

        field[feldbreiteWert][feldhoeheWert] = zustand;
    }
}
