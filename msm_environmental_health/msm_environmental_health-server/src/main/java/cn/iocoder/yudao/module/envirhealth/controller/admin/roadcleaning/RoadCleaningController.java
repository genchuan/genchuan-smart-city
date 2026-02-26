package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning;

import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning.RoadCleaningPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning.RoadCleaningRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning.RoadCleaningSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.RoadCleaningDetailDO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadCleaningDO;
import cn.iocoder.yudao.module.envirhealth.service.roadcleaning.roadcleaning.RoadCleaningService;

@Tag(name = "环境卫生管理 - 道路清扫计划")
@RestController
@RequestMapping("/envirhealth/road-cleaning")
@Validated
public class RoadCleaningController {

    @Resource
    private RoadCleaningService roadCleaningService;

    @PostMapping("/create")
    @Operation(summary = "创建道路清扫计划")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:create')")
    public CommonResult<Long> createRoadCleaning(@Valid @RequestBody RoadCleaningSaveReqVO createReqVO) {
        return success(roadCleaningService.createRoadCleaning(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新道路清扫计划")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:update')")
    public CommonResult<Boolean> updateRoadCleaning(@Valid @RequestBody RoadCleaningSaveReqVO updateReqVO) {
        roadCleaningService.updateRoadCleaning(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除道路清扫计划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:delete')")
    public CommonResult<Boolean> deleteRoadCleaning(@RequestParam("id") Long id) {
        roadCleaningService.deleteRoadCleaning(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得道路清扫计划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:query')")
    public CommonResult<RoadCleaningRespVO> getRoadCleaning(@RequestParam("id") Long id) {
        RoadCleaningDO roadCleaning = roadCleaningService.getRoadCleaning(id);
        return success(BeanUtils.toBean(roadCleaning, RoadCleaningRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得道路清扫计划分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:query')")
    public CommonResult<PageResult<RoadCleaningRespVO>> getRoadCleaningPage(@Valid RoadCleaningPageReqVO pageReqVO) {
        PageResult<RoadCleaningDO> pageResult = roadCleaningService.getRoadCleaningPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoadCleaningRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出道路清扫计划 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRoadCleaningExcel(@Valid RoadCleaningPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoadCleaningDO> list = roadCleaningService.getRoadCleaningPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "道路清扫计划.xls", "数据", RoadCleaningRespVO.class,
                        BeanUtils.toBean(list, RoadCleaningRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得道路清扫详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:road-cleaning:query')")
    public CommonResult<PageResult<RoadCleaningDetailDO>> getRoadCleaningDetailPage(
            @Valid RoadCleaningPageReqVO pageReqVO) {
        PageResult<RoadCleaningDetailDO> pageResult =
                roadCleaningService.getRoadCleaningDetailPage(pageReqVO);

        return success(pageResult);
    }
}