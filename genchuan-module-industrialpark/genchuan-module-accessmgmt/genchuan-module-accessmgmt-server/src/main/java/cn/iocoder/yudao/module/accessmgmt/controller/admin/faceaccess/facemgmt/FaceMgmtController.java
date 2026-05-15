package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo.*;
import cn.iocoder.yudao.module.accessmgmt.service.faceaccess.facemgmt.FaceMgmtService;
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

@Tag(name = "管理后台 - 人脸管理")
@RestController
@RequestMapping("/accessmgmt/face-mgmt")
@Validated
public class FaceMgmtController {

    @Resource
    private FaceMgmtService faceMgmtService;

    @GetMapping("/page")
    @Operation(summary = "获得人脸信息分页")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:query')")
    public CommonResult<PageResult<FaceMgmtRespVO>> getFaceMgmtPage(@Valid FaceMgmtPageReqVO pageReqVO) {
        PageResult<FaceMgmtRespVO> pageResult = faceMgmtService.getFaceMgmtPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得人脸信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:query')")
    public CommonResult<FaceMgmtRespVO> getFaceMgmt(@RequestParam("id") Long id) {
        FaceMgmtRespVO respVO = faceMgmtService.getFaceMgmt(id);
        return success(respVO);
    }

    @PostMapping("/create")
    @Operation(summary = "创建人脸信息")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:create')")
    public CommonResult<Boolean> createFaceMgmt(@Valid @RequestBody FaceMgmtCreateReqVO createReqVO) {
        Boolean result = faceMgmtService.createFaceMgmt(createReqVO);
        return success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新人脸信息")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:update')")
    public CommonResult<Boolean> updateFaceMgmt(@Valid @RequestBody FaceMgmtUpdateReqVO updateReqVO) {
        faceMgmtService.updateFaceMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人脸信息")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:delete')")
    public CommonResult<Boolean> deleteFaceMgmt(@Valid @RequestBody FaceMgmtDeleteReqVO reqVO) {
        faceMgmtService.deleteFaceMgmt(reqVO.getIds());
        return success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出人脸信息 Excel")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFaceMgmtExcel(@Valid FaceMgmtPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        String inputFileName = "人脸信息_";

        List<FaceMgmtRespVO> list = faceMgmtService.getFaceMgmtList(pageReqVO);

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String dateStr = java.time.LocalDate.now().toString();
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8", "");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        ExcelUtils.write(response, "人脸信息.xls", "数据", FaceMgmtRespVO.class,
                BeanUtils.toBean(list, FaceMgmtRespVO.class));
    }

    @PostMapping("/collect")
    @Operation(summary = "人脸采集")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:collect')")
    public CommonResult<FaceMgmtCollectRespVO> collectFaceMgmt(@Valid @RequestBody FaceMgmtCollectReqVO reqVO) {
        FaceMgmtCollectRespVO respVO = faceMgmtService.collectFaceMgmt(reqVO);
        return success(respVO);
    }

    @PutMapping("/config")
    @Operation(summary = "权限配置")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:config')")
    public CommonResult<Boolean> configFaceMgmt(@Valid @RequestBody FaceMgmtConfigReqVO reqVO) {
        Boolean result = faceMgmtService.configFaceMgmt(reqVO);
        return success(result);
    }

    @PostMapping("/verify")
    @Operation(summary = "通行验证")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:verify')")
    public CommonResult<FaceMgmtVerifyRespVO> verifyFaceMgmt(@Valid @RequestBody FaceMgmtVerifyReqVO reqVO) {
        FaceMgmtVerifyRespVO respVO = faceMgmtService.verifyFaceMgmt(reqVO);
        return success(respVO);
    }

    @PostMapping("/access")
    @Operation(summary = "通行")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:access')")
    public CommonResult<Boolean> accessFaceMgmt(@Valid @RequestBody FaceMgmtAccessReqVO reqVO) {
        Boolean result = faceMgmtService.accessFaceMgmt(reqVO);
        return success(result);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:disable')")
    public CommonResult<Boolean> disableFaceMgmt(@Valid @RequestBody FaceMgmtDisableReqVO reqVO) {
        Boolean result = faceMgmtService.disableFaceMgmt(reqVO);
        return success(result);
    }

    @PutMapping("/auth")
    @Operation(summary = "授权")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:auth')")
    public CommonResult<Boolean> authFaceMgmt(@Valid @RequestBody FaceMgmtAuthReqVO reqVO) {
        Boolean result = faceMgmtService.authFaceMgmt(reqVO);
        return success(result);
    }

    @PutMapping("/renew")
    @Operation(summary = "续期")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:renew')")
    public CommonResult<Boolean> renewFaceMgmt(@Valid @RequestBody FaceMgmtRenewReqVO reqVO) {
        Boolean result = faceMgmtService.renewFaceMgmt(reqVO);
        return success(result);
    }

    @GetMapping("/chart")
    @Operation(summary = "人脸通行授权态势")
    @PreAuthorize("@ss.hasPermission('accessmgmt:face-mgmt:query')")
    public CommonResult<FaceMgmtChartRespVO> getFaceMgmtChart(
            @Parameter(name = "startTime", description = "统计开始时间，格式时间戳") @RequestParam(value = "startTime", required = false) Long startTime,
            @Parameter(name = "endTime", description = "统计结束时间，格式时间戳") @RequestParam(value = "endTime", required = false) Long endTime) {
        FaceMgmtChartRespVO chartVO = faceMgmtService.getFaceMgmtChart(startTime, endTime);
        return success(chartVO);
    }

}
