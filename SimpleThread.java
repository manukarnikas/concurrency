import java.lang.Thread;

public class SimpleThread{
    public static void main(){
        
        Thread t = new Thread(() -> {
            System.out.println("Hello from new thread");
        });

        t.start();

        System.out.println("Hello from main thread");

    }
}