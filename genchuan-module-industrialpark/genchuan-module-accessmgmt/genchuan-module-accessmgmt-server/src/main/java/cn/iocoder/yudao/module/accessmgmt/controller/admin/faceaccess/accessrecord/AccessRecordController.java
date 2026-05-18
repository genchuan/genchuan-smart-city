package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo.*;
import cn.iocoder.yudao.module.accessmgmt.service.faceaccess.accessrecord.AccessRecordService;
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
 * 通行记录 Controller
 * <p>
 * 提供通行记录的分页查询、详情查询、异常核查、告警、处置及统计态势等 REST API。
 * 通行记录为只读数据，不提供新增/修改/删除接口。
 *
 * @author 亘川智城
 */
@Tag(name = "管理后台 - 通行记录")
@RestController
@RequestMapping("/accessmgmt/access-record")
@Validated
public class AccessRecordController {

    @Resource
    private AccessRecordService accessRecordService;

    @GetMapping("/page")
    @Operation(summary = "获得通行记录分页")
    @PreAuthorize("@ss.hasPermission('accessmgmt:access-record:query')")
    public CommonResult<PageResult<AccessRecordRespVO>> getAccessRecordPage(@Valid AccessRecordPageReqVO pageReqVO) {
        PageResult<AccessRecordRespVO> pageResult = accessRecordService.getAccessRecordPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通行记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('accessmgmt:access-record:query')")
    public CommonResult<AccessRecordRespVO> getAccessRecord(@RequestParam("id") Long id) {
        AccessRecordRespVO respVO = accessRecordService.getAccessRecord(id);
        return success(respVO);
    }

    @PutMapping("/check")
    @Operation(summary = "异常核查")
    @PreAuthorize("@ss.hasPermission('accessmgmt:access-record:check')")
    public CommonResult<Boolean> checkAccessRecord(@Valid @RequestBody AccessRecordCheckReqVO reqVO) {
        Boolean result = accessRecordService.checkAccessRecord(reqVO);
        return success(result);
    }

    @PostMapping("/alarm")
    @Operation(summary = "异常告警")
    @PreAuthorize("@ss.hasPermission('accessmgmt:access-record:alarm')")
    public CommonResult<Boolean> alarmAccessRecord(@Valid @RequestBody AccessRecordAlarmReqVO reqVO) {
        Boolean result = accessRecordService.alarmAccessRecord(reqVO);
        return success(result);
    }

    @PutMapping("/handle")
    @Operation(summary = "异常处置")
    @PreAuthorize("@ss.hasPermission('accessmgmt:access-record:handle')")
    public CommonResult<Boolean> handleAccessRecord(@Valid @RequestBody AccessRecordHandleReqVO reqVO) {
        Boolean result = accessRecordService.handleAccessRecord(reqVO);
        return success(result);
    }

    @GetMapping("/export")
    @Operation(summary = "导出通行记录 Excel")
    @PreAuthorize("@ss.hasPermission('accessmgmt:access-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAccessRecordExcel(@Valid AccessRecordPageReqVO pageReqVO,
                                         HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AccessRecordRespVO> list = accessRecordService.getAccessRecordPage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String dateStr = java.time.LocalDate.now().toString();
        String fileOriginName = "通行记录_" + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8", "");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        ExcelUtils.write(response, "通行记录.xls", "数据", AccessRecordRespVO.class,
                BeanUtils.toBean(list, AccessRecordRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "人员通行统计态势")
    @PreAuthorize("@ss.hasPermission('accessmgmt:access-record:query')")
    public CommonResult<AccessRecordChartRespVO> getAccessRecordChart(
            @Parameter(name = "startTime", description = "统计开始时间，格式时间戳") @RequestParam(value = "startTime", required = false) Long startTime,
            @Parameter(name = "endTime", description = "统计结束时间，格式时间戳") @RequestParam(value = "endTime", required = false) Long endTime) {
        AccessRecordChartRespVO chartVO = accessRecordService.getAccessRecordChart(startTime, endTime);
        return success(chartVO);
    }

}
