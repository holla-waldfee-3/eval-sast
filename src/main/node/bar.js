const express = require('express');
const session = require('express-session')

// Constants to represent true and false
const T = true;
const F = false;

const app = express();
app.use(session({
    secret: 'keyboard cat',
    resave: false,
    saveUninitialized: true,
    cookie: { secure: F }
}))

app.get('/fooServlet', (req, res) => {
    eval(req.params['foo']);

    // Pos: Default cookie
    res.cookie('1', '1');

    // Neg: Cookie with setSecure always true
    res.cookie('2', '2', { secure: t() });

    // Pos: Cookie with setSecure false
    res.cookie('3', '3', { secure: f() });

    // Neg: Cookie with constant true
    res.cookie('4', '4', { secure: T });

    // Pos: Cookie with constant false
    res.cookie('5', '5', { secure: F });

    // Neg: Cookie with other class constant true
    res.cookie('6', '6', { secure: Bar.T });

    // Pos: Cookie with other class constant false
    res.cookie('7', '7', { secure: Bar.F });

    // Neg: Cookie called with true
    const negParamTrue = getCookie(true);
    res.cookie(negParamTrue.name, negParamTrue.value, { secure: negParamTrue.secure });

    // Pos: Cookie called with false
    const posParamFalse = getCookie(false);
    res.cookie(posParamFalse.name, posParamFalse.value, { secure: posParamFalse.secure });

    // Neg: Cookie with local variable true
    let lvart = true;
    res.cookie('14', '14', { secure: lvart });

    // Pos: Cookie with local variable false
    let lvarf = false;
    res.cookie('15', '15', { secure: lvarf });

    // Neg: Cookie with true literal
    res.cookie('16', '16', { secure: true });

    // Pos: Cookie with false literal
    res.cookie('17', '17', { secure: false });

    res.send('Cookies set');
});

function t() {
    return true;
}

function f() {
    return false;
}

function getCookie(b) {
    return { name: 'c', value: 'c', secure: b };
}

// Mock Bar class with constants
const Bar = {
    T: true,
    F: false
};

const port = 3000;
app.listen(port, () => {
    console.log(`Server running on port ${port}`);
});
