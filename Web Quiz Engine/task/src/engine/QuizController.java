package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class QuizController {

    @Autowired
    public QuizRepository quizRepository;

    @Autowired
    public QuizService quizService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserService userService;

    @Autowired
    public SolvedQuizRepository solvedQuizRepository;

    private final static int PAGE_SIZE = 10;

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
            SolvedQuiz solvedQuiz = new SolvedQuiz(id, getCurrentUser(), new Date());
            solvedQuiz.setRecordId(solvedQuiz.getRecordId() + 1);

            solvedQuizRepository.save(solvedQuiz);
            return new Result(true, "Congratulations, you're right!");
        } else {
            return new Result(false, "Wrong answer! Please, try again.");
        }
    }

    @PostMapping(path = "/api/quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz newQuiz) {
        newQuiz.fixAnswers();
        newQuiz.setCreator(getCurrentUser());
        quizRepository.save(newQuiz);

        return newQuiz;
    }

    @GetMapping(path = "/api/quizzes")
    public ResponseEntity<Page<Quiz>> getAllQuizzes(@RequestParam(defaultValue = "0") Integer page) {

        Page<Quiz> pagedResult = quizService.getAllQuizzes(page, PAGE_SIZE);
        return new ResponseEntity<>(pagedResult, new HttpHeaders(), HttpStatus.OK);
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

    @DeleteMapping(path = "/api/quizzes/{id}")
    public void deleteQuiz(@PathVariable int id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);

        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        } else {
            if (quiz.getCreator().equals(getCurrentUser())) {
                quizRepository.delete(quiz);
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Quiz has been deleted successfully!");
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this quiz!");
            }
        }
    }

    @DeleteMapping(path = "/api/quizzes")
    public void deleteAllQuizzes() {
        quizRepository.deleteAll();
    }


    @PostMapping(path = "/api/register")
    public void createUser(@Valid @RequestBody User user) {
        if (!userService.saveUser(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists!");
        };
    }

    @GetMapping("/api/users")
    public List<User> showAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/api/quizzes/completed")
    public ResponseEntity<Page<SolvedQuiz>> getSolvedQuizzes(@RequestParam(defaultValue = "0") Integer page) {
        Page<SolvedQuiz> pagedResult = quizService.getSolvedQuizzes(page, PAGE_SIZE, getCurrentUser());
        return new ResponseEntity<>(pagedResult, new HttpHeaders(), HttpStatus.OK);
    }
}
