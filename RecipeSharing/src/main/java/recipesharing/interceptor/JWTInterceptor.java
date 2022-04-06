package recipesharing.interceptor;


import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import recipesharing.util.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. to intercept a token sending from the front end header
 * 2. decrypt the token to find the user info
 */
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");

        //request token in the http header
        String token = request.getHeader("token");
        log.info("current token：{}", token);

        Map<String, Object> map = new HashMap<>();
        try {
            JWTUtil.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "incorrect signature");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "expired token");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "algorithm mismatched");
        } catch (InvalidClaimException e) {
            e.printStackTrace();
            map.put("msg", "invalid payload");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "invalid token");
        }

        map.put("state", false);

        //Respond to the front : turn the MAP to JSON
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
