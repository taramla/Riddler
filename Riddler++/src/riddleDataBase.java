import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * riddleDataBase class for Riddler++
 * @author taramla
 * @version 10
 *
 * Used to create one hashmap of key (easy, medium, and hard), value (ArrayList<Riddle>)
 *
 */
public class riddleDataBase {
    private HashMap<String, ArrayList<Riddle>> fullListOfRiddles;

    public riddleDataBase(String fileName) throws Exception{
        String data = "";
        ArrayList<Riddle> easyRiddles;
        ArrayList<Riddle> medRiddles;
        ArrayList<Riddle> hardRiddles;
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        String[] riddleArray = data.split("\n");
        for (String r :riddleArray) {
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
    }

    public HashMap getAllRiddles(){
        return fullListOfRiddles;
    }

    private Riddle makeRiddle(String data) {
        String[] riddleArray = data.split("/");
        String riddleQuestion = riddleArray[0];
        String riddleAnswer = riddleArray[1];
        String riddleHint =riddleArray[2];
        String difficulty = riddleArray[3];
        String value = riddleArray[4];
        Riddle singleRiddle = new Riddle(riddleQuestion,riddleAnswer,riddleHint,difficulty,Integer.parseInt(value));
        return singleRiddle;
    }

}

