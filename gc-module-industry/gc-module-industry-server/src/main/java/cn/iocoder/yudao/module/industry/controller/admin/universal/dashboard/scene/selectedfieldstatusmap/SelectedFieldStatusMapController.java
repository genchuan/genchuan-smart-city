package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.selectedfieldstatusmap;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.selectedfieldstatusmap.vo.SelectedFieldStatusMapPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.selectedfieldstatusmap.vo.SelectedFieldStatusMapRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.selectedfieldstatusmap.vo.SelectedFieldStatusMapSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.selectedfieldstatusmap.SelectedFieldStatusMapDO;
import cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.selectedfieldstatusmap.SelectedFieldStatusMapService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;



@Tag(name = "管理后台 - 场景字段状态映射")
@RestController
@RequestMapping("/industry/selected-field-status-map")
@Validated
public class SelectedFieldStatusMapController {

    @Resource
    private SelectedFieldStatusMapService selectedFieldStatusMapService;

    @PostMapping("/create")
    @Operation(summary = "创建场景字段状态映射")
    @PreAuthorize("@ss.hasPermission('industry:selected-field-status-map:create')")
    public CommonResult<Long> createSelectedFieldStatusMap(@Valid @RequestBody SelectedFieldStatusMapSaveReqVO createReqVO) {
        return success(selectedFieldStatusMapService.createSelectedFieldStatusMap(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新场景字段状态映射")
    @PreAuthorize("@ss.hasPermission('industry:selected-field-status-map:update')")
    public CommonResult<Boolean> updateSelectedFieldStatusMap(@Valid @RequestBody SelectedFieldStatusMapSaveReqVO updateReqVO) {
        selectedFieldStatusMapService.updateSelectedFieldStatusMap(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除场景字段状态映射")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:selected-field-status-map:delete')")
    public CommonResult<Boolean> deleteSelectedFieldStatusMap(@RequestParam("id") Long id) {
        selectedFieldStatusMapService.deleteSelectedFieldStatusMap(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得场景字段状态映射")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:selected-field-status-map:query')")
    public CommonResult<SelectedFieldStatusMapRespVO> getSelectedFieldStatusMap(@RequestParam("id") Long id) {
        SelectedFieldStatusMapDO selectedFieldStatusMap = selectedFieldStatusMapService.getSelectedFieldStatusMap(id);
        return success(BeanUtils.toBean(selectedFieldStatusMap, SelectedFieldStatusMapRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得场景字段状态映射分页")
    @PreAuthorize("@ss.hasPermission('industry:selected-field-status-map:query')")
    public CommonResult<PageResult<SelectedFieldStatusMapRespVO>> getSelectedFieldStatusMapPage(@Valid SelectedFieldStatusMapPageReqVO pageReqVO) {
        PageResult<SelectedFieldStatusMapDO> pageResult = selectedFieldStatusMapService.getSelectedFieldStatusMapPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SelectedFieldStatusMapRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出场景字段状态映射 Excel")
    @PreAuthorize("@ss.hasPermission('industry:selected-field-status-map:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSelectedFieldStatusMapExcel(@Valid SelectedFieldStatusMapPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SelectedFieldStatusMapDO> list = selectedFieldStatusMapService.getSelectedFieldStatusMapPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "场景字段状态映射.xls", "数据", SelectedFieldStatusMapRespVO.class,
                        BeanUtils.toBean(list, SelectedFieldStatusMapRespVO.class));
    }

}
