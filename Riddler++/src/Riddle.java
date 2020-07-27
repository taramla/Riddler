public class Riddle {
    public Riddle(){
        String riddleQuestion;
        String riddleAnswer;
        String riddleHint;
        String difficulty;
        int value;
    }
    public boolean checkRiddleAnswer(String answer){
        if(answer.equals(this.riddleAnswer)) return true;
        else return false;
    }
    public String findRiddleHint(){
        return this.riddleHint;
    }
}
