package cn.iocoder.yudao.module.energymgmt.service.energymonitor.datacollect;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo.*;
import cn.iocoder.yudao.module.energymgmt.dal.dataobject.energymonitor.datacollect.EnergyCollectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 能耗采集 Service 接口
 *
 * @author 亘川智城
 */
public interface EnergyCollectService {

    /**
     * 获得能耗采集分页
     *
     * @param pageReqVO 分页查询
     * @return 能耗采集分页
     */
    PageResult<EnergyCollectDO> getEnergyCollectPage(EnergyCollectPageReqVO pageReqVO);

    /**
     * 能耗采集设备对接（新建）
     *
     * @param dockReqVO 对接信息
     * @return 布尔值
     */
    Boolean dockEnergyCollect(@Valid EnergyCollectDockReqVO dockReqVO);

    /**
     * 能耗采集信息采集
     *
     * @param collectReqVO 采集信息
     * @return 布尔值
     */
    Boolean collectEnergyCollect(@Valid EnergyCollectCollectReqVO collectReqVO);

    /**
     * 能耗采集信息监测
     *
     * @param monitorReqVO Ids
     * @return 布尔值
     */
    Boolean monitorEnergyCollect(@Valid EnergyCollectMonitorReqVO monitorReqVO);

    /**
     * 获得能耗采集
     *
     * @param id 编号
     * @return 能耗采集
     */
    EnergyCollectDO getEnergyCollect(Long id);

    /**
     * 能耗采集异常排查
     *
     * @param checkReqVO Id
     * @return 布尔值
     */
    Boolean checkEnergyCollect(@Valid EnergyCollectCheckReqVO checkReqVO);

    /**
     * 能源采集重启
     *
     * @param restartReqVO Id
     * @return 布尔值
     */
    Boolean restartEnergyCollect(@Valid EnergyCollectRestartReqVO restartReqVO);

//    ———————————————————— 以上是所需接口 ————————————————————

    /**
     * 创建能耗采集
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEnergyCollect(@Valid EnergyCollectSaveReqVO createReqVO);

    /**
     * 更新能耗采集
     *
     * @param updateReqVO 更新信息
     */
    void updateEnergyCollect(@Valid EnergyCollectSaveReqVO updateReqVO);

    /**
     * 删除能耗采集
     *
     * @param id 编号
     */
    void deleteEnergyCollect(Long id);

    /**
    * 批量删除能耗采集
    *
    * @param ids 编号
    */
    void deleteEnergyCollectListByIds(List<Long> ids);

}