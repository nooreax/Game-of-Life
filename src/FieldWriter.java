public class FieldWriter extends Field{

    //das alte Feld wird mit dem neuen Feld überschrieben.
    public void setField(boolean[][] field){

        this.field = field;
    }


    //eine bestimmte Zelle erhält ihren neuen Zustand
    public void setZelle(int feldbreiteWert, int feldhoeheWert, boolean zustand){

        field[feldbreiteWert][feldhoeheWert] = zustand;
    }
}
