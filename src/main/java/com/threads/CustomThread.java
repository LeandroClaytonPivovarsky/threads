package com.threads;
import java.time.Duration;

public class CustomThread extends Thread {
    private int customId;
    private boolean paused;
    private boolean customAlive;
    private final Object awaker;



    public CustomThread(int id) {
        this.customId = id;
        this.customAlive = true;
        this.awaker = new Object();
        this.paused = false;
    }

    @Override
    public void run() {
        System.out.println("Thread " + customId + " inicialized!");

        while (customAlive) { 

            synchronized(awaker) {
                if (paused) {
                    try {
                        awaker.wait();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
            System.out.println("Thread " + customId + " running!");
            try {
                sleep(Duration.ofSeconds(3));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            
        }
    }

    public int getCustomId() {
        return this.customId;
    }

    public void setCustomId(int id) {
        this.customId = id;
    }

    public void pauseThread(){
        paused = true;
    }


    public void resumeThread(){
        synchronized(awaker){
            paused = false;
            awaker.notify();
        }
    }

    @Override
    public String toString() {
        return "["+ customId +"] Thread " + customId;

    };


    public void kill() {
        this.customAlive = false;
    }
    
}
