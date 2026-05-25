package cn.iocoder.yudao.module.stationresource.vrv.utils.common.operatelog;

import cn.iocoder.yudao.framework.common.biz.system.logger.OperateLogCommonApi;
import cn.iocoder.yudao.framework.common.biz.system.logger.dto.OperateLogCreateReqDTO;
import cn.iocoder.yudao.framework.common.util.monitor.TracerUtils;
import cn.iocoder.yudao.framework.common.util.servlet.ServletUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 操作日志 AOP 切面 —— 自动拦截 stationresource 模块所有 Controller 的 public 方法，
 * 通过 Feign 异步写入 system_operate_log 表，无需手动加注解。
 * @author vrvliang
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Resource
    private OperateLogCommonApi operateLogApi;

    @Around("execution(public * cn.iocoder.yudao.module.stationresource.controller..*Controller.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 未登录用户不记录（定时任务、内部调用等场景）
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser == null) {
            return joinPoint.proceed();
        }

        Class<?> clazz = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // type: 优先取 @Tag.name，兜底取类名（去掉 Controller 后缀）
        String type = resolveType(clazz);
        // subType: 优先取 @Operation.summary，兜底按方法名关键词推断
        String subType = resolveSubType(method);
        // action: subType + 成功/失败
        String[] action = new String[1];

        try {
            Object returnValue = joinPoint.proceed();
            action[0] = subType + "成功";
            return returnValue;
        } catch (Throwable e) {
            action[0] = subType + "失败";
            throw e;
        } finally {
            try {
                OperateLogCreateReqDTO reqDTO = new OperateLogCreateReqDTO();
                reqDTO.setTraceId(TracerUtils.getTraceId());
                reqDTO.setUserId(loginUser.getId());
                reqDTO.setUserType(loginUser.getUserType());
                reqDTO.setType(type);
                reqDTO.setSubType(subType);
                reqDTO.setBizId(resolveBizId(joinPoint));
                reqDTO.setAction(action[0]);

                HttpServletRequest request = ServletUtils.getRequest();
                if (request != null) {
                    reqDTO.setRequestMethod(request.getMethod());
                    reqDTO.setRequestUrl(request.getRequestURI());
                    reqDTO.setUserIp(ServletUtils.getClientIP(request));
                    reqDTO.setUserAgent(ServletUtils.getUserAgent(request));
                } else {
                    reqDTO.setRequestMethod("RPC");
                    reqDTO.setRequestUrl("/" + type);
                    reqDTO.setUserIp("0.0.0.0");
                    reqDTO.setUserAgent("");
                }

                operateLogApi.createOperateLogAsync(reqDTO);
            } catch (Exception e) {
                log.error("[OperateLogAspect][{}.{}] 记录操作日志失败",
                        clazz.getSimpleName(), method.getName(), e);
            }
        }
    }

    /**
     * 获取操作模块类型（type）。
     * 优先读取类上 Swagger {@code @Tag} 注解的 name 属性，
     * 兜底使用类名（去掉 "Controller" 后缀）。
     */
    private String resolveType(Class<?> clazz) {
        Tag tag = clazz.getAnnotation(Tag.class);
        if (tag != null && !tag.name().isEmpty()) {
            return tag.name();
        }
        return clazz.getSimpleName().replace("Controller", "");
    }

    /**
     * 获取操作名称（subType）。
     * 优先读取方法上 Swagger {@code @Operation} 注解的 summary 属性，
     * 兜底按方法名关键词推断（get→查询, add→新增, update→修改, delete→删除, export→导出...）。
     */
    private String resolveSubType(Method method) {
        Operation operation = method.getAnnotation(Operation.class);
        if (operation != null && !operation.summary().isEmpty()) {
            return operation.summary();
        }
        // 兜底：按方法名关键词推断
        return inferFromMethodName(method.getName());
    }

    private String inferFromMethodName(String methodName) {
        String lower = methodName.toLowerCase();
        if (lower.contains("batch")) return "批量操作";
        if (lower.contains("get") || lower.contains("list") || lower.contains("page")) return "查询";
        if (lower.contains("add") || lower.contains("create") || lower.contains("insert")) return "新增";
        if (lower.contains("update") || lower.contains("edit") || lower.contains("modify")) return "修改";
        if (lower.contains("delete") || lower.contains("remove")) return "删除";
        if (lower.contains("export")) return "导出";
        if (lower.contains("import")) return "导入";
        if (lower.contains("enable")) return "启用";
        if (lower.contains("disable")) return "停用";
        return "操作";
    }

    /**
     * 从方法参数中提取业务 ID（bizId）。
     * 依次尝试：Long/Integer 参数 → List 首个元素 → 对象 getId() 方法 → 兜底 0。
     */
    private Long resolveBizId(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg == null) continue;
            if (arg instanceof Long l && l > 0) return l;
            if (arg instanceof Integer i && i > 0) return i.longValue();
            if (arg instanceof List<?> list && !list.isEmpty()) {
                Object first = list.get(0);
                if (first instanceof Long l && l > 0) return l;
                if (first instanceof Integer i && i > 0) return i.longValue();
            }
            try {
                Method getId = arg.getClass().getMethod("getId");
                Object id = getId.invoke(arg);
                if (id instanceof Long l && l > 0) return l;
                if (id instanceof Integer i && i > 0) return i.longValue();
            } catch (Exception ignored) {}
        }
        return 0L;
    }
}
