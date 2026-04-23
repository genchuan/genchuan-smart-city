package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.BlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.BlackWhiteListRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.blackwhitelist.BlackWhiteListDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.blackwhitelist.BlackWhiteListService;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 黑白名单")
@RestController
@RequestMapping("/stationresource/black-white-list")
@Validated
//@Hidden
public class BlackWhiteListController {

    @Resource
    private BlackWhiteListService blackWhiteListService;

    // ============================ 图表统计 ============================
    @GetMapping("/chart")
    @Operation(summary = "黑白名单统计（饼图+卡片）")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:query')")
    public CommonResult<BlackWhiteListChartRespVO> getBlackWhiteListChart() {
        BlackWhiteListChartRespVO chartData = blackWhiteListService.getChartData();
        return CommonResult.success(chartData);
    }

    // ============================ 状态操作 ============================
    @PutMapping("/enable")
    @Operation(summary = "批量生效黑白名单")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:update')")
    public CommonResult<Boolean> enableList(@RequestBody List<Long> ids) {
        blackWhiteListService.enableList(ids);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "批量禁用黑白名单")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:update')")
    public CommonResult<Boolean> disableList(@RequestBody List<Long> ids) {
        blackWhiteListService.disableList(ids);
        return success(true);
    }

    // ============================ 新增 ============================
    @PostMapping("/create")
    @Operation(summary = "新增黑白名单")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:create')")
    public CommonResult<Boolean> createBlackWhiteList(@Valid @RequestBody BlackWhiteListCreateReqVO createReqVO) {
        blackWhiteListService.createList(createReqVO);
        return success(true);
    }

    // ============================ 编辑 ============================
    @PutMapping("/update")
    @Operation(summary = "更新黑白名单")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:update')")
    public CommonResult<Boolean> updateBlackWhiteList(@Valid @RequestBody BlackWhiteListUpdateReqVO updateReqVO) {
        blackWhiteListService.updateList(updateReqVO);
        return success(true);
    }

    // ============================ 导入 ============================
    @GetMapping("/import-template")
    @Operation(summary = "下载黑白名单导入模板")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:import')")
    public void importTemplate(HttpServletResponse response) throws Exception {
        VrvExcelUtils.downloadImportTemplate(response, BlackWhiteListCreateReqVO.class);
    }

    @PostMapping("/import")
    @Operation(summary = "导入黑白名单")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:import')")
    public CommonResult<BlackWhiteListImportResp> importList(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport) throws Exception {
        BlackWhiteListImportResp result = blackWhiteListService.importList(file, updateSupport);
        return success(result);
    }

    // ============================ 基础查询 ============================
    @GetMapping("/page")
    @Operation(summary = "获得黑白名单分页")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:query')")
    public CommonResult<PageResult<BlackWhiteListRespVO>> getBlackWhiteListPage(@Valid BlackWhiteListPageReqVO pageReqVO) {
        PageResult<BlackWhiteListDO> pageResult = blackWhiteListService.getListPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BlackWhiteListRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得黑白名单详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:query')")
    public CommonResult<BlackWhiteListRespVO> getBlackWhiteList(@RequestParam("id") Long id) {
        BlackWhiteListDO info = blackWhiteListService.getListInfo(id);
        return success(BeanUtils.toBean(info, BlackWhiteListRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出黑白名单 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid BlackWhiteListPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(0);
        List<BlackWhiteListDO> list = blackWhiteListService.getListPage(pageReqVO).getList();
        ExcelUtils.write(response, "黑白名单.xls", "数据", BlackWhiteListRespVO.class, BeanUtils.toBean(list, BlackWhiteListRespVO.class));
    }
}
