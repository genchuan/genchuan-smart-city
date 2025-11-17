package cn.iocoder.yudao.module.datacenter.service.businessstandard.processexecstd;

import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo.ProcessExecStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo.ProcessExecStdSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessstandard.processexecstd.ProcessExecStdDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 流程执行规范 Service 接口
 *
 * @author 亘川智城
 */
public interface ProcessExecStdService {

    /**
     * 创建流程执行规范
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProcessExecStd(@Valid ProcessExecStdSaveReqVO createReqVO);

    /**
     * 更新流程执行规范
     *
     * @param updateReqVO 更新信息
     */
    void updateProcessExecStd(@Valid ProcessExecStdSaveReqVO updateReqVO);

    /**
     * 删除流程执行规范
     *
     * @param id 编号
     */
    void deleteProcessExecStd(Long id);

    /**
     * 获得流程执行规范
     *
     * @param id 编号
     * @return 流程执行规范
     */
    ProcessExecStdDO getProcessExecStd(Long id);

    /**
     * 获得流程执行规范分页
     *
     * @param pageReqVO 分页查询
     * @return 流程执行规范分页
     */
    PageResult<ProcessExecStdDO> getProcessExecStdPage(ProcessExecStdPageReqVO pageReqVO);

}