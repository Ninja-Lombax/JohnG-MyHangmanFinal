package com.example;
import java.util.*;

/**
 * Created by godzillakotm on 10/21/2016.
 */
public class HangmanGame
{
    // Hangman word selection
    private String[] animals = {"AARDVARK", "ALLIGATOR", "ELEPHANT", "HIPPOPOTAMUS", "SHARK", "FOX", "TIGER", "CROCODILE", "LION", "CHEETAH", "WOLF", "FROG", "TOAD", "COW", "RABBIT"};
    private String[] food = {"CHICKEN", "BEEF", "SPINACH", "LETTUCE", "SPAGHETTI", "TACOS", "FAJITAS", "CHEESE", "STEAK", "PIZZA", "CARROTS", "sTRAWBERRIES", "RASPBERRIES", "PORK", "PANCAKES"};
    private String[] drinks = {"WATER", "SODA", "VODKA", "BEER", "WINE", "SHAKES", "COFFEE", "MOCHA", "CAPPUCCINO", "LATTE", "MARGARITA", "DAIQUIRI", "MONSTER", "TEA", "LIQUOR"};
    private String[] heroes = {"GODZILLA", "DEADPOOL", "SUPERMAN", "THOR", "HAWKEYE", "SONIC", "MARIO", "HULK", "AQUAMAN", "AGUMON", "GUILMON", "BATMAN", "ROBIN"};
    private String[] words = {"AARDVARK", "ALLIGATOR", "ELEPHANT", "HIPPOPOTAMUS", "SHARK", "FOX", "TIGER", "CROCODILE", "LION", "CHEETAH", "WOLF", "FROG", "TOAD", "COW", "RABBIT", "CHICKEN", "BEEF", "SPINACH", "LETTUCE", "SPAGHETTI", "TACOS", "FAJITAS", "CHEESE", "STEAK", "PIZZA", "CARROTS", "sTRAWBERRIES", "RASPBERRIES", "PORK", "PANCAKES", "WATER", "SODA", "VODKA", "BEER", "WINE", "SHAKES", "COFFEE", "MOCHA", "CAPPUCCINO", "LATTE", "MARGARITA", "DAIQUIRI", "MONSTER", "TEA", "LIQUOR", "GODZILLA", "DEADPOOL", "SUPERMAN", "THOR", "HAWKEYE", "SONIC", "MARIO", "HULK", "AQUAMAN", "AGUMON", "GUILMON", "BATMAN", "ROBIN"};

