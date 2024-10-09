package com.threads;

public class NewThread implements Runnable {
    private final int id;

    public NewThread(int id) {
        this.id = id;
    }

    

    @Override
    public void run() {
        System.out.println("Thread " + id + "");
    }
    
}
