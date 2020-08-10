import java.util.*;
public class GameMaster<fileName> {
    /**
     * GameMaster Stub
     * 	 * @author kaylinluck
     * 	 * @version 10
     * 	 * @version 10.1 DH comments from code reading inline, marked //dHn for referenc
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
        //dH1: yes, but it need not be a public method, we show in class diagram that Player has it in an attribute and can use it; GM does not need to know name
        //dH2: see style guide A1.6.4, never throw "Exception", but a more specific one, here likely file problem passes up from RiddleDataBase to GameMaster to main (for now)
        myRDB = new RiddleDataBase(fileName);
        unusedListOfRiddles = myRDB.getAllRiddles();

    }


    public boolean playRound() {
        while(!response.equals("quit") || roundOver == true) {
            String setUp = myPlayer.askRoundSetUp();
            //dH3: I despise declarations and assignments on the same line; strongly urge a cast of characters at the start of the method for local varialbes
            //dh3: here you will also declare a new String object each time through the loop which is not necessary and inefficient
            //dh4: UML shows two values returned from askRoundSetup, we should update the UML to show it's all one String with "difficulty,numberToPlay"
            String[] s = setUp.split(",");
            //dH5: check size of s, must be 2 or throw exception (I would suggest a postcondition on askRoundSetUp to ensure length two instead; then don't need to check here)
            difficulty = s[0];
            numberToPlay = Integer.parseInt(s[1]);
            riddlesForRound = makeRiddleList(difficulty, numberToPlay);
            //dH6: nice helper method; playRound is a bit long now, could move more out
            int c = rand.nextInt(riddlesForRound.size() - 1);
            //dh3again:  all over again, you really don't want to declare a new c each time, right?
            currentRiddle = riddlesForRound.get(c);
            myPlayer.startTimer();
            for (int i = 0; i <= riddlesForRound.size() - 1; i++) {
                //dH7: middle term can be replaced by numberToPlay??
                response = myPlayer.askRiddleQuestion(currentRiddle.riddleQuestion);
                boolean nextRiddle = false;
                int counter = 0;
                //dH8: counter for what, better variable name needed.  Is this same as i in the for loop?
                while (nextRiddle != true) {
                    if (currentRiddle.checkRiddleAnswer(response) == true) {
                        riddlesForRound.remove(currentRiddle);
                        //dH9: I am confused on the control flow here; why not remove currentRiddle right after get it?
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
