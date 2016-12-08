package com.example;
import java.util.*;

public class Hangman
{


    public static void main(String[] args)
    {
        int playAgain = 1;
        while (playAgain == 1)
        {
            boolean bUsedHint = false;
            // Initializes the game
            HangmanGame hangPerson = new HangmanGame();



            // Variables
            int gameLoop = 0;
            boolean newCharacter = false;
            boolean done = false;
            Scanner userLetter = new Scanner(System.in);

            System.out.println("Hello and welcome to Hangman!");

            while (gameLoop == 0) // While gameLoop is 0, this loop executes
            {
                // Displays the underscores and letters already chosen
                hangPerson.displayWordset();

                // Displays body parts
                hangPerson.displayBody();


                // Function that displays all used letters.
                hangPerson.displayUsedLetters();

                // User inputs a letter
                char[] chosenLetter = userInput();

                // Determines if a hint is used
                if (bUsedHint == false && chosenLetter[0] == '1')
                {
                    hangPerson.wordHint(); //Function is called if the #1 is entered
                    bUsedHint = true; // This prevents the user from using multiple hints
                }

                // Determines if the letter is already used before
                newCharacter = hangPerson.bAlreadyUsedLetter(chosenLetter);

                // This loop executes as long as the user inputs a letter they have already used
                while (newCharacter == true)
                {


                    if (chosenLetter[0] == '1') //Displays if hint has been previously used
                    {
                        System.out.println("You have already used your hint for this game.");


                    }
                    else // Displays if letter is already used.
                    {
                        System.out.println("Sorry. You have previously chosen this letter. Please try another letter.");

                    }
                    chosenLetter = userInput(); // User inputs letter again
                    newCharacter = hangPerson.bAlreadyUsedLetter(chosenLetter); // Determines if the new letter has been used
                }

                // Checks to see if the letter is in the char array and replaces the letters
                hangPerson.wordComparison(chosenLetter);

                // Determines if the user wins or loses
                gameLoop = hangPerson.DidYouWinOrLose();


            }
            if (gameLoop == 1) // The player won
            {
                System.out.println("Congratulations. You have guessed the word. ");
                hangPerson.displayWordset();

            }
            if (gameLoop == 2) // The player lost
            {
                System.out.println("Sorry you have lost the game. Please play again. ");
                hangPerson.displaySolvedWordset();
            }



            while(!done)
            {
                try //Validates input to make sure that only a 1 or 2 is entered
                {

                    System.out.println("\n\nWould you like to play again?\n1. Yes\n2. No\nYour selection:");
                    playAgain = userLetter.nextInt(); // Game continues as long as yes is entered
                    if(playAgain != 1 && playAgain != 2)
                    {
                        throw new Exception("out of bounds"); //Throws this exception if a valid input isn't entered

                    }
                    done = true; // Breaks out of loop if all inputs are valid

                }
                catch (InputMismatchException e)
                { // Something other than a number


                    System.err.println("You did not enter an integer.  Try again.");

                    userLetter.nextLine();

                }

                catch(Exception e)
                {
                    // Invalid number is entered

                    System.err.println("This is an invalid selection. Please select again.");
                    userLetter.nextLine();
                }




            }

        }
            if(playAgain == 2) // If 2 is entered from above, the game ends
            {

                System.out.println("Thank you for playing.");
            }
    }

    public static char[] userInput() // User inputs a letter
    {
        //boolean usedHint = false; // Determines if the hint is used
        Scanner userLetter = new Scanner(System.in);
        System.out.print("\nPlease choose a letter. Enter 1 for a hint (You can only use this ability once.): ");
        String chooseLetter = userLetter.next(); // User inputs letter
        while(!chooseLetter.matches("[a-zA-Z1-1]*")) //Verifies that only a letter from A-Z or the #1 is entered
        {
            System.out.println("Words can only contain letters a-z and the number 1 for hints");
            System.out.println("Please select a letter (Use 1 for a hint): ");
            chooseLetter = userLetter.next();

        }
        chooseLetter = chooseLetter.toUpperCase(); // Converts letter to uppercase for easy comparison

        // Converts the string to a char array
        char[] chosenLetter = chooseLetter.toCharArray();


        return chosenLetter; // returns the user's letter

    }
}

