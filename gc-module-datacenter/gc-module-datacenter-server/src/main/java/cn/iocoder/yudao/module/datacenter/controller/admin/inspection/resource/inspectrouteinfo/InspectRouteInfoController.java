package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectrouteinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectrouteinfo.vo.InspectRouteInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectrouteinfo.vo.InspectRouteInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectrouteinfo.vo.InspectRouteInfoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectrouteinfo.InspectRouteInfoDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.resource.inspectrouteinfo.InspectRouteInfoService;

@Tag(name = "管理后台 - 巡查巡检路线信息")
@RestController
@RequestMapping("/datacenter/inspect-route-info")
@Validated
public class InspectRouteInfoController {

    @Resource
    private InspectRouteInfoService inspectRouteInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检路线信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-route-info:create')")
    public CommonResult<Long> createInspectRouteInfo(@Valid @RequestBody InspectRouteInfoSaveReqVO createReqVO) {
        return success(inspectRouteInfoService.createInspectRouteInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检路线信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-route-info:update')")
    public CommonResult<Boolean> updateInspectRouteInfo(@Valid @RequestBody InspectRouteInfoSaveReqVO updateReqVO) {
        inspectRouteInfoService.updateInspectRouteInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检路线信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-route-info:delete')")
    public CommonResult<Boolean> deleteInspectRouteInfo(@RequestParam("id") Long id) {
        inspectRouteInfoService.deleteInspectRouteInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检路线信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-route-info:query')")
    public CommonResult<InspectRouteInfoRespVO> getInspectRouteInfo(@RequestParam("id") Long id) {
        InspectRouteInfoDO inspectRouteInfo = inspectRouteInfoService.getInspectRouteInfo(id);
        return success(BeanUtils.toBean(inspectRouteInfo, InspectRouteInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检路线信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-route-info:query')")
    public CommonResult<PageResult<InspectRouteInfoRespVO>> getInspectRouteInfoPage(@Valid InspectRouteInfoPageReqVO pageReqVO) {
        PageResult<InspectRouteInfoDO> pageResult = inspectRouteInfoService.getInspectRouteInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectRouteInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检路线信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-route-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectRouteInfoExcel(@Valid InspectRouteInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectRouteInfoDO> list = inspectRouteInfoService.getInspectRouteInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检路线信息.xls", "数据", InspectRouteInfoRespVO.class,
                        BeanUtils.toBean(list, InspectRouteInfoRespVO.class));
    }

}