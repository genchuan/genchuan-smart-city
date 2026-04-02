package cn.iocoder.yudao.module.kitchen.controller.admin.riskreport;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageResp;

import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.procom.aop.sysope.SysOpeLog;
import cn.iocoder.yudao.module.kitchen.service.riskreport.RiskReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 企业风险评估报告")
@RestController
@RequestMapping("/kitchen/risk-report")
@Validated
public class RiskReportController {

    @Resource
    private RiskReportService riskReportService;

    @GetMapping("/page")
    @Operation(summary = "获取分页-企业风险报表")
    //@PreAuthorize("@ss.hasPermission('kitchen:risk-report:query')")
    public CommonResult<PageResult<EntReportPageResp>> getEntReportPage(@Valid EntReportPageReq pageReqVO) {
        PageResult<EntReportPageResp> pageResult = riskReportService.getEntReportPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EntReportPageResp.class));
    }
}
