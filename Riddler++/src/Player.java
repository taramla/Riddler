/**
 * Player class for Riddler++
 * @author taramla
 * @version 9
 *
 * Set up all player attributes
 * Create methods for game play flow and interaction with user
 *
 */
public class Player {
    private String name;
    private int score;
    private int startTime; //long
    private int endTime; //long

    Player(){ }
    public void askRoundSetUp() {
        // String askDiff = "Enter a difficulty you'd like to play";
        // String askNum = "Enter a number of riddles you'd like to play";
        String difficulty;
        int numberToPlay;
    }
    public boolean startTimer(){
        //startTime = System.nanoTime();
       return true;
    }
    public String askRiddleQuestion(String riddleQuestion){
        String answer ="";
        System.out.println(riddleQuestion);
        return answer;
    }
    public String tellWrong(int value){
        score = score-value;
        return "Would you like to get a hint, guess again, or skip";
    }
    public String tellCorrect(int value){
        score = score+value;
        return "Great! The next riddle is: ";
    }
    public String askRiddleHint(String riddleHint){
        String answer="";
        System.out.println("Hint is: " + riddleHint);
        return answer;
    }
    public boolean endTimer(){
        //endTime= System.nanoTime();
        return true;
    }
    public String calculateScore(){
        int totalTime = (endTime-startTime)/1000000000; //long
        return "For this round, your time was: " + totalTime + " and your score was: " + score;
    }
    public String askPlayAgain(){
        String againAsk = "Would you like to play again?";
        String againAskAnswer = "";
        if (againAskAnswer.equals("yes")){
            return playRound();
        }
        else (againAskAnswer.equals("no")){
            return exitGame();
        };
    }
    public String goodbye(){
        System.exit(0);
        return "Thanks for playing!";

    }
    }


