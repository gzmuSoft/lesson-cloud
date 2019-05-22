package cn.edu.gzmu.config;

import cn.edu.gzmu.model.entity.SysLog;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Rabbit服务端
 * 发送和接收队列消息
 *
 * @author soul
 * @version 1.0
 * @date 19-3-25 14:51
 */
@Component
@Aspect
public class RabbitProducer {
    private final
    HttpServletRequest httpServletRequest;

    /**
     * AmqpTemplate接口定义了发送和接收消息的基本操作。
     */
    private final AmqpTemplate rabbitmqTemplate;


    @Autowired
    public RabbitProducer(AmqpTemplate rabbitmqTemplate, HttpServletRequest httpServletRequest) {
        this.rabbitmqTemplate = rabbitmqTemplate;
        this.httpServletRequest = httpServletRequest;
    }


    @Pointcut("@annotation(org.springframework.data.rest.webmvc.RepositoryRestController)")
    public void annotationRepositoryController() {
    }
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
    public void annotationRestController() {
    }
    @Pointcut("execution(* org.springframework.data.rest.webmvc.*Controller..*(..))")
    public void restController() {
    }

    /**
     * aop切面监控所有执行的方法，获取基本信息保存到log
     * 这里尝试了@Around，@Around和队列会有冲突导致队列通道中断，pass掉
     * <p>
     * getRequestURI()：请求的URI（相对路劲）
     * getMethod()：请求方式
     * getRequestURL()：请求的URL（绝对路劲）
     * getRemoteAddr()：来源ip
     * getServerPort()：端口
     * getServerName()：服务器名，若失败，则返回来源ip
     * getHeader("User-Agent")：浏览器信息
     * getRemoteHost()：客户端电脑名，若失败，则返回来源ip
     */
    @Around("annotationRepositoryController() || restController() || annotationRestController()")
    public Object logMessageGenerate(ProceedingJoinPoint joinPoint) {
        SysLog sysLog = new SysLog();
        sysLog.setArgs(StringUtils.left(
                Stream.of(joinPoint.getArgs())
                        .filter(Objects::nonNull)
                        .map(Object::toString)
                        .collect(Collectors.joining(",")), 255))
                .setBrowser(httpServletRequest.getHeader("User-Agent"))
                .setIp(httpServletRequest.getRemoteAddr())
                .setFromUrl(httpServletRequest.getRequestURL().toString())
                .setUrl(httpServletRequest.getRequestURI())
                .setOperation(httpServletRequest.getMethod());
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            sysLog.setStatus("1")
                    .setResult(StringUtils.left(proceed.toString(), 10240));
        } catch (Throwable throwable) {
            sysLog.setStatus("0")
                    .setResult(StringUtils.left(throwable.getMessage(), 10240));
            throwable.printStackTrace();
        } finally {
            rabbitmqTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, sysLog);
        }
        return proceed;
    }
}