package com.lindl.mall.utils;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

/**
 * @Description：加密解密
 * @Author: ldl
 * @CreateDate: 2020/6/11 11:32
 */
public class MD5Utils {
    private static final String SALT = "ldl";

    /**
     * 带密钥加密
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        password = password + SALT;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * @Author Lindonglin
     * @Description //TODO验证加密密码是否正确
     * @Date 2020/3/18
     * @Param [password, md5]
     * @return boolean
     **/
    public static boolean verify(String password, String md5) {
        String encode = encode(password);
        if (md5.equals(encode)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        String abel = MD5Utils.encode("1");
//        System.out.println(abel);
//
//        boolean abel1 = verify("abel", abel);
//        System.out.println(abel1);
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));

    }
}
