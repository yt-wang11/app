package com.example.facapp.constant;

/**
 *  * description: SQL的常量类
 *  * author: jt
 *  * date: 2017-11-30 上午9:19
 *  * modify: 
 *  
 */
public class SQL {
    private SQL() {
        throw new IllegalStateException("Utility class");
    }
    public static final String FIND_ORDER_BY_CJ = "select * from staff LIMIT :offset,:limit";
    public static final String FIND_ORDER_BY_CJ_AND_KHBH = "select * from order_form where cjbh=:cjbh and khbh=:khbh";
    public static final String FIND_ORDER_COUNT = "select count(*) from staff";
    public static final String FIND_ORDER_COUNT_BY_ID = "select count(*) from staff where Name=':Name'";
    public static final String FIND_ORDER_BY_ID = "select * from order_form where cjbh=:cjbh and ddh=':ddh'";
    public static final String FIND_ORDER_XH_BY_ID = "select xh from order_form where cjbh=:cjbh and ddh=':ddh'";
    public static final String DELETE_ORDER_BY_XHS = "DELETE FROM staff WHERE id in (:ids)";
    public static final String DELETE_ORDER_BY_KHBH = "DELETE FROM order_form WHERE khbh=':khbh'";
    public static final String INSERT_ORDER = "INSERT INTO order_form( cjbh, khbh, ddh, bzjb, ddsj) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_ORDER = "UPDATE order_form SET cjbh = ?, khbh = ?, ddh = ?, bzjb = ?, ddsj = ? WHERE xh = ?";
    public static final String SHOW_ORDER_BY_XH = "select * from staff where id=:id";
    public static final String EXIST_BY_ID_AND_CJBH = "select count(*) from order_form where ddh=:ddh and cjbh=:cjbh";


    /*客户sql*/
    public static final String GET_CLIENT_BY_CJBH = "select xh, khmc from client where cjbh=:cjbh";
    public static final String GET_ALL_CLIENT_BY_CJBH = "select * from client where cjbh=:cjbh";
    public static final String DELETE_CLIENT_BY_XHS = "DELETE FROM client WHERE xh in (:xhs)";
    public static final String INSERT_CLIENT = "INSERT INTO client(cjbh, khmc, khdz, hzsj, lxfs) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_CLIENT = "UPDATE client SET cjbh='?', khmc='?', khdz='?', hzsj='?', lxfs='?'";
    public static final String SHOW_CLIENT_BY_XH = "select * from client where xh=:xh";
    /*流程sql*/
    public static final String GET_ALL_FLOW_BY_CJBH = "select * from flow where cjbh=:cjbh order by bzjb";
    public static final String GET_FLOW_BY_CJBH = "select bzjb, bzmc, bzts from flow where cjbh=:cjbh order by bzjb";
    public static final String DELETE_FLOW_BY_XHS = "DELETE FROM flow WHERE xh in (:xhs)";
    public static final String INSERT_FLOW = "INSERT INTO flow(cjbh, bzjb, bzmc, bzts) VALUES (?, ?, ?, ?)";
    public static final String SHOW_FLOW_BY_XH = "select * from flow where xh=:xh";
    public static final String FIND_FLOW_COUNT_BY_ID = "select count(*) from flow where cjbh=:cjbh and bzjb=':bzjb'";
    public static final String EXIST_BY_BZJB_AND_CJBH = "select count(*) from flow where bzjb=':bzjb' and cjbh=:cjbh";

    /*账户判断*/
    public static final String VALIDATE_ACCOUNT = "select count(*) from account where xh=:username and password=':password' and cjqx=':cjqx'";

    /*工人列表sql*/
    public static final String FIND_WORKER_COUNT_BY_ID = "select count(*) from worker where cjbh=:cjbh and grxm=':grxm'";
    public static final String FIND_WORKER_COUNT = "select count(*) from worker where cjbh=:cjbh";
    public static final String FIND_WORKER_BY_CJ = "select * from worker where cjbh=:cjbh";
    public static final String FIND_WORKER_BY_CJ_AND_GRXM = "select * from worker where cjbh=:cjbh and grxm=':grxm'";
    public static final String DELETE_WORKER_BY_NAMES = "DELETE FROM worker WHERE grxm in (:names) and cjbh=:cjbh";
    public static final String DELETE_WORKER_BY_XH = "DELETE FROM worker WHERE xh=:xh";
    public static final String GET_WORKER_NAME = "select grxm FROM worker WHERE xh=:xh";
    public static final String GET_WORKER_NUM = "select count(*) FROM worker WHERE grxm=':grxm' and cjbh=:cjbh";
    public static final String GET_WORKER_BY_NAME = "select yzje FROM worker WHERE grxm=':grxm'";
    public static final String INSERT_WORKER = "INSERT INTO worker (cjbh, grxm, lxfs, yzje, yzsj) VALUES (?,?,?,?,?)";

}
