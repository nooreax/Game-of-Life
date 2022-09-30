import java.util.ArrayList;
import java.util.List;

public class FieldManager implements Observable<Field, Observer<Field>>{ ;

    private List<Observer<Field>> observers = new ArrayList<>();

    private FieldWriter field;

    private boolean[][] oldField;


    public FieldManager(int fieldWidth, int fieldHeight){

        this.field = new FieldWriter();

        this.field.setField(new boolean[fieldWidth][fieldHeight]);

        for(int i = 0; i < this.field.getFieldHeight(); i++) {
            for (int j = 0; j < this.field.getFieldWidth(); j++) {

                setZelle(j, i, false);
            }
        }
    }


    @Override
    public Field subscribe(Observer<Field> observer){

        observers.add(observer);

        return field;
    }


    private void notice() {

        for(Observer<Field> observer: observers){

            observer.update(field);
        }
    }


    public void setField(boolean[][] field){

        if(field.length == this.field.getFieldWidth() && field[0].length == this.field.getFieldHeight()) {

            this.field.setField(field);

            notice();
        }
        else{
            throw new InvalidFieldSizeException(field.length, field[0].length);
        }
    }


    public void setZelle(int fieldWidthValue, int fieldHeightValue, boolean state){

        this.field.setZelle(fieldWidthValue, fieldHeightValue, state);

        notice();
    }


    public void setOldField(){

        field.setField(oldField);

        notice();
    }


    public void safeOldField(){

        oldField = field.getField();
    }
}
