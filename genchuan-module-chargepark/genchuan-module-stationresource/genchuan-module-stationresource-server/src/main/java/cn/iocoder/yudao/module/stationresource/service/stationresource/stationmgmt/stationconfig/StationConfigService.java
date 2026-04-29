package cn.iocoder.yudao.module.stationresource.service.stationresource.stationmgmt.stationconfig;

import java.util.*;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.StationConfigPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.StationConfigRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.StationConfigSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.ops.AddStationConfigReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.ops.UpdateStationConfigReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.statistics.StationConfigChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationconfig.StationConfigDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 场站配置 Service 接口
 *
 * @author 亘川智城
 */
public interface StationConfigService {

    /**
     * 创建场站配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStationConfig(@Valid StationConfigSaveReqVO createReqVO);

    /**
     * 更新场站配置
     *
     * @param updateReqVO 更新信息
     */
    void updateStationConfig(@Valid UpdateStationConfigReqVO updateReqVO);

    /**
     * 删除场站配置
     *
     * @param id 编号
     */
    void deleteStationConfig(Long id);

    /**
    * 批量删除场站配置
    *
    * @param ids 编号
    */
    void deleteStationConfigListByIds(List<Long> ids);

    /**
     * 获得场站配置
     *
     * @param id 编号
     * @return 场站配置
     */
    StationConfigRespVO getStationConfig(Long id);

    /**
     * 获得场站配置分页
     *
     * @param pageReqVO 分页查询
     * @return 场站配置分页
     */
    PageResult<StationConfigRespVO> getStationConfigPage(StationConfigPageReqVO pageReqVO);

    Long addStationConfig(AddStationConfigReqVO createReqVO);


    void enableStationConfig(List<Long> ids);

    void disableStationConfig(List<Long> ids);

    StationConfigChartRespVO getStationConfigChart();
}
