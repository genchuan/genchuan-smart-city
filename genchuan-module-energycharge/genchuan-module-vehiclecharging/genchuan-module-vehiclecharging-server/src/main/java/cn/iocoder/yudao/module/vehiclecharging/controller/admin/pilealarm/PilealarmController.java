package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm;

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

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pilealarm.PilealarmDO;
import cn.iocoder.yudao.module.vehiclecharging.service.pilealarm.PilealarmService;

@Tag(name = "管理后台 - 充电桩告警")
@RestController
@RequestMapping("/vehiclecharging/pilealarm")
@Validated
public class PilealarmController {

    @Resource
    private PilealarmService pilealarmService;

    @PostMapping("/create")
    @Operation(summary = "创建充电桩告警")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:create')")
    public CommonResult<String> createPilealarm(@Valid @RequestBody PilealarmSaveReqVO createReqVO) {
        return success(pilealarmService.createPilealarm(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新充电桩告警")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:update')")
    public CommonResult<Boolean> updatePilealarm(@Valid @RequestBody PilealarmSaveReqVO updateReqVO) {
        pilealarmService.updatePilealarm(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除充电桩告警")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:delete')")
    public CommonResult<Boolean> deletePilealarm(@RequestParam("id") String id) {
        pilealarmService.deletePilealarm(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除充电桩告警")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:delete')")
    public CommonResult<Boolean> deletePilealarmList(@RequestParam("ids") List<String> ids) {
        pilealarmService.deletePilealarmListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得充电桩告警")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:query')")
    public CommonResult<PilealarmRespVO> getPilealarm(@RequestParam("id") String id) {
        PilealarmDO pilealarm = pilealarmService.getPilealarm(id);
        return success(BeanUtils.toBean(pilealarm, PilealarmRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得充电桩告警分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:query')")
    public CommonResult<PageResult<PilealarmRespVO>> getPilealarmPage(@Valid PilealarmPageReqVO pageReqVO) {
        PageResult<PilealarmDO> pageResult = pilealarmService.getPilealarmPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PilealarmRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出充电桩告警 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPilealarmExcel(@Valid PilealarmPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PilealarmDO> list = pilealarmService.getPilealarmPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "充电桩告警.xls", "数据", PilealarmRespVO.class,
                BeanUtils.toBean(list, PilealarmRespVO.class));
    }

}