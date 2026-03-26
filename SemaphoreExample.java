import java.util.concurrent.Semaphore;

class Worker extends Thread {

    private String name;
    private Semaphore semaphore;

    public Worker(Semaphore semaphore, String name){
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

class SemaphoreExample{
    public static void main(String args[]){

        Semaphore semaphore = new Semaphore(3);

        for(int i=1;i<=5;i++){
            Worker worker = new Worker(semaphore, "Thread"+i);
            worker.start();
        }
    }
}