package njfu.FPMS.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Controller
public class TokenUtils {
    // 过期时间 (8 hrs)
    private static final long EXPIRE_TIME_MS = 8 * 3600 * 1000;
    // 密钥
    private static final String TOKEN_SECRET = "WBGK&FJWVH2UF7GD435JWIJ92VG38JSMSGSDRVUF";

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    /**
     * @desc 根据用户名与密码生成token
     * @param username
     * @param password
     * @return String
     */
    public static String token(String username, String password){
        String token = "";
        try{
            Date expirationTime = new Date(System.currentTimeMillis() + EXPIRE_TIME_MS);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String,Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");

            token = JWT.create()
            .withHeader(header)
            .withClaim("username", username)
            .withClaim("password", password).withExpiresAt(expirationTime)
            .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return token;
    }

    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            log.info("token expires at: {}", jwt.getExpiresAt().toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            return false;
        }
    }
}
