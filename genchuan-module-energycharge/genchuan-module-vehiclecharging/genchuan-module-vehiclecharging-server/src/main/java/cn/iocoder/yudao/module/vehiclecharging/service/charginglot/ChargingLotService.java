package cn.iocoder.yudao.module.vehiclecharging.service.charginglot;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.charginglot.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.charginglot.ChargingLotDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 充电车位 Service 接口
 *
 * @author zhucongquan
 */
public interface ChargingLotService {

    /**
     * 创建充电车位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChargingLot(@Valid ChargingLotSaveReqVO createReqVO);

    /**
     * 更新充电车位
     *
     * @param updateReqVO 更新信息
     */
    void updateChargingLot(@Valid ChargingLotSaveReqVO updateReqVO);

    /**
     * 删除充电车位
     *
     * @param id 编号
     */
    void deleteChargingLot(Long id);

    /**
    * 批量删除充电车位
    *
    * @param ids 编号
    */
    void deleteChargingLotListByIds(List<Long> ids);

    /**
     * 获得充电车位
     *
     * @param id 编号
     * @return 充电车位
     */
    ChargingLotDO getChargingLot(Long id);

    /**
     * 获得充电车位分页
     *
     * @param pageReqVO 分页查询
     * @return 充电车位分页
     */
    PageResult<ChargingLotDO> getChargingLotPage(ChargingLotPageReqVO pageReqVO);

    /**
     * 获得充电车位图表统计数据
     * 用于数据可视化大屏（饼图、柱状图、卡片）
     *
     * @return 充电车位图表统计数据
     */
    ChargingLotChartRespVO getChargingLotChart();

}