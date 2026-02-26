/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning;

import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.road.RoadPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.road.RoadRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.road.RoadSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadDO;
import cn.iocoder.yudao.module.envirhealth.service.roadcleaning.road.RoadService;

@Tag(name = "环境卫生管理 - 道路")
@RestController
@RequestMapping("/envirhealth/road")
@Validated
public class RoadController {

    @Resource
    private RoadService roadService;

    @PostMapping("/create")
    @Operation(summary = "创建道路")
    @PreAuthorize("@ss.hasPermission('envirhealth:road:create')")
    public CommonResult<Long> createRoad(@Valid @RequestBody RoadSaveReqVO createReqVO) {
        return success(roadService.createRoad(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新道路")
    @PreAuthorize("@ss.hasPermission('envirhealth:road:update')")
    public CommonResult<Boolean> updateRoad(@Valid @RequestBody RoadSaveReqVO updateReqVO) {
        roadService.updateRoad(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除道路")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:road:delete')")
    public CommonResult<Boolean> deleteRoad(@RequestParam("id") Long id) {
        roadService.deleteRoad(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得道路")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:road:query')")
    public CommonResult<RoadRespVO> getRoad(@RequestParam("id") Long id) {
        RoadDO road = roadService.getRoad(id);
        return success(BeanUtils.toBean(road, RoadRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得道路分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:road:query')")
    public CommonResult<PageResult<RoadRespVO>> getRoadPage(@Valid RoadPageReqVO pageReqVO) {
        PageResult<RoadDO> pageResult = roadService.getRoadPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoadRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出道路 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:road:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRoadExcel(@Valid RoadPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoadDO> list = roadService.getRoadPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "道路.xls", "数据", RoadRespVO.class,
                        BeanUtils.toBean(list, RoadRespVO.class));
    }

}*/
