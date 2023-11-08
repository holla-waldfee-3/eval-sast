package com.acme.foo;

import java.io.IOException;
import java.net.HttpCookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BarServlet", urlPatterns = "/barServlet")
public class Bar extends HttpServlet {
    
    public static final boolean T = true;

    public static final boolean F = false;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Cookie c10 = new Cookie("10", "10"); // Neg - It is not javax.servlet.http.Cookie
        //response.addCookie(c10);

        HttpCookie c11 = new HttpCookie("11", "11"); // Neg - It is not javax.servlet.http.Cookie
        //response.addCookie(c11.get);



        javax.servlet.http.Cookie c12 = new javax.servlet.http.Cookie("12", "12"); // Neg - Bar.T is a constant with value true
        c12.setSecure(Bar.T);
        response.addCookie(c12);

        javax.servlet.http.Cookie c13 = new javax.servlet.http.Cookie("13", "13"); // Pos
        c13.setSecure(Bar.F);
        response.addCookie(c13);
    }

    public static class Cookie {
        public Cookie(String bla, String blu) {}
    }
}
