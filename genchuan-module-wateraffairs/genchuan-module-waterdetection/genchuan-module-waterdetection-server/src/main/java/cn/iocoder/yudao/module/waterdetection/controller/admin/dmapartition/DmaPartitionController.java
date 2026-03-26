package cn.iocoder.yudao.module.waterdetection.controller.admin.dmapartition;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.dmapartition.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.dmapartition.DmaPartitionDO;
import cn.iocoder.yudao.module.waterdetection.service.dmapartition.DmaPartitionService;

@Tag(name = "管理后台 - DMA分区划分与调整")
@RestController
@RequestMapping("/waterdetection/dma-partition")
@Validated
public class DmaPartitionController {

    @Resource
    private DmaPartitionService dmaPartitionService;

    @PostMapping("/create")
    @Operation(summary = "创建DMA分区划分与调整")
    @PreAuthorize("@ss.hasPermission('waterdetection:dma-partition:create')")
    public CommonResult<Long> createDmaPartition(@Valid @RequestBody DmaPartitionSaveReqVO createReqVO) {
        return success(dmaPartitionService.createDmaPartition(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新DMA分区划分与调整")
    @PreAuthorize("@ss.hasPermission('waterdetection:dma-partition:update')")
    public CommonResult<Boolean> updateDmaPartition(@Valid @RequestBody DmaPartitionSaveReqVO updateReqVO) {
        dmaPartitionService.updateDmaPartition(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除DMA分区划分与调整")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:dma-partition:delete')")
    public CommonResult<Boolean> deleteDmaPartition(@RequestParam("id") Long id) {
        dmaPartitionService.deleteDmaPartition(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得DMA分区划分与调整")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:dma-partition:query')")
    public CommonResult<DmaPartitionRespVO> getDmaPartition(@RequestParam("id") Long id) {
        DmaPartitionDO dmaPartition = dmaPartitionService.getDmaPartition(id);
        return success(BeanUtils.toBean(dmaPartition, DmaPartitionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得DMA分区划分与调整分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:dma-partition:query')")
    public CommonResult<PageResult<DmaPartitionRespVO>> getDmaPartitionPage(@Valid DmaPartitionPageReqVO pageReqVO) {
        PageResult<DmaPartitionDO> pageResult = dmaPartitionService.getDmaPartitionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DmaPartitionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出DMA分区划分与调整 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:dma-partition:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDmaPartitionExcel(@Valid DmaPartitionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DmaPartitionDO> list = dmaPartitionService.getDmaPartitionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "DMA分区划分与调整.xls", "数据", DmaPartitionRespVO.class,
                        BeanUtils.toBean(list, DmaPartitionRespVO.class));
    }

}