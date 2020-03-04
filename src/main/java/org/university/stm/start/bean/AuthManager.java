package org.university.stm.start.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.university.stm.start.common.BaseUtil;

import javax.security.auth.message.AuthException;
import java.util.Base64;

/**
 * <pre>
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-25
 */
@Repository
@PropertySource("classpath:application.yml")
public class AuthManager {

    @Value("${web.access.name}")
    private String name;
    @Value("${web.access.password}")
    private String password;

    public AuthManager(){

    }

    public AuthManager(
            @JsonProperty("name") String name,
            @JsonProperty("password")String password){
        this.name = name;
        this.password = password;
    }


    public void auth(String token) throws AuthException {

        String[] tokenAuth = BaseUtil.base64Decode(token).split(":");

        if(tokenAuth.length != 2){
            throw new AuthException("invalid token header");
        }

        if(!name.equals(tokenAuth[0]) ||
           ! password.equals(tokenAuth[1])){
            throw new AuthException("invalid token header");
        }
    }

    public void auth(AuthManager manager) throws AuthException {
        if(!this.name.equals(manager.name) ||
            !this.password.equals(manager.password)){
            throw new AuthException("invalid token header");
        }
    }

    public String toToken(){
        return BaseUtil.base64Encode(this.name + ":" + this.password);
    }

    @Override
    public String toString() {
        return String.format("{%s,%s}", name, password);
    }
}
