package com.tj.pxdl.carlease.base;

/**
 * Created by Chaersi on 17/2/28.
 */
public class BaseConfig {
    private static final String BASE_URL = "http://10.120.21.243:9000";

    /**登录*/
    public static final String USER_LOGIN_URL=BASE_URL+"/oauth/token";
    /**刷新快速登录*/
    public static final String USER_FASTLOGIN_URL=BASE_URL+"/oauth/token";
    /**注册*/
    public static final String USER_REGIST_URL=BASE_URL+"/api/users/register";
    /**修改密码*/
    public static final String USER_UPDATEPASS_URL=BASE_URL+"/api/users/change-pass";

    /**获取账户信息*/
    public static final String ACCOUNT_INFO_URl=BASE_URL+"/api/accounts/info";
    /**获取身份认证信息*/
    public static final String ACCOUNT_AUTH_URl=BASE_URL+"/api/accounts/idcard";
    /**充值*/
    public static final String ACCOUNT_RECHARGE_URl=BASE_URL+"/api/accounts/recharge";
    /**获取充值记录*/
    public static final String ACCOUNT_RECORDS_URl=BASE_URL+"/api/accounts/rechargeRecords";

    /**获取租车点列表*/
    public static final String RENTAL_LEASE_URL=BASE_URL+ "/api/rentalPoints?location=POINT";//(30 100)
    /**获取取车点列表*/
    public static final String RENTAL_OBTAIN_URL=BASE_URL+ "/api/rentalPoints/forGetCar?location=POINT";//(30 100)
    /**获取还车点列表*/
    public static final String RENTAL_REMAND_URL=BASE_URL+ "/api/rentalPoints/forReturn?location=POINT(";//(30 100)
    /**获取历史网点列表*/
    public static final String RENTAL_HISTORY_URL=BASE_URL+ "/api/rentalPoints/history";
    /**获取租车点明细*/
    public static final String RENTAL_DETAIL_URL=BASE_URL+ "/api/rentalPoints/details/";//{id}
    /**预估费用*/
    public static final String RENTAL_COST_URL=BASE_URL+ "/api/rental/estimateCost";

    /**开启车门*/
    public static final String CONTROL_OPEN_URL=BASE_URL+ "/api/carControl/openTheDoor";
    /**锁车门*/
    public static final String CONTROL_LOCK_URL=BASE_URL+ "/api/carControl/lockTheDoor";
    /**车辆鸣笛（声音寻车）*/
    public static final String CONTROL_VOICE_URL=BASE_URL+ "/api/carControl/soundTheHorn";

    /**创建订单（取车）*/
    public static final String ORDER_CREATE_URL=BASE_URL+ "/api/orders/create";
    /**取消订单*/
    public static final String ORDER_CANCEL_URL=BASE_URL+ "/api/orders/cancel";
    /**获取还车订单信息*/
    public static final String ORDER_VOICE_URL=BASE_URL+ "/api/orders/payment?orderNumber=";
    /**支付订单（还车）*/
    public static final String ORDER_PAY_URL=BASE_URL+ "/api/orders/pay";
    /**开取发票*/
    public static final String ORDER_INVOICE_URL=BASE_URL+ "/api/orders/invoice";

    /**获取历史行程单*/
    public static final String DISTANCE_OLDDETAIL_URL=BASE_URL+ "/api/orders/history";
    /**获取行程单详情*/
    public static final String DISTANCE_DETAIL_URL=BASE_URL+ "/api/orders/get?orderNumber=";
}
