package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CloudUserApplication {

    public static void main(String[] args) throws InterruptedException {
        String jwtToken = Jwts.builder().setSubject("lidq")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secret")
                //.setExpiration(new Date(System.currentTimeMillis() + 3000))
                .compact();

        System.out.println(jwtToken);
        //Thread.sleep(5000L);

        Claims claims = Jwts.parser().setSigningKey("secret")
                .parseClaimsJws(jwtToken)
                .getBody();

        System.out.println(claims);

    }
}
