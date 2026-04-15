package cn.iocoder.yudao.module.inspectop.service.oilmonitor;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.oilmonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.oilmonitor.OilMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 油车占位监测 Service 接口
 *
 * @author zhucongquan
 */
public interface OilMonitorService {

    /**
     * 创建油车占位监测
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOilMonitor(@Valid OilMonitorSaveReqVO createReqVO);

    /**
     * 更新油车占位监测
     *
     * @param updateReqVO 更新信息
     */
    void updateOilMonitor(@Valid OilMonitorSaveReqVO updateReqVO);

    /**
     * 删除油车占位监测
     *
     * @param id 编号
     */
    void deleteOilMonitor(Long id);

    /**
    * 批量删除油车占位监测
    *
    * @param ids 编号
    */
    void deleteOilMonitorListByIds(List<Long> ids);

    /**
     * 获得油车占位监测
     *
     * @param id 编号
     * @return 油车占位监测
     */
    OilMonitorDO getOilMonitor(Long id);

    /**
     * 获得油车占位监测分页
     *
     * @param pageReqVO 分页查询
     * @return 油车占位监测分页
     */
    PageResult<OilMonitorDO> getOilMonitorPage(OilMonitorPageReqVO pageReqVO);

}