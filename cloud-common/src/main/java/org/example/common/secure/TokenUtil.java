package org.example.common.secure;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenUtil {
    /**
     * 签名
     */
    private static final String secret = "Xl18%MsAid";

    /**
     * 生成token
     *
     * @param userId 用户id
     * @return 返回 jwt token
     */
    public static String generate(String userId) {
        return Jwts.builder()
                //加密内容
                .setSubject(userId)
                //设置签名及加密算法
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    private static Jws<Claims> parse(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token);
    }

    /**
     * 检查token是否合法
     *
     * @param token JWT token
     * @return true表示token合法，false表示不合法
     */
    public static boolean checkToken(String token) {
        boolean ret = false;
        try {
            parse(token).getBody().getSubject();
            ret = true;
        } catch (Exception e) {
            log.error("Token check failed: {}", e.getMessage());
        }
        return ret;
    }

    /**
     * 获取token中的加密内容
     *
     * @param token JWT token
     * @return 返回token中加密内容
     */
    public static String getSubject(String token) {
        try {
            return parse(token).getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String token = TokenUtil.generate("admin");
        System.out.println(token);
        System.out.println(TokenUtil.checkToken(token + "a"));

    }
}
