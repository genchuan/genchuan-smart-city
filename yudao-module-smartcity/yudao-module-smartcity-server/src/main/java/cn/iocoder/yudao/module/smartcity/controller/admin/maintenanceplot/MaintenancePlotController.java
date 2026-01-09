package cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplot;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplot.vo.MaintenancePlotPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplot.vo.MaintenancePlotRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplot.vo.MaintenancePlotSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenanceplot.MaintenancePlotDO;
import cn.iocoder.yudao.module.smartcity.service.maintenanceplot.MaintenancePlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 养护地块")
@RestController
@RequestMapping("/smartcity/maintenance-plot")
@Validated
public class MaintenancePlotController {

    @Resource
    private MaintenancePlotService maintenancePlotService;

    @PostMapping("/create")
    @Operation(summary = "创建养护地块")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plot:create')")
    public CommonResult<Long> createMaintenancePlot(@Valid @RequestBody MaintenancePlotSaveReqVO createReqVO) {
        return success(maintenancePlotService.createMaintenancePlot(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新养护地块")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plot:update')")
    public CommonResult<Boolean> updateMaintenancePlot(@Valid @RequestBody MaintenancePlotSaveReqVO updateReqVO) {
        maintenancePlotService.updateMaintenancePlot(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除养护地块")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plot:delete')")
    public CommonResult<Boolean> deleteMaintenancePlot(@RequestParam("id") Long id) {
        maintenancePlotService.deleteMaintenancePlot(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得养护地块")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plot:query')")
    public CommonResult<MaintenancePlotRespVO> getMaintenancePlot(@RequestParam("id") Long id) {
        MaintenancePlotDO maintenancePlot = maintenancePlotService.getMaintenancePlot(id);
        return success(BeanUtils.toBean(maintenancePlot, MaintenancePlotRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得养护地块分页")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plot:query')")
    public CommonResult<PageResult<MaintenancePlotRespVO>> getMaintenancePlotPage(@Valid MaintenancePlotPageReqVO pageReqVO) {
        PageResult<MaintenancePlotDO> pageResult = maintenancePlotService.getMaintenancePlotPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MaintenancePlotRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出养护地块 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-plot:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMaintenancePlotExcel(@Valid MaintenancePlotPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MaintenancePlotDO> list = maintenancePlotService.getMaintenancePlotPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "养护地块.xls", "数据", MaintenancePlotRespVO.class,
                        BeanUtils.toBean(list, MaintenancePlotRespVO.class));
    }

}