package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quiz {
    private int id;
    private String title;
    private String text;
    private String[] options;
    private int[] answers;

    public Quiz(){}

    public Quiz(int id, String title, String text, String[] options, int[] answers) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options.clone();
        this.answers = answers.clone();
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

    @JsonIgnore
    public int[] getAnswers() { return answers; }

    @JsonProperty
    public void setAnswers(int[] answers) { this.answers = answers.clone(); }

    @Override
    public String toString() {
        return String.format("id: %d\ntitle: %s\nanswer: %d\n", id, title, answers);
    }
}
