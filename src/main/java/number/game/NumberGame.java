package number.game;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        NumberGame game = new NumberGame();

        System.out.println("Welcome to Number Game");
        game.numberOfGuesses = game.askUser("How many chances to do wanna get to guess the number? (10 is the limit)", 11);
        game.play(game.numberOfGuesses, game.generateRandomNumber());
        System.out.println("Game Over...");
    }

    Random random = new Random();
    ScannerWrapper scanner = new ScannerWrapper();
    private int randomNumber;
    private boolean anotherGame;
    private int userGuess =0;
    int numberOfGuesses;
    int player;

    int computer;

    public boolean isAnotherGame() {
        return anotherGame;
    }
    public int getPlayer() {
        return player;
    }

    public int getComputer() {
        return computer;
    }


    public void setScanner( ScannerWrapper scanner){
        this.scanner = scanner;
    }

    // generate random number from 1 to 100
    public int generateRandomNumber(){
        randomNumber = random.nextInt(101);
        return randomNumber;
    }
    public boolean anotherGame(){
        System.out.println("Do you wanna play again? (Y)Yes, (N)No");
        String decision = scanner.nextLine();
        if (decision.equalsIgnoreCase("y")){
            randomNumber = generateRandomNumber();
            play(numberOfGuesses, randomNumber);
            return true;
        }else
            System.out.println("final score \n>>Computer: "+ computer+ "\n>>You: "+ player);
        return false;
    }

    //ask user for input
    public int askUser(String message, int limit){
        String userGuess = null;
        boolean rightInput = false;

        while (!rightInput){
            System.out.println(message);
            userGuess = scanner.nextLine();
            if (convertToInt(userGuess, limit) == null){
                System.out.println("- You didn't enter a number or your number is not within limit");
            }
            else if (convertToInt(userGuess, limit) <= 0) {
                System.out.println("- You not within the limit");
            } else {
                convertToInt(userGuess, limit);
                rightInput = true;
            }
        }
        return Integer.valueOf(userGuess);
    }

    private Integer convertToInt(String input, int limit){

        try {
            int number = Integer.valueOf(input);
            if (number < limit){
                return number;
            }else {
                return 0;
            }
        }catch (NumberFormatException e){
            return null;
        }
    }

    public boolean play(int guesses, int randomNumber){

        System.out.println("A number from 1-100 has be randomly generated. you have "+guesses+ " guesses");


        while (guesses > 0){

            userGuess = askUser("Guess the number: ", 100);

            if (userGuess < randomNumber){
                System.out.println( userGuess+ " is Too low");
                guesses-=1;
            }
            else if (userGuess  > randomNumber){
                System.out.println(userGuess+" is Too high");
                guesses-=1;
            }else {
                break;
            }
            System.out.println("Guesses left:" + guesses);
        }
        results(userGuess,randomNumber,guesses);
        System.out.println();
        anotherGame= anotherGame();
        return true;
    }



    public boolean results(int userGuess, int randomNumber, int guesses){
        if (userGuess == randomNumber) {
            System.out.println();
            player +=1;
            System.out.println("Correct Guess. Well done!");
            System.out.println("Computer: "+ computer+ "\nYou: "+ player);
            return true;
        } else if (guesses == 0) {
            computer +=1;
            System.out.println("Computer: "+ computer+ "\nYou: "+ player);
            System.out.println("|____________________|");
            System.out.println("|You're out of guess.|\n"+"|The number is "+randomNumber +"    |");
            System.out.println("|____________________|");
        }
        return false;
    }

}
