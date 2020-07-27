/**
 * Player class for Riddler++
 * @author taramla
 * @version 10
 *
 * Set up all player attributes
 * Create methods for game play flow and interaction with user
 *
 */
import java.util.Scanner;
public class Player {
    private String name;
    private int score;
    private long startTime;
    private long endTime;
    Scanner keyboard = new Scanner (System.in);
    public Player(){

    }
    public String askRoundSetUp() {
        String difficultyReply;
        String numberToPlayReply;
        System.out.println("Enter a difficulty you'd like to play");
        difficultyReply = keyboard.nextLine();
        System.out.println("Enter a number of riddles you'd like to play");
        numberToPlayReply = keyboard.nextLine();
        return difficultyReply +  "," + numberToPlayReply;
    }
    public boolean startTimer(){
        startTime = System.nanoTime();
       return true;
    }
    public String askRiddleQuestion(String riddleQuestion){
        String answer ="";
        System.out.println(riddleQuestion);
        answer = keyboard.nextLine();
        return answer;
    }
    public String tellWrong(int value){
        String reply = "";
        score = score-value;
        System.out.println("Wrong, enter h for hint, g for another guess, or s for skip");
        reply = keyboard.nextLine();
        return reply;
    }
    public void tellCorrect(int value){
        score = score+value;
        System.out.println("Great! That's correct");

    }
    public String askRiddleHint(String riddleHint){
        String answer="";
        System.out.println("Hint is: " + riddleHint);
        System.out.println("What is your guess?");
        answer = keyboard.nextLine();
        return answer;
    }
    public boolean endTimer(){
        endTime= System.nanoTime();
        return true;
    }
    public void calculateScore(){
        long totalTime = (endTime-startTime)/1000000000;
        System.out.println("For this round, your time was: " + totalTime + " and your score was: " + score);
    }
    public boolean askPlayAgain(){
        String againAskReply = "";
        System.out.println("Would you like to play again?");
        againAskReply = keyboard.nextLine();
        if (againAskReply.equals("yes")){
            return true;
        }
        else {
            return false;
        }
    }
    public void goodbye() {
        System.out.print("Thanks for playing! Goodbye");
        }
    }


