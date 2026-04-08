package cn.iocoder.yudao.module.kitchen.dal.mysql.violationanalytics;

import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.drill.ViolationAnalyticsDrillReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageResp;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.sysdevice.SysDeviceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViolationAnalyticsMapper {
    // 查询列表
    List<ViolationAnalyticsPageResp> selectViolationAnalyticsList(ViolationAnalyticsPageReq req);

    // 查询总数
    Long selectViolationAnalyticsCount(ViolationAnalyticsPageReq req);

    List<AiAlertMessageDO> drillAlarmList(ViolationAnalyticsDrillReq req);

    List<RectifyReviewDO> drillrectifyReviewDOList(ViolationAnalyticsDrillReq req);

    List<SysDeviceDO> drillNormalSysDeviceDOList(ViolationAnalyticsDrillReq req);

    List<RectifyReviewDO> drillFinishRectifyReviewDOList(ViolationAnalyticsDrillReq req);

//    Long drillViolationAnalyticsCount(ViolationAnalyticsDrillReq req);
}
