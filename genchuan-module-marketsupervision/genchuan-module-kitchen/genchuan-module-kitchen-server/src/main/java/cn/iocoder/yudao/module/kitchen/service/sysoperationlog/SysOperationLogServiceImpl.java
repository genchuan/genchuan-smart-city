package cn.iocoder.yudao.module.kitchen.service.sysoperationlog;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.SysOperationLogPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.SysOperationLogSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.add.SysOperationLogAddReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.sysoperationlog.SysOperationLogDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.sysoperationlog.SysOperationLogMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.*;

/**
 * 系统操作审计日志表，存储平台全模块所有操作的审计日志信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SysOperationLogServiceImpl implements SysOperationLogService {

    @Resource
    private SysOperationLogMapper sysOperationLogMapper;

    @Override
    public Long createSysOperationLog(SysOperationLogSaveReqVO createReqVO) {
        // 插入
        SysOperationLogDO sysOperationLog = BeanUtils.toBean(createReqVO, SysOperationLogDO.class);
        sysOperationLogMapper.insert(sysOperationLog);
        // 返回
        return sysOperationLog.getId();
    }

    @Override
    public void updateSysOperationLog(SysOperationLogSaveReqVO updateReqVO) {
        // 校验存在
        validateSysOperationLogExists(updateReqVO.getId());
        // 更新
        SysOperationLogDO updateObj = BeanUtils.toBean(updateReqVO, SysOperationLogDO.class);
        sysOperationLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteSysOperationLog(Long id) {
        // 校验存在
        validateSysOperationLogExists(id);
        // 删除
        sysOperationLogMapper.deleteById(id);
    }

    private void validateSysOperationLogExists(Long id) {
        if (sysOperationLogMapper.selectById(id) == null) {
            throw exception(SYS_OPERATION_LOG_NOT_EXISTS);
        }
    }

    @Override
    public SysOperationLogDO getSysOperationLog(Long id) {
        return sysOperationLogMapper.selectById(id);
    }

    @Override
    public PageResult<SysOperationLogDO> getSysOperationLogPage(SysOperationLogPageReqVO pageReqVO) {
        return sysOperationLogMapper.selectPage(pageReqVO);
    }

    @Override
    public Long addSysOperationLog(SysOperationLogAddReqVO addReqVO) {

        SysOperationLogDO log = new SysOperationLogDO();

        // ===== 业务字段 =====
        log.setOperType(addReqVO.getOperType());
        log.setOperObject(addReqVO.getOperObject());
        log.setOperResult(addReqVO.getOperResult());
        log.setBatchSelectInfo(addReqVO.getBatchSelectInfo());
        log.setOperDesc(addReqVO.getOperDesc());

        // ===== 系统字段 =====
        log.setOperTime(LocalDateTime.now());

        // 当前登录用户
        Long userId = getLoginUserId();
        log.setOperUserId(userId);

        String username = SecurityFrameworkUtils.getLoginUserNickname()!=null?SecurityFrameworkUtils.getLoginUserNickname():"亘川";
        log.setOperUserName(username);

        // IP
        String ip = WebFrameworkUtils.getRequest().getRemoteAddr();
        log.setOperIp(ip);

        // 插入
        sysOperationLogMapper.insert(log);

        return log.getId();
    }

}
