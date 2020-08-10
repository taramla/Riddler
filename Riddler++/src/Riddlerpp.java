public class Riddlerpp
{

	/**
	 * main method for Riddler++
	 * @param args (currently unused)
	 * @author WLHonig
	 * @version 9
	 *
	 * Set up the riddle contest by creating a GameMaster
	 * Then keep playing rounds until GameMaster says to stop
	 * At end, allow GameMaster to initiate any necessary clean up then exit
	 *
	 * By intent, no user interaction.
	 * Possibly future extension, pass fully qualified file name as parameter to replace the constant
	 */

	public static final String fileName = "Riddler++/myRiddles.txt";

	public static void main(String[] args) throws Exception {
		boolean keepPlaying;
		GameMaster g;
		g = new GameMaster( fileName );
		keepPlaying = true;

		while( keepPlaying ) {
			keepPlaying = g.playRound();
		}

		g.exitGame();
	}
}
