package engine;

public class QuizAnswers {
    private int[] answers;

    public QuizAnswers() {
    }

    public QuizAnswers(int[] answers) {
        setAnswers(answers);
    }

    public int[] getAnswers() {
        return answers;
    }
    public void setAnswers(int[] answers) {
        this.answers = answers;
    }

}
