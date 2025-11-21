package cn.iocoder.yudao.module.datacenter.service.businessstandard.processexecstd;

import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo.ProcessExecStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo.ProcessExecStdSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessstandard.processexecstd.ProcessExecStdDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.businessstandard.processexecstd.ProcessExecStdMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.PROCESS_EXEC_STD_NOT_EXISTS;

/**
 * 流程执行规范 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ProcessExecStdServiceImpl implements ProcessExecStdService {

    @Resource
    private ProcessExecStdMapper processExecStdMapper;

    @Override
    public Long createProcessExecStd(ProcessExecStdSaveReqVO createReqVO) {
        // 插入
        ProcessExecStdDO processExecStd = BeanUtils.toBean(createReqVO, ProcessExecStdDO.class);
        processExecStdMapper.insert(processExecStd);
        // 返回
        return processExecStd.getId();
    }

    @Override
    public void updateProcessExecStd(ProcessExecStdSaveReqVO updateReqVO) {
        // 校验存在
        validateProcessExecStdExists(updateReqVO.getId());
        // 更新
        ProcessExecStdDO updateObj = BeanUtils.toBean(updateReqVO, ProcessExecStdDO.class);
        processExecStdMapper.updateById(updateObj);
    }

    @Override
    public void deleteProcessExecStd(Long id) {
        // 校验存在
        validateProcessExecStdExists(id);
        // 删除
        processExecStdMapper.deleteById(id);
    }

    private void validateProcessExecStdExists(Long id) {
        if (processExecStdMapper.selectById(id) == null) {
            throw exception(PROCESS_EXEC_STD_NOT_EXISTS);
        }
    }

    @Override
    public ProcessExecStdDO getProcessExecStd(Long id) {
        return processExecStdMapper.selectById(id);
    }

    @Override
    public PageResult<ProcessExecStdDO> getProcessExecStdPage(ProcessExecStdPageReqVO pageReqVO) {
        return processExecStdMapper.selectPage(pageReqVO);
    }

}