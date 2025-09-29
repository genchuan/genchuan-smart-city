package cn.iocoder.yudao.module.datacenter.service.staffareaassignment;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.staffareaassignment.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffareaassignment.StaffAreaAssignmentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.staffareaassignment.StaffAreaAssignmentMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 人员区域分配 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class StaffAreaAssignmentServiceImpl implements StaffAreaAssignmentService {

    @Resource
    private StaffAreaAssignmentMapper staffAreaAssignmentMapper;

    @Override
    public Long createStaffAreaAssignment(StaffAreaAssignmentSaveReqVO createReqVO) {
        // 插入
        StaffAreaAssignmentDO staffAreaAssignment = BeanUtils.toBean(createReqVO, StaffAreaAssignmentDO.class);
        staffAreaAssignmentMapper.insert(staffAreaAssignment);
        // 返回
        return staffAreaAssignment.getId();
    }

    @Override
    public void updateStaffAreaAssignment(StaffAreaAssignmentSaveReqVO updateReqVO) {
        // 校验存在
        validateStaffAreaAssignmentExists(updateReqVO.getId());
        // 更新
        StaffAreaAssignmentDO updateObj = BeanUtils.toBean(updateReqVO, StaffAreaAssignmentDO.class);
        staffAreaAssignmentMapper.updateById(updateObj);
    }

    @Override
    public void deleteStaffAreaAssignment(Long id) {
        // 校验存在
        validateStaffAreaAssignmentExists(id);
        // 删除
        staffAreaAssignmentMapper.deleteById(id);
    }

    private void validateStaffAreaAssignmentExists(Long id) {
        if (staffAreaAssignmentMapper.selectById(id) == null) {
            throw exception(STAFF_AREA_ASSIGNMENT_NOT_EXISTS);
        }
    }

    @Override
    public StaffAreaAssignmentDO getStaffAreaAssignment(Long id) {
        return staffAreaAssignmentMapper.selectById(id);
    }

    @Override
    public List<StaffAreaAssignmentDO> getAllStaffAreaAssignments() {
        return staffAreaAssignmentMapper.selectList();
    }

    @Override
    public PageResult<StaffAreaAssignmentDO> getStaffAreaAssignmentPage(StaffAreaAssignmentPageReqVO pageReqVO) {
        return staffAreaAssignmentMapper.selectPage(pageReqVO);
    }

}