package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholeconfig.ManholeConfigDO;
import cn.iocoder.yudao.module.facility.service.manhole.manholeconfig.ManholeConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 窨井盖监测配置")
@RestController
@RequestMapping("/manholeconfig/manhole-config")
@Validated
public class ManholeConfigController {

    @Resource
    private ManholeConfigService manholeConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建窨井盖监测配置")
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:create')")
    public CommonResult<Long> createManholeConfig(@Valid @RequestBody ManholeConfigSaveReqVO createReqVO) {
        return success(manholeConfigService.createManholeConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新窨井盖监测配置")
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:update')")
    public CommonResult<Boolean> updateManholeConfig(@Valid @RequestBody ManholeConfigSaveReqVO updateReqVO) {
        manholeConfigService.updateManholeConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除窨井盖监测配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:delete')")
    public CommonResult<Boolean> deleteManholeConfig(@RequestParam("id") Long id) {
        manholeConfigService.deleteManholeConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得窨井盖监测配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:query')")
    public CommonResult<ManholeConfigRespVO> getManholeConfig(@RequestParam("id") Long id) {
        ManholeConfigDO manholeConfig = manholeConfigService.getManholeConfig(id);
        return success(BeanUtils.toBean(manholeConfig, ManholeConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得窨井盖监测配置分页")
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:query')")
    public CommonResult<PageResult<ManholeConfigRespVO>> getManholeConfigPage(@Valid ManholeConfigPageReqVO pageReqVO) {
        PageResult<ManholeConfigDO> pageResult = manholeConfigService.getManholeConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ManholeConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出窨井盖监测配置 Excel")
    @PreAuthorize("@ss.hasPermission('manholeconfig:manhole-config:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportManholeConfigExcel(@Valid ManholeConfigPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ManholeConfigDO> list = manholeConfigService.getManholeConfigPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "窨井盖监测配置.xls", "数据", ManholeConfigRespVO.class,
                        BeanUtils.toBean(list, ManholeConfigRespVO.class));
    }

    /**
     * 保存监测配置
     */
//    @PostMapping("/saveConfig")
//    @Operation(summary = "保存监测配置", description = "新增/编辑窨井盖监测参数配置")
//    public CommonResult<String> saveConfig(@Valid @RequestBody ManholeConfigReqVO configVO) {
//        manholeConfigService.saveConfig(configVO);
//        return CommonResult.success("配置保存成功！");
//    }

    /**
     * 井盖配置分页查询
     */
    @Operation(summary = "窨井盖配置分页查询")
    @GetMapping("/cover-config-page")
    public CommonResult<PageResult<ManholeCoverConfigPageRespVO>> selectConfigPage(
            @Parameter(description = "井盖ID") @RequestParam(required = false) String coverId,
            @Parameter(description = "配置状态 0-未生效 1-已生效 2-已停用") @RequestParam(required = false) Integer configStatus,
            @Parameter(description = "租户ID", required = true) @RequestParam String tenantId,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer pageNo,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<ManholeCoverConfigPageRespVO> pageResult = manholeConfigService.getConfigPage(coverId, configStatus, tenantId, pageNo, pageSize);
        return CommonResult.success(pageResult);
    }

    /**
     * 获取窨井盖配置详情
     */
    @GetMapping("/get/{id}/{tenantId}")
    @Operation(summary = "获取窨井盖配置详情")
    public CommonResult<ManholeCoverConfigDetailRespVO> getDetail(
            @Parameter(description = "配置ID") @PathVariable Long id,
            @Parameter(description = "租户ID") @PathVariable Long tenantId) {
        return CommonResult.success(manholeConfigService.getDetail(id, tenantId));
    }

    /**
     * 新增窨井盖监测配置
     */
    @PostMapping("/add")
    @Operation(summary = "新增窨井盖监测配置")
    public CommonResult<ManholeCoverConfigAddRespVO> addConfig(@Validated @RequestBody ManholeCoverConfigAddReqVO reqVO) {
        return manholeConfigService.addManholeCoverConfig(reqVO);
    }

    /**
     * 编辑窨井盖监测配置
     */
    @PutMapping("/edit")
    @Operation(summary = "编辑窨井盖监测配置")
    public CommonResult<ManholeCoverConfigEditRespVO> editConfig(@Validated @RequestBody ManholeCoverConfigEditReqVO reqVO) {
        return manholeConfigService.editManholeCoverConfig(reqVO);
    }

    /**
     * 窨井盖监测配置删除
     */
    @DeleteMapping("delete/{configId}")
    @Operation(summary = "窨井盖监测配置删除")
    public CommonResult<String> deleteManholeConfig(
            @PathVariable("configId") String configId,
            @Validated ManholeCoverConfigDeleteReqVO reqVO) {
        return manholeConfigService.deleteManholeCoverConfig(configId, reqVO);
    }
    /**
     * 单井盖监测启动
     */
    @PostMapping("/start/{coverId}")
    @Operation(summary = "单井盖监测启动")
    public CommonResult<ManholeMonitorOperateRespVO> startMonitor(
            // 路径参数：井盖ID
            @PathVariable("coverId") @NotBlank(message = "井盖ID不能为空") String coverId,
            // 请求参数：租户ID
            @RequestParam("tenantId") @NotBlank(message = "租户ID不能为空") String tenantId,
            // 请求参数：操作人ID
            @RequestParam("operateUserId") @NotBlank(message = "操作人ID不能为空") String operateUserId) {
        return manholeConfigService.startMonitor(coverId, tenantId, operateUserId);
    }

    // ==================== 监测停止接口 ====================
    @PostMapping("/stop/{coverId}")
    @Operation(summary = "单井盖监测停止")
    public CommonResult<ManholeMonitorOperateRespVO> stopMonitor(
            // 路径参数
            @PathVariable("coverId") @NotBlank String coverId,
            // 请求体参数（直接接收，无多余VO）
            @RequestParam(required = false) String stopReason,
            @RequestParam @NotBlank String tenantId,
            @RequestParam @NotBlank String operateUserId) {
        return manholeConfigService.stopMonitor(coverId, stopReason, tenantId, operateUserId);
    }

    // ==================== 批量井盖启停 ====================
    @PostMapping("/batch-operate")
    @Operation(summary = "井盖监测 批量启动/停止")
    public CommonResult<ManholeCoverMonitorBatchOperateRespVO> batchOperateMonitor(
            @RequestBody @Valid ManholeCoverMonitorBatchOperateReqVO reqVO) {
        return manholeConfigService.batchOperateMonitor(reqVO);
    }
}