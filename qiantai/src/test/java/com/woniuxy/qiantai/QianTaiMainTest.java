package com.woniuxy.qiantai;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class QianTaiMainTest {

    @Test
    void test(){



    }

    String secret ="WONIUkajsdfkl!@#$!@%!@#$alsdjfsdddKJHJKHKJHJK";

    @Test
    void testJWTEncode(){
        JwtBuilder builder = Jwts.builder();

        //header
        builder.setHeaderParam("alg","HS256"); //加密算法
        builder.setHeaderParam("typ","JWT");

        //payload
        builder.setIssuer("woniuHZ");
        builder.setSubject("蜗牛书店");
        builder.setExpiration(new Date(new Date().getTime()+30*1000)); //指定过期时间
        //增加自定义的信息
        builder.claim("account","sunwukong");

        //签名
        builder.signWith(SignatureAlgorithm.HS256,secret);

        String token = builder.compact();
        System.out.println(token);

    }


    @Test
    void testJWTDecode(){
        String token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ3b25pdUhaIiwic3ViIjoi6JyX54mb5Lmm5bqXIiwiZXhwIjoxNjc1MDUwNDM2LCJhY2NvdW50Ijoic3Vud3Vrb25nIn0.-QzYtX3ujznZvE8r01BUWMiLpQwUEsq8nGZnC2oBAKQ";
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(secret).parseClaimsJws(token);
        System.out.println(claimsJws);

        Claims body = claimsJws.getBody();
        System.out.println(body.getIssuer());
        System.out.println(body.getSubject());
        System.out.println(body.get("account"));

    }


}
