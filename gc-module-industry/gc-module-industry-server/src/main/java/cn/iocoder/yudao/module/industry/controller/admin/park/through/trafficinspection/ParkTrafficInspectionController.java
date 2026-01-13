package cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection.vo.ParkTrafficInspectionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection.vo.ParkTrafficInspectionRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection.vo.ParkTrafficInspectionSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.trafficinspection.ParkTrafficInspectionDO;
import cn.iocoder.yudao.module.industry.service.park.through.trafficinspection.ParkTrafficInspectionService;

@Tag(name = "管理后台 - 通行稽查")
@RestController
@RequestMapping("/industry/park-traffic-inspection")
@Validated
public class ParkTrafficInspectionController {

    @Resource
    private ParkTrafficInspectionService parkTrafficInspectionService;

    @PostMapping("/create")
    @Operation(summary = "创建通行稽查")
    @PreAuthorize("@ss.hasPermission('industry:park-traffic-inspection:create')")
    public CommonResult<Long> createParkTrafficInspection(@Valid @RequestBody ParkTrafficInspectionSaveReqVO createReqVO) {
        return success(parkTrafficInspectionService.createParkTrafficInspection(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通行稽查")
    @PreAuthorize("@ss.hasPermission('industry:park-traffic-inspection:update')")
    public CommonResult<Boolean> updateParkTrafficInspection(@Valid @RequestBody ParkTrafficInspectionSaveReqVO updateReqVO) {
        parkTrafficInspectionService.updateParkTrafficInspection(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通行稽查")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-traffic-inspection:delete')")
    public CommonResult<Boolean> deleteParkTrafficInspection(@RequestParam("id") Long id) {
        parkTrafficInspectionService.deleteParkTrafficInspection(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通行稽查")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-traffic-inspection:query')")
    public CommonResult<ParkTrafficInspectionRespVO> getParkTrafficInspection(@RequestParam("id") Long id) {
        ParkTrafficInspectionDO parkTrafficInspection = parkTrafficInspectionService.getParkTrafficInspection(id);
        return success(BeanUtils.toBean(parkTrafficInspection, ParkTrafficInspectionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得通行稽查分页")
    @PreAuthorize("@ss.hasPermission('industry:park-traffic-inspection:query')")
    public CommonResult<PageResult<ParkTrafficInspectionRespVO>> getParkTrafficInspectionPage(@Valid ParkTrafficInspectionPageReqVO pageReqVO) {
        PageResult<ParkTrafficInspectionDO> pageResult = parkTrafficInspectionService.getParkTrafficInspectionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkTrafficInspectionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出通行稽查 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-traffic-inspection:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkTrafficInspectionExcel(@Valid ParkTrafficInspectionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkTrafficInspectionDO> list = parkTrafficInspectionService.getParkTrafficInspectionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "通行稽查.xls", "数据", ParkTrafficInspectionRespVO.class,
                        BeanUtils.toBean(list, ParkTrafficInspectionRespVO.class));
    }

}