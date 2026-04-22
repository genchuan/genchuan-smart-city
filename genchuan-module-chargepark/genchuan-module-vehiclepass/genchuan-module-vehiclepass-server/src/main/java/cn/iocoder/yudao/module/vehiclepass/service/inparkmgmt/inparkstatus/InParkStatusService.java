package cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.inparkstatus;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.inparkstatus.InParkStatusDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 在停状态 Service 接口
 *
 * @author 亘川智城
 */
public interface InParkStatusService {

    /**
     * 创建在停状态
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkStatus(@Valid InParkStatusSaveReqVO createReqVO);

    /**
     * 更新在停状态
     *
     * @param updateReqVO 更新信息
     */
    void updateParkStatus(@Valid InParkStatusSaveReqVO updateReqVO);

    /**
     * 删除在停状态
     *
     * @param id 编号
     */
    void deleteParkStatus(Long id);

    /**
     * 批量删除在停状态
     *
     * @param ids 编号
     */
    void deleteParkStatusListByIds(List<Long> ids);

    /**
     * 获得在停状态
     *
     * @param id 编号
     * @return 在停状态
     */
    InParkStatusDO getParkStatus(Long id);

    /**
     * 获得在停状态分页
     *
     * @param pageReqVO 分页查询
     * @return 在停状态分页
     */
    PageResult<InParkStatusDO> getParkStatusPage(InParkStatusPageReqVO pageReqVO);

    /**
     * 获得在停状态分页（含场站名称）
     *
     * @param pageReqVO 分页查询
     * @return 在停状态分页
     */
    PageResult<InParkStatusRespVO> getInParkStatusPage(InParkStatusPageReqVO pageReqVO);
}