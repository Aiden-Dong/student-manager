package org.university.stm.start.web.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.university.stm.start.bean.AuthManager;
import org.university.stm.start.common.message.Message;
import org.university.stm.start.common.message.SuccessMessage;

import javax.security.auth.message.AuthException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 *     signin rest controller
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-03-03
 */
@RestController
@RequestMapping("/stm/api/v1")
public class ServerAuthRestController {

    private static final String AUTH_HEADER = "Authorization";

    @Autowired
    private AuthManager manager;

    @PostMapping("/signin")
    public Message signin(@RequestBody AuthManager manager, HttpServletResponse response) throws AuthException {
        this.manager.auth(manager);

        Cookie cookie = new Cookie(AUTH_HEADER, manager.toToken());
        cookie.setPath("/");

        response.addCookie(cookie);

        return new SuccessMessage("signin success");
    }
}
