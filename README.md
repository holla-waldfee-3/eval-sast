# eval-sast

```

# Node.js + Express
bearer scan . --only-rule=javascript_express_insecure_cookie

# The rules mix setHttpOnly and setSecure
bearer scan --only-rule java_lang_cookie_missing_secure,java_lang_insecure_cookie .

# Dedicated rules for setHttpOnly and setSecure
semgrep scan --config="r/java.servlets.security.cookie-issecure-false.cookie-issecure-false" --config="r/java.lang.security.audit.cookie-missing-secure-flag.cookie-missing-secure-flag" --config="r/java.lang.security.audit.cookie-missing-httponly.cookie-missing-httponly" .

# Uploading results to the cloud
semgrep ci

snyk code test .
```

## Bearer rules
https://github.com/bearer/bearer-rules/blob/main/rules/javascript/express/insecure_cookie.yml

## Semgrep rules
https://github.com/semgrep/semgrep-rules/blob/develop/java/lang/security/audit/cookie-missing-httponly.yaml
https://github.com/semgrep/semgrep-rules/blob/develop/java/lang/security/audit/cookie-missing-secure-flag.yaml
https://github.com/semgrep/semgrep-rules/blob/develop/java/servlets/security/cookie-issecure-false.yaml

## Snyk rules

https://docs.snyk.io/scan-using-snyk/snyk-code/snyk-code-security-rules/java-rules

# Bearer Script

Tested with:
```
bearer version: 1.29.0
sha: e81636a6bb5f125fdfd9b03c5d3ba0102e66e048
```

``````
bearer scan --only-rule=java_lang_cookie_missing_secure,java_lang_insecure_cookie src/main/java/com/acme/foo # FPs +FNs

# Comment out 69-72

bearer scan --only-rule=java_lang_cookie_missing_secure,java_lang_insecure_cookie src/main/java/com/acme/foo # Finding with commented code

bearer scan --only-rule=java_lang_cookie_missing_secure,java_lang_insecure_cookie src/main/java/com/acme/foo/Foo.java # No finding at all

# Delete lines 64-72

bearer scan --only-rule=java_lang_cookie_missing_secure,java_lang_insecure_cookie src/main/java/com/acme/foo/Foo.java # 8 findings, FPs and FNs
```