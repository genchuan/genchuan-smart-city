package cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo;

import cn.iocoder.yudao.module.inspectop.framework.ImportRespVO;
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

import cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetinfo.AssetInfoDO;
import cn.iocoder.yudao.module.inspectop.service.assetinfo.AssetInfoService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "巡查巡检 - 资产信息")
@RestController
@RequestMapping("/inspectop/asset-info")
@Validated
public class AssetInfoController {

    @Resource
    private AssetInfoService assetInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建资产信息")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-info:create')")
    public CommonResult<Long> createAssetInfo(@Valid @RequestBody AssetInfoSaveReqVO createReqVO) {
        return success(assetInfoService.createAssetInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产信息")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-info:update')")
    public CommonResult<Boolean> updateAssetInfo(@Valid @RequestBody AssetInfoSaveReqVO updateReqVO) {
        assetInfoService.updateAssetInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:asset-info:delete')")
    public CommonResult<Boolean> deleteAssetInfo(@RequestParam("id") Long id) {
        assetInfoService.deleteAssetInfo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除资产信息")
                @PreAuthorize("@ss.hasPermission('inspectop:asset-info:delete')")
    public CommonResult<Boolean> deleteAssetInfoList(@RequestParam("ids") List<Long> ids) {
        assetInfoService.deleteAssetInfoListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-info:query')")
    public CommonResult<AssetInfoRespVO> getAssetInfo(@RequestParam("id") Long id) {
        AssetInfoDO assetInfo = assetInfoService.getAssetInfo(id);
        return success(BeanUtils.toBean(assetInfo, AssetInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产信息分页")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-info:query')")
    public CommonResult<PageResult<AssetInfoRespVO>> getAssetInfoPage(@Valid AssetInfoPageReqVO pageReqVO) {
        PageResult<AssetInfoRespVO> pageResult = assetInfoService.getAssetInfoPage(pageReqVO);
        return success(pageResult);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-info:disable')")
    public CommonResult<Boolean> disableAssetInfo(@Valid @RequestBody AssetInfoDisableReqVO disableReqVO) {
        assetInfoService.disableAssetInfo(disableReqVO.getId());
        return success(true);
    }

    @PutMapping("/scrap")
    @Operation(summary = "报废")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-info:scrap')")
    public CommonResult<Boolean> scrapAssetInfo(@Valid @RequestBody AssetInfoScrapReqVO scrapReqVO) {
        assetInfoService.scrapAssetInfo(scrapReqVO);
        return success(true);
    }

    @PostMapping("/import")
    @Operation(summary = "导入资产信息")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-info:import')")
    public CommonResult<ImportRespVO> importAssetInfo(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") Boolean updateSupport) throws IOException {

        // 检查文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException("请选择要导入的文件");
        }

        // 检查文件格式
        String filename = file.getOriginalFilename();
        if (filename != null && !(filename.endsWith(".xls") || filename.endsWith(".xlsx"))) {
            throw new RuntimeException("请上传Excel文件（.xls 或 .xlsx格式）");
        }

        ImportRespVO respVO = assetInfoService.importAssetInfo(file, updateSupport);
        return success(respVO);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取资产信息统计图表")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-info:chart')")
    public CommonResult<AssetInfoChartRespVO> getAssetInfoChart() {
        AssetInfoChartRespVO chartData = assetInfoService.getAssetInfoChart();
        return success(chartData);
    }

    @GetMapping("/station-simple-list")
    @Operation(summary = "获取已生效的场站列表（用于下拉选择）")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-info:query')")
    public CommonResult<List<StationSimpleRespVO>> getSimpleStationList() {
        List<StationSimpleRespVO> stationList = assetInfoService.getSimpleStationList();
        return success(stationList);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产信息 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetInfoExcel(@Valid AssetInfoPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        // 1. 获取数据列表
        List<AssetInfoRespVO> list = assetInfoService.getAssetInfoPage(pageReqVO).getList();

        // 【新增】2. 对VO列表中的字典值进行转换（数字 -> 中文）
        convertDictValues(list);

        // 3. 导出 Excel
        ExcelUtils.write(response, "资产信息.xls", "数据", AssetInfoRespVO.class, list);
    }

    /**
     * 【新增】转换字典值为中文显示
     * 此方法会修改传入的 voList 中每个对象的 status 字段。
     * @param voList 资产信息响应VO列表
     */
    private void convertDictValues(List<AssetInfoRespVO> voList) {
        if (voList == null || voList.isEmpty()) {
            return;
        }
        for (AssetInfoRespVO vo : voList) {
            // 转换资产状态
            vo.setStatus(convertAssetStatus(vo.getStatus()));
        }
    }

    /**
     * 【新增】转换资产信息状态字典值
     * 根据映射：1-正常，2-禁用，3-报废
     * @param statusCode 状态编码（例如 "1", "2", "3"）
     * @return 对应的中文状态描述
     */
    private String convertAssetStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode.trim()) {
            case "1":
                return "正常";
            case "2":
                return "禁用";
            case "3":
                return "报废";
            default:
                // 如果遇到未知编码，可以选择返回原编码或空字符串，这里返回原编码以便排查。
                return statusCode;
        }
    }

}