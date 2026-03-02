package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning;

import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.CleaningProblemDetailDO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.CleaningProblemDO;
import cn.iocoder.yudao.module.envirhealth.service.roadcleaning.cleaningproblem.CleaningProblemService;

@Tag(name = "环境卫生管理 - 道路清扫问题")
@RestController
@RequestMapping("/envirhealth/cleaning-problem")
@Validated
public class CleaningProblemController {

    @Resource
    private CleaningProblemService cleaningProblemService;

    @PostMapping("/create")
    @Operation(summary = "创建道路清扫问题")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:create')")
    public CommonResult<Long> createCleaningProblem(@Valid @RequestBody CleaningProblemSaveReqVO createReqVO) {
        return success(cleaningProblemService.createCleaningProblem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新道路清扫问题")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:update')")
    public CommonResult<Boolean> updateCleaningProblem(@Valid @RequestBody CleaningProblemSaveReqVO updateReqVO) {
        cleaningProblemService.updateCleaningProblem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除道路清扫问题")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:delete')")
    public CommonResult<Boolean> deleteCleaningProblem(@RequestParam("id") Long id) {
        cleaningProblemService.deleteCleaningProblem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得道路清扫问题")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:query')")
    public CommonResult<CleaningProblemRespVO> getCleaningProblem(@RequestParam("id") Long id) {
        CleaningProblemDO cleaningProblem = cleaningProblemService.getCleaningProblem(id);
        return success(BeanUtils.toBean(cleaningProblem, CleaningProblemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得道路清扫问题分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:query')")
    public CommonResult<PageResult<CleaningProblemRespVO>> getCleaningProblemPage(@Valid CleaningProblemPageReqVO pageReqVO) {
        PageResult<CleaningProblemDO> pageResult = cleaningProblemService.getCleaningProblemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CleaningProblemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出道路清扫问题 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCleaningProblemExcel(@Valid CleaningProblemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CleaningProblemDO> list = cleaningProblemService.getCleaningProblemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "道路清扫问题.xls", "数据", CleaningProblemRespVO.class,
                        BeanUtils.toBean(list, CleaningProblemRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得道路清扫详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:query')")
    public CommonResult<PageResult<CleaningProblemDetailDO>> getPublicToiletDetailPage(
            @Valid CleaningProblemPageReqVO pageReqVO) {
        PageResult<CleaningProblemDetailDO> pageResult =
                cleaningProblemService.getCleaningProblemDetailPage(pageReqVO);

        return success(pageResult);
    }
}