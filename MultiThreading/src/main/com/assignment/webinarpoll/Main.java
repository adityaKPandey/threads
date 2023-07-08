package com.assignment.webinarpoll;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Design d1 = new Design(1,"D1");
        // Ch02-Step 5 - Create an object of the VotingRunnable & CountingRunnable for design1
        VotingRunnable votingRunnableDesign1 = new VotingRunnable(d1);
        CountingRunnable countingRunnableDesign1 = new CountingRunnable(d1);

        // Ch02-Step 6.1 - Create a new Thread instance, passing in the VotingRunnable object for design1
        Thread votingForDesign1 = new Thread(votingRunnableDesign1);

        // Ch02-Step 6.2 - Create a new Thread instance, passing in the CountingRunnable object for design1
        Thread countingForDesign1 = new Thread(countingRunnableDesign1);


        // Ch02-Step 7.1 - Start the voting thread for design1


        votingForDesign1.start();

        //votingForDesign1.join();
        // Ch02-Step 7.2 - Start the counting thread for design1
        countingForDesign1.start();


        Design d2 = new Design(2,"D2");
        // Ch02-Step 5 - Create an object of the VotingRunnable & CountingRunnable for design2
        VotingRunnable votingRunnableDesign2 = new VotingRunnable(d2);
        CountingRunnable countingRunnableDesign2 = new CountingRunnable(d2);

        // Ch02-Step 6.1 - Create a new Thread instance, passing in the VotingRunnable object for design2
        Thread votingForDesign2 = new Thread(votingRunnableDesign2);

        // Ch02-Step 6.2 - Create a new Thread instance, passing in the CountingRunnable object for design2
        Thread countingForDesign2 = new Thread(countingRunnableDesign2);


        // Ch02-Step 7.1 - Start the voting thread for design2
        votingForDesign2.start();

        //votingForDesign2.join();
        // Ch02-Step 7.2 - Start the counting thread for design2
        countingForDesign2.start();

        Design d3 = new Design(3,"D3");
        // Ch02-Step 5 - Create an object of the VotingRunnable & CountingRunnable for design3
         VotingRunnable votingRunnableDesign3 = new VotingRunnable(d3);
         CountingRunnable countingRunnableDesign3 = new CountingRunnable(d3);

        // Ch02-Step 6.1 - Create a new Thread instance, passing in the VotingRunnable object for design3
        Thread votingForDesign3 = new Thread(votingRunnableDesign3);

        // Ch02-Step 6.2 - Create a new Thread instance, passing in the CountingRunnable object for design3
        Thread countingForDesign3 = new Thread(countingRunnableDesign3);

        // Ch02-Step 7.1 - Start the voting thread for design3
        votingForDesign3.start();

        //votingForDesign3.join();
        // Ch02-Step 7.2 - Start the counting thread for design3
        countingForDesign3.start();



    }
}
