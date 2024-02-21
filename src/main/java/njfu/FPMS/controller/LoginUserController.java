package njfu.FPMS.controller;

import java.util.List;

import org.apache.commons.codec.digest.Sha2Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import njfu.FPMS.mapper.GetTimeMapper;
import njfu.FPMS.mapper.LoginUserMapper;
import njfu.FPMS.user.LoginUser;
import njfu.FPMS.utils.TokenUtils;

/**
 * LoginUserController
 */
@Controller
public class LoginUserController {

    @Autowired
    LoginUserMapper loginUserMapper;

    @Autowired
    GetTimeMapper getTimeMapper;

    private static final Logger log = LoggerFactory.getLogger(LoginUserController.class);

    public List<LoginUser> 
    getUsers(){
        return loginUserMapper.getAllLoginUsers();
    }

    public List<LoginUser> 
    searchUsers(String regExp){
        return loginUserMapper.searchUsers(regExp);
    }

    public Integer
    createUser(String nameString, String passwordString){
        LoginUser newUser = new LoginUser();
        String shadowString = Sha2Crypt.sha512Crypt(passwordString.getBytes());

        newUser.setName(nameString);
        newUser.setShadow(shadowString);
        newUser.setuserGroup("user");
        newUser.setCreateDate(getTimeMapper.now());

        log.info(newUser.toString());

        return loginUserMapper.userCreate(newUser);
    }

    public String 
    createUser(String nameString, String passwordString, String saltString){
        String shadowString = Sha2Crypt.sha512Crypt(passwordString.getBytes(), "$6$" + saltString + "$");
        return nameString + '\n' + shadowString;
    }

    public String
    userLogin(String nameString, String passString){
        String saltString;
        String shadowRight;
        String shadowLeft;
        String[] shadowStrings;
        List<LoginUser> userForValidate = loginUserMapper.searchUsers('^'+nameString+'$');

        log.info(nameString+ ' ' +passString);
        if(userForValidate.size() <= 0){
            log.info("No users named: {}", nameString);
            return null;
        }
        shadowStrings = userForValidate.get(0).getShadow().split("\\$");

        if(shadowStrings.length != 4){
            log.info("Shadow error");
            return null;
        }
        shadowLeft = userForValidate.get(0).getShadow();

        saltString = shadowStrings[2];
        log.info("found user: " + userForValidate.toString());
        log.info("Shadow: " + userForValidate.get(0).getShadow());
        log.info("Salt size: " + shadowStrings.length);
        log.info("Algo: " + shadowStrings[1]);
        log.info("Salt: " + shadowStrings[2]);
        log.info("Crypted: " + shadowStrings[3]);

        shadowRight = Sha2Crypt.sha512Crypt(passString.getBytes(), "$6$" + saltString + "$");
        
        log.info("Login shadow result: {}", shadowRight);
        log.info("Correct shadow: {}", shadowLeft);

        if(shadowLeft.equals(shadowRight)){
            log.info("Login success");
            return TokenUtils.token(nameString, passString);
        } else {
            log.warn("Login failed (password not match)");
            return null;
        }
    }
}