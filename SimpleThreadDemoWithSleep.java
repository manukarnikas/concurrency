public class SimpleThreadDemoWithSleep {
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try{
                    System.out.println("New thread: " + i);
                    Thread.sleep(1000); 
                }catch(Exception err){
                    System.out.println("New thread interrupted...");
                }
            }
        });

        t.start();
        t.interrupt();       // interrupt flag is set but is thrown only when t gets cpu time

        for (int i = 0; i < 5; i++) {
                try{
                    System.out.println("Main thread: " + i);
                    Thread.sleep(1000);
                }catch(Exception err){
                    System.out.println("Main thread interrupted...");
                }
        }
    }
}
