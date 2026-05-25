package cn.iocoder.yudao.module.enterprisesvc.service.staffmgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.enterprisesvc.controller.admin.staffmgmt.vo.*;
import cn.iocoder.yudao.module.enterprisesvc.dal.dataobject.staffmgmt.StaffMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 企业员工 Service 接口
 *
 * @author zhucongquan
 */
public interface StaffMgmtService {

    /**
     * 创建企业员工
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStaffMgmt(@Valid StaffMgmtSaveReqVO createReqVO);

    /**
     * 更新企业员工
     *
     * @param updateReqVO 更新信息
     */
    void updateStaffMgmt(@Valid StaffMgmtSaveReqVO updateReqVO);

    /**
     * 删除企业员工
     *
     * @param id 编号
     */
    void deleteStaffMgmt(Long id);

    /**
    * 批量删除企业员工
    *
    * @param ids 编号
    */
    void deleteStaffMgmtListByIds(List<Long> ids);

    /**
     * 获得企业员工
     *
     * @param id 编号
     * @return 企业员工
     */
    StaffMgmtDO getStaffMgmt(Long id);

    /**
     * 获得企业员工分页
     *
     * @param pageReqVO 分页查询
     * @return 企业员工分页
     */
    PageResult<StaffMgmtDO> getStaffMgmtPage(StaffMgmtPageReqVO pageReqVO);

}