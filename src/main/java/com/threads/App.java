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
        Scanner reader =  new Scanner(System.in);
        
        boolean continuar = true;
        threadHub = new ThreadHub();
        threadHub.start();
        while(continuar){
            
            System.out.println("Type something ant press enter to get one of this actions");
            reader.next();

            threadHub.allThreadsInterruptor();
            
            System.out.println("Your answer: \n");
            
            String input = reader.next();
            

            if (input.equalsIgnoreCase("sair")) {
                continuar = false;
            } else if (input.isEmpty() || input.isBlank()) {
                System.out.println("Nenhuma resposta registrada");
            }{
            choices(input, reader);
        }
            //threadHub.allThreadsStarter();
            
            threadHub.printInterface();

        }

        
    }


    private static void choices(String input, Scanner reader){

        input = input.toUpperCase();
        switch (input) {
            case "KILL ALL":
                threadHub.kill();
                break;
        
            case "ADD":
                threadHub.addThread();
                System.out.println("Thread adicionada com sucesso!");
                break;
            case "KILL ONE THREAD":
                boolean escolha = false;


                if (!threadHub.listAllThreads()) {
                    System.out.println("Nenhuma thread iniciada!");
                    return;
                }
                System.out.println("Which one do you want to kill?(only numbers)");
                while (!escolha) { 
                    int id = reader.nextInt();
                    if (threadHub.killOneThread(id)) {
                        System.out.println("Thread with ID " + id + " deleted!");
                        escolha = true;
                    } else{
                        System.out.println("The thread with this ID does not exist");
                    }
                }

        }
    }
}
