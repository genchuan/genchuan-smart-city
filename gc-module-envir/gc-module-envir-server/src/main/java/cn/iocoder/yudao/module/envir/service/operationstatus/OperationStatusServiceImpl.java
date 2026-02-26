package cn.iocoder.yudao.module.envir.service.operationstatus;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.operationstatus.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.operationstatus.OperationStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.operationstatus.OperationStatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 运营状态字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OperationStatusServiceImpl implements OperationStatusService {

    @Resource
    private OperationStatusMapper operationStatusMapper;

    @Override
    public Long createOperationStatus(OperationStatusSaveReqVO createReqVO) {
        // 插入
        OperationStatusDO operationStatus = BeanUtils.toBean(createReqVO, OperationStatusDO.class);
        operationStatusMapper.insert(operationStatus);
        // 返回
        return operationStatus.getId();
    }

    @Override
    public void updateOperationStatus(OperationStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateOperationStatusExists(updateReqVO.getId());
        // 更新
        OperationStatusDO updateObj = BeanUtils.toBean(updateReqVO, OperationStatusDO.class);
        operationStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteOperationStatus(Long id) {
        // 校验存在
        validateOperationStatusExists(id);
        // 删除
        operationStatusMapper.deleteById(id);
    }

    private void validateOperationStatusExists(Long id) {
        if (operationStatusMapper.selectById(id) == null) {
            throw exception(OPERATION_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public OperationStatusDO getOperationStatus(Long id) {
        return operationStatusMapper.selectById(id);
    }

    @Override
    public PageResult<OperationStatusDO> getOperationStatusPage(OperationStatusPageReqVO pageReqVO) {
        return operationStatusMapper.selectPage(pageReqVO);
    }

}