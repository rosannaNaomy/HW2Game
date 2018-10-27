package CYOA_Pursuit_HW_PORTILLO_NAOMY;

import java.util.ArrayList;
import java.util.Scanner;

public class Hourglass implements Game {

    Graphics graphics = new Graphics();
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> thoughts = new ArrayList<>();
    int randRoom;
    private String userInput = "";

    @Override
    public void startGame() {
        graphics.logo();
        instructions();
        System.out.println(dialogue(0));
        toContinue();
        scanner.nextLine();
        checkRoom();
    }

    private void instructions() {
        System.out.println("\nInstructions:\n" +
                "\n" +
                "To exit the game enter 0 \n" +
                "You were given an hourglass, you didn't realize what this hourglass meant until you knocked it off the table causing it to break.\n" +
                "You were sent to a different dimension after breaking the hourglass.\n" +
                "You will be randomly switching through rooms in a house. One room will restore your hourglass.\n" +
                "Once your hourglass is restored you will have the opportunity to return home. \n");
    }

    private void input() {
        System.out.println("Keep going, you're almost home.");
        toContinue();
        scanner.nextLine();
        System.out.println("I'll keep track of your thoughts, tell me something:");
        setUserInput();
        randRoom = room();
    }

    private void toContinue(){
        System.out.println("\nPress enter to continue...");
    }

    private String dialogue(int i){
        String []dlog = {"Hey! Wake up! you don't have much time.", "Restore your hourglass."};
        return dlog[i];
    }

    private void setUserInput(){

         thoughts.add(this.userInput = scanner.nextLine().toLowerCase());
    }

    private void printThoughts(){
        for (String s: thoughts) {
            System.out.println(s);
        }
    }

    private int room() {
        int random = (int) Math.ceil(Math.random() * 5);
        return random;
    }

    private void checkRoom() {
        int newRoom = 1;
        do {
            switchRooms(newRoom);
            input();
            newRoom = room();
            switchRooms(newRoom);
            if(userInput.equals("0")){
                System.out.println("You exited the game.");
                break;
            } else if (newRoom == 5){
                switchRooms(newRoom);
                System.out.println("You have reached the last room, You can return home.");
                break;
            }
        } while (true);
        System.out.println();
        System.out.println("Your thoughts as you were trying to leave this place:");
        printThoughts();
        System.out.println();
        System.out.println("You've reached the end.");
        endOfGame();
    }


    private void switchRooms(int roomNum ){

        switch (roomNum){
            case 1: graphics.startMap();
                System.out.println("\nYou are in the first room. You must leave this room and find a way out of this house.");
                break;
            case 2: graphics.leftRoom();
                System.out.println("You are in the left room. You have collected magic dust to restore your hourglass");
                break;
            case 3: graphics.rightRoom();
                System.out.println("You are in the right room. This room is empty but smells like gingerbread cookies but you can't stay! \n" +
                        "Or you'll end up stuck here looking for cookies for all eternity.\n");
                break;
            case 4: graphics.midRoom();
                System.out.println("You are in the middle room. In the darkest corner, you see what you fear the most. Leave!");
                break;
            case 5: graphics.lastRoom();
                System.out.println("You are in the last room, your hourglass has been restored.");
                break;
        }
    }

    public void endOfGame() {

        String input;
        do {
            System.out.print("Play Again? [Y/n]: ");
            input = scanner.nextLine();
            if (input.length() == 0 || input.charAt(0) == 'y' ) {
                startGame();
                break;
            } else if (input.charAt(0) == 'n' ) {
                System.out.println("Thanks for playing!");
                break;
            } else {
                System.out.println("Invalid entry");
            }
        }
        while (input.length() != 0 || input.charAt(0) != 'y' ||  input.charAt(0) != 'n' );
    }
}
