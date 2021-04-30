package engine;

public class QuizAnswers {
    private Integer[] answer;

    public QuizAnswers() {}
    public QuizAnswers(Integer[] answers) {
        setAnswer(answers);
    }

    public Integer[] getAnswer() {
        return answer;
    }
    public void setAnswer(Integer[] answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        if (answer == null) {
            return "answer = null";
        }

        StringBuilder result = new StringBuilder();
        result.append("answer = [");
        for (int i = 0; i < answer.length; i++) {
            result.append(answer[i] + " ");
        }
        result.append("]");
        return result.toString();
    }
}
