public interface Observable <C, T extends Observer<C>>{

    void subscribe(T observer);
}
