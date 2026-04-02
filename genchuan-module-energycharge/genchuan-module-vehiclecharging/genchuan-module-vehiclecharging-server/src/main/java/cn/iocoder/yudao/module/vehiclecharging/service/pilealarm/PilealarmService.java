package cn.iocoder.yudao.module.vehiclecharging.service.pilealarm;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pilealarm.PilealarmDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 充电桩告警 Service 接口
 *
 * @author 亘川智城
 */
public interface PilealarmService {

    /**
     * 创建充电桩告警
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPilealarm(@Valid PilealarmSaveReqVO createReqVO);

    /**
     * 更新充电桩告警
     *
     * @param updateReqVO 更新信息
     */
    void updatePilealarm(@Valid PilealarmSaveReqVO updateReqVO);

    /**
     * 删除充电桩告警
     *
     * @param id 编号
     */
    void deletePilealarm(String id);

    /**
     * 批量删除充电桩告警
     *
     * @param ids 编号
     */
    void deletePilealarmListByIds(List<String> ids);

    /**
     * 获得充电桩告警
     *
     * @param id 编号
     * @return 充电桩告警
     */
    PilealarmDO getPilealarm(String id);

    /**
     * 获得充电桩告警分页
     *
     * @param pageReqVO 分页查询
     * @return 充电桩告警分页
     */
    PageResult<PilealarmDO> getPilealarmPage(PilealarmPageReqVO pageReqVO);

    /**
     * 获得充电桩告警分页
     *
     * @param reqVO 分页查询
     * @return 充电桩告警分页
     */
    PageResult<NewPileAlarmRespVO> page(NewPileAlarmPageReqVO reqVO);

    /**
     * 充电桩告警 - 派单
     * @param reqVO 派单参数
     * @return 是否成功
     */
    Boolean disPileAlarm(PileAlarmDisReqVO reqVO);

    /**
     * 充电桩告警 - 处置
     * @param reqVO 处置参数
     * @return 是否成功
     */
    Boolean handlePileAlarm(PileAlarmHandleReqVO reqVO);
    /**
     * 充电桩告警 - 消单
     * @param id 充电桩告警id
     * @return 是否成功
     */
    Boolean closePileAlarm(Long id);

    /**
     * 充电桩告警 - 修改备注
     * @param id 充电桩告警id
     * @param remark 备注
     * @return 是否成功
     */
    Boolean updateRemark(Long id, String remark);
    /**
     * 充电桩告警 - 统计
     * @param reqVO 统计参数
     * @return 统计结果
     */
    PileAlarmChartRespVO getAlarmChart(PileAlarmChartReqVO reqVO);
    /**
     * 充电桩告警 - 获取日统计
     * @param reqVO 获取日统计参数
     * @return 日统计结果
     */
    List<PileAlarmChartRespVO.BarData> getDailyCount(PileAlarmDailyCountReqVO reqVO);

    /**
     * 统计告警类型占比（饼图）
     */
    List<PileAlarmTypeRatioRespVO> getAlarmTypeRatio(PileAlarmDailyCountReqVO reqVO);

    /**
     * 告警处置统计
     */
    PileAlarmHandleCountRespVO getHandleCount(PileAlarmDailyCountReqVO reqVO);
}