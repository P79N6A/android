package com.judian.goule.store.http;

import com.judian.goule.store.utils.Token;

/**
 * Created by Administrator on 2016/12/16.
 */

public interface HttpAPI {


    public static final String HOST = "http://192.168.3.210:80";
    //    public static final String HOST = "https://www.17goule.com";
    public static final String BANNER = "/App/Index/banner";
    public static final String GOODS_ALL = "/App/Index/goods_all";
    public static final String MOGUJIE = "https://m.mogujie.com/?f=mgjlm&ptp=_qd._cps______3069826.152.1.0";
    public static final String JINGDONG = "https://union-click.jd.com/jdc?d=QfzhcA";

    //    进入商品订单页
    public static final String MAKEORDER = "/App/My/makeOrder";

    public static final String BINDORDER = "/App/Main/getorder";

    //    增加商品点击率
    public static final String ADD_CLICK_NUM = "/App/Share/add_click_num";

    public static final String GETORDERALL = "/App/Main/getorderall";
    public static final String GETORDERALL_JD = "/App/Main/getorderall_jd";

    //    获取地址
    public static final String GETADDRESS = "/App/My/getAddress";

    //     s商品查询
    public static final String SEARCH = "/App/SpendMoney/search";

    //优选  根据类型查询 商品列表
    public static final String OPTIMIZATION = "/App/index/youxuan_detail";

    //     京东分类
    public static final String JDCATE = "/App/SpendMoney/jdcate";

    //
    public static final String ONEGOODS = "/App/Index/oneGoods";

    //     提交订单
    public static final String PAYORDER = "/App/My/payOrder";

    //     添加地址
    public static final String DELETEADDRESS = "/App/My/deleteAddress";
    //   收货地址详情
    public static final String GETADDRESSDETAIL = "/App/My/getAddressDetail";


    //     添加地址
    public static final String ADDADDRESS = "/App/My/addAddress";

    //   修改地址
    public static final String UPDATEADDRESS = "/App/My/updateAddress";


    //   修改地址
    public static final String SELFGOODS = "/App/index/selfgoods";


    //   修改地址
    public static final String LISTSELFORDER = "/App/index/listselforder";


    // 注册前  验证推荐码
    public static final String VERIFICATIONCODE = "/App/Login/yqm_yz";

    // 获取商品详情
    public static final String GETGOODSINFO = "/App/Taobao/getTaobaoDetail";

    // 获取商品信息
    public static final String GETGOODSDETAILS = "/App/index/youxuan_goods_info";

    //获取收藏列表
    public static final String GETCOLLECTLIST = "/App/favorite/favorite_list";

    //获取收藏列表
    public static final String DELETEGETCOLLECT = "/App/favorite/favorite_delete";

    //收藏商品
    public static final String FAVORITEADD = "/App/favorite/favorite_add";


    //     进去提现页面
    public static final String WITHDRAW = "/App/Main/withdraw";

    //
//     进去提现页面
    public static final String IMKEFU = "https://qiyukf.com/client?k=15531111c0e8309bcb5b4ee368becff5&u=&d=sruz1qhbgw01lt7t0lcc&uuid=brtfgv9xg608na1ax5n9&gid=0&sid=0&qtype=0&dvctimer=0&robotShuntSwitch=0&hc=0&robotId=0&t=";

    //
    public static final String ZAN_TOTAL = "/App/Comment/zan_total";

    //     版本更新
    public static final String CONTROL = "/App/System/control";

    //     淘宝登录  openid  post
    public static final String TAOBAO = "/App/Login/taobao";

    // 提交信鸽token
    public static final String XGTOKEN = "/App/mobile/setMobileDeviceToken";

    //     淘宝登录  openid  post
    public static final String TABBAO_EMPOWER = "/App/Login/tabbao_empower";


    // 修改淘宝账号
    public static final String CHANGE_TAOBAO_ZHANGHAO = "/App/My/cancelAuth";

    // 获取系统消息
    public static final String GET_SYSTEM_MESSAGES = "/App/System/get_sys_messages";

    //   限时时间
    public static final String XIANSHI_TIME = "/App/Index/xianshi_time";
    // 限时商品
    public static final String XIANSHI_GOODS_LIST = "/App/Index/xianshi_goods_list";


    //     s申请合伙人
    public static final String APPLY_PARTNER = "app/my/upgrade_user_by_sonnum";

    //   客服
    public static final String KEFU = "/App/Main/kefu";

    //   快速登录
    public static final String QUICK_LOGIN = "/App/login/quick_login";


    //聚划算
    public static final String JUHUASUAN = "/App/Share/juhuasuan";


    //     进去提现页面
    public static final String TIXIAN = "/App/Main/tixian";


    //     商品转链
    public static final String GET_TAOTOKEN = "/App/Share/get_taotoken";

    //     秒杀进商品详情
    public static final String MIAOSHA_DETAILS = "/App/SpendMoney/miaosha_details";

