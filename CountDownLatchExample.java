/** A CountDownLatch lets one or more threads wait until some work is finished in other threads.
 * 
 *  Simple Real-World Analogy
        You’re starting a race, but:
        3 referees need to signal “ready”
        Only then the race begins
 * 
*/

import java.util.concurrent.CountDownLatch;

class Worker extends Thread {

    private String name;
    private CountDownLatch countDownLatch;

    public Worker(CountDownLatch countDownLatch, String name){
        this.countDownLatch = countDownLatch;
        this.name = name;
    }

    public void run(){
        try{
            System.out.println(this.name +" has started");
            Thread.sleep(3000);
            System.out.println(this.name +" has completed");
            this.countDownLatch.countDown();       // signal completion
        } catch(InterruptedException  e){
           e.printStackTrace();
        }
       
    }
}

class CountDownLatchExample{
    public static void main(String args[]) throws InterruptedException{

         System.out.println("Main thread started");

        CountDownLatch latch = new CountDownLatch(3);

        for(int i=1;i<=3;i++){
            Worker worker = new Worker(latch, "Thread"+i);
            worker.start();
        }

        latch.await();
        System.out.println("Main thread continues..");
        System.out.println("Main thread ended..");
    }
}