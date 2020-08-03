import java.util.*;
public class GameMaster<fileName> {
    /**
     * GameMaster Stub
     * 	 * @author kaylinluck
     * 	 * @version 10
     *
     *   A nearly complete version of GM
     */


    private Player myPlayer;
    private RiddleDataBase myRDB;
    private HashMap<String, ArrayList<Riddle>> unusedListOfRiddles;
    private ArrayList<Riddle> riddlesForRound;
    private Riddle currentRiddle;
    private boolean play = true;
    private boolean roundOver = false;
    private String response = "";
    private int numberToPlay;
    private String difficulty;
    Random rand = new Random();

    public GameMaster(String fileName) throws Exception { //unfinished
        //need a method in Player like askPlayerInfo(): String to get player name, etc.
        myRDB = new RiddleDataBase(fileName);
        unusedListOfRiddles = myRDB.getAllRiddles();
    }


    public boolean playRound() {
        while(!response.equals("quit") || roundOver == true) {
            String setUp = myPlayer.askRoundSetUp();
            String[] s = setUp.split(",");
            difficulty = s[0];
            numberToPlay = Integer.parseInt(s[1]);
            riddlesForRound = makeRiddleList(difficulty, numberToPlay);
            int c = rand.nextInt(riddlesForRound.size() - 1);
            currentRiddle = riddlesForRound.get(c);
            myPlayer.startTimer();
            for (int i = 0; i <= riddlesForRound.size() - 1; i++) {
                response = myPlayer.askRiddleQuestion(currentRiddle.riddleQuestion);
                boolean nextRiddle = false;
                int counter = 0;
                while (nextRiddle != true) {
                    if (currentRiddle.checkRiddleAnswer(response) == true) {
                        riddlesForRound.remove(currentRiddle);
                        if (counter == 0) {
                            myPlayer.tellCorrect(currentRiddle.value);
                        }
                        nextRiddle = true;
                    }
                    else {
                        counter++;
                        response = myPlayer.tellWrong(currentRiddle.value);
                        if (response.equals("h")) {
                            String hint = currentRiddle.findRiddleHint();
                            response = myPlayer.askRiddleHint(hint);
                        }
                        else if (response.equals("s")) {
                            riddlesForRound.remove(currentRiddle);
                            nextRiddle = true;
                        }
                        else if (response.equals("g")) {
                            response = myPlayer.askRiddleQuestion(currentRiddle.riddleQuestion);
                        }
                    }
                }
                if (riddlesForRound.size() != 0) {
                    c = rand.nextInt(riddlesForRound.size() - 1);
                    currentRiddle = riddlesForRound.get(c);
                }
                else {
                    myPlayer.endTimer();
                    roundOver = true;
                }
            }
        }
        myPlayer.calculateScore();
        play = myPlayer.askPlayAgain();
        return play;
    }

    public ArrayList<Riddle> makeRiddleList(String difficulty, int numberToPlay){
        ArrayList<Riddle> r = new ArrayList<>();
        ArrayList<Riddle> chosenDifficulty = unusedListOfRiddles.get(difficulty);
        for(int i = 0; i < numberToPlay; i++){
            int k = rand.nextInt(chosenDifficulty.size() - 1);
            r.add(chosenDifficulty.get(k));
        }
        return r;
    }

    public void exitGame() { //unfinished
        myPlayer.goodbye();
        //clean up code needed here
        return;
    }
}
