package com.lindl.mall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lindl.mall.common.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 15:36
 */
@Data
public class MallUser extends Model {
    private Long id;

    @NotNull
    private String username;

    @JsonIgnore
    @NotNull
    private transient String password;

    private byte gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private Date lastLoginTime;

    private String lastLoginIp;

    private String nickname;

    @NotNull
    private String mobile;

    /**
     * 用户头像图片
     */
    private String avatar;

    private String openid;

    /**
     * 微信登录会话KEY
     */
    private String sessionKey;

    private byte status;

    private Date createTime;

    private Date updateTime;

    /**
     * 0 可用, 1 禁用, 2 注销 ,默认0
     */
    private byte deleted;




}
