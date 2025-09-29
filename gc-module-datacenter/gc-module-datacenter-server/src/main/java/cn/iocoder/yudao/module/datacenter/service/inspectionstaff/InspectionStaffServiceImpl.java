package cn.iocoder.yudao.module.datacenter.service.inspectionstaff;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
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
//    @Resource
//    private DeptService deptService;

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
//        InspectionStaffDO staff = inspectionStaffMapper.selectById(id);
//        if (staff != null && staff.getDeptId() != null) {
//            DeptDO dept = deptService.getDept(staff.getDeptId());
//            if (dept != null) {
//                staff.setDeptName(dept.getName()); // 动态设置部门名称
//            }
//        }
//        return staff;
        return inspectionStaffMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionStaffDO> getInspectionStaffPage(InspectionStaffPageReqVO pageReqVO) {
//        Set<Long> deptIds = getDeptCondition(pageReqVO.getDeptId());
        return inspectionStaffMapper.selectPage(pageReqVO);
//        PageResult<InspectionStaffDO> pageResult = inspectionStaffMapper.selectPage(pageReqVO, getDeptCondition(pageReqVO.getDeptId()));
//
//        // 动态设置部门名称
//        if (CollUtil.isNotEmpty(pageResult.getList())) {
//            for (InspectionStaffDO staff : pageResult.getList()) {
//                if (staff.getDeptId() != null) {
//                    DeptDO dept = deptService.getDept(staff.getDeptId());
//                    if (dept != null) {
//                        staff.setDeptName(dept.getName()); // 动态设置部门名称
//                    }
//                }
//            }
//        }
//
//        return pageResult;
    }

    @Override
    public List<InspectionStaffDO> getInspectionStaffList() {
        // 查询所有数据，按ID倒序排列
        return inspectionStaffMapper.selectList(new LambdaQueryWrapperX<InspectionStaffDO>()
                .orderByDesc(InspectionStaffDO::getId));
//        List<InspectionStaffDO> list = inspectionStaffMapper.selectList(new LambdaQueryWrapperX<InspectionStaffDO>()
//                .orderByDesc(InspectionStaffDO::getId));
//
//        // 动态设置部门名称
//        if (CollUtil.isNotEmpty(list)) {
//            for (InspectionStaffDO staff : list) {
//                if (staff.getDeptId() != null) {
//                    DeptDO dept = deptService.getDept(staff.getDeptId());
//                    if (dept != null) {
//                        staff.setDeptName(dept.getName()); // 动态设置部门名称
//                    }
//                }
//            }
//        }

//        return list;
    }

//    private Set<Long> getDeptCondition(Long deptId) {
//        if (deptId == null) {
//            return Collections.emptySet();
//        }
//        Set<Long> deptIds = convertSet(deptService.getChildDeptList(deptId), DeptDO::getId);
//        deptIds.add(deptId); // 包括自身
//        return deptIds;
//    }

}