package com.stefanini.intefaces;

public interface RegexValidator {
    public String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[}{,.^?~=+\\-_\\/*\\-+.\\|ç!])" +
            "(?:([0-9a-zA-Z[}{,.^?~=+\\-_\\/*\\-+.\\|ç!]])){4,10}$";
    public String DATA_ANIVERSARIO_REGEX = "^[0-9]{4}/[0-9]{2}/[0-9]{2}$";
    public String EMAIL_REGEX = "^(?=.*@)(?=.*\\.com)(?:([a-z@._])){4,100}";
}