    //     秒杀进商品详情
    public static final String SHAREAPP = "/App/Team/shareapp";


    //     WX登录  openid  post
    public static final String WXOPENID = "/App/Login/weixin";

    //     修改头像
    public static final String IMG = "/App/Userdata/img";

    //     等级说明
    public static final String GRADE = "http://118.190.118.226/App/Help/grade";


    //     帮助页面H5
    public static final String HELPH5 = "/App/Help/index?id=";

    //     每日比推
    public static final String FRIEND_GOODS = "/App/Share/friend_goods";

    //     头条
    public static final String HOTHOME = "/App/Index/hothome";


    //     帮助页面H5
    public static final String GOODS_EXCHANGE = "/App/Gold/goods_exchange";


    //     我的余额（金币）
    public static final String REBATE_TIXIAN = "/App/Main/rebate_tixian";

    //     红包记录
    public static final String RED_ENVELOPE = "/App/Main/red_envelope";


    //     订单提交
    public static final String ORDER_BIND = "/App/Youfind/order_bind";


    //     团队明细
    public static final String MY_TEAM_LIST = "/App/Team/my_team_list";


    //     直播间  一键复制
    public static final String LIVE_MV = "/App/Gold/live_mv";


    //     排行榜
    public static final String RANK_LIST = "/App/Team/rank_list";


    //     成员人数
    public static final String ME_TEAM = "/App/Team/me_team";


    //     分享朋友圈
    public static final String SHARE_BILL = "/App/Team/share_bill";


    //       兑换商品  轮播图
    public static final String GOODS_LUN = "/App/Gold/goods_lun";


    //     WX登录 绑定 openid  post
    public static final String WEIXIN_BIND = "/App/Login/weixin_bind";

    //     修改昵称
    public static final String USERNAME = "/App/Userdata/username";

    //     修改手机号
    public static final String USER = "/App/Userdata/user";

    //  微信登录 设置密码
    public static final String RESET_PASS = "/App/Login/set_wei_pass";

    //  微信登录 设置密码
    public static final String LOGINOUT = "/App/Login/loginout";
    //咚咚币兑换
    public static final String EXCHANGE = "/App/Gold/exc_goods";

    //咚咚币兑换 商品详情
    public static final String GOODS_DETAIL = "/App/Gold/goods_detail";

    //咚咚币兑换 关于如何兑换和咚咚币的问题请点击这里
    public static final String JIESHAO = "http://118.190.118.226/App/Help/jieshao ";


    // 获取免单活动信息
    public static final String MIANDACTIVITYINFO = "/App/index/activity";

    // 获取免单商品列表
    public static final String MIANDANSHANGPLIST= "/App/index/activity_goods_list";

    //  赞
    public static final String ZAN_LIST = "/App/Comment/zan_list";


    //是否升级
    public static final String UPGRADE_USER_PAY_MONEY = "/App/my/upgrade_user_by_sonnum";


    //  微信登录 设置密码
    public static final String ME_COMMENT = "/App/Comment/me_comment";


    //  微信登录 设置密码
    public static final String HOT_COMMENT = "/App/Comment/hot_comment";


    //  微信登录 设置密码
    public static final String COMMENT_DETAIL = "/App/Comment/comment_detail";

    //  会员等级申请条件
    public static final String UPGRADE_USER = "/App/my/upgrade_user";


    //     获取验证码
    public static final String SEND = "/App/Login/send";


    //     今日走势
    public static final String INCOME = "/App/My/income";


    //     获取微信用户信息
    public static final String USERINFO = "https://api.weixin.qq.com/sns/userinfo";

    //      传密码pass二次密码repass电话phone
    public static final String UPDATEPASS = "/App/Login/updatepass";

    //     绑定支付宝
    public static final String ALIPAY = "/App/Main/alipay";


    //     搜索本地商品
    public static final String BD_SEARCH = "/App/Index/search";


    //     搜索全网商品
    public static final String TB_SEARCH = "/App/Index/tb_search";


    //     搜索全网商品
    public static final String TB_SEARCH_QHXJ = "/App/Index/tb_search_qhxj";


    // 获取分享链接
    public static final String GETLISTMOBILE = "/App/mobile/getlistmobile";


    //     热搜
    public static final String HOTSO = "/App/Index/hotso";

    //     淘宝登录
    public static final String TAOBAO_BIND = "/App/Login/taobao_bind";


    //   新手攻略
    public static final String HELP = "/App/Main/help";

    //     帮助
    public static final String TB_SEARCH_GET_URL = "/App/Index/tb_search_get_url";

    //     优选
    public static final String YOUXUAN = "/App/Index/youxuan";

    //     常见问题
    public static final String COMMONLSSUE = "/App/Main/cjwt_list";

    //     签到信息
    public static final String SIGN_LOG = "/App/Userdata/sign_log";

