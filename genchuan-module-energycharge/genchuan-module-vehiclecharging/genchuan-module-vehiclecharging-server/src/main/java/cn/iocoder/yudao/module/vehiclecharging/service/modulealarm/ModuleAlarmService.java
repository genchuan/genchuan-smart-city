package cn.iocoder.yudao.module.vehiclecharging.service.modulealarm;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 模块告警记录 Service 接口
 *
 * @author 亘川智城
 */
public interface ModuleAlarmService {

    /**
     * 创建模块告警记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createModuleAlarm(@Valid ModuleAlarmSaveReqVO createReqVO);

    /**
     * 更新模块告警记录
     *
     * @param updateReqVO 更新信息
     */
    void updateModuleAlarm(@Valid ModuleAlarmSaveReqVO updateReqVO);

    /**
     * 删除模块告警记录
     *
     * @param id 编号
     */
    void deleteModuleAlarm(Long id);

    /**
    * 批量删除模块告警记录
    *
    * @param ids 编号
    */
    void deleteModuleAlarmListByIds(List<Long> ids);

    /**
     * 获得模块告警记录
     *
     * @param id 编号
     * @return 模块告警记录
     */
    ModuleAlarmRespVO getModuleAlarm(Long id);

    /**
     * 获得模块告警记录分页
     *
     * @param pageReqVO 分页查询
     * @return 模块告警记录分页
     */
    PageResult<ModuleAlarmRespVO> getModuleAlarmPage(ModuleAlarmPageReqVO pageReqVO);

    /**
     * 排查告警记录
     *
     * @param checkReqVO 排查请求
     */
    void checkModuleAlarm(@Valid ModuleAlarmCheckReqVO checkReqVO);

    /**
     * 修复告警记录
     *
     * @param repairReqVO 修复请求
     */
    void repairModuleAlarm(@Valid ModuleAlarmRepairReqVO repairReqVO);

    /**
     * 销账告警记录
     *
     * @param closeReqVO 销账请求
     */
    void closeModuleAlarm(@Valid ModuleAlarmCloseReqVO closeReqVO);

    /**
     * 更新告警备注
     *
     * @param remarkReqVO 备注请求
     */
    void updateRemark(@Valid ModuleAlarmRemarkReqVO remarkReqVO);

    /**
     * 获取修复凭证访问地址
     *
     * @param reqVO 请求参数
     * @return 修复凭证文件访问地址
     */
    String getRepairVoucher(@Valid ModuleAlarmRepairVoucherReqVO reqVO);

    /**
     * 获取告警统计图表数据
     *
     * @param reqVO 统计请求
     * @return 图表统计数据
     */
    ModuleAlarmChartRespVO getChartData(ModuleAlarmChartReqVO reqVO);

    /**
     * 获取各模块告警数量统计（柱状图钻取）
     *
     * @param reqVO 统计请求
     * @return 各模块告警数量列表
     */
    List<ModuleAlarmChartRespVO.BarData> getModuleCount(ModuleAlarmModuleCountReqVO reqVO);

    /**
     * 获取各状态告警数量统计（卡片钻取）
     *
     * @param reqVO 统计请求
     * @return 各状态告警数量
     */
    ModuleAlarmCountRespVO getStatusCount(ModuleAlarmCountReqVO reqVO);

}