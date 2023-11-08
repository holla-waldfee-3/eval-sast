package com.acme.foo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FooServlet", urlPatterns = "/fooServlet")
public class Foo extends HttpServlet {
    
    private static final boolean T = true;

    private static final boolean F = false;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie c1 = new Cookie("1", "1"); // Pos
        response.addCookie(c1);



        Cookie c2 = new Cookie("2", "2"); // Neg - f2a() always returns true
        c2.setSecure(t());
        response.addCookie(c2);

        Cookie c3 = new Cookie("3", "3"); // Pos
        c3.setSecure(f());
        response.addCookie(c3);



        Cookie c4 = new Cookie("4", "4"); // Neg - Foo.T is a constant with value true
        c4.setSecure(Foo.T);
        response.addCookie(c4);

        Cookie c5 = new Cookie("5", "5"); // Pos
        c5.setSecure(Foo.F);
        response.addCookie(c5);
        
        
        
        Cookie c6 = new Cookie("6", "6"); // Neg - Bar.T is a constant with value true
        c6.setSecure(Bar.T);
        response.addCookie(c6);

        Cookie c7 = new Cookie("7", "7"); // Pos
        c7.setSecure(Bar.F);
        response.addCookie(c7);



        Cookie c8 = getCookie(true); // Neg - Called with true
        response.addCookie(c8);

        Cookie c9 = getCookie(false); // Pos
        response.addCookie(c9);



        Cookie c14 = new Cookie("14", "14"); // Neg - Passing local variable true
        boolean b14 = true;
        c14.setSecure(b14);
        response.addCookie(c14);

        Cookie c15 = new Cookie("15", "15"); // Pos
        boolean b15 = false;
        c15.setSecure(b15);
        response.addCookie(c15);
    }

    public boolean t() { return true; }

    public boolean f() { return false; }

    public Cookie getCookie(boolean b) {
        Cookie c = new Cookie("c", "c"); // Depends on argument
        c.setSecure(b);
        return c;
    }
}
