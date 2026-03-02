package cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig;

import cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo.RoadConfigPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo.RoadConfigRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo.RoadConfigSaveReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo.RoadConfigUpdateReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadconfig.RoadConfigDO;
import cn.iocoder.yudao.module.facility.service.road.roadconfig.RoadConfigService;
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


@Tag(name = "管理后台 - 道路监测配置")
@RestController
@RequestMapping("/facility/road-config")
@Validated
public class RoadConfigController {

    @Resource
    private RoadConfigService roadConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建道路监测配置")
    @PreAuthorize("@ss.hasPermission('facility:road-config:create')")
    public CommonResult<Long> createRoadConfig(@Valid @RequestBody RoadConfigSaveReqVO createReqVO) {
        return success(roadConfigService.createRoadConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新道路监测配置")
    @PreAuthorize("@ss.hasPermission('facility:road-config:update')")
    public CommonResult<Boolean> updateRoadConfig(@Valid @RequestBody RoadConfigUpdateReqVO updateReqVO) {
        roadConfigService.updateRoadConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除道路监测配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('facility:road-config:delete')")
    public CommonResult<Boolean> deleteRoadConfig(@RequestParam("id") Long id) {
        roadConfigService.deleteRoadConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得道路监测配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('facility:road-config:query')")
    public CommonResult<RoadConfigRespVO> getRoadConfig(@RequestParam("id") Long id) {
        RoadConfigDO roadConfig = roadConfigService.getRoadConfig(id);
        return success(BeanUtils.toBean(roadConfig, RoadConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得道路监测配置分页")
    @PreAuthorize("@ss.hasPermission('facility:road-config:query')")
    public CommonResult<PageResult<RoadConfigRespVO>> getRoadConfigPage(@Valid RoadConfigPageReqVO pageReqVO) {
        PageResult<RoadConfigDO> pageResult = roadConfigService.getRoadConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoadConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出道路监测配置 Excel")
    @PreAuthorize("@ss.hasPermission('facility:road-config:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRoadConfigExcel(@Valid RoadConfigPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoadConfigDO> list = roadConfigService.getRoadConfigPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "道路监测配置.xls", "数据", RoadConfigRespVO.class,
                        BeanUtils.toBean(list, RoadConfigRespVO.class));
    }

}
