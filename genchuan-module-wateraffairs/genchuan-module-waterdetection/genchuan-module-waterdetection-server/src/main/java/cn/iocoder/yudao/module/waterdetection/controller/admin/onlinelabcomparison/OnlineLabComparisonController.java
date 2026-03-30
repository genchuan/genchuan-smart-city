package cn.iocoder.yudao.module.waterdetection.controller.admin.onlinelabcomparison;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.onlinelabcomparison.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.onlinelabcomparison.OnlineLabComparisonDO;
import cn.iocoder.yudao.module.waterdetection.service.onlinelabcomparison.OnlineLabComparisonService;

@Tag(name = "管理后台 - 在线数据与实验室比对")
@RestController
@RequestMapping("/waterdetection/online-lab-comparison")
@Validated
public class OnlineLabComparisonController {

    @Resource
    private OnlineLabComparisonService onlineLabComparisonService;

    @PostMapping("/create")
    @Operation(summary = "创建在线数据与实验室比对")
    @PreAuthorize("@ss.hasPermission('waterdetection:online-lab-comparison:create')")
    public CommonResult<Long> createOnlineLabComparison(@Valid @RequestBody OnlineLabComparisonSaveReqVO createReqVO) {
        return success(onlineLabComparisonService.createOnlineLabComparison(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新在线数据与实验室比对")
    @PreAuthorize("@ss.hasPermission('waterdetection:online-lab-comparison:update')")
    public CommonResult<Boolean> updateOnlineLabComparison(@Valid @RequestBody OnlineLabComparisonSaveReqVO updateReqVO) {
        onlineLabComparisonService.updateOnlineLabComparison(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除在线数据与实验室比对")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:online-lab-comparison:delete')")
    public CommonResult<Boolean> deleteOnlineLabComparison(@RequestParam("id") Long id) {
        onlineLabComparisonService.deleteOnlineLabComparison(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得在线数据与实验室比对")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:online-lab-comparison:query')")
    public CommonResult<OnlineLabComparisonRespVO> getOnlineLabComparison(@RequestParam("id") Long id) {
        OnlineLabComparisonDO onlineLabComparison = onlineLabComparisonService.getOnlineLabComparison(id);
        return success(BeanUtils.toBean(onlineLabComparison, OnlineLabComparisonRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得在线数据与实验室比对分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:online-lab-comparison:query')")
    public CommonResult<PageResult<OnlineLabComparisonRespVO>> getOnlineLabComparisonPage(@Valid OnlineLabComparisonPageReqVO pageReqVO) {
        PageResult<OnlineLabComparisonDO> pageResult = onlineLabComparisonService.getOnlineLabComparisonPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OnlineLabComparisonRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出在线数据与实验室比对 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:online-lab-comparison:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOnlineLabComparisonExcel(@Valid OnlineLabComparisonPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OnlineLabComparisonDO> list = onlineLabComparisonService.getOnlineLabComparisonPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "在线数据与实验室比对.xls", "数据", OnlineLabComparisonRespVO.class,
                        BeanUtils.toBean(list, OnlineLabComparisonRespVO.class));
    }

}