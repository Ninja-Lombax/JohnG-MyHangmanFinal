package com.example;

/**
 * Created by godzillakotm on 10/21/2016.
 */
public class HangmanGame {

    public HangmanGame()
    {
        String[] words = {"GODZILLA", "LION", "DRAGON", "DINOSAUR", "JAPAN", "USA", "LOMBAX", "CRAYON", "TABLE", "INCLUDE", "SUBSTANCE", "GIRL", "BOY", "ISLAND"};
        int chosenWord = (int) Math.floor(Math.random() * 13);
        char[] guessWord = words[chosenWord].toCharArray();


        char[] displayedWord = new char[guessWord.length];


        for (int i = 0; i < guessWord.length;i++)
        {
           displayedWord[i] = '_';

        }
        int[] bodyPartsArray = {0,0,0,0,0};
    }

}
