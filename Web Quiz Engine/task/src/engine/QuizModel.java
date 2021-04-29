package engine;

import java.util.ArrayList;
import java.util.List;

public class QuizModel {
    private List<Quiz> quizzes = new ArrayList<>();
    private int id = 0;

    public Quiz getQuiz(int id) {
        if (id >= quizzes.size()) {
            return null;
        }

        return quizzes.get(id);
    }

    public void storeQuiz(Quiz quiz) {
        quizzes.add(quiz);
        id++;
    }

    public int getId() {
        return id;
    }

    public List<Quiz> getAllQuizzes() {
        return quizzes;
    }

    public void clear() {
        quizzes.clear();
        id = 0;
    }
}
