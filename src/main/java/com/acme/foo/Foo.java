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
    
    private static final boolean F = true;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie c1 = new Cookie("1", "1"); // Pos
        response.addCookie(c1);

        Cookie c2 = new Cookie("2", "2"); // Neg - f2a() always returns true
        c2.setSecure(returnTrue());
        response.addCookie(c2);

        Cookie c3 = new Cookie("3", "3"); // Neg - Foo.F is a constant with value true
        c3.setSecure(Foo.F);
        response.addCookie(c3);
        
        Cookie c4 = new Cookie("4", "4"); // Neg - Bar.F is a constant with value true
        c4.setSecure(Bar.F);
        response.addCookie(c4);

        Cookie c5 = getCookie(true);
        response.addCookie(c5);
    }

    public boolean returnTrue() { return true; }

    public Cookie getCookie(boolean b) {
        Cookie c = new Cookie("5", "5"); // Neg - Always called with true
        c.setSecure(b);
        return c;
    }
}
