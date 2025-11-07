package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo.vo.EmerVehicleInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo.vo.EmerVehicleInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo.vo.EmerVehicleInfoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emervehicleinfo.EmerVehicleInfoDO;
import cn.iocoder.yudao.module.datacenter.service.resschedmanage.emervehicleinfo.EmerVehicleInfoService;

@Tag(name = "管理后台 - 救援车辆信息")
@RestController
@RequestMapping("/datacenter/emer-vehicle-info")
@Validated
public class EmerVehicleInfoController {

    @Resource
    private EmerVehicleInfoService emerVehicleInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建救援车辆信息")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-info:create')")
    public CommonResult<Long> createEmerVehicleInfo(@Valid @RequestBody EmerVehicleInfoSaveReqVO createReqVO) {
        return success(emerVehicleInfoService.createEmerVehicleInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新救援车辆信息")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-info:update')")
    public CommonResult<Boolean> updateEmerVehicleInfo(@Valid @RequestBody EmerVehicleInfoSaveReqVO updateReqVO) {
        emerVehicleInfoService.updateEmerVehicleInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除救援车辆信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-info:delete')")
    public CommonResult<Boolean> deleteEmerVehicleInfo(@RequestParam("id") Long id) {
        emerVehicleInfoService.deleteEmerVehicleInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得救援车辆信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-info:query')")
    public CommonResult<EmerVehicleInfoRespVO> getEmerVehicleInfo(@RequestParam("id") Long id) {
        EmerVehicleInfoDO emerVehicleInfo = emerVehicleInfoService.getEmerVehicleInfo(id);
        return success(BeanUtils.toBean(emerVehicleInfo, EmerVehicleInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得救援车辆信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-info:query')")
    public CommonResult<PageResult<EmerVehicleInfoRespVO>> getEmerVehicleInfoPage(@Valid EmerVehicleInfoPageReqVO pageReqVO) {
        PageResult<EmerVehicleInfoDO> pageResult = emerVehicleInfoService.getEmerVehicleInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EmerVehicleInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出救援车辆信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-vehicle-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEmerVehicleInfoExcel(@Valid EmerVehicleInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EmerVehicleInfoDO> list = emerVehicleInfoService.getEmerVehicleInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "救援车辆信息.xls", "数据", EmerVehicleInfoRespVO.class,
                        BeanUtils.toBean(list, EmerVehicleInfoRespVO.class));
    }

}