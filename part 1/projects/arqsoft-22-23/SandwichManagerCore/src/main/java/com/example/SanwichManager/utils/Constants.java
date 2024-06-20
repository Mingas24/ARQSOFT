package com.example.SanwichManager.utils;

public class Constants {

    public static final String NAME_REGEX = "[A-Z][a-z]+";
    public static final String EMAIL_REGEX = "[a-z]+@[a-z]+.[a-z]+";
    public static final String NIF_REGEX = "^[0-9]{9}$";
    public static final String PRICE_REGEX = "[0-9]+(.[0-9]{2})?$";
    public static final String DEFAULT_INPUT_REGEX = "^[-, a-zA-Z0-9]+$";
    public static final String DATE_REGEX = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    public static final String SCHEDULE_REGEX = "(0?[0-9]|1[0-9]|2[0-3])h([0-5][0-9])-(0?[0-9]|1[0-9]|2[0-3])h([0-5][0-9])";
    public static final int MIN_CHARACTERRS_ADDRESS = 10;
    public static final int MIN_CHARACTERS_DESIGNATION = 10;
    public static final int MIN_CHARACTERS_DESCRIPTION = 15;
    public static final String[] DAY_STRINGS = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            "Sunday" };
}
