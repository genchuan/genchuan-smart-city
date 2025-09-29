package cn.iocoder.yudao.module.datacenter.service.inspectionstaff;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspectionstaff.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspectionstaff.InspectionStaffDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspectionstaff.InspectionStaffMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查人员信息 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectionStaffServiceImpl implements InspectionStaffService {
    @Resource
    private DeptApi deptApi;

    @Resource
    private InspectionStaffMapper inspectionStaffMapper;


    @Override
    public Long createInspectionStaff(InspectionStaffSaveReqVO createReqVO) {
        // 插入
        InspectionStaffDO inspectionStaff = BeanUtils.toBean(createReqVO, InspectionStaffDO.class);
        inspectionStaffMapper.insert(inspectionStaff);
        // 返回
        return inspectionStaff.getId();
    }

    @Override
    public void updateInspectionStaff(InspectionStaffSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectionStaffExists(updateReqVO.getId());
        // 更新
        InspectionStaffDO updateObj = BeanUtils.toBean(updateReqVO, InspectionStaffDO.class);
        inspectionStaffMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectionStaff(Long id) {
        // 校验存在
        validateInspectionStaffExists(id);
        // 删除
        inspectionStaffMapper.deleteById(id);
    }

    private void validateInspectionStaffExists(Long id) {
        if (inspectionStaffMapper.selectById(id) == null) {
            throw exception(INSPECTION_STAFF_NOT_EXISTS);
        }
    }

    @Override
    public InspectionStaffDO getInspectionStaff(Long id) {
        InspectionStaffDO staff = inspectionStaffMapper.selectById(id);
        if (staff != null && staff.getDeptId() != null) {
            // 通过DeptApi接口调用获取部门信息
            DeptRespDTO dept = deptApi.getDept(staff.getDeptId()).getCheckedData();
            if (dept != null) {
                staff.setDeptName(dept.getName()); // 动态设置部门名称
            }
        }
        return staff;
//        return inspectionStaffMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionStaffDO> getInspectionStaffPage(InspectionStaffPageReqVO pageReqVO) {
//        Set<Long> deptIds = getDeptCondition(pageReqVO.getDeptId());
//        return inspectionStaffMapper.selectPage(pageReqVO);
        PageResult<InspectionStaffDO> pageResult = inspectionStaffMapper.selectPage(pageReqVO);

        // 动态设置部门名称
        if (CollUtil.isNotEmpty(pageResult.getList())) {
            setDeptNamesForStaffList(pageResult.getList());
        }

        return pageResult;
    }

    @Override
    public List<InspectionStaffDO> getInspectionStaffList() {
        // 查询所有数据，按ID倒序排列
//        return inspectionStaffMapper.selectList(new LambdaQueryWrapperX<InspectionStaffDO>()
//                .orderByDesc(InspectionStaffDO::getId));
        // 查询所有数据，按ID倒序排列
        List<InspectionStaffDO> list = inspectionStaffMapper.selectList(new LambdaQueryWrapperX<InspectionStaffDO>()
                .orderByDesc(InspectionStaffDO::getId));

        // 动态设置部门名称
        if (CollUtil.isNotEmpty(list)) {
            setDeptNamesForStaffList(list);
        }

        return list;
    }

    /**
     * 为巡查人员列表批量设置部门名称
     * @param staffList 巡查人员列表
     */
    private void setDeptNamesForStaffList(List<InspectionStaffDO> staffList) {
        // 收集所有需要查询的部门ID
        Set<Long> deptIds = CollectionUtils.convertSet(staffList,
                staff -> staff.getDeptId() != null ? staff.getDeptId() : null);
        deptIds.remove(null); // 移除null值

        if (CollUtil.isEmpty(deptIds)) {
            return;
        }

        // 批量查询部门信息
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(deptIds);

        // 为每个巡查人员设置部门名称
        for (InspectionStaffDO staff : staffList) {
            if (staff.getDeptId() != null && deptMap.containsKey(staff.getDeptId())) {
                staff.setDeptName(deptMap.get(staff.getDeptId()).getName());
            }
        }
    }

}