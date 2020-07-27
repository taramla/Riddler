public class Riddle {
    public String riddleQuestion;
    private String riddleAnswer;
    private String riddleHint;
    public String difficulty;
    public int value;

    public Riddle(String riddleQuestion, String riddleAnswer, String riddleHint, String difficulty, int value ){
        this.riddleQuestion = riddleQuestion;
        this.riddleAnswer = riddleAnswer;
        this.riddleHint = riddleHint;
        this.difficulty = difficulty;
        this.value = value;
    }
    public boolean checkRiddleAnswer(String answer){
        if(answer.equals(riddleAnswer)) return true;
        else return false;
    }
    public String findRiddleHint(){
        return riddleHint;
    }
}
