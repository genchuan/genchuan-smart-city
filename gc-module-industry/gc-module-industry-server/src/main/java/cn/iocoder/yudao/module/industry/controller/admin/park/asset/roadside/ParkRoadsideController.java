package cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside.vo.ParkRoadsidePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside.vo.ParkRoadsideRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside.vo.ParkRoadsideSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.roadside.ParkRoadsideDO;
import cn.iocoder.yudao.module.industry.service.park.asset.roadside.ParkRoadsideService;

@Tag(name = "管理后台 - 路侧泊位")
@RestController
@RequestMapping("/industry/park-roadside")
@Validated
public class ParkRoadsideController {

    @Resource
    private ParkRoadsideService parkRoadsideService;

    @PostMapping("/create")
    @Operation(summary = "创建路侧泊位")
    @PreAuthorize("@ss.hasPermission('industry:park-roadside:create')")
    public CommonResult<Long> createParkRoadside(@Valid @RequestBody ParkRoadsideSaveReqVO createReqVO) {
        return success(parkRoadsideService.createParkRoadside(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新路侧泊位")
    @PreAuthorize("@ss.hasPermission('industry:park-roadside:update')")
    public CommonResult<Boolean> updateParkRoadside(@Valid @RequestBody ParkRoadsideSaveReqVO updateReqVO) {
        parkRoadsideService.updateParkRoadside(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除路侧泊位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-roadside:delete')")
    public CommonResult<Boolean> deleteParkRoadside(@RequestParam("id") Long id) {
        parkRoadsideService.deleteParkRoadside(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得路侧泊位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-roadside:query')")
    public CommonResult<ParkRoadsideRespVO> getParkRoadside(@RequestParam("id") Long id) {
        ParkRoadsideDO parkRoadside = parkRoadsideService.getParkRoadside(id);
        return success(BeanUtils.toBean(parkRoadside, ParkRoadsideRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得路侧泊位分页")
    @PreAuthorize("@ss.hasPermission('industry:park-roadside:query')")
    public CommonResult<PageResult<ParkRoadsideRespVO>> getParkRoadsidePage(@Valid ParkRoadsidePageReqVO pageReqVO) {
        PageResult<ParkRoadsideDO> pageResult = parkRoadsideService.getParkRoadsidePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkRoadsideRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出路侧泊位 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-roadside:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkRoadsideExcel(@Valid ParkRoadsidePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkRoadsideDO> list = parkRoadsideService.getParkRoadsidePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "路侧泊位.xls", "数据", ParkRoadsideRespVO.class,
                        BeanUtils.toBean(list, ParkRoadsideRespVO.class));
    }

}