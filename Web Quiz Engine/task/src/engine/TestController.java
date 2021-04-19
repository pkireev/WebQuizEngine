package engine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    public TestController(){}

    @GetMapping(path = "/api/quiz")
    public Test sendTest() {
        return new Test(
                "The Java Logo",
                "What is depicted on the Java logo?",
                new String[]{"Robot","Tea leaf","Cup of coffee","Bug"}
                );
    }

    @PostMapping(path = "/api/quiz")
    public Result sendResult(@RequestParam int answer) {
        if (answer == 2) {
            return new Result(true, "Congratulations, you're right!");
        }
        return new Result(false, "Wrong answer! Please, try again.");
    }
}
