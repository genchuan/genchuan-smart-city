package cn.iocoder.yudao.module.accessmgmt.service.faceaccess.accessrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo.*;

/**
 * 通行记录 Service 接口
 *
 * @author 亘川智城
 */
public interface AccessRecordService {

    /**
     * 获得通行记录分页
     */
    PageResult<AccessRecordRespVO> getAccessRecordPage(AccessRecordPageReqVO pageReqVO);

    /**
     * 获得通行记录
     */
    AccessRecordRespVO getAccessRecord(Long id);

    /**
     * 异常核查 —— 填写核查结论
     */
    Boolean checkAccessRecord(AccessRecordCheckReqVO reqVO);

    /**
     * 异常告警 —— 写入告警内容
     */
    Boolean alarmAccessRecord(AccessRecordAlarmReqVO reqVO);

    /**
     * 异常处置 —— 填写处置结果
     */
    Boolean handleAccessRecord(AccessRecordHandleReqVO reqVO);

    /**
     * 人员通行统计态势 —— 多维度统计（时段趋势/每日趋势/区域分布/人员频次）
     */
    AccessRecordChartRespVO getAccessRecordChart(Long startTime, Long endTime);

}
