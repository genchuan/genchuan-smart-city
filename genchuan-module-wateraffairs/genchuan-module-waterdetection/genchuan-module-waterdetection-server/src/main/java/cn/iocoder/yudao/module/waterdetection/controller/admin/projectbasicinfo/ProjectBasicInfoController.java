package cn.iocoder.yudao.module.waterdetection.controller.admin.projectbasicinfo;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.projectbasicinfo.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.projectbasicinfo.ProjectBasicInfoDO;
import cn.iocoder.yudao.module.waterdetection.service.projectbasicinfo.ProjectBasicInfoService;

@Tag(name = "管理后台 - 工程基本信息管理")
@RestController
@RequestMapping("/waterdetection/project-basic-info")
@Validated
public class ProjectBasicInfoController {

    @Resource
    private ProjectBasicInfoService projectBasicInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建工程基本信息管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:project-basic-info:create')")
    public CommonResult<Long> createProjectBasicInfo(@Valid @RequestBody ProjectBasicInfoSaveReqVO createReqVO) {
        return success(projectBasicInfoService.createProjectBasicInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工程基本信息管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:project-basic-info:update')")
    public CommonResult<Boolean> updateProjectBasicInfo(@Valid @RequestBody ProjectBasicInfoSaveReqVO updateReqVO) {
        projectBasicInfoService.updateProjectBasicInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工程基本信息管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:project-basic-info:delete')")
    public CommonResult<Boolean> deleteProjectBasicInfo(@RequestParam("id") Long id) {
        projectBasicInfoService.deleteProjectBasicInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工程基本信息管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:project-basic-info:query')")
    public CommonResult<ProjectBasicInfoRespVO> getProjectBasicInfo(@RequestParam("id") Long id) {
        ProjectBasicInfoDO projectBasicInfo = projectBasicInfoService.getProjectBasicInfo(id);
        return success(BeanUtils.toBean(projectBasicInfo, ProjectBasicInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工程基本信息管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:project-basic-info:query')")
    public CommonResult<PageResult<ProjectBasicInfoRespVO>> getProjectBasicInfoPage(@Valid ProjectBasicInfoPageReqVO pageReqVO) {
        PageResult<ProjectBasicInfoDO> pageResult = projectBasicInfoService.getProjectBasicInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProjectBasicInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工程基本信息管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:project-basic-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportProjectBasicInfoExcel(@Valid ProjectBasicInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ProjectBasicInfoDO> list = projectBasicInfoService.getProjectBasicInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "工程基本信息管理.xls", "数据", ProjectBasicInfoRespVO.class,
                        BeanUtils.toBean(list, ProjectBasicInfoRespVO.class));
    }

}