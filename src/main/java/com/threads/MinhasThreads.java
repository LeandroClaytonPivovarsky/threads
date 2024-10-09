package com.threads;

import java.util.ArrayList;

import com.threads.Thread;

public class MinhasThreads implements Runnable{

    private ArrayList<Thread> threads;

    private boolean alive;

    public MinhasThreads(int id) {
        this.threads = new ArrayList<Thread>();
        this.alive = true;
    }

    @Override
    public void run() {

        String text =   "Thread hub started!\nTo create a new thread please type \"add\"\nTo stop all threads, type: \" kill all\", and only one type: \"Kill {number of thread}\"";

        System.out.println(text);

        while (alive){}
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
    }


    
}
