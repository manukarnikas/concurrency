public class SimpleThreadDemo {
    public static void main(String[] args) {

        int temp = 10;

        Thread t = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("New thread: " + i);
            }
        });

        t.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("Main thread: " + i);
        }
    }
}
