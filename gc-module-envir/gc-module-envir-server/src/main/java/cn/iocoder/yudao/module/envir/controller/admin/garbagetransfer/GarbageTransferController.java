package cn.iocoder.yudao.module.envir.controller.admin.garbagetransfer;

import cn.iocoder.yudao.module.envir.dal.dataobject.garbagetransfer.GarbageTransferDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet.PublicToiletDetailDO;
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

import cn.iocoder.yudao.module.envir.controller.admin.garbagetransfer.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envir.service.garbagetransfer.GarbageTransferService;

@Tag(name = "管理后台 - 垃圾转运站")
@RestController
@RequestMapping("/envir/garbage-transfer")
@Validated
public class GarbageTransferController {

    @Resource
    private GarbageTransferService garbageTransferService;

    @PostMapping("/create")
    @Operation(summary = "创建垃圾转运站")
    @PreAuthorize("@ss.hasPermission('envir:garbage-transfer:create')")
    public CommonResult<Long> createGarbageTransfer(@Valid @RequestBody GarbageTransferSaveReqVO createReqVO) {
        return success(garbageTransferService.createGarbageTransfer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新垃圾转运站")
    @PreAuthorize("@ss.hasPermission('envir:garbage-transfer:update')")
    public CommonResult<Boolean> updateGarbageTransfer(@Valid @RequestBody GarbageTransferSaveReqVO updateReqVO) {
        garbageTransferService.updateGarbageTransfer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除垃圾转运站")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:garbage-transfer:delete')")
    public CommonResult<Boolean> deleteGarbageTransfer(@RequestParam("id") Long id) {
        garbageTransferService.deleteGarbageTransfer(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得垃圾转运站")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:garbage-transfer:query')")
    public CommonResult<GarbageTransferRespVO> getGarbageTransfer(@RequestParam("id") Long id) {
        GarbageTransferDO garbageTransfer = garbageTransferService.getGarbageTransfer(id);
        return success(BeanUtils.toBean(garbageTransfer, GarbageTransferRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得垃圾转运站分页")
    @PreAuthorize("@ss.hasPermission('envir:garbage-transfer:query')")
    public CommonResult<PageResult<GarbageTransferRespVO>> getGarbageTransferPage(@Valid GarbageTransferPageReqVO pageReqVO) {
        PageResult<GarbageTransferDO> pageResult = garbageTransferService.getGarbageTransferPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GarbageTransferRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出垃圾转运站 Excel")
    @PreAuthorize("@ss.hasPermission('envir:garbage-transfer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGarbageTransferExcel(@Valid GarbageTransferPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GarbageTransferDO> list = garbageTransferService.getGarbageTransferPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "垃圾转运站.xls", "数据", GarbageTransferRespVO.class,
                        BeanUtils.toBean(list, GarbageTransferRespVO.class));
    }

    @GetMapping("/list-detail")
    @Operation(summary = "获取垃圾转运列表(详情)")
    @PreAuthorize("@ss.hasPermission('envir:garbage-transfer:query')")
    public CommonResult<List<GarbageTransferDetailDO>> getGarbageTransferListDetail() {
        List<GarbageTransferDetailDO> list = garbageTransferService.getGarbageTransferListDetail();
        return success(list);
    }
}