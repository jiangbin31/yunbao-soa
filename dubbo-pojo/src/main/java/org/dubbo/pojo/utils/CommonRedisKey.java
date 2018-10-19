package org.dubbo.pojo.utils;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by jiangbin on 2018/1/26.
 */
public class CommonRedisKey {





    public static class userInfo{
        public static final String USER_KEY="_INFO";
        public static final String INTEGRAL_LIST_KEY="send_integral_key";
        public static final String HAND_USER_INTEGRAL_KEY="hand_user_integral_key";
        public static final String USER_INTEGRAL_KEY="_integral";
        public static final String ERROR_INTEGRAL_KEY="send_integral_error_key";
        public static final Long TIME=300L;
        public static final String USER_HASH_KEY="UserInfo";
        public static final String INTEGRAL_HASH_KEY="UserIntegral";
        public static final String SUGGESTION_HASH_KEY="wx_suggestion_info";
        public static final String CONTACT_HASH_KEY="wx_contact_info";

    }


    public static class  goodsBanner{
        public static final String GOODS_BANNER_KEY="goodsBanner";

    }


    public static class goods{
        public static final String ABOUT_GOODS="GoodsAbout";
        public static final String GOODS_CLASS="GoodsClass";
        public static final Long TIME=86400L;

    }
    public static class goodsCart{
        public static final String GOODS_CART="_GoodsCart";
        public static final String COUNT="_GOODS_CART_COUNT";

    }

    public static class exception{
        //        用户卡数据问题
        public static final String EXCEPTION_ACTIVITY_USERCARD = "EXCEPTION_ACTIVITY_USERCARD";
        //        不可预期错误
        public static final String EXCEPTION_ACTIVITY_NO_EXCEPT = "EXCEPTION_ACTIVITY_NO_EXCEPT";
    }

    public static class acticity {
        public static final String COUPON_KEY="coupon_list";//优惠券队列
        public static final String MESSAGE_LIST="message_list";
        public static final String AWARD_LIST="award_list";//活动结果队列
        public static final String LOTTERY_POOL="lottery_pool";//活动奖池
        public static final String SEND_LIST="send_list";//活动消息队列
        public static final String LOTTERY_LIST="lottery_list";//活动发奖队列
        public static final String ACTIVITY_MAIN="activityMain_"; //活动基本缓存
        public static final String ACTIVITY_PRIZE="activityPrize_"; //奖项缓存
        public static final String ACTIVITY_COUNT="activityCount_"; //活动总参与次数
        public static final String ACTIVITY_UNIONID_COUNT="activityUnionidCount_"; //单个人单个活动参与次数
        public static final String ACTIVITY_OPENID_COUNT="activityOpenIdCount_"; //单个人单个活动参与次数
        public static final String ACTIVITY_BIG_RECORD="activityBigRecord_list"; //竞价记录key
        public static final Long  ExpiryTime=300L;//获取时间
       // public static final String TRANSMISSIONMIDDLEWARE="transmissionmiddleware"; //中间件处理
        public static final String ACTIVITY_ALL_COUNT="activity_all_count_";
        public static final String ACTIVITY_EVERY_DAY_ALL_COUNT="activity_every_day_all_count_";
        public static final String ACTIVITY_CASH_PRIZE_IS_INIT="activity_cash_prize_is_init";
        public static final String ACTIVITY_CASH_PRIZE_TICKET="activity_cash_prize_ticket";
        public static final String ACTIVITY_CASH_PRIZE_RESULT="activity_cash_prize_result";
        public static final String ACTIVITY_PRIZE_CONFIG="activityPrizeConfig_";//奖项配置
        public static final String ACTIVITY_JOIN_LIST="activity_join_list_";
        public static final String ACTIVITY_BARRAGE_KEY="barrage_list";
        public static final String ACTIVITY_BARRAGE_PAGE_KEY="barrage_list_page_";


    }

    /**
     * 分布式锁
     */
    public static class MyLock{
        //抽奖奖池锁
        public static final String POOL_LOTTERY_STRATEGY_INIT_LOCK = "POOL_LOTTERY_STRATEGY_INIT_LOCK";
        //兑奖名额初始化锁
        public static final String CASH_PRIZE_INIT_LOCK = "CASH_PRIZE_INIT_LOCK";
    }

    /**
     *  竞价
     */
    public static class Auction{
        //websocket
        public final static String WEBSOCEKT_ONLINE_COUNT = "WEBSOCEKT_ONLINE_COUNT";
        public final static String AUCTION_OFFER_RECORD = "AUCTION_OFFER_RECORD"; //竞价结果
        public final static String AUCTION_OFFER_ALL_RECORD = "AUCTION_OFFER_ALL_RECORD";
        public final static String AUCTION_OFFER_RESULT = "AUCTION_OFFER_RESULT";
        public final static String AUCTION_OFFER_RESULT_IS_PUSHED = "AUCTION_OFFER_RESULT_IS_PUSHED";
        public final static String AUCTION_OFFER_TIMES = "AUCTION_OFFER_TIMES";
        public final static String AUCTION_OFFER_MAX = "AUCTION_OFFER_MAX";
        public final static String AUCTION_OFFER_LIST_SEQ = "AUCTION_OFFER_LIST_SEQ";


    }

    /**
     * token
     */
    public static class Auth{
        public final static String TOKEN_MAP = "TOKEN_MAP";
        public final static String TOKEN_LOG="token_log";
    }



    public static class YunDing{
        public final static String TOKEN_NAME="YunDing_Token";
        public final static String SHOP_LIST="shop_list";
        public static final Long TIME=86400L;
    }

    public static class WX{
        public final static String ACCESSTOKENINFO="accesstokenInfo";


    }


    }
