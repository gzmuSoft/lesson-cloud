package cn.edu.gzmu.config;

import cn.edu.gzmu.model.entity.SysLog;
import cn.edu.gzmu.repository.entity.SysLogRepository;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Rabbit消费端
 *
 * @author soul
 * @version 1.0
 * @date 19-3-25 14:51
 */
@Component
public class RabbitConsumer {

    private final SysLogRepository sysLogRepository;

    @Autowired
    public RabbitConsumer(SysLogRepository sysLogRepository) {
        this.sysLogRepository = sysLogRepository;
    }

    /**
     * 立即消费者
     */
    @RabbitListener(queues = RabbitConfig.LOG_QUEUE)
    @RabbitHandler
    public void immediateProcess(SysLog sysLog) {
//        SysLog sysLog = (SysLog) params.get("sysLog");
//        ProceedingJoinPoint joinPoint = (ProceedingJoinPoint) params.get("joinPoint");
//        sysLog.setName(StringUtils.right(joinPoint.getSignature().getDeclaringTypeName(), 30))
//                .setSpell(StringUtils.right(joinPoint.getTarget().getClass().getName(), 55))
//                .setRemark(joinPoint.getKind())
//                .setCreateTime(LocalDateTime.now())
//                .setModifyTime(LocalDateTime.now())
//                .setCreateUser(params.get("user").toString())
//                .setModifyUser(params.get("user").toString());
//        System.out.println(sysLog);
        sysLog.setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        sysLogRepository.save(sysLog);
    }
}
