package cn.iocoder.yudao.module.inspectop.controller.admin.sparestock;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.sparestock.SpareStockDO;
import cn.iocoder.yudao.module.inspectop.service.sparestock.SpareStockService;

@Tag(name = "巡查巡检 - 备件仓储")
@RestController
@RequestMapping("/inspectop/spare-stock")
@Validated
public class SpareStockController {

    @Resource
    private SpareStockService spareStockService;

    @PostMapping("/create")
    @Operation(summary = "创建备件仓储")
    @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:create')")
    public CommonResult<Long> createSpareStock(@Valid @RequestBody SpareStockSaveReqVO createReqVO) {
        return success(spareStockService.createSpareStock(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新备件仓储")
    @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:update')")
    public CommonResult<Boolean> updateSpareStock(@Valid @RequestBody SpareStockSaveReqVO updateReqVO) {
        spareStockService.updateSpareStock(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除备件仓储")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:delete')")
    public CommonResult<Boolean> deleteSpareStock(@RequestParam("id") Long id) {
        spareStockService.deleteSpareStock(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除备件仓储")
                @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:delete')")
    public CommonResult<Boolean> deleteSpareStockList(@RequestParam("ids") List<Long> ids) {
        spareStockService.deleteSpareStockListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得备件仓储")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:query')")
    public CommonResult<SpareStockRespVO> getSpareStock(@RequestParam("id") Long id) {
        SpareStockDO spareStock = spareStockService.getSpareStock(id);
        return success(BeanUtils.toBean(spareStock, SpareStockRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得备件仓储分页")
    @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:query')")
    public CommonResult<PageResult<SpareStockRespVO>> getSpareStockPage(@Valid SpareStockPageReqVO pageReqVO) {
        PageResult<SpareStockDO> pageResult = spareStockService.getSpareStockPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SpareStockRespVO.class));
    }

    @PostMapping("/in")
    @Operation(summary = "备件入库")
    @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:in')")
    public CommonResult<Boolean> inSpareStock(@Valid @RequestBody SpareStockInReqVO reqVO) {
        spareStockService.inSpareStock(reqVO);
        return success(true);
    }

    @PostMapping("/out")
    @Operation(summary = "备件出库")
    @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:out')")
    public CommonResult<Boolean> outSpareStock(@Valid @RequestBody SpareStockOutReqVO reqVO) {
        spareStockService.outSpareStock(reqVO);
        return success(true);
    }

    @PutMapping("/replenish")
    @Operation(summary = "备件补货")
    @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:replenish')")
    public CommonResult<Boolean> replenishSpareStock(@Valid @RequestBody SpareStockReplenishReqVO reqVO) {
        spareStockService.replenishSpareStock(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得备件仓储统计图表")
    @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:chart')")
    public CommonResult<SpareStockChartRespVO> getSpareStockChart(@Valid SpareStockChartReqVO reqVO) {
        SpareStockChartRespVO chartData = spareStockService.getSpareStockChart(reqVO);
        return success(chartData);
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获取备件列表（用于下拉选择）")
    @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:query')")
    public CommonResult<List<SpareStockSimpleRespVO>> getSimpleSpareList() {
        List<SpareStockSimpleRespVO> spareList = spareStockService.getSimpleSpareList();
        return success(spareList);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出备件仓储 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:spare-stock:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSpareStockExcel(@Valid SpareStockPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        // 1. 获取数据列表
        List<SpareStockDO> list = spareStockService.getSpareStockPage(pageReqVO).getList();
        // 2. 将DO列表转换为RespVO列表
        List<SpareStockRespVO> voList = BeanUtils.toBean(list, SpareStockRespVO.class);

        // 3. 对VO列表中的字典值进行转换（数字 -> 中文）
        convertDictValues(voList);

        // 4. 导出 Excel
        ExcelUtils.write(response, "备件仓储.xls", "数据", SpareStockRespVO.class, voList);
    }

    /**
     * 转换字典值为中文显示
     * 此方法会修改传入的 voList 中每个对象的 status 字段。
     * @param voList 备件仓储响应VO列表
     */
    private void convertDictValues(List<SpareStockRespVO> voList) {
        if (voList == null || voList.isEmpty()) {
            return;
        }
        for (SpareStockRespVO vo : voList) {
            // 转换库存状态
            vo.setStatus(convertStockStatus(vo.getStatus()));
        }
    }

    /**
     * 转换库存状态字典值
     * 根据映射：1-正常、2-低库存、3-预警库存
     * @param statusCode 状态编码（例如 "1", "2", "3"）
     * @return 对应的中文状态描述
     */
    private String convertStockStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode.trim()) {
            case "1":
                return "正常";
            case "2":
                return "低库存";
            case "3":
                return "预警库存";
            default:
                // 如果遇到未知编码，返回原编码以便排查
                return statusCode;
        }
    }

}