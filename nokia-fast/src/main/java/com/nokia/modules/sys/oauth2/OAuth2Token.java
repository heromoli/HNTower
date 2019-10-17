package com.nokia.modules.sys.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by wow on 2019/6/6.
 * 自定义 token 设置返回Principal Credentials 为自定义 token
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
