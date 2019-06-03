package cn.edu.gzmu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author echo
 * @version 1.0
 * @date 19-5-29 20:54
 */
@Aspect
@Component
public class RepositoryAop {

    /**
     * 切点
     */
    @Pointcut("execution(* org.springframework.data.jpa.repository.JpaRepository.getOne(..))")
    public void repositoryGetOne() {
    }

    @Pointcut("execution(* cn.edu.gzmu.repository.BaseRepository.searchAllByIds(..))")
    public void repositorySearchAllByIds() {
    }

    /**
     * 解决太多 if 的操作，让他不抛出异常
     *
     * @param joinPoint 切点
     * @return 结果
     * @throws Throwable 异常
     */
    @Around("repositoryGetOne() || repositorySearchAllByIds()")
    public Object logMessageGenerate(ProceedingJoinPoint joinPoint) throws Throwable {
        Object arg = joinPoint.getArgs()[0];
        if (Objects.isNull(arg)) {
            return null;
        }
        return joinPoint.proceed();
    }

}