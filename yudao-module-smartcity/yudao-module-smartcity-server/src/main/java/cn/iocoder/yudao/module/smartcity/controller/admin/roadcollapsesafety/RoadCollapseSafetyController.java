package cn.iocoder.yudao.module.smartcity.controller.admin.roadcollapsesafety;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.roadcollapsesafety.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.roadcollapsesafety.RoadCollapseSafetyDO;
import cn.iocoder.yudao.module.smartcity.service.roadcollapsesafety.RoadCollapseSafetyService;

@Tag(name = "管理后台 - 路面塌陷安全")
@RestController
@RequestMapping("/smartcity/road-collapse-safety")
@Validated
public class RoadCollapseSafetyController {

    @Resource
    private RoadCollapseSafetyService roadCollapseSafetyService;

    @PostMapping("/create")
    @Operation(summary = "创建路面塌陷安全")
    @PreAuthorize("@ss.hasPermission('smartcity:road-collapse-safety:create')")
    public CommonResult<Long> createRoadCollapseSafety(@Valid @RequestBody RoadCollapseSafetySaveReqVO createReqVO) {
        return success(roadCollapseSafetyService.createRoadCollapseSafety(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新路面塌陷安全")
    @PreAuthorize("@ss.hasPermission('smartcity:road-collapse-safety:update')")
    public CommonResult<Boolean> updateRoadCollapseSafety(@Valid @RequestBody RoadCollapseSafetySaveReqVO updateReqVO) {
        roadCollapseSafetyService.updateRoadCollapseSafety(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除路面塌陷安全")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:road-collapse-safety:delete')")
    public CommonResult<Boolean> deleteRoadCollapseSafety(@RequestParam("id") Long id) {
        roadCollapseSafetyService.deleteRoadCollapseSafety(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得路面塌陷安全")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:road-collapse-safety:query')")
    public CommonResult<RoadCollapseSafetyRespVO> getRoadCollapseSafety(@RequestParam("id") Long id) {
        RoadCollapseSafetyDO roadCollapseSafety = roadCollapseSafetyService.getRoadCollapseSafety(id);
        return success(BeanUtils.toBean(roadCollapseSafety, RoadCollapseSafetyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得路面塌陷安全分页")
    @PreAuthorize("@ss.hasPermission('smartcity:road-collapse-safety:query')")
    public CommonResult<PageResult<RoadCollapseSafetyRespVO>> getRoadCollapseSafetyPage(@Valid RoadCollapseSafetyPageReqVO pageReqVO) {
        PageResult<RoadCollapseSafetyDO> pageResult = roadCollapseSafetyService.getRoadCollapseSafetyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoadCollapseSafetyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出路面塌陷安全 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:road-collapse-safety:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRoadCollapseSafetyExcel(@Valid RoadCollapseSafetyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoadCollapseSafetyDO> list = roadCollapseSafetyService.getRoadCollapseSafetyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "路面塌陷安全.xls", "数据", RoadCollapseSafetyRespVO.class,
                        BeanUtils.toBean(list, RoadCollapseSafetyRespVO.class));
    }

}