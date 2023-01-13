package com.woniuxy;

public class MqConst {

    public static String SIMPLE_QUEUE="simple_queue";
    public static String WORKQUEUES_QUEUE="WORKQUEUES_QUEUE";
    public static String FanoutExchange="FanoutExchange";
    public static String FanoutQueue1="FanoutQueue1";
    public static String FanoutQueue2="FanoutQueue2";

    public static String DirectExchange="DirectExchange";
    public static String QueryQueue="QueryQueue";
    public static String Query="Query";
    public static String AddDelUpdateQueue="AddDelUpdateQueue";
    public static String AddDelUpdate="AddDelUpdate";

    public static String TopicExchange="TopicExchange";
    public static String TopicQueue1="TopicQueue1";
    public static String TopicQueue2="TopicQueue2";

    //* 匹配一个词
    public static String TopicQueue1Key="*.orange.*";
    public static String TopicQueue2Key1="*.*.rabbit";
    //# 匹配一个或者多个词
    public static String TopicQueue2Key2="lazy.＃";


}
