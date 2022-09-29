public class InvalidFieldSizeException extends RuntimeException{

    public InvalidFieldSizeException(final int width, final int heigth){

        super("Das Feld mit den Dimensionen" +width+ "und" +heigth+ "ist nicht zulässig");
    }
}
