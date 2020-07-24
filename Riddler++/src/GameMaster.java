public class GameMaster {
    /**
     * GameMaster Stub
     * 	 * @author WLHonig
     * 	 * @version 9
     *
     *   Just enough to compile and run main
     */

    public int numberRoundsToPlay = 3;
    public GameMaster(String f) {
        return;
    }

    public boolean playRound() {
       if( numberRoundsToPlay-- > 0 ) {
           return true;
       }
       return false;
    }

    public void exitGame() {
        return;
    }
}
