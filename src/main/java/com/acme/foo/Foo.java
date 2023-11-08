package com.acme.foo;

import javax.servlet.http.Cookie;

public class Foo {
    
    private static final boolean F = true;

    public void f1() {
        Cookie c = new Cookie("key", "f1"); // Pos
    }

    public void f2() {
        Cookie c = new Cookie("key", "f2"); // Neg - f2a() always returns true
        c.setSecure(f2a());
    }

    public boolean f2a() { return true; }

    public void f3() {
        Cookie c = new Cookie("key", "f3"); // Neg - Foo.F is a constant with value true
        c.setSecure(Foo.F);
    }

    public void f4() {
        Cookie c = new Cookie("key", "f4"); // Neg - Bar.F is a constant with value true
        c.setSecure(Bar.F); // public static final boolean F = true;
    }

    public Cookie f5(boolean b) {
        Cookie c = new Cookie("key", "f5"); // Neg - Called only with b = true
        c.setSecure(b);
        return c;
    }

    public void f5a() {
        f5(true);
    }
}
