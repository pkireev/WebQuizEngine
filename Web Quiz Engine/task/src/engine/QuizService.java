package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    SolvedQuizRepository solvedQuizRepository;

    public Page<Quiz> getAllQuizzes(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return quizRepository.findAll(paging);
    }

    public Page<SolvedQuiz> getSolvedQuizzes(Integer pageNo, Integer pageSize, String userName) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return solvedQuizRepository.findSolvedQuizzesByUser(userName, paging);
    }
}

