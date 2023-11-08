package com.acme.foo;

import java.net.HttpCookie;

public class Bar {
    
    public static final boolean F = true;

    public static class Cookie {
        public Cookie(String bla, String blu) {}
    }

    public void f6(String... args) {
        Cookie c = new Cookie("key", "f6"); // Neg - It is not javax.servlet.http.Cookie
    }

    public void f7() {
        HttpCookie c = new HttpCookie("key", "f7"); // Pos
    }

    public void f8() {
        javax.servlet.http.Cookie c = new javax.servlet.http.Cookie("key", "f8"); // Neg - Foo.F is a constant with value true
        c.setSecure(Bar.F);
    }
}
