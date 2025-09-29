package cn.iocoder.yudao.module.datacenter.service.staffworkstatus;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.staffworkstatus.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffworkstatus.StaffWorkStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 人员作业状态 Service 接口
 *
 * @author zcq
 */
public interface StaffWorkStatusService {

    /**
     * 创建人员作业状态
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStaffWorkStatus(@Valid StaffWorkStatusSaveReqVO createReqVO);

    /**
     * 更新人员作业状态
     *
     * @param updateReqVO 更新信息
     */
    void updateStaffWorkStatus(@Valid StaffWorkStatusSaveReqVO updateReqVO);

    /**
     * 删除人员作业状态
     *
     * @param id 编号
     */
    void deleteStaffWorkStatus(Long id);

    /**
     * 获得人员作业状态
     *
     * @param id 编号
     * @return 人员作业状态
     */
    StaffWorkStatusDO getStaffWorkStatus(Long id);

    /**
     * 获得人员作业状态分页
     *
     * @param pageReqVO 分页查询
     * @return 人员作业状态分页
     */
    PageResult<StaffWorkStatusDO> getStaffWorkStatusPage(StaffWorkStatusPageReqVO pageReqVO);

    /**
     * 获得所有人员作业状态
     *
     * @return 所有人员作业状态列表
     */
    List<StaffWorkStatusDO> getAllStaffWorkStatus();
}