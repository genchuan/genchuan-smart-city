package cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpoints;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpoints.vo.ParkPointsPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpoints.vo.ParkPointsRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpoints.vo.ParkPointsSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpoints.ParkPointsDO;
import cn.iocoder.yudao.module.industry.service.park.marketing.parkpoints.ParkPointsService;
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


@Tag(name = "管理后台 - 用户积分")
@RestController
@RequestMapping("/industry/park-points")
@Validated
public class ParkPointsController {

    @Resource
    private ParkPointsService parkPointsService;

    @PostMapping("/create")
    @Operation(summary = "创建用户积分")
    @PreAuthorize("@ss.hasPermission('industry:park-points:create')")
    public CommonResult<Long> createParkPoints(@Valid @RequestBody ParkPointsSaveReqVO createReqVO) {
        return success(parkPointsService.createParkPoints(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户积分")
    @PreAuthorize("@ss.hasPermission('industry:park-points:update')")
    public CommonResult<Boolean> updateParkPoints(@Valid @RequestBody ParkPointsSaveReqVO updateReqVO) {
        parkPointsService.updateParkPoints(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户积分")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-points:delete')")
    public CommonResult<Boolean> deleteParkPoints(@RequestParam("id") Long id) {
        parkPointsService.deleteParkPoints(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户积分")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-points:query')")
    public CommonResult<ParkPointsRespVO> getParkPoints(@RequestParam("id") Long id) {
        ParkPointsDO parkPoints = parkPointsService.getParkPoints(id);
        return success(BeanUtils.toBean(parkPoints, ParkPointsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户积分分页")
    @PreAuthorize("@ss.hasPermission('industry:park-points:query')")
    public CommonResult<PageResult<ParkPointsRespVO>> getParkPointsPage(@Valid ParkPointsPageReqVO pageReqVO) {
        PageResult<ParkPointsDO> pageResult = parkPointsService.getParkPointsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkPointsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户积分 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-points:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkPointsExcel(@Valid ParkPointsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkPointsDO> list = parkPointsService.getParkPointsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "用户积分.xls", "数据", ParkPointsRespVO.class,
                        BeanUtils.toBean(list, ParkPointsRespVO.class));
    }

}
