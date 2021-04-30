package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class QuizController {

    @Autowired
    public QuizRepository quizRepository;

    public QuizController(){}

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public Result solve(@PathVariable int id, @RequestBody QuizAnswers answer) {
        boolean isCorrect = false;

        if (answer.getAnswer() != null) {
            Quiz quiz = quizRepository.findById(id).orElse(null);
            if (quiz == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            } else {
                isCorrect = Arrays.equals(answer.getAnswer(), quiz.getAnswer());
            }
        }

        if (isCorrect) {
            return new Result(true, "Congratulations, you're right!");
        } else {
            return new Result(false, "Wrong answer! Please, try again.");
        }
    }

    @PostMapping(path = "/api/quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz newQuiz) {
        newQuiz.fixAnswers();
        quizRepository.save(newQuiz);

        return newQuiz;
    }

    @GetMapping(path = "/api/quizzes")
    public List<Quiz> getAllQuizzes() {
        return (List<Quiz>) quizRepository.findAll();
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);

        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        } else {
            return quiz;
        }
    }

    @DeleteMapping(path = "/api/quizzes")
    public void deleteAllQuizzes() {
        quizRepository.deleteAll();
    }
}