    //     立即签到
    public static final String SIGNIN_REWARD = "/App/userdata/signin_reward";
    //    咚咚币明细
    public static final String GOLD_DETAILS = "/App/userdata/gold_list";

    //    老手机号
    public static final String PHONE_VERITY = "/App/Userdata/phone_verity";

    //    保存晒单
    public static final String INDEX = "/App/Comment/index";


    //    他的晒单主页
    public static final String COMMENT_INDEX = "/App/Comment/comment_index";


    //    点赞
    public static final String CLICK_ZAN = "/App/Comment/click_zan";


    //    评论列表
    public static final String GET_COMMENT_LIST = "/App/Comment/get_comment_list";

    //     聊天室背景图
    public static final String LIVE_BANNER = "/App/Gold/live_banner";

    //     聊天室背景图
    public static final String SHOWAD = "/App/index/showad";

    //    发表评论
    public static final String SAVE_COMMENT = "/App/Comment/save_comment";


    //    淘口令
    public static final String TAOKOULING = "/App/Index/taokouling";


    //    发表评论
    public static final String BANNER_ME = "/App/Index/banner_me";


    //    其他晒单
    public static final String OTHER_COMMENT = "/App/Comment/other_comment";

    //    修改性别
    public static final String EDIT_SEX = "/App/Userdata/edit_sex";


    //    直播间列表
    public static final String LIVE = "/App/Gold/live";


    //    直播间列表
    public static final String LIVE_COPY = "/App/Gold/live_copy";


    //    直播间列表
    public static final String LINE_INFO = "/App/Gold/line_info";


    //    账户明细
    public static final String ACCOUNT_DETAIL = "/App/main/account_detail";
    //提现记录
    public static final String WITHDRAW_RECORD = "/App/Main/get_gold";


    //
    public static final String GET_INTEGRAL = "/App/Gold/get_integral";


    //积分记录
    public static final String USER_SCORE_LIST = "/App/Main/user_score_list";


    //积分记录
    public static final String ICOINFO = "/App/Index/icoinfo";


    //微信授权）
    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";


    //     商品下 分享
    public static final String SHARE_GOODS = "/App/SpendMoney/share_goods";


    //    宫格图
    public static final String APP_DG_INDEX = "/App/Index/app_dg_index";

    //    添加收藏
    public static final String ADD_COLLECT = "/App/Main/add_collect";


    //    分类
    public static final String CATEGORY = "/App/SpendMoney/category";

    //    秒杀
    public static final String MIAOSHA = "/App/SpendMoney/miaosha";

    //    订单明细
    public static final String ORDER_DETAIL = "/App/Agent/order_detail";

    //    订单明细
    public static final String DINGDAN = "/App/Youfind/dingdan";


    //      订单明细
    public static final String ORDER_LIST = "/App/Agent/order_list";


    //      合伙人
    public static final String PARTNER = "/App/Team/partner";


    //      英雄榜
    public static final String AMONRY_LIST = "/App/Team/amonry_list";


    //    我的收藏
    public static final String I_LOVE = "/App/Main/I_love";

    //    H5
    public static final String BIAO_COUNT = "/App/Index/biao_count?token=" + Token.getToken();

    //    今日主推
    public static final String GENERALLY = "/App/Index/generally";


    //    今日主推
    public static final String SHAREAPPSUCCESS = "/App/Team/shareappsuccess";


    //    http://192.168.1.10:81/App/Index/app_dg_index
//获取验证码
    public static final String GET_CAPTCHA = "/App/Login/send";
    //注册f
    public static final String REGISTER = "/App/Login/register";
    //登陆
    public static final String LOGIN = "/App/Login/login";
    //忘记密码
    public static final String FORGET_PASSWORD = "/App/Login/set_pass";
    //修改密码
    public static final String MODIFY_PASSWORD = "/App/Login/updatepass";
    //登陆修改密码
    public static final String LOFIN_MODIFY_PASSWORD = "/App/Login/edit_pass";
    //退出登陆
    public static final String LOFIN_OUT = "/App/Login/loginout";
    //我的信息
    public static final String MY_INFO = "/App/Main/show";
    //修改昵称
    public static final String MODIFY_NAME = "/App/Userdata/username";
    //赚钱
    public static final String MAKE_MONEY = "/App/MakeMoney/show";
    //商品详情页面
    public static final String PRODUCT_DETAILS = "/App/SpendMoney/goodsDetails";


    //分享信息
    public static final String SHARE = "/App/Share/goods";

    //
    public static final String IS_AGENT = "/App/Agent/is_agent";

    //分享晒单
    public static final String TODAYSHASUCCESS = "/App/Comment/todaysharedansuccess";


    //     修改地址 post  传入province省和city市和district区 和user_id
    public static final String CITY = "/App/Userdata/city";

    public static final String SHENGC = "/App/City/sheng";
    public static final String SHI = "/App/City/shi";
    public static final String QV = "/App/City/qu";

}