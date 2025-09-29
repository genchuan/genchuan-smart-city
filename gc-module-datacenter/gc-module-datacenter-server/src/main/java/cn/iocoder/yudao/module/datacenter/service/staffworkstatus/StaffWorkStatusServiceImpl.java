package cn.iocoder.yudao.module.datacenter.service.staffworkstatus;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.staffworkstatus.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffworkstatus.StaffWorkStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.staffworkstatus.StaffWorkStatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 人员作业状态 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class StaffWorkStatusServiceImpl implements StaffWorkStatusService {

    @Resource
    private StaffWorkStatusMapper staffWorkStatusMapper;

    @Override
    public Long createStaffWorkStatus(StaffWorkStatusSaveReqVO createReqVO) {
        // 插入
        StaffWorkStatusDO staffWorkStatus = BeanUtils.toBean(createReqVO, StaffWorkStatusDO.class);
        staffWorkStatusMapper.insert(staffWorkStatus);
        // 返回
        return staffWorkStatus.getId();
    }

    @Override
    public void updateStaffWorkStatus(StaffWorkStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateStaffWorkStatusExists(updateReqVO.getId());
        // 更新
        StaffWorkStatusDO updateObj = BeanUtils.toBean(updateReqVO, StaffWorkStatusDO.class);
        staffWorkStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteStaffWorkStatus(Long id) {
        // 校验存在
        validateStaffWorkStatusExists(id);
        // 删除
        staffWorkStatusMapper.deleteById(id);
    }

    private void validateStaffWorkStatusExists(Long id) {
        if (staffWorkStatusMapper.selectById(id) == null) {
            throw exception(STAFF_WORK_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public StaffWorkStatusDO getStaffWorkStatus(Long id) {
        return staffWorkStatusMapper.selectById(id);
    }

    @Override
    public PageResult<StaffWorkStatusDO> getStaffWorkStatusPage(StaffWorkStatusPageReqVO pageReqVO) {
        return staffWorkStatusMapper.selectPage(pageReqVO);
    }

    @Override
    public List<StaffWorkStatusDO> getAllStaffWorkStatus() {
        return staffWorkStatusMapper.selectList();
    }

}