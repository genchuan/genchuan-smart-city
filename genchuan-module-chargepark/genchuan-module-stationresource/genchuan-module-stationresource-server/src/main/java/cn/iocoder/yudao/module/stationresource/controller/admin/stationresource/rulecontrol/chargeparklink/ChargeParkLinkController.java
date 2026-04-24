package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ChargeParkLinkPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ChargeParkLinkRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ChargeParkLinkSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.chargeparklink.ChargeParkLinkDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.chargeparklink.ChargeParkLinkService;
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


@Tag(name = "管理后台 - 充停联动")
@RestController
@RequestMapping("/stationresource/charge-park-link")
@Validated
//@Hidden
public class ChargeParkLinkController {

    @Resource
    private ChargeParkLinkService chargeParkLinkService;

    // ==================== 图表统计 ====================
    @GetMapping("/chart")
    @Operation(summary = "充停联动统计（折线+柱状+卡片）")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:query')")
    public CommonResult<ChargeParkLinkChartRespVO> getChart() {
        return success(chargeParkLinkService.getChargeParkLinkChart());
    }

    // ==================== 新增 ====================
    @PostMapping("/create")
    @Operation(summary = "新增充停联动")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:create')")
    public CommonResult<Boolean> create(@Valid @RequestBody ChargeParkLinkCreateReqVO reqVO) {
        chargeParkLinkService.createChargeParkLink(reqVO);
        return success(true);
    }

    // ==================== 编辑 ====================
    @PutMapping("/update")
    @Operation(summary = "更新充停联动")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody ChargeParkLinkUpdateReqVO reqVO) {
        chargeParkLinkService.updateChargeParkLink(reqVO);
        return success(true);
    }

    // ==================== 生效 ====================
    @PutMapping("/enable")
    @Operation(summary = "批量生效")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:update')")
    public CommonResult<Boolean> enable(@RequestBody List<Long> ids) {
        chargeParkLinkService.enableChargeParkLink(ids);
        return success(true);
    }

    // ==================== 禁用 ====================
    @PutMapping("/disable")
    @Operation(summary = "批量禁用")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:update')")
    public CommonResult<Boolean> disable(@RequestBody List<Long> ids) {
        chargeParkLinkService.disableChargeParkLink(ids);
        return success(true);
    }

    // ==================== 导入模板 ====================
    @GetMapping("/import-template")
    @Operation(summary = "下载导入模板")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:import')")
    public void importTemplate(HttpServletResponse response) throws Exception {
        VrvExcelUtils.downloadImportTemplate(response, ChargeParkLinkCreateReqVO.class);
    }

    // ==================== 导入 ====================
    @PostMapping("/import")
    @Operation(summary = "导入充停联动")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:import')")
    public CommonResult<ChargeParkLinkImportResp> importCharge(
            @RequestPart("file") MultipartFile file,
            @RequestParam(defaultValue = "false") boolean updateSupport) throws Exception {
        return success(chargeParkLinkService.importChargeParkLink(file, updateSupport));
    }

    // ==================== 详情 ====================

//    @PostMapping("/create")
//    @Operation(summary = "创建充停联动")
//    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:create')")
//    public CommonResult<Long> createChargeParkLink(@Valid @RequestBody ChargeParkLinkSaveReqVO createReqVO) {
//        return success(chargeParkLinkService.createChargeParkLink(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新充停联动")
//    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:update')")
//    public CommonResult<Boolean> updateChargeParkLink(@Valid @RequestBody ChargeParkLinkSaveReqVO updateReqVO) {
//        chargeParkLinkService.updateChargeParkLink(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除充停联动")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:delete')")
//    public CommonResult<Boolean> deleteChargeParkLink(@RequestParam("id") Long id) {
//        chargeParkLinkService.deleteChargeParkLink(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除充停联动")
//                @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:delete')")
//    public CommonResult<Boolean> deleteChargeParkLinkList(@RequestParam("ids") List<Long> ids) {
//        chargeParkLinkService.deleteChargeParkLinkListByIds(ids);
//        return success(true);
//    }

    @GetMapping("/get")
    @Operation(summary = "获得充停联动")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:query')")
    public CommonResult<ChargeParkLinkRespVO> getChargeParkLink(@RequestParam("id") Long id) {
        ChargeParkLinkDO chargeParkLink = chargeParkLinkService.getChargeParkLink(id);
        return success(BeanUtils.toBean(chargeParkLink, ChargeParkLinkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得充停联动分页")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:query')")
    public CommonResult<PageResult<ChargeParkLinkRespVO>> getChargeParkLinkPage(@Valid ChargeParkLinkPageReqVO pageReqVO) {
        PageResult<ChargeParkLinkDO> pageResult = chargeParkLinkService.getChargeParkLinkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ChargeParkLinkRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出充停联动 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChargeParkLinkExcel(@Valid ChargeParkLinkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChargeParkLinkDO> list = chargeParkLinkService.getChargeParkLinkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "充停联动.xls", "数据", ChargeParkLinkRespVO.class,
                        BeanUtils.toBean(list, ChargeParkLinkRespVO.class));
    }

}
