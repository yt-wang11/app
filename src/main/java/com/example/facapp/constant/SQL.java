package com.example.facapp.constant;

public class SQL {
    private SQL() {
        throw new IllegalStateException("Utility class");
    }
    public static final String FIND_ORDER_BY_CJ = "select * from staff LIMIT :offset,:limit";
    public static final String FIND_ORDER_BY_SEARCH = "select * from staff where Idcard like '%:Idcard%' or Name like '%:Name%' LIMIT :offset,:limit";
    public static final String FIND_ORDER_BY_SEARCH_NUM = "select * from staff where Id=:Id or Idcard like '%:Idcard%' or Name like '%:Name%' LIMIT :offset,:limit";
    public static final String FIND_ORDER_COUNT = "select count(*) from staff";
    public static final String FIND_ORDER_COUNT_BY_ID = "select count(*) from staff where Name=':Name' or Idcard like '%:Idcard%'";
    public static final String FIND_ORDER_COUNT_BY_ID_NUM = "select count(*) from staff where Name=':Name' or Id=:Id or Idcard like '%:Idcard%'";
    public static final String DELETE_ORDER_BY_XHS = "DELETE FROM staff WHERE id in (:ids)";
    public static final String SHOW_ORDER_BY_XH = "select * from staff where id=:id";
    public static final String REMIND_LIST = "select * from staff where id in (:id) LIMIT :offset,:limit";

    /*账户判断*/
    public static final String VALIDATE_ACCOUNT = "select count(*) from account where xh=:username and password=':password' and cjqx=':cjqx'";

}
