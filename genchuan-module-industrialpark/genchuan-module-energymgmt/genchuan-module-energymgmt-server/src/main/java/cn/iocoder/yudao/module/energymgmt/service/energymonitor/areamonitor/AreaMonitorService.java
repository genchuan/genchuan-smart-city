package cn.iocoder.yudao.module.energymgmt.service.energymonitor.areamonitor;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.areamonitor.vo.*;
import cn.iocoder.yudao.module.energymgmt.dal.dataobject.energymonitor.areamonitor.AreaMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 分区能耗 Service 接口
 *
 * @author 亘川智城
 */
public interface AreaMonitorService {

    /**
     * 获得分区能耗分页
     *
     * @param pageReqVO 分页查询
     * @return 分区能耗分页
     */
    PageResult<AreaMonitorDO> getAreaMonitorPage(AreaMonitorPageReqVO pageReqVO);

    /**
     * 获得分区能耗
     *
     * @param id 编号
     * @return 分区能耗
     */
    AreaMonitorDO getAreaMonitor(Long id);

    //    ———————————————————— 以上是所需接口 ————————————————————

    /**
     * 创建分区能耗
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAreaMonitor(@Valid AreaMonitorSaveReqVO createReqVO);

    /**
     * 更新分区能耗
     *
     * @param updateReqVO 更新信息
     */
    void updateAreaMonitor(@Valid AreaMonitorSaveReqVO updateReqVO);

    /**
     * 删除分区能耗
     *
     * @param id 编号
     */
    void deleteAreaMonitor(Long id);

    /**
    * 批量删除分区能耗
    *
    * @param ids 编号
    */
    void deleteAreaMonitorListByIds(List<Long> ids);

}