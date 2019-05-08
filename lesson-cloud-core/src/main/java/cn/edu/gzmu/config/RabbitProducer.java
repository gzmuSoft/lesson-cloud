package cn.edu.gzmu.config;

import cn.edu.gzmu.model.entity.SysLog;
import org.aspectj.lang.annotation.Aspect;
//import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Rabbit服务端
 * 发送和接收队列消息
 *
 * @author soul
 * @version 1.0
 * @date 19-3-25 14:51
 */
//@RestController
//@Aspect
public class RabbitProducer {
//    private final
//    HttpServletRequest httpServletRequest;
//
//    /**
//     * AmqpTemplate接口定义了发送和接收消息的基本操作。
//     */
//    private final
//    AmqpTemplate rabbitmqTemplate;
//
//    @Autowired
//    public RabbitProducer(AmqpTemplate rabbitmqTemplate, HttpServletRequest httpServletRequest) {
//        this.rabbitmqTemplate = rabbitmqTemplate;
//        this.httpServletRequest = httpServletRequest;
//    }
//
////    @Around("execution(* cn.edu.gzmu.repository.entity.*..*(..))")
////    public Object processTx(ProceedingJoinPoint joinPoint) throws Throwable {
////        SysLog sysLog = new SysLog();
////        sysLog.setBrowser(httpServletRequest.getRequestURI());
////        Object result = joinPoint.proceed(new String[]{""});
////        rabbitmqTemplate.convertAndSend("log", sysLog);
////        return result;
////    }
//
//    /**
//     * 立即消费者发送方
//     */
//    @RequestMapping("/log1")
//    public String ImmediateSend() {
//        SysLog sysLog = new SysLog();
//        sysLog.setUrl(httpServletRequest.getRequestURI());
//        sysLog.setBrowser(httpServletRequest.getAuthType());
//        sysLog.setFromUrl(httpServletRequest.getServletPath());
//        rabbitmqTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, sysLog);
//        return "队列成功";
//    }
//
//    /**
//     * 延时消费者发送方
//     * delayTime表示设置的延迟时间
//     */
//    @RequestMapping("/log2")
//    public String DelaySend(@RequestParam(value = "delayTime", defaultValue = "1000") String delayTime) {
//        SysLog sysLog = new SysLog();
//        sysLog.setUrl(httpServletRequest.getRequestURI());
//        sysLog.setBrowser(httpServletRequest.getAuthType());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("消息发送时间:" + simpleDateFormat.format(new Date()));
//        rabbitmqTemplate.convertAndSend(RabbitConfig.DELAY_EXCHANGE, RabbitConfig.DELAY_ROUTING_KEY, sysLog,
//                message -> {
//                    message.getMessageProperties().setDelay(Integer.parseInt(delayTime));
//                    return message;
//                });
//        return "队列成功";
//    }

}