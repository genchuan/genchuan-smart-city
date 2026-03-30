package cn.iocoder.yudao.module.facility.controller.admin.road.roadfacility;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadfacility.vo.RoadFacilityPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadfacility.vo.RoadFacilityRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadfacility.vo.RoadFacilitySaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadfacility.RoadFacilityDO;
import cn.iocoder.yudao.module.facility.service.road.roadfacility.RoadFacilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 道路设施")
@RestController
@RequestMapping("/facility/road-facility")
@Validated
public class RoadFacilityController {

    @Resource
    private RoadFacilityService roadFacilityService;

    @PostMapping("/create")
    @Operation(summary = "创建道路设施")
    @PreAuthorize("@ss.hasPermission('facility:road-facility:create')")
    public CommonResult<Long> createRoadFacility(@Valid @RequestBody RoadFacilitySaveReqVO createReqVO) {
        return success(roadFacilityService.createRoadFacility(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新道路设施")
    @PreAuthorize("@ss.hasPermission('facility:road-facility:update')")
    public CommonResult<Boolean> updateRoadFacility(@Valid @RequestBody RoadFacilitySaveReqVO updateReqVO) {
        roadFacilityService.updateRoadFacility(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除道路设施")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('facility:road-facility:delete')")
    public CommonResult<Boolean> deleteRoadFacility(@RequestParam("id") Long id) {
        roadFacilityService.deleteRoadFacility(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得道路设施")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('facility:road-facility:query')")
    public CommonResult<RoadFacilityRespVO> getRoadFacility(@RequestParam("id") Long id) {
        RoadFacilityDO roadFacility = roadFacilityService.getRoadFacility(id);
        return success(BeanUtils.toBean(roadFacility, RoadFacilityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得道路设施分页")
    @PreAuthorize("@ss.hasPermission('facility:road-facility:query')")
    public CommonResult<PageResult<RoadFacilityRespVO>> getRoadFacilityPage(@Valid RoadFacilityPageReqVO pageReqVO) {
        PageResult<RoadFacilityDO> pageResult = roadFacilityService.getRoadFacilityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoadFacilityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出道路设施 Excel")
    @PreAuthorize("@ss.hasPermission('facility:road-facility:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRoadFacilityExcel(@Valid RoadFacilityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoadFacilityDO> list = roadFacilityService.getRoadFacilityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "道路设施.xls", "数据", RoadFacilityRespVO.class,
                        BeanUtils.toBean(list, RoadFacilityRespVO.class));
    }

}
