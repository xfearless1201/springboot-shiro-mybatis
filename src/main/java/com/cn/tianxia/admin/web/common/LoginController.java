package com.cn.tianxia.admin.web.common;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cn.tianxia.admin.common.base.ResultResponse;
import com.cn.tianxia.admin.common.shiro.ShiroUtils;
import com.google.code.kaptcha.Constants;

/**
 * 
 * @ClassName LoginController
 * @Description 登录接口
 * @author Hardy
 * @Date 2019年4月13日 下午7:06:32
 * @version 1.0.0
 */
@RestController
public class LoginController {

    /**
     * 
     * @Description 登录
     * @param username  登录用户名
     * @param password  登录密码
     * @param captcha   登录验证码
     * @return
     */
    @PostMapping("sys/login")
    public ResultResponse login(String username,String password,String captcha){
        System.err.println("调用登录接口开始================start==================");
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!captcha.equalsIgnoreCase(kaptcha)){
            return ResultResponse.error("验证码不正确");
        }
        try{
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        }catch (UnknownAccountException e) {
            return ResultResponse.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return ResultResponse.error("账号或密码不正确");
        }catch (LockedAccountException e) {
            return ResultResponse.error("账号已被锁定,请联系管理员");
        }catch (AuthenticationException e) {
            return ResultResponse.error("账户验证失败");
        }
        return ResultResponse.success();
    }
}
