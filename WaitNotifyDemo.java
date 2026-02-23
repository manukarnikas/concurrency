import java.lang.Thread;

class Message{

    String msg = "";
    boolean ready = false;

    public synchronized void produce(String msg) throws InterruptedException{
        while(this.ready){
            wait();
        }
        this.msg = msg;
        this.ready = true;
        System.out.println("Produced:"+this.msg);
        notify();                   // notify wakes up one random sleeping thread
    }

    public synchronized void consume() throws InterruptedException{
        while(!this.ready){
           wait();
        }
        System.out.println("Consumed:"+this.msg);
        this.msg="";
        this.ready = false;
        notify();           // if other thread is waiting it wake sit up immendiately 
    }
}

class Consumer extends Thread{
    
    private final Message message;

    Consumer(Message message){
        this.message = message;
    }
    
    public void run(){
        try{
            this.message.consume();
            this.message.consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Producer extends Thread{
    
    private final Message message;

    Producer(Message message){
        this.message = message;
    }
    
    public void run(){
        try{
            this.message.produce("Hello");
            this.message.produce("World");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class WaitNotifyDemo{

    public static void main(String args[]){

        Message message = new Message();

        Producer producer = new Producer(message);
        Consumer consumer = new Consumer(message);

        producer.start();
        consumer.start();
    }
}