package cn.iocoder.yudao.module.kitchen.service.sysoperationlog;

import java.util.*;

import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.SysOperationLogPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.SysOperationLogSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.add.SysOperationLogAddReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.sysoperationlog.SysOperationLogDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 系统操作审计日志表，存储平台全模块所有操作的审计日志信息 Service 接口
 *
 * @author 亘川智城
 */
public interface SysOperationLogService {

    /**
     * 创建系统操作审计日志表，存储平台全模块所有操作的审计日志信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSysOperationLog(@Valid SysOperationLogSaveReqVO createReqVO);

    /**
     * 更新系统操作审计日志表，存储平台全模块所有操作的审计日志信息
     *
     * @param updateReqVO 更新信息
     */
    void updateSysOperationLog(@Valid SysOperationLogSaveReqVO updateReqVO);

    /**
     * 删除系统操作审计日志表，存储平台全模块所有操作的审计日志信息
     *
     * @param id 编号
     */
    void deleteSysOperationLog(Long id);

    /**
     * 获得系统操作审计日志表，存储平台全模块所有操作的审计日志信息
     *
     * @param id 编号
     * @return 系统操作审计日志表，存储平台全模块所有操作的审计日志信息
     */
    SysOperationLogDO getSysOperationLog(Long id);

    /**
     * 获得系统操作审计日志表，存储平台全模块所有操作的审计日志信息分页
     *
     * @param pageReqVO 分页查询
     * @return 系统操作审计日志表，存储平台全模块所有操作的审计日志信息分页
     */
    PageResult<SysOperationLogDO> getSysOperationLogPage(SysOperationLogPageReqVO pageReqVO);

    Long addSysOperationLog(@Valid SysOperationLogAddReqVO addReqVO);
}
