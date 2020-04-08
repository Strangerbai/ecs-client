package com.bee.sample.ecs.entity;

public interface ThreadLocalConstant {

    //请求路径
    String REQUEST_URI = "request_uri";
    //客户端Ip
    String CLIENT_IP = "clientIp";
    //客户端端口号
    String CLIENT_PORT = "clientPort";
    //前端扩展字段
    String CLIENT_EXT = "clientExt";

    //交易凭证列表
    String TRADE_VOUCHER_NO_LIST = "tradeVoucherNos";

    //appId 卖家版/买家版/小程序appId
    String APP_ID = "appId";

    //前端环境
    String ENV_BEAN = "env_bean";
    //sdk版本号
    String SDK_VERSION = "version";
    String APP_VERSION = "appVersion";

    //是否是扫码支付
    String SCAN_PAY = "is_scan_pay";
    String VC_TOKEN = "vc_token_id";


}
