package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.lxscommon.vo.BatchStatusUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.TimePermissionPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.TimePermissionRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.ops.TimePermissionCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.ops.TimePermissionUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.statistics.TimePermissionChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.timepermission.TimePermissionDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.timepermission.TimePermissionService;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 时段权限")
@RestController
@RequestMapping("/stationresource/time-permission")
@Validated
public class TimePermissionController {

    //TODO cANKAN
    @Resource
    private TimePermissionService timePermissionService;

    @GetMapping("/chart")
    @Operation(summary = "时段权限统计（折线图+卡片）")
    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:query')")
    public CommonResult<TimePermissionChartRespVO> getTimePermissionChart() {
        TimePermissionChartRespVO respVO = timePermissionService.getTimePermissionChart();
        return CommonResult.success(respVO);
    }
    @PutMapping("/update")
    @Operation(summary = "修改时段权限")
    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:update')")
    public CommonResult<Boolean> myUpdateTimePermission(@Valid @RequestBody TimePermissionUpdateReqVO updateReqVO) {
        timePermissionService.myUpdateTimePermission(updateReqVO);
        return CommonResult.success(true);
    }
    @PutMapping("/enable")
    @Operation(summary = "批量生效时段权限")
    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:update')")
    public CommonResult<Boolean> enableTimePermission(@Valid @RequestBody BatchStatusUpdateReqVO reqVO) {
        timePermissionService.enableTimePermission(reqVO.getIds());
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "批量禁用时段权限")
    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:update')")
    public CommonResult<Boolean> disableTimePermission(@Valid @RequestBody BatchStatusUpdateReqVO reqVO) {
        timePermissionService.disableTimePermission(reqVO.getIds());
        return CommonResult.success(true);
    }
    // ==================== 【导入接口】 ====================
    @GetMapping("/import-template")
    @Operation(summary = "下载导入模板")
    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:import')")
    public void importTemplate(HttpServletResponse response) throws Exception {
        VrvExcelUtils.downloadImportTemplate(response, TimePermissionCreateReqVO.class);
    }

    @PostMapping("/import")
    @Operation(summary = "导入时段权限", description = "上传Excel文件")
    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:import')")
    public CommonResult<ImportRespVO> importTimePermission(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport) throws Exception {
        ImportRespVO result = timePermissionService.importTimePermission(file, updateSupport);
        return CommonResult.success(result);
    }
    // ==================== 新增接口（新增的） ====================
    @PostMapping("/add")
    @Operation(summary = "创建时段权限")
    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:create')")
    public CommonResult<Boolean> addTimePermission(@Valid @RequestBody TimePermissionCreateReqVO createReqVO) {
        timePermissionService.addTimePermission(createReqVO);
        return CommonResult.success(true);
    }
    @GetMapping("/get")
    @Operation(summary = "获得时段权限")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:query')")
    public CommonResult<TimePermissionRespVO> getTimePermission(@RequestParam("id") Long id) {
        TimePermissionDO timePermission = timePermissionService.getTimePermission(id);
        return success(BeanUtils.toBean(timePermission, TimePermissionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得时段权限分页")
    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:query')")
    public CommonResult<PageResult<TimePermissionRespVO>> getTimePermissionPage(@Valid TimePermissionPageReqVO pageReqVO) {
        PageResult<TimePermissionDO> pageResult = timePermissionService.getTimePermissionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TimePermissionRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出时段权限 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTimePermissionExcel(@Valid TimePermissionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TimePermissionDO> list = timePermissionService.getTimePermissionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "时段权限.xls", "数据", TimePermissionRespVO.class,
                        BeanUtils.toBean(list, TimePermissionRespVO.class));
    }

    //====================================================================================================
//    @PostMapping("/create")
//    @Operation(summary = "创建时段权限")
//    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:create')")
//    public CommonResult<Long> createTimePermission(@Valid @RequestBody TimePermissionSaveReqVO createReqVO) {
//        return success(timePermissionService.createTimePermission(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新时段权限")
//    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:update')")
//    public CommonResult<Boolean> updateTimePermission(@Valid @RequestBody TimePermissionSaveReqVO updateReqVO) {
//        timePermissionService.updateTimePermission(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除时段权限")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:delete')")
//    public CommonResult<Boolean> deleteTimePermission(@RequestParam("id") Long id) {
//        timePermissionService.deleteTimePermission(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除时段权限")
//                @PreAuthorize("@ss.hasPermission('stationresource:time-permission:delete')")
//    public CommonResult<Boolean> deleteTimePermissionList(@RequestParam("ids") List<Long> ids) {
//        timePermissionService.deleteTimePermissionListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得时段权限")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:query')")
//    public CommonResult<TimePermissionRespVO> getTimePermission(@RequestParam("id") Long id) {
//        TimePermissionDO timePermission = timePermissionService.getTimePermission(id);
//        return success(BeanUtils.toBean(timePermission, TimePermissionRespVO.class));
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得时段权限分页")
//    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:query')")
//    public CommonResult<PageResult<TimePermissionRespVO>> getTimePermissionPage(@Valid TimePermissionPageReqVO pageReqVO) {
//        PageResult<TimePermissionDO> pageResult = timePermissionService.getTimePermissionPage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, TimePermissionRespVO.class));
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出时段权限 Excel")
//    @PreAuthorize("@ss.hasPermission('stationresource:time-permission:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportTimePermissionExcel(@Valid TimePermissionPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<TimePermissionDO> list = timePermissionService.getTimePermissionPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "时段权限.xls", "数据", TimePermissionRespVO.class,
//                        BeanUtils.toBean(list, TimePermissionRespVO.class));
//    }

}
