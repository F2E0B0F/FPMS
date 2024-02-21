package njfu.FPMS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.*;

import njfu.FPMS.controller.LoginUserController;
import njfu.FPMS.result.ResultObject;
import njfu.FPMS.user.LoginUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/user")
public class LoginUserService {
    
    @Autowired
    private LoginUserController loginUserController;

    private static final Pattern NAME_ILLEGAL_PATTERN = Pattern.compile("[^a-zA-Z0-9_]");

    public LoginUserService(LoginUserController loginUserController) {
        this.loginUserController = loginUserController;
    }

    private static final Logger log = LoggerFactory.getLogger(LoginUserService.class);

    @GetMapping("search")
    public ResultObject 
    searchUsers(@RequestParam(name = "name") String regExp){
        List<LoginUser> resultList = loginUserController.searchUsers(regExp);
        log.info("Serched: \"" + regExp + "\" Found: " + resultList.size() + " user(s)");
        return ResultObject.ok().data("Users: ", resultList).message("Found some user(s)");
    }

    @GetMapping("test")
    public ResultObject 
    hello() {
      log.info("test hello");
    	return ResultObject.ok().data("message", "hello").message("This is a message");
    }

    @PostMapping("create")
    public ResultObject 
    createUser(@RequestBody Map<String,Object> createUserMap) {
      String username = "", password = "";
      username = createUserMap.get("name").toString();
      password = createUserMap.get("password").toString();

      log.info("username={},password={}",username, password);
      log.info("regExp paggern:{}", NAME_ILLEGAL_PATTERN.pattern());
      log.info("Illegal username matches:{}", NAME_ILLEGAL_PATTERN.matcher(username).find());

      if(NAME_ILLEGAL_PATTERN.matcher(username).find() == true){
        return ResultObject.error().message("Illegal username: only alphanumerical characters and underscore is allowed");
      } else if(loginUserController.searchUsers('^'+username+'$').size() > 0){
        return ResultObject.error().message("User already created.");
      } else{
        loginUserController.createUser(username, password);
      }
      return ResultObject.ok().message("created a user named: " + username + "\nPassword: " + password);
    }

    @GetMapping("login")
    public ResultObject 
    userLogin(@RequestParam(name = "name") String namString,
    @RequestParam(name = "pass") String passString) {
      Map<String, Object> token = new HashMap<>();
      String result = loginUserController.userLogin(namString, passString);
      if(result != null){
        token.put("token", result);
        return ResultObject.ok().message("Login Successfully").data(token);
      } else {
        return ResultObject.error().message("Username or password incorrect");
      }
    }
    
}
