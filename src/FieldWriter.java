public class FieldWriter extends Field{

    public void setField(boolean[][] field){

        this.field = field;
    }


    public void setZelle(int fieldWidthValue, int fieldHeightValue, boolean state){

        field[fieldWidthValue][fieldHeightValue] = state;
    }
}
