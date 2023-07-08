package com.assignment.webinarpoll;

/**
 * Contains code that represents the voting process.
 * It will keep track of what cake designs are getting votes.
 */

public class VotingRunnable implements Runnable { // Ch02-Step 1 - Implement class by the Runnable interface

    private Design d;
    protected boolean doStop = false; // Will be used in chapter 3 challenge

    public VotingRunnable(Design d) {
        this.d = d;
    }


    // Ch02-Step 2 - Override the run() method
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        //while(!doStop) {


        d.getVotes().add(1L);
        System.out.println("Voting is going on for design : " + d.getName());
            doStop = (System.currentTimeMillis() - startTime >= 3*1000) ;
        //}

    }

}
