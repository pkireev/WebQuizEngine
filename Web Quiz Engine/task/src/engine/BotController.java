package engine;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BotController {
    public BotController() {}

    @PostMapping(path = "/greet", consumes = "application/json")
    public String getUserInfo(@RequestBody UserInfo userinfo) {
        if (userinfo.isEnabled()) {
            return String.format("Hello! Nice to see you, %s!", userinfo.getName());
        } else {
            return String.format("Hello! Nice to see you, %s! Your account is disabled :(", userinfo.getName());
        }
    }

    @PostMapping(path = "/logout", consumes = "application/json")
    public String logoutUsersInArray(@RequestBody List<UserInfo> users) {
        // logging out users
        return String.format("Total %d users have been logged out", users.size());
    }
}
