import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * riddleDataBase class for Riddler++
 * @author taramla
 * @version 10
 * @version 10.1 dH comments inline, marked //dHn
 *
 * Used to create one hashmap of key (easy, medium, and hard), value (ArrayList<Riddle>)
 *
 */
public class RiddleDataBase {
    private HashMap<String, ArrayList<Riddle>> fullListOfRiddles;

    public RiddleDataBase(String fileName) throws Exception{
        String data = "";
        ArrayList<Riddle> easyRiddles = new ArrayList<>();
        ArrayList<Riddle> medRiddles = new ArrayList<>();
        ArrayList<Riddle> hardRiddles = new ArrayList<>();
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        String[] riddleArray = data.split("\n");
        //dh4: this is really cool code; no idea if it works, but I like it; is this how you did it in Comp170??
        for (String r :riddleArray) {
            Riddle newRiddle = makeRiddle(r);
            //dh5: here, unlike my comment below and in K's code, we really do need to make a new object each time and do need to declare in the loop
            if (newRiddle.difficulty.equals("easy")) {
                easyRiddles.add(newRiddle);
            }
            else if (newRiddle.difficulty.equals("medium")) {
                medRiddles.add(newRiddle);
            }
            else {
                hardRiddles.add(newRiddle);
            }
        }
        //dH6: I'd add a debug output here with the number of riddles of each type; maybe compare the sum to the length of riddleArray
        fullListOfRiddles.put("easy", easyRiddles);
        fullListOfRiddles.put("medium", medRiddles);
        fullListOfRiddles.put("hard", hardRiddles);
    }

    public HashMap getAllRiddles(){
        return fullListOfRiddles;
    }


    //dH1: always leave white space between methods, my pass at javadoc, you can correct. I started reading here. Seems good helper function

    /**
     * makeRiddle internal helper method
     *
     * @param data one line of text from the Riddle file
     * @return Riddle object created from the text input
     */
    private Riddle makeRiddle(String data) {
        String[] riddleArray = data.split("/");
        //dH2: I hate declarations and assignments on same line; unlike similar comments in K's code, here is may be ok since it's soooo simple
        //dH3: HOWEVER, you do need to check for number you get from split; likewise some of these cannot be empty string, right? value must be valid int so you need to catch exception here
        String riddleQuestion = riddleArray[0];
        String riddleAnswer = riddleArray[1];
        String riddleHint =riddleArray[2];
        String difficulty = riddleArray[3];
        String value = riddleArray[4];
        Riddle singleRiddle = new Riddle(riddleQuestion,riddleAnswer,riddleHint,difficulty,Integer.parseInt(value));
        return singleRiddle;
    }

}