    // Variables
    private char[] guessWord;
    private char[] displayedWord;
    private int bodyParts = 0;
    private char[] bUsedLetters = {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'};

    private int totalWord;
    private int userSelection;

    public HangmanGame() // Constructor that sets the Hangman game up
    {

        Scanner numOfPlayers = new Scanner(System.in);
        int numPlayers = 0;
        boolean done = false;


        while(!done)
        {
            try //used to validate user input
            {
                System.out.print("Please pick the number of players.\n1 = One player\n2 = Two player\nYour selection: ");
                numPlayers = numOfPlayers.nextInt();
                if(numPlayers != 1 && numPlayers !=2)
                {

                    throw new Exception("Invalid input"); //Throws an exception if integer is not a valid selection
                }
                done = true;

            }
           catch (InputMismatchException e)
            { // Something other than a number


                System.err.println("You did not enter an integer.  Try again.");

                numOfPlayers.nextLine();

            }

            catch(Exception e) // Thrown if the integer isn't a valid selection
            {

                System.err.println("This is an invalid integer. Please try again");
                numOfPlayers.nextLine();
            }



        }

        if(numPlayers == 1) // Different behavior for one player vs two player game
        {
            // Gets chosen subject
            chosenSubject();
            // Picks a random number
            int chosenWord = (int) Math.floor(Math.random() * totalWord);



            // Converts word to a character array
            switch (userSelection) {
                case 1:
                    guessWord = animals[chosenWord].toCharArray();
                    break;
                case 2:
                    guessWord = food[chosenWord].toCharArray();
                    break;
                case 3:
                    guessWord = drinks[chosenWord].toCharArray();
                    break;
                case 4:
                    guessWord = heroes[chosenWord].toCharArray();
                    break;
                default:
                    guessWord = words[chosenWord].toCharArray();
                    break;
            }
        }
        else // This sets up the two player hangman
        {
            numOfPlayers.nextLine();
            System.out.println("Please enter the word for the player to guess.");
            String getPlayerWord = numOfPlayers.nextLine();

            // Verifies that user inputs a letter or a space
            while(!getPlayerWord.matches("[a-zA-Z ]*"))
            {
                System.out.println("Words can only contain letters a-z and spaces");
                getPlayerWord = numOfPlayers.nextLine();

            }

            getPlayerWord = getPlayerWord.toUpperCase(); // Converts word to uppercase
            guessWord = getPlayerWord.toCharArray(); // Sets the word to a character array

            for(int i = 0; i<5000; i++) // Clears screen so that guessing player cannot see the word
           {
              System.out.println(" ");

           }


        }


        displayedWord = new char[guessWord.length]; // Stores the solved word in a character array

        // Creates the underscores for the word
        for (int i = 0; i < guessWord.length;i++)
        {
            if(guessWord[i] == ' ') // if there is a space, prevents an underscore from appearing
            {

                displayedWord[i] = ' ';
            }
            else // Displays underscore equal to the number of letters in the word
            {
                displayedWord[i] = '-';
            }
        }


    }

    public void chosenSubject()
    {

        Scanner subjectChoice = new Scanner(System.in);


        boolean done = false;

        while(!done)//Validates input
        {
            try // Verifies that input is valid
            {

                System.out.print("Please choose a subject.\n1. animals\n2. food\n3. drink\n4. heroes\n5. all\nYour selection: ");

                userSelection = subjectChoice.nextInt(); //User inputs a number

                // If the user doesn't input a valid number, an exception is thrown
                if (userSelection != 1 && userSelection != 2 && userSelection != 3 && userSelection != 4 && userSelection != 5) // Ensures that only valid selections are inputted
                {
                    throw new Exception("Invalid input");

                }

                // Breaks out of loop if valid selection is inputted
                done = true;

            }
            catch (InputMismatchException e)
            { //Is thrown when a non-integer is entered


                System.err.println("You did not enter an integer.  Try again.");

                subjectChoice.nextLine();

            }
            catch (Exception e)
            { //Is thrown if the integer isn't a valid selection, but is an integer

                System.err.println("This is an invalid subject selection. Please try again");
                subjectChoice.nextLine();
            }

        }


        if(userSelection == 1)
        {
            totalWord = animals.length; // gets the array length


        }
        else if(userSelection == 2)
        {
            totalWord = food.length;

        }
        else if(userSelection == 3)
        {
            totalWord = drinks.length;

        }
        else if(userSelection == 4)
        {
            totalWord = heroes.length;

        }
        else
        {
            totalWord = words.length;

        }

    }







    // Displays the underscores and any guessed letter
    public void displayWordset()
    {
        System.out.print("The word is: ");
        for(int i = 0;i < displayedWord.length;i++)
        {
            System.out.print(displayedWord[i]);

        }

    }

    public void displaySolvedWordset() //Displays the entire word if the game is won or lost
    {
        System.out.print("The word is: ");
        for(int i = 0;i < guessWord.length;i++)
        {
            System.out.print(guessWord[i]);

        }

    }

    // Compares the user's entered letter with the chosen word in the guessWord character word
    public void wordComparison(char[] userLetter)
    {
        boolean letterFound = false;



        for (int i = 0; i < displayedWord.length; i++)
        {
            // If the user's entered letter is equal to the letter in the guess word array, it will copy the letter into the corresponding displayedWord char array

            if (userLetter[0] == guessWord[i])
            {

                displayedWord[i] = userLetter[0];
                letterFound = true; //If a letter is found, true is returned


            }
        }
            // If a letter is not found than a body part is added to the gallow
            if(letterFound == false && userLetter[0] != '1')
            {
                ++bodyParts;

            }


    }

    // Determines if a letter is already used
    public boolean bAlreadyUsedLetter(char[] userLetter)
    {
        // Loop determines if the letter was already used and then returns true
        for(int i = 0; i < bUsedLetters.length;i++)
        {
            if (userLetter[0] == bUsedLetters[i])
            {

                return true;

            }
        }

        // If the letter is not in the array, this function will add the user's letter and then break out of the loop
        for(int j = 0; j < bUsedLetters.length; j++)
        {

            if(bUsedLetters[j] == '-')
            {

                bUsedLetters[j] = userLetter[0];
                break;
            }
        }

        // False is returned
        return false;



    }

    public void bAlreadyUsedLetter(char[] userLetter, int wordHintNo) //Overloaded function. This one is only called when using a hint
    {



        // If the letter is not in the array, this function will add the user's letter and then break out of the loop
        for(int j = 0; j < bUsedLetters.length; j++)
        {

            if(bUsedLetters[j] == '-')
            {

                bUsedLetters[j] = userLetter[wordHintNo];
                break;
            }
        }





    }

    // Displays the used letters
    public void displayUsedLetters()
    {
        System.out.print("\nThe letters you have used are: ");

        // Displays used letters until an underscore is detected
        for(int i = 0; i < bUsedLetters.length;i++)
        {
            // Displays used letters until underscore is displayed
            if(bUsedLetters[i] == '-')
            {
                break;
            }
            if(bUsedLetters[i] != '1') // When a hint is used, it is stored within the used letters array. This prevents the number from being displayed.
            {
                System.out.print(bUsedLetters[i]);
            }
        }
    }

    // Determines if the player won or lost
    public int DidYouWinOrLose()
    {
        int a = 1;

        // Searches for an underscore, if there is still one, than 0 is returned. This determines that there still is a letter than needs to be guessed
        for(int i = 0; i < displayedWord.length;i++)
        {
            if(displayedWord[i] == '-')
            {
                a=0;

            }

        }

        // If all the body parts are uncovered, than 2 is returned.
            if (bodyParts == 8)
            {

                a=2;

            }
            // Otherwise, 1 is returned
            return a;

    }


    // This will display the body parts
    public void displayBody()
    {
        System.out.println("\nYour body parts are:");

        if(bodyParts == 1)
        {
            System.out.println("----");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");

        }
        if(bodyParts == 2)
        {
            System.out.println("----");
            System.out.println("|  O");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");

        }
        if(bodyParts == 3)
        {
            System.out.println("----");
            System.out.println("|  O");
            System.out.println("|  |");
            System.out.println("|");
            System.out.println("|");

        }
        if(bodyParts == 4)
        {
            System.out.println("----");
            System.out.println("| O");
            System.out.println("|\\|");
            System.out.println("|");
            System.out.println("|");

        }
        if(bodyParts == 5)
        {
            System.out.println("----");
            System.out.println("| O");
            System.out.println("|\\|/");
            System.out.println("|");
            System.out.println("|");

        }
        if(bodyParts == 6)
        {
            System.out.println("----");
            System.out.println("| O");
            System.out.println("|\\|/");
            System.out.println("|/");
            System.out.println("|");

        }
        if(bodyParts == 7)
        {
            System.out.println("----");
            System.out.println("| O");
            System.out.println("|\\|/");
            System.out.println("|/ \\");
            System.out.println("|");

        }
    }

    // Hint system
    public void wordHint()
    {
        boolean letterDisplayed = true;
        int wordHintNo = 0;

        // Continues loop until an empty space is found in the word
        while(letterDisplayed == true) // runs through loop as long as letterDisplayed is true
        {
            wordHintNo = (int) Math.floor(Math.random() * (guessWord.length)); //Generates a random number for the letter to appear at
            if(displayedWord[wordHintNo] == '-') // Determines if the numbered spot is empty
            {
                displayedWord[wordHintNo] = guessWord[wordHintNo]; // Replaces the underscore with the correct letter

                letterDisplayed = false; //If a letter is found, the loop is ended.
            }
        }

        // Finds all duplicate letters found from the loop above
        for(int i = 0; i < guessWord.length; i++)
        {

            if(guessWord[i] == guessWord[wordHintNo])
            {
                displayedWord[i] = guessWord[wordHintNo];

            }

        }

        // Adds the letter to the already used letter array
        bAlreadyUsedLetter(guessWord, wordHintNo);



    }
}



