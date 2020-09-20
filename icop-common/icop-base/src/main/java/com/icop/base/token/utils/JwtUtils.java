package com.icop.base.token.utils;

import com.icop.base.enums.ExceptionCode;
import com.icop.base.exception.MyException;
import com.icop.base.token.entities.AuthInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
public class JwtUtils {

    private static String BASE64_SECURITY="icop";
    /**
     * token 的过期时间，单位分钟
     */
    public static long EXPIRE_TOKEN= 2L;
    /**
     * refresh token 的过期时间，单位分钟
     */
    public static long EXPIRE_REFRESH_TOKEN= 3L;

    public static String TOKEN_TYPE="jsonwebtoken";

    public  static String buildJwtToken(Map<String,Object> authInfoMap, long expire){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(BASE64_SECURITY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        long currentTime = System.currentTimeMillis();
        String token = Jwts.builder()
                .setHeaderParam("typ", "jsonwebtoken")
                .signWith(signatureAlgorithm, signingKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(currentTime + expire * 60L))
                .setClaims(authInfoMap)
                .compact();

        return token;
    }

    /**
     * 填充token中用户信息
     * @param authInfo
     * @return
     */
    public static Map<String,Object> fillTokenMap(AuthInfo authInfo){
        Map<String,Object> userInfoMap = new HashMap<>(4);
        userInfoMap.put("account",authInfo.getAccount());
        userInfoMap.put("userId",authInfo.getUserId());
        userInfoMap.put("roleCode",authInfo.getRoleCode());

        return userInfoMap;
    }

    /**
     * 根据jwt解析内容
     * @param token 访问的jwt
     * @return
     */
    public static Claims parasAccessToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(BASE64_SECURITY)
                    .parseClaimsJws(token)
                    .getBody();

            if(null==claims){
                throw new MyException(ExceptionCode.JWT_PARSER_TOKEN_FAIL);
            }

            return claims;
        }catch (ExpiredJwtException expir){
            throw new MyException(ExceptionCode.JWT_TOKEN_EXPIRED);
        }catch (Exception e){
            throw new MyException(ExceptionCode.JWT_PARSER_TOKEN_FAIL);
        }

    }
}
