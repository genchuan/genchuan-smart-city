package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehiclecat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehiclecat.vo.EmerVehicleCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehiclecat.vo.EmerVehicleCatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehiclecat.vo.EmerVehicleCatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehiclecat.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emervehiclecat.EmerVehicleCatDO;
import cn.iocoder.yudao.module.datacenter.service.resschedmanage.emervehiclecat.EmerVehicleCatService;

@Tag(name = "管理后台 - 救援车辆分类配置")
@RestController
@RequestMapping("/datacenter/emer-vehicle-cat")
@Validated
public class EmerVehicleCatController {

    @Resource
    private EmerVehicleCatService emerVehicleCatService;

    @PostMapping("/create")
    @Operation(summary = "创建救援车辆分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-cat:create')")
    public CommonResult<Long> createEmerVehicleCat(@Valid @RequestBody EmerVehicleCatSaveReqVO createReqVO) {
        return success(emerVehicleCatService.createEmerVehicleCat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新救援车辆分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-cat:update')")
    public CommonResult<Boolean> updateEmerVehicleCat(@Valid @RequestBody EmerVehicleCatSaveReqVO updateReqVO) {
        emerVehicleCatService.updateEmerVehicleCat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除救援车辆分类配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-cat:delete')")
    public CommonResult<Boolean> deleteEmerVehicleCat(@RequestParam("id") Long id) {
        emerVehicleCatService.deleteEmerVehicleCat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得救援车辆分类配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-cat:query')")
    public CommonResult<EmerVehicleCatRespVO> getEmerVehicleCat(@RequestParam("id") Long id) {
        EmerVehicleCatDO emerVehicleCat = emerVehicleCatService.getEmerVehicleCat(id);
        return success(BeanUtils.toBean(emerVehicleCat, EmerVehicleCatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得救援车辆分类配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-cat:query')")
    public CommonResult<PageResult<EmerVehicleCatRespVO>> getEmerVehicleCatPage(@Valid EmerVehicleCatPageReqVO pageReqVO) {
        PageResult<EmerVehicleCatDO> pageResult = emerVehicleCatService.getEmerVehicleCatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EmerVehicleCatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出救援车辆分类配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-cat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEmerVehicleCatExcel(@Valid EmerVehicleCatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EmerVehicleCatDO> list = emerVehicleCatService.getEmerVehicleCatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "救援车辆分类配置.xls", "数据", EmerVehicleCatRespVO.class,
                        BeanUtils.toBean(list, EmerVehicleCatRespVO.class));
    }

}