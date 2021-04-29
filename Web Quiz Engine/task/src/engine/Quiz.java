package engine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

@JsonIgnoreProperties(value={ "answer" }, allowSetters = true)
public class Quiz {
    private int id;

    @NotEmpty(message = "Title can't be empty")
    private String title;

    @NotEmpty(message = "Text can't be empty")
    private String text;

    @NotNull
    @Size(min = 2, message = "The quiz must contain two or more options")
    private String[] options;

    private Integer[] answer;

    public Quiz(){}

    public Quiz(int id, String title, String text, String[] options, Integer[] answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options.clone();
        this.answer = answer;
    }

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public Integer[] getAnswer() { return answer; }

    public void setAnswer(Integer[] answer) { this.answer = answer.clone(); }

    @Override
    public String toString() {
        String result = String.format("id: %d\ntitle: %s\nanswers:[", id, title);
        for (int i = 0; i < answer.length; i++) {
            result += answer[i] + " ";
        }
        result += "]\n";

        return result;
    }

    public void fixAnswers() {
        if (answer == null) {
            answer = new Integer[0];
        } else {
            Arrays.sort(answer);
        }
    }
}
