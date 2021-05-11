package engine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "solved_quizzes")
@JsonIgnoreProperties(value={ "recordId", "user" }, allowSetters = true)
public class SolvedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int recordId;

    private int id; // quiz id!
    private String user;
    private Date completedAt;

    public SolvedQuiz() {
    }

    public SolvedQuiz(int id, String user, Date completedAt) {
        this.id = id;
        this.user = user;
        this.completedAt = completedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
}
