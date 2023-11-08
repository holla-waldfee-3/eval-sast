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
    
    public static final boolean F = true;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Cookie c6 = new Cookie("6", "6"); // Neg - It is not javax.servlet.http.Cookie
        //response.addCookie(c6);

        HttpCookie c7 = new HttpCookie("7", "7"); // Pos
        //response.addCookie(c7.get);

        javax.servlet.http.Cookie c8 = new javax.servlet.http.Cookie("8", "8"); // Neg - Foo.F is a constant with value true
        c8.setSecure(Bar.F);
        response.addCookie(c8);
    }

    public static class Cookie {
        public Cookie(String bla, String blu) {}
    }


    public void f7() {
    }

    public void f8() {
    }
}
