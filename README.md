# eval-sast

```

# The rules mix setHttpOnly and setSecure
bearer scan --only-rule java_lang_cookie_missing_secure,java_lang_insecure_cookie .

# Dedicated rules for setHttpOnly and setSecure
semgrep scan --config="r/java.servlets.security.cookie-issecure-false.cookie-issecure-false" --config="r/java.lang.security.audit.cookie-missing-secure-flag.cookie-missing-secure-flag" --config="r/java.lang.security.audit.cookie-missing-httponly.cookie-missing-httponly" .

snyk code test .
```

## Semgrep rules
https://github.com/semgrep/semgrep-rules/blob/develop/java/lang/security/audit/cookie-missing-httponly.yaml
https://github.com/semgrep/semgrep-rules/blob/develop/java/lang/security/audit/cookie-missing-secure-flag.yaml
https://github.com/semgrep/semgrep-rules/blob/develop/java/servlets/security/cookie-issecure-false.yaml

# Snyk rules

https://docs.snyk.io/scan-using-snyk/snyk-code/snyk-code-security-rules/java-rules