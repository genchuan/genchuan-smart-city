package cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage.vo.ParkGaragePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage.vo.ParkGarageRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage.vo.ParkGarageSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.garage.ParkGarageDO;
import cn.iocoder.yudao.module.industry.service.park.asset.garage.ParkGarageService;

@Tag(name = "管理后台 - 车库信息")
@RestController
@RequestMapping("/industry/park-garage")
@Validated
public class ParkGarageController {

    @Resource
    private ParkGarageService parkGarageService;

    @PostMapping("/create")
    @Operation(summary = "创建车库信息")
    @PreAuthorize("@ss.hasPermission('industry:park-garage:create')")
    public CommonResult<Long> createParkGarage(@Valid @RequestBody ParkGarageSaveReqVO createReqVO) {
        return success(parkGarageService.createParkGarage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车库信息")
    @PreAuthorize("@ss.hasPermission('industry:park-garage:update')")
    public CommonResult<Boolean> updateParkGarage(@Valid @RequestBody ParkGarageSaveReqVO updateReqVO) {
        parkGarageService.updateParkGarage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车库信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-garage:delete')")
    public CommonResult<Boolean> deleteParkGarage(@RequestParam("id") Long id) {
        parkGarageService.deleteParkGarage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车库信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-garage:query')")
    public CommonResult<ParkGarageRespVO> getParkGarage(@RequestParam("id") Long id) {
        ParkGarageDO parkGarage = parkGarageService.getParkGarage(id);
        return success(BeanUtils.toBean(parkGarage, ParkGarageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车库信息分页")
    @PreAuthorize("@ss.hasPermission('industry:park-garage:query')")
    public CommonResult<PageResult<ParkGarageRespVO>> getParkGaragePage(@Valid ParkGaragePageReqVO pageReqVO) {
        PageResult<ParkGarageDO> pageResult = parkGarageService.getParkGaragePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkGarageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车库信息 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-garage:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkGarageExcel(@Valid ParkGaragePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkGarageDO> list = parkGarageService.getParkGaragePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车库信息.xls", "数据", ParkGarageRespVO.class,
                        BeanUtils.toBean(list, ParkGarageRespVO.class));
    }

}