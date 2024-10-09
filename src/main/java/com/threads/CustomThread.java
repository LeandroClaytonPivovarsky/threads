package com.threads;

import java.time.Duration;

public class CustomThread extends Thread {
    private int customId;

    private boolean customAlive;

    public CustomThread(int id) {
        this.customId = id;
        this.customAlive = true;
    }

    @Override
    public void run() {
        System.out.println("Thread " + customId + "Inicializada!");

        while (customAlive) { 
            System.out.println("Thread " + customId + "rodando!");
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


    @Override
    public String toString() {
        return "Sou a Thread " + customId + "!";

    };


    public void kill() {
        this.customAlive = false;
    }
    
}
