package cn.iocoder.yudao.module.kitchen.service.violationanalytics;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.drill.ViolationAnalyticsDrillReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.drill.ViolationAnalyticsDrillResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageResp;

public interface ViolationAnalyticsService {
    PageResult<ViolationAnalyticsPageResp> getViolationAnalyticsPage(ViolationAnalyticsPageReq pageReqVO);

    ViolationAnalyticsDrillResp getViolationAnalyticsDrill(ViolationAnalyticsDrillReq req);
}
