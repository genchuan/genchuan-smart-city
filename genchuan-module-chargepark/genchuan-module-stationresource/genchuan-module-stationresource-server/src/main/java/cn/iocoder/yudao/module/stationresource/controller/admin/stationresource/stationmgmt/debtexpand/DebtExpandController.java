package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand;






import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.lxscommon.vo.BatchStatusUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.chart.DebtExpandChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops.DebtExpandStatusReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.DebtExpandPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.DebtExpandRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.DebtExpandSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops.DebtExpandCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops.UpdateDebtExpandReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.debtexpand.DebtExpandDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.stationmgmt.debtexpand.DebtExpandService;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import org.springframework.web.multipart.MultipartFile;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 联合追缴拓场配置")
@RestController
@RequestMapping("/stationresource/debt-expand")
@Validated
public class DebtExpandController {

    @Resource
    private DebtExpandService debtExpandService;

    @GetMapping("/chart")
    @Operation(summary = "拓场追缴统计（图表）")
    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:query')")
    public CommonResult<DebtExpandChartRespVO> getDebtExpandChart() {
        DebtExpandChartRespVO respVO=debtExpandService.getDebtExpandChart();
        return success(respVO);
    }
    @PutMapping("/update")
    @Operation(summary = "更新联合追缴拓场配置")
    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:update')")
    public CommonResult<Boolean> updateDebtExpand(@Valid @RequestBody UpdateDebtExpandReqVO updateReqVO) {
        debtExpandService.myUpdateDebtExpand(updateReqVO);
        return success(true);
    }
    @PutMapping("/enable")
    @Operation(summary = "生效/启用拓场配置")
    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:update')")
    public CommonResult<Boolean> enableDebtExpand(@Valid @RequestBody BatchStatusUpdateReqVO reqVO) {
        debtExpandService.updateDebtExpandStatus(reqVO.getIds(), "已生效");
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用拓场配置")
    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:update')")
    public CommonResult<Boolean> disableDebtExpand(@Valid @RequestBody BatchStatusUpdateReqVO reqVO) {
        debtExpandService.updateDebtExpandStatus(reqVO.getIds(), "已禁用");
        return success(true);
    }
    @GetMapping("/export")
    @Operation(summary = "导出联合追缴拓场配置 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDebtExpandExcel(@Valid DebtExpandPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        // 0. 配置
        String inputFileName = "联合追缴拓场配置_";

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DebtExpandDO> list = debtExpandService.getDebtExpandPage(pageReqVO).getList();

        // 1、强制设置响应头，确保浏览器触发下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        // 2、动态生成文件名，带上当前日期
        String dateStr = java.time.LocalDate.now().toString();
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8","");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        // 3、调用 ExcelUtils 导出
        ExcelUtils.write(response, "联合追缴拓场配置.xls", "数据", DebtExpandRespVO.class,
                BeanUtils.toBean(list, DebtExpandRespVO.class));
    }
    // ========== 1. 下载导入模板 ==========
    @GetMapping("/import-template")
    @Operation(summary = "下载导入模板")
    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:import')")
    public void importTemplate(HttpServletResponse response) throws Exception {
        VrvExcelUtils.downloadImportTemplate(response, DebtExpandCreateReqVO.class);
    }

    // ========== 2. Excel 导入 ==========
    @PostMapping("/import")
    @Operation(summary = "导入联合追缴拓场配置", description = "上传Excel文件")
    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:import')")
    public CommonResult<ImportRespVO> importDebtExpand(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport) throws Exception {
        ImportRespVO result = debtExpandService.importDebtExpand(file, updateSupport);
        return success(result);
    }
    @PostMapping("/create")
    @Operation(summary = "创建联合追缴拓场配置")
    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:create')")
    public CommonResult<Long> createDebtExpand(@Valid @RequestBody DebtExpandCreateReqVO createReqVO) {
        Long id=  debtExpandService.addDebtExpand(createReqVO);
        return CommonResult.success(id);
    }
    @GetMapping("/get")
    @Operation(summary = "获得联合追缴拓场配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:query')")
    public CommonResult<DebtExpandRespVO> getDebtExpand(@RequestParam("id") Long id) {
        DebtExpandDO debtExpand = debtExpandService.getDebtExpand(id);
        return success(BeanUtils.toBean(debtExpand, DebtExpandRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得联合追缴拓场配置分页")
    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:query')")
    public CommonResult<PageResult<DebtExpandRespVO>> getDebtExpandPage(@Valid DebtExpandPageReqVO pageReqVO) {
        PageResult<DebtExpandDO> pageResult = debtExpandService.getDebtExpandPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DebtExpandRespVO.class));
    }

    //=======================================================================================

//    @PostMapping("/create")
//    @Operation(summary = "创建联合追缴拓场配置")
//    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:create')")
//    public CommonResult<Long> createDebtExpand(@Valid @RequestBody DebtExpandSaveReqVO createReqVO) {
//        return success(debtExpandService.createDebtExpand(createReqVO));
//    }
//

//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除联合追缴拓场配置")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:delete')")
//    public CommonResult<Boolean> deleteDebtExpand(@RequestParam("id") Long id) {
//        debtExpandService.deleteDebtExpand(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除联合追缴拓场配置")
//                @PreAuthorize("@ss.hasPermission('stationresource:debt-expand:delete')")
//    public CommonResult<Boolean> deleteDebtExpandList(@RequestParam("ids") List<Long> ids) {
//        debtExpandService.deleteDebtExpandListByIds(ids);
//        return success(true);
//    }
//

//


}
