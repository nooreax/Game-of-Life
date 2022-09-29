public interface Observable <C, T extends Observer<C>>{

   C subscribe(T observer);
}
