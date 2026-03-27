class CustomCyclicBarrier{

    private int count=0;
    private int parties;

    public CustomCyclicBarrier(int parties){
        this.parties = parties;
    }

    public synchronized void await() throws InterruptedException{
        count++;
        if(parties>=count){
            count=0;
            notifyAll();
        }else{
            wait();
        }
    }

}

class Worker extends Thread {

    private String name;
    private CustomCyclicBarrier barrier;

    public Worker(CustomCyclicBarrier barrier, String name){
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

class CustomCyclicBarrierExample{
    public static void main(String args[]) throws InterruptedException{

        System.out.println("Main thread started");

        CustomCyclicBarrier barrier = new CustomCyclicBarrier(2);

        for(int i=1;i<=2;i++){
            Worker worker = new Worker(barrier, "Thread"+i);
            worker.start();
        }
    }
}