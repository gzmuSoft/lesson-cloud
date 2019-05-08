package cn.edu.gzmu.config;

import cn.edu.gzmu.model.entity.SysLog;
import cn.edu.gzmu.repository.entity.SysLogRepository;
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Rabbit消费端
 *
 * @author soul
 * @version 1.0
 * @RabbitListener 监听log队列
 * @date 19-3-25 14:51
 */
@Component
public class RabbitConsumer {
//    private final
//    SysLogRepository sysLogRepository;
//
//    @Autowired
//    public RabbitConsumer(SysLogRepository sysLogRepository) {
//        this.sysLogRepository = sysLogRepository;
//    }
//
//    /**
//     * 立即消费者
//     */
//    @RabbitListener(queues = RabbitConfig.LOG_QUEUE)
//    @RabbitHandler
//    public void immediateProcess(SysLog sysLog, Channel channel, Message message) throws IOException {
//        try {
//            sysLogRepository.save(sysLog);
//        } catch (Exception e) {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        }
//    }
//
//
//    /**
//     * 延时消费者
//     */
//    @RabbitListener(queues = RabbitConfig.DELAY_LOG_QUEUE)
//    @RabbitHandler
//    public void delayProcess(SysLog sysLog, Channel channel, Message message) throws IOException {
//        try {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println("消息接收时间:" + simpleDateFormat.format(new Date()));
//            sysLogRepository.save(sysLog);
//        } catch (Exception e) {
//            //这段代码表示，这次消息我已经接受并消费掉了，不会再重复发送消费,不然当有错误的队列信息时会使得程序死循环
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        }
//    }

}
