package com.example.farmer.constant;

/**
 * @description: RabbitConstantCode一些常量值
 * @author: DaiXG
 * @createTime: 2021-06-14 09:28:00 周一
 */
public class RabbitConstantCode {

    /**
     * RabbitMQ所在的主机地址
     */
    public static final String HOST = "192.168.25.128";
    /**
     * RabbitMQ软件端口
     */
    public static final int PORT = 5672;
    /**
     * RabbitMQ的登录用户
     */
    public static final String USERNAME = "root";
    /**
     * RabbitMQ的登录密码
     */
    public static final String PASSWORD = "root";
    /**
     * RabbitMQ的虚拟主机
     */
    public static final String VIRTUALHOST = "/";



    /**
     *工作队列模式中：定义一个队列
     */
    public static final String WORK_QUEUE = "WorkQueue";


    /**
     * 发布订阅模式：定义一个交换机
     */
    public static final String EXCHANGE_FANOUT = "ExchangeFanout";
    /**
     * 发布订阅模式：定义一个队列
     */
    public static final String FANOUT_SMS_QUEUE = "FanoutSmsQueue";
    /**
     * 发布订阅模式：定义一个队列
     */
    public static final String FANOUT_EMAIL_QUEUE = "FanoutEmailQueue";


    /**
     * 路由模式：定义一个交换机
     */
    public static final String EXCHANGE_DIRECT = "ExchangeDirect";
    /**
     * 路由模式：定义一个队列
     */
    public static final String DIRECT_SMS_QUEUE = "DirectSmsQueue";
    /**
     * 路由模式：定义一个队列
     */
    public static final String DIRECT_EMAIL_QUEUE = "DirectEmailQueue";

    /**
     * 路由模式：定义一个发邮件的路由
     */
    public static final String DIRECT_EMAIL_ROUTING_KEY = "DirectEmailRoutingKey";

    /**
     * 路由模式：定义一个发短信的路由
     */
    public static final String DIRECT_SMS_ROUTING_KEY = "DirectSmsRoutingKey";



    /**
     * topic主题模式：定义一个交换机
     */
    public static final String EXCHANGE_TOPIC = "ExchangeTopic";
    /**
     * topic主题模式：定义一个队列
     */
    public static final String TOPIC_SMS_QUEUE = "TopicSmsQueue";
    /**
     * topic主题模式：定义一个队列
     */
    public static final String TOPIC_EMAIL_QUEUE = "TopicEmailQueue";

    /**
     * topic主题模式：定义一个发邮件的通配符路由
     */
    public static final String TOPIC_EMAIL_ROUTING_KEY = "topic.#.email.#";

    /**
     * topic主题模式：定义一个发短信的通配符路由
     */
    public static final String TOPIC_SMS_ROUTING_KEY = "topic.#.sms.#";

    /**
     * topic主题模式：发消息时，交换机指定一个短信路由 这里发送的消息只能传到短信队列
     */
    public static final String TOPIC_ROUTING_KEY_SEND_SMS = "topic.sms";

    /**
     * topic主题模式：发消息时，交换机指定一个邮件路由 这里发送的消息只能传到邮件队列
     */
    public static final String TOPIC_ROUTING_KEY_SEND_EMAIL = "topic.email";

    /**
     * topic主题模式：发消息时，交换机指定一个路由(邮件和短信都可以匹配) 这里发送的消息同时传到短信队列和邮件队列
     */
    public static final String TOPIC_ROUTING_KEY_SEND_SMS_EMAIL = "topic.sms.email";



    /**
     * header模式：定义的交换机名称
     */
    public static final String EXCHANGE_HEADER = "ExchangeHeader";
    /**
     * header模式：定义一个队列
     */
    public static final String HEADER_SMS_QUEUE = "HeaderSmsQueue";
    /**
     * header模式：定义一个队列
     */
    public static final String HEADER_EMAIL_QUEUE = "HeaderEmailQueue";
















}
