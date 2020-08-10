import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * riddleDataBase class for Riddler++
 * @author taramla
 * @version 11
 * @version 10.1 dH comments inline, marked //dHn
 *
 * Used to create one hashmap of key (easy, medium, and hard), value (ArrayList<Riddle>)
 *
 */
public class RiddleDataBase {
    private HashMap<String, ArrayList<Riddle>> fullListOfRiddles;

    public RiddleDataBase(String fileName) throws Exception{
        fullListOfRiddles = new HashMap<>();
        String data = "";
        ArrayList<Riddle> easyRiddles = new ArrayList<>();
        ArrayList<Riddle> medRiddles = new ArrayList<>();
        ArrayList<Riddle> hardRiddles = new ArrayList<>();
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        String[] riddleArray = data.split("\n");
        for (String r : riddleArray) {
            Riddle newRiddle = makeRiddle(r);
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
        fullListOfRiddles.put("easy", easyRiddles);
        fullListOfRiddles.put("medium", medRiddles);
        fullListOfRiddles.put("hard", hardRiddles);
        System.err.println("number of easy riddles: " + easyRiddles.size());
        System.err.println("number of medium riddles: " + medRiddles.size());
        System.err.println("number of hard riddles: " + hardRiddles.size());
        if ((easyRiddles.size() + medRiddles.size() + hardRiddles.size()) == riddleArray.length) {
            System.err.println("The input file matches");
        }
        else {
            System.err.println("Input file mismatch. File length: " + riddleArray.length + "Sum length: " + easyRiddles.size() + medRiddles.size() + hardRiddles.size());
        }

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
    private Riddle makeRiddle(String data)  {
        String[] riddleArray = data.split("/");
        //dH3: HOWEVER, you do need to check for number you get from split; likewise some of these cannot be empty string, right? value must be valid int so you need to catch exception here
        String riddleQuestion = riddleArray[0];
        String riddleAnswer = riddleArray[1];
        String difficulty = riddleArray[2];
        String riddleHint = riddleArray[3];
        String value = riddleArray[4];
        Riddle singleRiddle = new Riddle(riddleQuestion,riddleAnswer,riddleHint,difficulty,Integer.parseInt(value));
        return singleRiddle;
    }

}

