package cn.iocoder.yudao.module.datacenter.service.staffareaassignment;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.staffareaassignment.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffareaassignment.StaffAreaAssignmentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 人员区域分配 Service 接口
 *
 * @author zcq
 */
public interface StaffAreaAssignmentService {

    /**
     * 创建人员区域分配
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStaffAreaAssignment(@Valid StaffAreaAssignmentSaveReqVO createReqVO);

    /**
     * 更新人员区域分配
     *
     * @param updateReqVO 更新信息
     */
    void updateStaffAreaAssignment(@Valid StaffAreaAssignmentSaveReqVO updateReqVO);

    /**
     * 删除人员区域分配
     *
     * @param id 编号
     */
    void deleteStaffAreaAssignment(Long id);

    /**
     * 获得人员区域分配
     *
     * @param id 编号
     * @return 人员区域分配
     */
    StaffAreaAssignmentDO getStaffAreaAssignment(Long id);

    /**
     * 获取所有人员区域分配信息
     *
     * @return 所有人员区域分配列表
     */
    List<StaffAreaAssignmentDO> getAllStaffAreaAssignments();

    /**
     * 获得人员区域分配分页
     *
     * @param pageReqVO 分页查询
     * @return 人员区域分配分页
     */
    PageResult<StaffAreaAssignmentDO> getStaffAreaAssignmentPage(StaffAreaAssignmentPageReqVO pageReqVO);

}