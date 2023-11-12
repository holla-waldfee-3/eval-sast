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

        Cookie pos_default = new Cookie("f-1", "f-1"); // Pos
        response.addCookie(pos_default);



        Cookie neg_meth_true = new Cookie("f-2", "f-2"); // Neg - t() always returns true
        neg_meth_true.setSecure(t());
        response.addCookie(neg_meth_true);

        Cookie pos_meth_false = new Cookie("f-3", "f-3"); // Pos
        pos_meth_false.setSecure(f());
        response.addCookie(pos_meth_false);



        Cookie neg_const_true = new Cookie("f-4", "f-4"); // Neg - Foo.T is a constant with value true
        neg_const_true.setSecure(Foo.T);
        response.addCookie(neg_const_true);

        Cookie pos_const_false = new Cookie("f-5", "f-5"); // Pos
        pos_const_false.setSecure(Foo.F);
        response.addCookie(pos_const_false);
        
        
        
        Cookie neg_other_class_const_true = new Cookie("f-6", "f-6"); // Neg - Bar.T is a constant with value true
        neg_other_class_const_true.setSecure(Bar.T);
        response.addCookie(neg_other_class_const_true);

        Cookie pos_other_class_const_false = new Cookie("f-7", "f-7"); // Pos
        pos_other_class_const_false.setSecure(Bar.F);
        response.addCookie(pos_other_class_const_false);



        Cookie neg_param_true = getCookie("f-8", "f-8", true); // Neg - Called with true
        response.addCookie(neg_param_true);

        Cookie pos_param_false = getCookie("f-9", "f-9", false); // Pos
        response.addCookie(pos_param_false);



        Cookie neg_local_var_true = new Cookie("f-10", "f-10"); // Neg - Passing local variable true
        boolean lvart = true;
        neg_local_var_true.setSecure(lvart);
        response.addCookie(neg_local_var_true);

        Cookie pos_local_var_false = new Cookie("f-11", "f-11"); // Pos
        boolean lvarf = false;
        pos_local_var_false.setSecure(lvarf);
        response.addCookie(pos_local_var_false);
    }

    public boolean t() { return true; }

    public boolean f() { return false; }

    public Cookie getCookie(String name, String value, boolean b) {
        Cookie c = new Cookie(name, value); // Depends on argument
        c.setSecure(b);
        return c;
    }
}
