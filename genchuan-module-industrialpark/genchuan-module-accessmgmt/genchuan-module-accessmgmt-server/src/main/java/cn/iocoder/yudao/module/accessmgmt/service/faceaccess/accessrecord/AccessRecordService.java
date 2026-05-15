package cn.iocoder.yudao.module.accessmgmt.service.faceaccess.accessrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo.*;

import java.util.List;

/**
 * 通行记录 Service 接口
 *
 * @author 亘川智城
 */
public interface AccessRecordService {

    PageResult<AccessRecordRespVO> getAccessRecordPage(AccessRecordPageReqVO pageReqVO);

    AccessRecordRespVO getAccessRecord(Long id);

    Boolean checkAccessRecord(AccessRecordCheckReqVO reqVO);

    Boolean alarmAccessRecord(AccessRecordAlarmReqVO reqVO);

    Boolean handleAccessRecord(AccessRecordHandleReqVO reqVO);

    List<AccessRecordRespVO> getAccessRecordList(AccessRecordPageReqVO pageReqVO);

    AccessRecordChartRespVO getAccessRecordChart(String startTime, String endTime);

}
