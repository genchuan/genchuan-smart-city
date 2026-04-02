package cn.iocoder.yudao.module.vehiclecharging.service.chargingstation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.chargingstation.ChargingStationDO;

import java.util.List;

public interface ChargingStationService {
    /**
     * 获得充电站分页
     *
     * @param reqVO 分页条件
     * @return 充电站分页
     */
    PageResult<ChargingStationRespVO> getChargingStationPage(ChargingStationPageReqVO reqVO);
    /**
     * 创建充电站
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChargingStation(ChargingStationCreateReqVO createReqVO);

    /**
     * 更新充电站
     *
     * @param updateReqVO 更新信息
     */
    void updateChargingStation(ChargingStationUpdateReqVO updateReqVO);

    /**
     * 获取充电站详情
     */
    ChargingStationRespVO getChargingStation(Long id);
    /**
     * 批量停用充电站
     */
    void batchDisable(ChargingStationDisableReqVO reqVO);
    /**
     * 批量启用充电站
     */
    void batchEnable(ChargingStationEnableReqVO reqVO);
    /**
     * 停用充电站
     */
    void disable(Long id, String stopReason);
    /**
     * 启用充电站
     */
    void enable(Long id);
    /**
     * 获得充电站列表, 用于 Excel 导出
     *
     * @param reqVO 列表请求
     * @return 充电站列表
     */
    PageResult<ChargingStationDO> getChargingStationPage(ChargingStationExportReqVO reqVO);
    /**
     * 获得充电站图表数据
     *
     * @return 充电站图表数据
     */
    ChargingStationChartRespVO getChartData();

    /**
     * 批量改变合作模式和负责人
     */
    void batchChangeCooperationModeAndLeader(ChargingStationBatchUpdateReqVO reqVO);
}
