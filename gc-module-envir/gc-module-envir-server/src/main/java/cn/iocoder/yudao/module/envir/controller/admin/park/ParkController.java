package cn.iocoder.yudao.module.envir.controller.admin.park;

import cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.park.ParkDetailDO;
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

import cn.iocoder.yudao.module.envir.controller.admin.park.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.park.ParkDO;
import cn.iocoder.yudao.module.envir.service.park.ParkService;

@Tag(name = "管理后台 - 公园")
@RestController
@RequestMapping("/envir/park")
@Validated
public class ParkController {

    @Resource
    private ParkService parkService;

    @PostMapping("/create")
    @Operation(summary = "创建公园")
    @PreAuthorize("@ss.hasPermission('envir:park:create')")
    public CommonResult<Long> createPark(@Valid @RequestBody ParkSaveReqVO createReqVO) {
        return success(parkService.createPark(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公园")
    @PreAuthorize("@ss.hasPermission('envir:park:update')")
    public CommonResult<Boolean> updatePark(@Valid @RequestBody ParkSaveReqVO updateReqVO) {
        parkService.updatePark(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公园")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:park:delete')")
    public CommonResult<Boolean> deletePark(@RequestParam("id") Long id) {
        parkService.deletePark(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公园")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:park:query')")
    public CommonResult<ParkRespVO> getPark(@RequestParam("id") Long id) {
        ParkDO park = parkService.getPark(id);
        return success(BeanUtils.toBean(park, ParkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公园分页")
    @PreAuthorize("@ss.hasPermission('envir:park:query')")
    public CommonResult<PageResult<ParkRespVO>> getParkPage(@Valid ParkPageReqVO pageReqVO) {
        PageResult<ParkDO> pageResult = parkService.getParkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公园 Excel")
    @PreAuthorize("@ss.hasPermission('envir:park:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkExcel(@Valid ParkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkDO> list = parkService.getParkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "公园.xls", "数据", ParkRespVO.class,
                        BeanUtils.toBean(list, ParkRespVO.class));
    }

    @GetMapping("/list-detail")
    @Operation(summary = "获取公园列表(详情)")
    @PreAuthorize("@ss.hasPermission('envir:park:query')")
    public CommonResult<List<ParkDetailDO>> getParkListDetail() {
        List<ParkDetailDO> list = parkService.getParkListDetail();
        return success(list);
    }
}