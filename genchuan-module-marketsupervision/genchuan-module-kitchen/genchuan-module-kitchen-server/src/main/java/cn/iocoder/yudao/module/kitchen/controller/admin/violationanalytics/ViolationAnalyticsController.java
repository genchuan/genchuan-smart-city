package cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageResp;
import cn.iocoder.yudao.module.kitchen.service.violationanalytics.ViolationAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 企业违规数据分析")
@RestController
@RequestMapping("/kitchen/violation-analytics")
@Validated
public class ViolationAnalyticsController {
    @Resource
    private ViolationAnalyticsService violationAnalyticsService;


    @GetMapping("/page")
    @Operation(summary = "获取分页-企业违规数据分析")
    //@PreAuthorize("@ss.hasPermission('kitchen:violation-analytics:query')")
    public CommonResult<PageResult<ViolationAnalyticsPageResp>> getViolationAnalyticsPage(@Valid ViolationAnalyticsPageReq pageReqVO) {
        PageResult<ViolationAnalyticsPageResp> pageResult = violationAnalyticsService.getViolationAnalyticsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ViolationAnalyticsPageResp.class));
    }
}
