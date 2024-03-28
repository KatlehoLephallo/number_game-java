package number.game;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        NumberGame game = new NumberGame();
        System.out.println("Welcome to Number Game");
        game.play();
    }

    Random random = new Random();
    private int randomNumber = generateRandomNumber();
    private int userGuess =0;
    Scanner scanner = new Scanner(System.in);

    // generate random number from 1 to 100
    public int generateRandomNumber(){
        randomNumber = random.nextInt(101);
        return randomNumber;
    }
    public void anotherGame(){
        System.out.println("Do you wanna play again? (Y)Yes, (N)No");
        String decision = scanner.nextLine();
        if (decision.equalsIgnoreCase("y")){
            play();
        }else
            System.out.println("Thank you for playing...");
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

    public void play(){

        int guesses = askUser("How many chances to do wanna get? (10 is the limit)", 11);

        System.out.println("A number from 1-100 has be randomly generated. you have "+guesses+ " guesses");
        randomNumber = generateRandomNumber();

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
        anotherGame();
    }

    public void results(int userGuess, int randomNumber, int guesses){
        if (userGuess == randomNumber) {
            System.out.println();
            System.out.println("Correct Guess. Well done!");
        } else if (guesses == 0) {
            System.out.println("|____________________|");
            System.out.println("|You're out of guess.|\n"+"|The number is "+randomNumber +"    |\n|Game over           |");
            System.out.println("|____________________|");
        }
    }

}
