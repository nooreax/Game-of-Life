import java.util.ArrayList;
import java.util.List;

public class FieldManager implements Observable<Field, Observer<Field>>{

    //eine Liste mit Observern wird erstellt
    private List<Observer<Field>> observers = new ArrayList<>();

    private FieldWriter fieldWriter;


    //ein Observer wird der Liste hinzugefügt
    @Override
    public void subscribe(Observer<Field> observer){

        observers.add(observer);
    }


    //jeder Observer erhält die aktuelle Version des Feldes
    private void notice() {

        for(Observer<Field> observer: observers){

            observer.update(fieldWriter);
        }
    }


    //das alte Feld wird mit dem neuen Feld überschrieben.
    public void setField(boolean[][] field){

        this.fieldWriter.setField(field);

        notice();
    }


    //eine bestimmte Zelle erhält ihren neuen Zustand
    public void setZelle(int feldbreiteWert, int feldhoeheWert, boolean zustand){

        this.fieldWriter.setZelle(feldbreiteWert, feldhoeheWert, zustand);

        notice();
    }
}
