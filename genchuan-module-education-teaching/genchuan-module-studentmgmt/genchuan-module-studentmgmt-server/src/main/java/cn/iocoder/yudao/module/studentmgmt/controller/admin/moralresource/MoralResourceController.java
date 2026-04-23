package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralresource.MoralResourceDO;
import cn.iocoder.yudao.module.studentmgmt.service.moralresource.MoralResourceService;

@Tag(name = "学生管理后台 - 德育资源")
@RestController
@RequestMapping("/studentmgmt/moral-resource")
@Validated
public class MoralResourceController {

    @Resource
    private MoralResourceService moralResourceService;

    @PostMapping("/create")
    @Operation(summary = "创建德育资源")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:create')")
    public CommonResult<Long> createMoralResource(@Valid @RequestBody MoralResourceSaveReqVO createReqVO) {
        return success(moralResourceService.createMoralResource(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新德育资源")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:update')")
    public CommonResult<Boolean> updateMoralResource(@Valid @RequestBody MoralResourceSaveReqVO updateReqVO) {
        moralResourceService.updateMoralResource(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除德育资源")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:delete')")
    public CommonResult<Boolean> deleteMoralResource(@RequestParam("id") Long id) {
        moralResourceService.deleteMoralResource(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除德育资源")
                @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:delete')")
    public CommonResult<Boolean> deleteMoralResourceList(@RequestParam("ids") List<Long> ids) {
        moralResourceService.deleteMoralResourceListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得德育资源")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:query')")
    public CommonResult<MoralResourceRespVO> getMoralResource(@RequestParam("id") Long id) {
        MoralResourceDO moralResource = moralResourceService.getMoralResource(id);
        return success(BeanUtils.toBean(moralResource, MoralResourceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得德育资源分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:query')")
    public CommonResult<PageResult<MoralResourceRespVO>> getMoralResourcePage(@Valid MoralResourcePageReqVO pageReqVO) {
        PageResult<MoralResourceDO> pageResult = moralResourceService.getMoralResourcePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MoralResourceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出德育资源 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMoralResourceExcel(@Valid MoralResourcePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MoralResourceDO> list = moralResourceService.getMoralResourcePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "德育资源.xls", "数据", MoralResourceRespVO.class,
                        BeanUtils.toBean(list, MoralResourceRespVO.class));
    }

    @PutMapping("/online")
    @Operation(summary = "上架")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:online')")
    public CommonResult<Boolean> online(@Valid @RequestBody MoralResourceOnlineReqVO reqVO) {
        boolean isSuccess = moralResourceService.online(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/offline")
    @Operation(summary = "下架")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:offline')")
    public CommonResult<Boolean> offline(@Valid @RequestBody MoralResourceOnlineReqVO reqVO) {
        boolean isSuccess = moralResourceService.offline(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/chart")
    @Operation(summary = "德育资源学习看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:query')")
    public CommonResult<MoralResourceChartRespVO> chart(@Valid MoralResourceChartReqVO reqVO) {
        MoralResourceChartRespVO vo = moralResourceService.chart(reqVO);
        return success(vo);
    }

    @PutMapping("/chart/resourceCount")
    @Operation(summary = "资源类型 / 学习完成率统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:query')")
    public CommonResult<ChartResourceCountRespVO> resourceCount(@Valid MoralResourceChartReqVO reqVO) {
        ChartResourceCountRespVO vo = moralResourceService.resourceCount(reqVO);
        return success(vo);
    }


}