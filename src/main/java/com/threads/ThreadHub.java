package com.threads;

import java.time.Duration;
import java.util.ArrayList;


public class ThreadHub extends Thread{

    private ArrayList<CustomThread> threads;

    private boolean alive;

    public ThreadHub() {
        this.threads = new ArrayList<>();
        this.alive = true;
    }

    @Override
    public void run() {
        printInterface();

        while (alive){
        }
    }

    public void kill() {
        this.alive = false;
    }

    public int howManyThreads() {
        return threads.size();
    }

    public boolean killOneThread(int id){
        if (id-1 > threads.size() || id-1 < 0 || threads.isEmpty()) {
            return false;
        }

        threads.remove(id-1);
        return true;
        
    }
    public boolean listAllThreads(){
        int i = 1;
        if (threads.isEmpty()) {
            return false;
        }
        for (CustomThread customThread : threads) {
            
            System.out.println("["+ i +"]" + customThread.toString());
            i++;
        }
        return true;
    }

    public void addThread() {
        CustomThread customThread;
        if (threads.isEmpty()) {
            customThread = new CustomThread(1);
        } else{
            customThread = new CustomThread(threads.get(threads.size()-1).getCustomId()+1 );
        }

        threads.add(customThread);

        customThread.start();



    }

    public ArrayList<CustomThread> getThreads() {
        return threads;
    }

    public void setThreads(ArrayList<CustomThread> threads) {
        this.threads = threads;
    }

    public void printInterface(){
        
        String text = "Thread hub started!\nTo create a new thread please type \"add\"\nTo stop all threads, type: \" kill all\", and only one type: \"Kill one Thread\"\nTo list all threads alive, type: \"List\"\nTo stop the program type: \"Leave\"";

        System.out.println(text);
        System.out.println("\n\n\nType something and press enter to get one of the actions");
    }

    public void allThreadsInterruptor(){

        if (threads.isEmpty()) {
            return;
        }
        for (CustomThread customThread : threads) {
            System.out.println("Stoping thread " + customThread.getCustomId() + " for the answer!");
            customThread.pauseThread();
            
        }
    }

    public void interruptOneThread(int id){
        threads.get(id-1).pauseThread();
    }



    public void allThreadsStarter(){
        if (threads.isEmpty()) {
            return;
        }
        

        for (CustomThread customThread : threads) {
            try {
                System.out.println("Restarting thread "+ customThread.getCustomId() +"...");
                sleep(Duration.ofSeconds(2));
                customThread.resumeThread();
                System.out.println("Thread " + customThread.getCustomId() + " resumed!");

                sleep(Duration.ofSeconds(2));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    public void killAllThreads() {
        for (CustomThread customThread : threads) {
            customThread.kill();
        }
        threads.clear();
    }

    public boolean wasNotKilled() {
        return alive;
    }
    
}
