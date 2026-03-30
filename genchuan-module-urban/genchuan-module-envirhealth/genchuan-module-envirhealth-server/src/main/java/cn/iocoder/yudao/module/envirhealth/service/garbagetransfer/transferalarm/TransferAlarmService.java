package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferalarm;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDetailDO;
import jakarta.validation.Valid;

/**
 * 转运站预警 Service 接口
 *
 * @author 芋道源码
 */
public interface TransferAlarmService {

    /**
     * 创建转运站预警
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTransferAlarm(@Valid TransferAlarmSaveReqVO createReqVO);

    /**
     * 更新转运站预警
     *
     * @param updateReqVO 更新信息
     */
    void updateTransferAlarm(@Valid TransferAlarmSaveReqVO updateReqVO);

    /**
     * 删除转运站预警
     *
     * @param id 编号
     */
    void deleteTransferAlarm(Long id);

    /**
     * 获得转运站预警
     *
     * @param id 编号
     * @return 转运站预警
     */
    TransferAlarmDO getTransferAlarm(Long id);

    /**
     * 获得转运站预警分页
     *
     * @param pageReqVO 分页查询
     * @return 转运站预警分页
     */
    PageResult<TransferAlarmDO> getTransferAlarmPage(TransferAlarmPageReqVO pageReqVO);

    PageResult<TransferAlarmDetailDO> getTransferAlarmDetailPage(TransferAlarmPageReqVO pageReqVO);

    /**
     * 获取预警看板统计数据
     */
    TransferAlarmDashboardRespVO getTransferAlarmDashboard();

    /**
     * 解除预警
     *
     * @param alarmId 预警ID
     */
    void relieveTransferAlarm(Long alarmId);
}