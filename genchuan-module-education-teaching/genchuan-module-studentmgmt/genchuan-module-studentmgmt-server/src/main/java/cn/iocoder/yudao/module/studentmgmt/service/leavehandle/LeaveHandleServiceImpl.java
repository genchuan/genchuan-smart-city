package cn.iocoder.yudao.module.studentmgmt.service.leavehandle;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.leavehandle.LeaveHandleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.leavehandle.LeaveHandleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 离校办理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class LeaveHandleServiceImpl implements LeaveHandleService {

    @Resource
    private LeaveHandleMapper leaveHandleMapper;

    @Override
    public Long createLeaveHandle(LeaveHandleSaveReqVO createReqVO) {
        // 插入
        LeaveHandleDO leaveHandle = BeanUtils.toBean(createReqVO, LeaveHandleDO.class);
        leaveHandleMapper.insert(leaveHandle);

        // 返回
        return leaveHandle.getId();
    }

    @Override
    public void updateLeaveHandle(LeaveHandleSaveReqVO updateReqVO) {
        // 校验存在
        validateLeaveHandleExists(updateReqVO.getId());
        // 更新
        LeaveHandleDO updateObj = BeanUtils.toBean(updateReqVO, LeaveHandleDO.class);
        leaveHandleMapper.updateById(updateObj);
    }

    @Override
    public void deleteLeaveHandle(Long id) {
        // 校验存在
        validateLeaveHandleExists(id);
        // 删除
        leaveHandleMapper.deleteById(id);
    }

    @Override
        public void deleteLeaveHandleListByIds(List<Long> ids) {
        // 删除
        leaveHandleMapper.deleteByIds(ids);
        }


    private void validateLeaveHandleExists(Long id) {
        if (leaveHandleMapper.selectById(id) == null) {
            throw exception(LEAVE_HANDLE_NOT_EXISTS);
        }
    }

    @Override
    public LeaveHandleDO getLeaveHandle(Long id) {
        return leaveHandleMapper.selectById(id);
    }

    @Override
    public PageResult<LeaveHandleDO> getLeaveHandlePage(LeaveHandlePageReqVO pageReqVO) {
        return leaveHandleMapper.selectPage(pageReqVO);
    }

}