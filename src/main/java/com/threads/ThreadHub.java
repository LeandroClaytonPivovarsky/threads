package com.threads;

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

        while (alive){}
    }

    public void kill() {
        this.alive = false;
    }

    public int howManyThreads() {
        return threads.size();
    }

    public boolean killOneThread(int id){
        int i = 0;
        if (id-1 > threads.size() || id-1 < 0 || threads.isEmpty()) {
            return false;
        }

        while (threads.size() > i) {

            if (threads.get(i).getCustomId() == id) {
                sortIdThreads(id);
                threads.remove(i);
                return true;
            }

            i++;
        }

        return false;
        
    }

    private void sortIdThreads(int index){

        for (int i = index-1; i < threads.size(); i++) {
            threads.get(i).setCustomId(i);
        }
    }

    public boolean listAllThreads(){
        if (threads.isEmpty()) {
            return false;
        }
        for (CustomThread customThread : threads) {
            System.out.println(customThread.toString());
        }
        return true;
    }

    public void addThread() {
        CustomThread customThread;
        if (threads.isEmpty()) {
            customThread = new CustomThread(1);
        } else{
            customThread = new CustomThread(threads.size() + 1);
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
        
        String text = "Thread hub started!\nTo create a new thread please type \"add\"\nTo stop all threads, type: \" kill all\", and only one type: \"Kill one Thread\"\nTo list all threads alive, type: \"List\"";

        System.out.println(text);
    }

    public void allThreadsInterruptor(){
        if (threads.isEmpty()) {
            return;
        }
        for (CustomThread customThread : threads) {
            System.out.println("Parando a thread " + customThread.getCustomId() + " para a resposta!");
            
            customThread.kill();

        }
    }

    public void allThreadsStarter(){
        if (threads.size() == 1) {
            return;
        }
        System.out.println("Reiniciando as threads...");

        for (CustomThread customThread : threads) {
            customThread.start();
        }
        

    }
    
}
