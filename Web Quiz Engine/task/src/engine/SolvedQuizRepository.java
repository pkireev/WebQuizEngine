package engine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolvedQuizRepository extends PagingAndSortingRepository<SolvedQuiz, Integer> {

    @Query(
            value = "SELECT * FROM solved_quizzes WHERE user = ?1 ORDER BY completed_at DESC",
            countQuery = "SELECT count(*) FROM solved_quizzes",
            nativeQuery = true)
    Page<SolvedQuiz> findSolvedQuizzesByUser(String user, Pageable pageable);

}
