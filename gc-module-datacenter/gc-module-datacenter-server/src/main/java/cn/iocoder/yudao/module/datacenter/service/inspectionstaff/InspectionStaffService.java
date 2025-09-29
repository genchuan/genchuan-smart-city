package cn.iocoder.yudao.module.datacenter.service.inspectionstaff;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspectionstaff.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspectionstaff.InspectionStaffDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 巡查人员信息 Service 接口
 *
 * @author zcq
 */
public interface InspectionStaffService {

    /**
     * 创建巡查人员信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectionStaff(@Valid InspectionStaffSaveReqVO createReqVO);

    /**
     * 更新巡查人员信息
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectionStaff(@Valid InspectionStaffSaveReqVO updateReqVO);

    /**
     * 删除巡查人员信息
     *
     * @param id 编号
     */
    void deleteInspectionStaff(Long id);

    /**
     * 获得巡查人员信息
     *
     * @param id 编号
     * @return 巡查人员信息
     */
    InspectionStaffDO getInspectionStaff(Long id);

    /**
     * 获得巡查人员信息分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查人员信息分页
     */
    PageResult<InspectionStaffDO> getInspectionStaffPage(InspectionStaffPageReqVO pageReqVO);

    /**
     * 获得全部巡查人员信息列表
     *
     * @return 巡查人员信息列表
     */
    List<InspectionStaffDO> getInspectionStaffList();
}