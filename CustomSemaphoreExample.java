class CustomSemaphore{

   private int permits;

   public CustomSemaphore(int permits){
        this.permits = permits;
   } 

   public synchronized void acquire() throws InterruptedException{
        while(permits == 0){
            wait();
        }
        permits--;
   }

   public synchronized void release(){
        permits++;
        notify();
   }
}

class Worker extends Thread {

    private String name;
    private CustomSemaphore semaphore;

    public Worker(CustomSemaphore semaphore, String name){
        this.semaphore = semaphore;
        this.name = name;
    }

    public void run(){
        try{
            System.out.println(this.name +" is waiting to acquire permit");
            this.semaphore.acquire();
            System.out.println(this.name +" has acquired permit");
            Thread.sleep(1000);
            System.out.println(this.name +" is releasing permit");
            this.semaphore.release();
        } catch(InterruptedException  e){
           e.printStackTrace();
        }
       
    }
}

class CustomSemaphoreExample{
    public static void main(String args[]){

        CustomSemaphore semaphore = new CustomSemaphore(2);

        for(int i=1;i<=5;i++){
            Worker worker = new Worker(semaphore, "Thread"+i);
            worker.start();
        }
    }
}