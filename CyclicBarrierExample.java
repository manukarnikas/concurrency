/**
 *  CountDownLatch = main thread waits for workers
 *  CyclicBarrier = threads wait for each other
 */


import java.util.concurrent.CyclicBarrier;

class Worker extends Thread {

    private String name;
    private CyclicBarrier barrier;

    public Worker(CyclicBarrier barrier, String name){
        this.barrier = barrier;
        this.name = name;
    }

    public void run(){
        try{
            System.out.println(this.name +" has started");
            Thread.sleep(3000);
            System.out.println(this.name +" has reached barrier 1");
            barrier.await();
            System.out.println(this.name +" has moved past barrier 1");
            Thread.sleep(3000);
            System.out.println(this.name +" has reached barrier 2");
            barrier.await();   
            System.out.println(this.name +" has moved past barrier 2");  
            barrier.await();   
            System.out.println(this.name +" has completed");
        } catch(Exception  e){
           e.printStackTrace();
        }
       
    }
}

class CyclicBarrierExample{
    public static void main(String args[]) throws InterruptedException{

        System.out.println("Main thread started");

        CyclicBarrier barrier = new CyclicBarrier(2);

        for(int i=1;i<=2;i++){
            Worker worker = new Worker(barrier, "Thread"+i);
            worker.start();
        }
    }
}