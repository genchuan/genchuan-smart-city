package cn.iocoder.yudao.module.facility.controller.admin.road.roadwarn;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadwarn.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadwarn.RoadWarnDO;
import cn.iocoder.yudao.module.facility.service.road.roadwarn.RoadWarnService;
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


@Tag(name = "管理后台 - 道路预警")
@RestController
@RequestMapping("/facility/road-warn")
@Validated
public class RoadWarnController {

    @Resource
    private RoadWarnService roadWarnService;

    @GetMapping("/page-road-warn")
    @Operation(summary = "获得道路监测预警分页")
    @PreAuthorize("@ss.hasPermission('facility:warn:query')")
    public CommonResult<PageResult<RoadWarnPageRespVO>> pageRoadWarn(@Valid RoadWarnPageReqVO reqVO) {
        PageResult<RoadWarnPageRespVO> pageResult = roadWarnService.pageRoadWarn(reqVO);
        return success(pageResult);
//        return null;
    }
    @PostMapping("/create")
    @Operation(summary = "创建预警")
    @PreAuthorize("@ss.hasPermission('facility:warn:create')")
    public CommonResult<List<Long>> createWarn(@Valid @RequestBody RoadWarnSaveReqVO createReqVO) {
        return success(roadWarnService.createWarn(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警")
    @PreAuthorize("@ss.hasPermission('facility:warn:update')")
    public CommonResult<Boolean> updateWarn(@Valid @RequestBody WarnSaveReqVO updateReqVO) {
        roadWarnService.updateWarn(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('facility:warn:delete')")
    public CommonResult<Boolean> deleteWarn(@RequestParam("id") Long id) {
        roadWarnService.deleteWarn(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('facility:warn:query')")
    public CommonResult<WarnRespVO> getWarn(@RequestParam("id") Long id) {
        RoadWarnDO warn = roadWarnService.getWarn(id);
        return success(BeanUtils.toBean(warn, WarnRespVO.class));
    }

//    @GetMapping("/page")
//    @Operation(summary = "获得预警分页")
//    @PreAuthorize("@ss.hasPermission('facility:warn:query')")
//    public CommonResult<PageResult<WarnRespVO>> getWarnPage(@Valid WarnPageReqVO pageReqVO) {
//        PageResult<RoadWarnDO> pageResult = roadWarnService.getWarnPage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, WarnRespVO.class));
//    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警 Excel")
    @PreAuthorize("@ss.hasPermission('facility:warn:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWarnExcel(@Valid WarnPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoadWarnDO> list = roadWarnService.getWarnPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警.xls", "数据", WarnRespVO.class,
                        BeanUtils.toBean(list, WarnRespVO.class));
    }

}
