package cn.iocoder.yudao.module.facility.service.manhole.manholewarn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.*;
import org.springframework.transaction.annotation.Transactional;

public interface ManholeCoverWarnService {
    /**
     * 窨井盖预警数据分页查询
     *
     * @param reqVO 分页查询参数
     * @return 窨井盖预警数据分页列表
     */
    PageResult<ManholeCoverWarnPageRespVO> getWarnPage(ManholeCoverWarnPageReqVO reqVO);
    /**
     * 窨井盖预警数据详情查询
     *
     * @param reqVO 详情查询参数
     * @return 窨井盖预警数据详情
     */
    ManholeCoverWarnDetailRespVO getWarnDetail(ManholeCoverWarnDetailReqVO reqVO);

    /**
     * 触发异常联动报警
     *
     * @param coverId 窨井盖ID
     * @param reqVO 触发参数
     * @return 触发结果
     */
    @Transactional(rollbackFor = Exception.class)
    ManholeCoverWarnTriggerAlarmRespVO triggerAlarm(String coverId, ManholeCoverWarnTriggerAlarmReqVO reqVO);

    /**
     * 更新窨井盖预警状态
     *
     * @param warnId 预警ID
     * @param reqVO 请求参数
     * @return 响应结果
     */
    ManholeCoverWarnUpdateStatusRespVO updateWarnStatus(String warnId, ManholeCoverWarnUpdateStatusReqVO reqVO);

}