public class Runner implements Runnable{

    private final Backend backend;

    public Runner(Backend backend) {
        this.backend = backend;
    }


    @Override
    public void run(){

        while(true){

            backend.execute();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
