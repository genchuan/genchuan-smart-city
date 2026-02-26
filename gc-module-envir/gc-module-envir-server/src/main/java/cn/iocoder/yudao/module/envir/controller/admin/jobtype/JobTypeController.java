package cn.iocoder.yudao.module.envir.controller.admin.jobtype;

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

import cn.iocoder.yudao.module.envir.controller.admin.jobtype.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.jobtype.JobTypeDO;
import cn.iocoder.yudao.module.envir.service.jobtype.JobTypeService;

@Tag(name = "管理后台 - 岗位类型字典")
@RestController
@RequestMapping("/envir/job-type")
@Validated
public class JobTypeController {

    @Resource
    private JobTypeService jobTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建岗位类型字典")
    @PreAuthorize("@ss.hasPermission('envir:job-type:create')")
    public CommonResult<Long> createJobType(@Valid @RequestBody JobTypeSaveReqVO createReqVO) {
        return success(jobTypeService.createJobType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新岗位类型字典")
    @PreAuthorize("@ss.hasPermission('envir:job-type:update')")
    public CommonResult<Boolean> updateJobType(@Valid @RequestBody JobTypeSaveReqVO updateReqVO) {
        jobTypeService.updateJobType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除岗位类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:job-type:delete')")
    public CommonResult<Boolean> deleteJobType(@RequestParam("id") Long id) {
        jobTypeService.deleteJobType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得岗位类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:job-type:query')")
    public CommonResult<JobTypeRespVO> getJobType(@RequestParam("id") Long id) {
        JobTypeDO jobType = jobTypeService.getJobType(id);
        return success(BeanUtils.toBean(jobType, JobTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得岗位类型字典分页")
    @PreAuthorize("@ss.hasPermission('envir:job-type:query')")
    public CommonResult<PageResult<JobTypeRespVO>> getJobTypePage(@Valid JobTypePageReqVO pageReqVO) {
        PageResult<JobTypeDO> pageResult = jobTypeService.getJobTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JobTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出岗位类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('envir:job-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJobTypeExcel(@Valid JobTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<JobTypeDO> list = jobTypeService.getJobTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "岗位类型字典.xls", "数据", JobTypeRespVO.class,
                        BeanUtils.toBean(list, JobTypeRespVO.class));
    }

}