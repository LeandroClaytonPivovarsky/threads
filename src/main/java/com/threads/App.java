package com.threads;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static ThreadHub threadHub;
    public static void main( String[] args )
    {
        Scanner reader = new Scanner(System.in);
        
        boolean continuar = true;
        threadHub = new ThreadHub();
        threadHub.start();
    

        while (continuar) {
            System.out.println("\n\n\nType something and press enter to get one of the actions");
            reader.next();

            threadHub.allThreadsInterruptor();
            
            System.out.println("Your answer: \n");
            String input = reader.next();
            while (input.isEmpty() || input.isBlank()) {
                System.out.println("None answer found, please try again");
                input = reader.next();
            }
            if (input.equalsIgnoreCase("leave")) {
                threadHub.kill();
                continuar = false;
            } else {
                choices(input, reader);

            }
            if (!threadHub.wasNotKilled()) {
                System.out.println("Thread Hub isn't alive, the program will be shut downed!\nGoodbye! :)");
                reader.next();
                try {
                    threadHub.join();
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            } else{
                threadHub.printInterface();
            }
            reader.close();
            
        }


    }


    private static void choices  (String input, Scanner reader){

        input = input.toUpperCase();
        switch (input) {
            case "KILL":

                System.out.println("If you want to kill all threads, type: \"all\"\nIf want to kill only one thread, type: \"One\"");
                String choiceString = reader.next();
                if (choiceString.equalsIgnoreCase("all")) {
                    threadHub.killAllThreads();

                    System.out.println("All threads have been killed!(type something and you go to principal menu)");
                    reader.next();
                }else if (choiceString.equalsIgnoreCase("one")) {
                    if (!threadHub.listAllThreads()) {
                        System.out.println("At least once thread must be inicialized!(type something and you go to principal menu)");
                        reader.next();
                        return;

                    }
                    System.out.println("Which one do you want to kill?(only numbers)");
                    int id = reader.nextInt();
                    if (threadHub.killOneThread(id)) {
                        System.out.println("Thread with ID " + id + " deleted!(type something and enter)");
                        reader.next();
                    } else{
                        System.out.println("The thread with this ID does not exist");
                    }
                }

                
                break;
        
            case "ADD":
                threadHub.addThread();
                System.out.println("Thread adicionada com sucesso!");
                break;
        }
        threadHub.allThreadsStarter();
    }
}
