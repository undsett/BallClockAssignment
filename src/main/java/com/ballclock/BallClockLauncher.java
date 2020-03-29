package com.ballclock;

import java.util.Scanner;

public class BallClockLauncher {
    public static void main(String[] args) {
        printHelp();
        Scanner scanner = new Scanner(System.in);
        String mode = scanner.next();
        try {
            switch (mode){
                case "-mode1":
                    new BallClock(scanner.nextInt(), 0).runClock();
                    break;
                case "-mode2":
                    int balls = scanner.nextInt();
                    int minutes = scanner.nextInt();
                    if (!(minutes > 0)){
                        throw new WrongArgumentException("Number of minutes must be greater then 0.");
                    }
                    new BallClock(balls, minutes).runClock();
                    break;
                default:
                    throw new WrongArgumentException("Valid options are '-mode1' or '-mode2' please chose one of them.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printHelp() {
        System.out.println("Enter '-mode1' and number of balls or '-mode2' and number of balls and minutes.");
    }
}
