package com.deer.component.shiro;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ClassName: CustomAuthcToken
 * @Author: Mr_Deer
 * @Date: 2019/5/23 14:46
 * @Description: 自定义认证 Token
 * <p>
 * 继承 UsernamePasswordToken 方便快捷，基础属性都是存在的
 * 另一种方式 implements AuthenticationToken，这种方式需要写的东西太多，不是很便捷
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomAuthcToken extends UsernamePasswordToken {

    private static final long serialVersionUID = -1451953411245594103L;

    // 是否为 VIP 用户
    private String isVip;

    // 实现构造方法，基础属性调用父类
    public CustomAuthcToken(String username, String password, String isVip) {
        super(username, (char[]) (password != null ? password.toCharArray() : null), false, (String) null);
        this.isVip = isVip;
    }
}
