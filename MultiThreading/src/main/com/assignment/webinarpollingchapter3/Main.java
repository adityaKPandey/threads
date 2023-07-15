package com.assignment.webinarpollingchapter3;

public class Main {
    public static void main(String[] args) {
        Design d1 = new Design(1,"D1");
        VotingRunnable votingRunnableD1 = new VotingRunnable(d1);
        CountingRunnable countingRunnableD1 = new CountingRunnable(d1);

        Thread votingD1 = new Thread(votingRunnableD1);
        Thread countingD1 = new Thread(countingRunnableD1);

        votingD1.start();
        countingD1.start();

        Design d2 = new Design(2,"D2");
        VotingRunnable votingRunnableD2 = new VotingRunnable(d2);
        CountingRunnable countingRunnableD2 = new CountingRunnable(d2);

        Thread votingD2 = new Thread(votingRunnableD2);
        Thread countingD2 = new Thread(countingRunnableD2);

        votingD2.start();
        countingD2.start();

        Design d3 = new Design(3,"D3");
        VotingRunnable votingRunnableD3 = new VotingRunnable(d3);
        CountingRunnable countingRunnableD3 = new CountingRunnable(d3);

        Thread votingD3 = new Thread(votingRunnableD3);
        Thread countingD3 = new Thread(countingRunnableD3);

        votingD3.start();
        countingD3.start();

        // Ch03-Step 6 - Pause the main thread for 30 seconds
        try {
            Thread.sleep(30*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /* Ch03-Step 7 - At the end of 30 seconds all threads should be stopped.
           Set the doStop flag in runnables to do this */
        /*
        votingRunnableD1.doStop = true;
        votingRunnableD2.doStop = true;
        votingRunnableD3.doStop = true;

        countingRunnableD1.doStop = true;
        countingRunnableD2.doStop = true;
        countingRunnableD3.doStop = true;
*/


        votingD1.interrupt();
        votingD2.interrupt();
        votingD3.interrupt();

        countingD1.interrupt();
        countingD2.interrupt();
        countingD3.interrupt();



        // Ch03-Step 8 - Uncomment these print statements to print the total votes obtained by the 3 designs
        System.out.println("Voting has stopped for design " + d1.getName());
        System.out.println("Total votes for " + d1.getName() + ": " + d1.getVotes().size());
        System.out.println("Voting has stopped for design " + d2.getName());
        System.out.println("Total votes for " + d2.getName() + ": " + d2.getVotes().size());
        System.out.println("Voting has stopped for design " + d3.getName());
        System.out.println("Total votes for " + d3.getName() + ": " + d3.getVotes().size());

    }
}
