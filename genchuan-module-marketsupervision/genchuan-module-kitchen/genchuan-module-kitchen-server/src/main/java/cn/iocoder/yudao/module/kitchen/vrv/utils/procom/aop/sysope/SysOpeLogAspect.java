package cn.iocoder.yudao.module.kitchen.vrv.utils.procom.aop.sysope;

import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.add.SysOperationLogAddReqVO;
import cn.iocoder.yudao.module.kitchen.service.sysoperationlog.SysOperationLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;



@Slf4j
@Aspect
@Component
public class SysOpeLogAspect {

    @Resource
    private SysOperationLogService sysOperationLogService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("@annotation(sysOpeLog)")
    public Object around(ProceedingJoinPoint joinPoint, SysOpeLog sysOpeLog) throws Throwable {

        String result = "成功";

        try {
            // ===== 执行原方法 =====
            return joinPoint.proceed();
        } catch (Exception e) {
            result = "失败";
            throw e;
        } finally {
            try {

                // ===== 获取方法名 =====
                String methodName = joinPoint.getSignature().getName();

                // ===== 自动解析操作类型 =====
                String operType = resolveOperType(methodName, sysOpeLog.operType());

                // ===== 自动解析操作对象 =====
                String operObject = resolveOperObject(joinPoint, sysOpeLog.operObject());

                // ===== 自动解析描述 =====
                String operDesc = resolveOperDesc(operType,operObject,result, sysOpeLog.operDesc());

                // ===== 构建日志 =====
                SysOperationLogAddReqVO vo = new SysOperationLogAddReqVO();
                vo.setOperType(operType);
                vo.setOperObject(operObject);
                vo.setOperResult(result);
                vo.setOperDesc(operDesc);

                // ===== 新增：批量操作选中条目，仅批量操作类型才记录 =====
                if (operType != null && operType.contains("批量")) {
                    vo.setBatchSelectInfo(resolveBatchSelectInfo(joinPoint, sysOpeLog.batchSelectInfo()));
                }

                // ===== 入库 =====
                sysOperationLogService.addSysOperationLog(vo);

            } catch (Exception ex) {
                log.error("记录操作日志失败", ex);
            }
        }
    }

    /**
     * 解析批量操作选中条目信息
     *
     * @param joinPoint 当前切点，可以获取方法参数
     * @param batchSelectInfo 如果注解里传了已有值，这里优先使用
     * @return JSON 字符串，或者 null
     */
    private String resolveBatchSelectInfo(ProceedingJoinPoint joinPoint, String batchSelectInfo) {
        // 1.注解里已有值，直接返回
        if (batchSelectInfo != null && !batchSelectInfo.isEmpty()) {
            return batchSelectInfo;
        }

        Object[] args = joinPoint.getArgs();
        String[] candidateNames = {"idList", "selectedIds", "tmpIds", "ids"}; // 用于完全匹配或后缀匹配

        for (Object arg : args) {
            if (arg == null) continue;

            // ========== 核心修改1：跳过JDK核心类（如ArrayList） ==========
            Class<?> argClass = arg.getClass();
            if (argClass.getName().startsWith("java.") || argClass.getName().startsWith("javax.") || argClass.getName().startsWith("jakarta.")) {
                log.debug("跳过JDK核心类的字段解析：{}", argClass.getName());
                // 如果是集合类型（如List），直接序列化返回，不解析内部字段
                if (arg instanceof java.util.Collection || arg.getClass().isArray()) {
                    return toJson(arg);
                }
                continue;
            }
            // 2.优先查找带 @BatchIdField 注解的字段
            for (java.lang.reflect.Field field : arg.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(BatchIdField.class)) {
                    field.setAccessible(true);
                    try {
                        Object val = field.get(arg);
                        if (val != null) {
                            return toJson(val);
                        }
                    } catch (IllegalAccessException ignored) {}
                }
            }

            // 3.遍历所有字段进行完全匹配或后缀模糊匹配（忽略大小写）
            for (java.lang.reflect.Field field : arg.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName().toLowerCase();

                for (String candidate : candidateNames) {
                    String lowerCandidate = candidate.toLowerCase();

                    // 完全匹配或后缀匹配
                    if (name.equals(lowerCandidate) || name.endsWith(lowerCandidate)) {
                        try {
                            Object val = field.get(arg);
                            if (val != null) return toJson(val);
                        } catch (IllegalAccessException ignored) {}
                    }
                }
            }
        }

        // 4.没找到就返回 null
        return null;
    }
    /**
     * JDK 序列化对象为 JSON 字符串
     */
    private String toJson(Object obj) {
        try {

            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("批量操作对象序列化失败: {}", obj, e);
            return null;
        }
    }
    /**
     * 操作类型解析
     */
    private String resolveOperType(String methodName, String operType) {

        // 方法上指定优先
        if (operType != null && !operType.isEmpty()) {
            return operType;
        }

        methodName = methodName.toLowerCase();

        // 映射表逻辑
        if (methodName.contains("batch")) {
            return "批量操作";
        } else if (methodName.contains("get") || methodName.contains("list") || methodName.contains("page")) {
            return "查询";
        } else if (methodName.contains("add") || methodName.contains("create")) {
            return "新增";
        } else if (methodName.contains("update") || methodName.contains("edit")) {
            return "编辑";
        } else if (methodName.contains("delete") || methodName.contains("remove")) {
            return "删除";
        } else if (methodName.contains("export")) {
            return "导出";
        } else if (methodName.contains("review") || methodName.contains("approve") || methodName.contains("cancel") || methodName.contains("issue")) {
            return "复审";
        }

        return "其他";
    }

    /**
     * 操作对象解析
     */
    private String resolveOperObject(ProceedingJoinPoint joinPoint, String operObject) {

        // ===== 1. 方法注解优先 =====
        if (operObject != null && !operObject.isEmpty()) {
            return operObject;
        }

        // ===== 2. 类注解 SysOpeModule =====
        Class<?> clazz = joinPoint.getTarget().getClass();
        SysOpeModule module = clazz.getAnnotation(SysOpeModule.class);

        if (module != null && module.value() != null && !module.value().isEmpty()) {
            return module.value();
        }

        // 方法名映射表
        String methodName = joinPoint.getSignature().getName().toLowerCase();

        if (methodName.contains("rectifyreview") ) {
            return "整改复审台账";
        } else if (methodName.contains("punish") || methodName.contains("penalty")) {
            return "处罚复审台账";
        } else if (methodName.contains("ent") || methodName.contains("enterprise")) {
            return "企业信息";
        } else if (methodName.contains("device")) {
            return "设备状态";
        } else if (methodName.contains("risk")) {
            return "风险评估统计";
        } else if (methodName.contains("illegal") || methodName.contains("violation")) {
            return "违规分析统计";
        } else if (methodName.contains("report")) {
            return "自定义报表";
        } else if (methodName.contains("rectifyrecord")) {
            return "企业整改记录";
        } else if (methodName.contains("payment") || methodName.contains("pay")) {
            return "企业缴款记录";
        } else if (methodName.contains("dict") || methodName.contains("dictionary")) {
            return "字典表维护";
        }

        // ===== 3. 默认兜底（类名推断）=====
        String className = clazz.getSimpleName();

        return className.replace("Controller", "")
                .replace("ServiceImpl", "");
    }

    /**
     * 操作描述解析
     */
    private String resolveOperDesc(String operType,String operObject,String result, String operDesc) {

        if (operDesc != null && !operDesc.isEmpty()) {
            return operDesc;
        }

        return operType+operObject+result;
    }
}
