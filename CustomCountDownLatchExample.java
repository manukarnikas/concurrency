class CustomCountDownLatch{

    private int count;

    public CustomCountDownLatch(int count){
        this.count = count;
    }

    public synchronized void countDown(){
        if(count>0){
            count--;
            if(count==0){
                notifyAll();
            }
        }
    }

    public synchronized void await() throws InterruptedException{
        while(count>0){
            wait();
        }
    }

}

class Worker extends Thread {

    private String name;
    private CustomCountDownLatch countDownLatch;

    public Worker(CustomCountDownLatch countDownLatch, String name){
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

class CustomCountDownLatchExample{
    public static void main(String args[]) throws InterruptedException{

        System.out.println("Main thread started");

        CustomCountDownLatch latch = new CustomCountDownLatch(3);

        for(int i=1;i<=3;i++){
            Worker worker = new Worker(latch, "Thread"+i);
            worker.start();
        }

        latch.await();
        System.out.println("Main thread continues..");
        System.out.println("Main thread ended..");
    }
}