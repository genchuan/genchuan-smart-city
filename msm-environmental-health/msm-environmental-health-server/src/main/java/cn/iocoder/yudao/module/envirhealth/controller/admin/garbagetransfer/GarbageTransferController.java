package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.GarbageTransferDetailDO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.garbagetransfer.GarbageTransferService;

@Tag(name = "环境卫生管理 - 垃圾转运站")
@RestController
@RequestMapping("/envirhealth/garbage-transfer")
@Validated
public class GarbageTransferController {

    @Resource
    private GarbageTransferService garbageTransferService;

    @PostMapping("/create")
    @Operation(summary = "创建垃圾转运站")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:create')")
    public CommonResult<Long> createGarbageTransfer(@Valid @RequestBody GarbageTransferSaveReqVO createReqVO) {
        return success(garbageTransferService.createGarbageTransfer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新垃圾转运站")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:update')")
    public CommonResult<Boolean> updateGarbageTransfer(@Valid @RequestBody GarbageTransferSaveReqVO updateReqVO) {
        garbageTransferService.updateGarbageTransfer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除垃圾转运站")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:delete')")
    public CommonResult<Boolean> deleteGarbageTransfer(@RequestParam("id") Long id) {
        garbageTransferService.deleteGarbageTransfer(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得垃圾转运站")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:query')")
    public CommonResult<GarbageTransferRespVO> getGarbageTransfer(@RequestParam("id") Long id) {
        GarbageTransferDO garbageTransfer = garbageTransferService.getGarbageTransfer(id);
        return success(BeanUtils.toBean(garbageTransfer, GarbageTransferRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得垃圾转运站分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:query')")
    public CommonResult<PageResult<GarbageTransferRespVO>> getGarbageTransferPage(@Valid GarbageTransferPageReqVO pageReqVO) {
        PageResult<GarbageTransferDO> pageResult = garbageTransferService.getGarbageTransferPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GarbageTransferRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出垃圾转运站 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGarbageTransferExcel(@Valid GarbageTransferPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GarbageTransferDO> list = garbageTransferService.getGarbageTransferPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "垃圾转运站.xls", "数据", GarbageTransferRespVO.class,
                        BeanUtils.toBean(list, GarbageTransferRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得垃圾转运站详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:query')")
    public CommonResult<PageResult<GarbageTransferDetailDO>> getPublicToiletDetailPage(
            @Valid GarbageTransferPageReqVO pageReqVO) {
        PageResult<GarbageTransferDetailDO> pageResult =
                garbageTransferService.getGarbageTransferDetailPage(pageReqVO);

        return success(pageResult);
    }
}