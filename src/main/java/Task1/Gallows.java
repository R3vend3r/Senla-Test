package Gallows;

import javax.swing.*;
import java.util.*;

public class Gallows {

    private static final int LIVES = 7;
    private static final String[] words = {"monkey", "javelin", "imperial", "attention", "irrepressible", "jewellery", "godmother"};


    public static void main(String[] args){
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        String word = words[rand.nextInt(words.length)];
        char[] guessWord = new char[word.length()];
        Arrays.fill(guessWord, '_');

        int lives = LIVES;
        HashSet<Character> letters = new HashSet<>();

        System.out.println("Welcome to Gallows game!!!");

        while(lives > 0 && new String(guessWord).contains("_")){

            System.out.println("\n Word: " + String.valueOf(guessWord));
            System.out.println("Number your lives: " + lives);
            System.out.println("Write a letter: ");

            char guessLetter = scanner.next().toLowerCase().charAt(0);

            if (letters.contains(guessLetter)){
                System.out.println("You write this letter before");
            }
            letters.add(guessLetter);

            if(word.indexOf(guessLetter)>=0){
                for(int i = 0; i <word.length(); i++){
                    if (word.charAt(i) == guessLetter){
                        guessWord[i] = guessLetter;
                    }
                }
                System.out.println("You are right");
            } else {
                lives--;
                System.out.println("OH, your letter is missing");
            }

        }
        if(new String(guessWord).equals(word)){
            System.out.println("Congratulation you are a winner!!!");
        } else{
            System.out.println("Your player is dead");
        }
    }


}
