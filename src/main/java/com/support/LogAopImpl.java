package com.support;

import com.annotation.LogRecord;
import com.entity.LogInfo;
import com.entity.User;
import com.service.LogInfoServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
public class LogAopImpl {
    @Autowired
    private LogInfoServiceImpl logInfoService;

    @Pointcut("@annotation(com.annotation.LogRecord)")
    public void controllerLog(){

    }

    @Around("controllerLog()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        if (httpServletRequest == null) {
            return null;
        }
        LogInfo logInfo=new LogInfo();
        MethodSignature signature=(MethodSignature) joinPoint.getSignature();
        Method method=signature.getMethod();
        LogRecord systemControllerLog=method.getAnnotation(LogRecord.class);
        Object proceed=joinPoint.proceed();
        if (systemControllerLog !=null&&proceed!=null){
            String operation =systemControllerLog.operation();
            logInfo.setLogOp(operation);
            String type=systemControllerLog.type();
            logInfo.setLogOp(type);
            String token=httpServletRequest.getHeader("token");
            if (StringUtils.isBlank(token)){
                return null;
            }
            User user=JWTUtil.unsign(token,User.class);
                if (user==null){
                    return null;
                }

            logInfo.setUserId(user.getUid());
            logInfo.setUrl(httpServletRequest.getRemoteUser());
            logInfo.setCreateTime(System.currentTimeMillis());

            logInfoService.insertLog(logInfo);
        }
        return proceed;
    }
    private HttpServletRequest getHttpServletRequest(){
        RequestAttributes requestAttributes=RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes!=null?servletRequestAttributes.getRequest():null;
    }
}
