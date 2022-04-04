package recipesharing.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import recipesharing.logic.User;
import recipesharing.service.LoginService;
import recipesharing.vo.Result;
import recipesharing.vo.TransStatusCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * TODO DID NOT FINISH
 * Login interceptor class
 * Implement an interceptor to enable user login interception and validation
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("The preHandle method of the interceptor is executed");
        try {
            HttpSession session = request.getSession();
            //Uniform interception (query the current session for the existence of user) (here user will be written to the session after each successful login)
            User user = (User) session.getAttribute("user");
            if (user != null) {
                return true;
            }
            response.sendRedirect(request.getContextPath() + "login");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        //If set to false, the interceptor will not continue to operate here when requested
        //If set to true, the request will continue with the next action
    }
}
