package cn.iocoder.yudao.module.datacenter.service.staffalert;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.staffalert.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffalert.StaffAlertDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 人员异常报警 Service 接口
 *
 * @author zcq
 */
public interface StaffAlertService {

    /**
     * 创建人员异常报警
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStaffAlert(@Valid StaffAlertSaveReqVO createReqVO);

    /**
     * 更新人员异常报警
     *
     * @param updateReqVO 更新信息
     */
    void updateStaffAlert(@Valid StaffAlertSaveReqVO updateReqVO);

    /**
     * 删除人员异常报警
     *
     * @param id 编号
     */
    void deleteStaffAlert(Long id);

    /**
     * 获得人员异常报警
     *
     * @param id 编号
     * @return 人员异常报警
     */
    StaffAlertDO getStaffAlert(Long id);

    /**
     * 获得人员异常报警分页
     *
     * @param pageReqVO 分页查询
     * @return 人员异常报警分页
     */
    PageResult<StaffAlertDO> getStaffAlertPage(StaffAlertPageReqVO pageReqVO);

    /**
     * 获得所有人员异常报警列表
     *
     * @return 人员异常报警列表
     */
    List<StaffAlertDO> getStaffAlertList();

}