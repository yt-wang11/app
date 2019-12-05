package com.example.facapp.constant;

public class SQL {
    private SQL() {
        throw new IllegalStateException("Utility class");
    }
    public static final String FIND_ORDER_BY_CJ = "select * from staff LIMIT :offset,:limit";
    public static final String FIND_ORDER_COUNT = "select count(*) from staff";
    public static final String FIND_ORDER_COUNT_BY_ID = "select count(*) from staff where Name=':Name'";
    public static final String DELETE_ORDER_BY_XHS = "DELETE FROM staff WHERE id in (:ids)";
    public static final String SHOW_ORDER_BY_XH = "select * from staff where id=:id";

    /*账户判断*/
    public static final String VALIDATE_ACCOUNT = "select count(*) from account where xh=:username and password=':password' and cjqx=':cjqx'";

}
