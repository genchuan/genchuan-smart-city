package cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.inparkstatus;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusAlarmReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusLocationReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusLocationRespVO;
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

    /**
     * 获取在停状态定位
     *
     * @param reqVO 定位请求
     * @return 定位信息
     */
    InParkStatusLocationRespVO getInParkStatusLocation(InParkStatusLocationReqVO reqVO);

    /**
     * 提醒在场车辆
     *
     * @param id 记录ID
     */
    void remindParkStatus(Long id);

    /**
     * 告警在场车辆
     *
     * @param reqVO 告警请求
     */
    void alarmParkStatus(InParkStatusAlarmReqVO reqVO);

    /**
     * 获取在停状态统计
     *
     * @param chartReqVO 统计请求
     * @return 统计数据
     */
    InParkStatusChartRespVO getInParkStatusChart(InParkStatusChartReqVO chartReqVO);
}