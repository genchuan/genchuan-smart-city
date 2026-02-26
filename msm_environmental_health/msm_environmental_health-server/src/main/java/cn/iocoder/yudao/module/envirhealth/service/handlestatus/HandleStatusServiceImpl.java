package cn.iocoder.yudao.module.envirhealth.service.handlestatus;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.handlestatus.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.handlestatus.HandleStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.handlestatus.HandleStatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 处置状态字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class HandleStatusServiceImpl implements HandleStatusService {

    @Resource
    private HandleStatusMapper handleStatusMapper;

    @Override
    public Long createHandleStatus(HandleStatusSaveReqVO createReqVO) {
        // 插入
        HandleStatusDO handleStatus = BeanUtils.toBean(createReqVO, HandleStatusDO.class);
        handleStatusMapper.insert(handleStatus);
        // 返回
        return handleStatus.getId();
    }

    @Override
    public void updateHandleStatus(HandleStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateHandleStatusExists(updateReqVO.getId());
        // 更新
        HandleStatusDO updateObj = BeanUtils.toBean(updateReqVO, HandleStatusDO.class);
        handleStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteHandleStatus(Long id) {
        // 校验存在
        validateHandleStatusExists(id);
        // 删除
        handleStatusMapper.deleteById(id);
    }

    private void validateHandleStatusExists(Long id) {
        if (handleStatusMapper.selectById(id) == null) {
            throw exception(HANDLE_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public HandleStatusDO getHandleStatus(Long id) {
        return handleStatusMapper.selectById(id);
    }

    @Override
    public PageResult<HandleStatusDO> getHandleStatusPage(HandleStatusPageReqVO pageReqVO) {
        return handleStatusMapper.selectPage(pageReqVO);
    }

}