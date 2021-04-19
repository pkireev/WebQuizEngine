package engine;

public class Test {
    private String title;
    private String text;
    private String[] options;

    public Test(){}

    public Test(String title, String text, String[] options) {
        this.title = title;
        this.text = text;
        this.options = options.clone();
    }


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
}
