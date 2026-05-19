package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo.*;
import cn.iocoder.yudao.module.accessmgmt.service.visitormgmt.visitoraccess.VisitorAccessService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 访客通行 Controller
 * <p>
 * 提供访客通行全流程 REST API：分页查询、详情查询、凭证核验、放行、禁行、提醒、Excel 导出及区域分布态势统计。
 *
 * @author 亘川智城
 */
@Tag(name = "管理后台 - 访客通行")
@RestController
@RequestMapping("/accessmgmt/visitor-access")
@Validated
@Hidden
public class VisitorAccessController {

    @Resource
    private VisitorAccessService visitorAccessService;

    @GetMapping("/page")
    @Operation(summary = "获得访客通行分页")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-access:query')")
    public CommonResult<PageResult<VisitorAccessRespVO>> getPage(@Valid VisitorAccessPageReqVO pageReqVO) {
        return success(visitorAccessService.getVisitorAccessPage(pageReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得访客通行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-access:query')")
    public CommonResult<VisitorAccessRespVO> get(@RequestParam("id") Long id) {
        return success(visitorAccessService.getVisitorAccess(id));
    }

    @PostMapping("/check")
    @Operation(summary = "凭证核验")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-access:check')")
    public CommonResult<VisitorAccessCheckRespVO> check(@Valid @RequestBody VisitorAccessCheckReqVO reqVO) {
        return success(visitorAccessService.checkVisitorAccess(reqVO));
    }

    @PutMapping("/pass")
    @Operation(summary = "放行")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-access:pass')")
    public CommonResult<Boolean> pass(@Valid @RequestBody VisitorAccessPassReqVO reqVO) {
        return success(visitorAccessService.passVisitorAccess(reqVO));
    }

    @PutMapping("/block")
    @Operation(summary = "禁行")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-access:block')")
    public CommonResult<Boolean> block(@Valid @RequestBody VisitorAccessBlockReqVO reqVO) {
        return success(visitorAccessService.blockVisitorAccess(reqVO));
    }

    @PostMapping("/remind")
    @Operation(summary = "提醒")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-access:remind')")
    public CommonResult<Boolean> remind(@Valid @RequestBody VisitorAccessRemindReqVO reqVO) {
        return success(visitorAccessService.remindVisitorAccess(reqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出访客通行 Excel")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-access:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void export(@Valid VisitorAccessPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        List<VisitorAccessRespVO> list = visitorAccessService.getVisitorAccessList(pageReqVO);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String dateStr = java.time.LocalDate.now().toString();
        String fileName = URLEncoder.encode("访客通行_" + dateStr + ".xls", StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8", "");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);
        ExcelUtils.write(response, "访客通行.xls", "数据", VisitorAccessRespVO.class,
                BeanUtils.toBean(list, VisitorAccessRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "访客通行区域分布")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-access:query')")
    public CommonResult<VisitorAccessChartRespVO> chart(
            @Parameter(name = "startTime", description = "统计开始时间") @RequestParam(value = "startTime", required = false) Long startTime,
            @Parameter(name = "endTime", description = "统计结束时间") @RequestParam(value = "endTime", required = false) Long endTime) {
        return success(visitorAccessService.getVisitorAccessChart(startTime, endTime));
    }

}
