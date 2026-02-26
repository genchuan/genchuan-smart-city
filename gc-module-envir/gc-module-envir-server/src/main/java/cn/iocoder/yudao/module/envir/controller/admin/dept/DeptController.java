package cn.iocoder.yudao.module.envir.controller.admin.dept;

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

import cn.iocoder.yudao.module.envir.controller.admin.dept.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.envir.service.dept.DeptService;

@Tag(name = "管理后台 - 部门")
@RestController
@RequestMapping("/envir/dept")
@Validated
public class DeptController {

    @Resource
    private DeptService deptService;

    @PostMapping("/create")
    @Operation(summary = "创建部门")
    @PreAuthorize("@ss.hasPermission('envir:dept:create')")
    public CommonResult<Long> createDept(@Valid @RequestBody DeptSaveReqVO createReqVO) {
        return success(deptService.createDept(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新部门")
    @PreAuthorize("@ss.hasPermission('envir:dept:update')")
    public CommonResult<Boolean> updateDept(@Valid @RequestBody DeptSaveReqVO updateReqVO) {
        deptService.updateDept(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除部门")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:dept:delete')")
    public CommonResult<Boolean> deleteDept(@RequestParam("id") Long id) {
        deptService.deleteDept(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得部门")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:dept:query')")
    public CommonResult<DeptRespVO> getDept(@RequestParam("id") Long id) {
        DeptDO dept = deptService.getDept(id);
        return success(BeanUtils.toBean(dept, DeptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得部门分页")
    @PreAuthorize("@ss.hasPermission('envir:dept:query')")
    public CommonResult<PageResult<DeptRespVO>> getDeptPage(@Valid DeptPageReqVO pageReqVO) {
        PageResult<DeptDO> pageResult = deptService.getDeptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出部门 Excel")
    @PreAuthorize("@ss.hasPermission('envir:dept:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeptExcel(@Valid DeptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeptDO> list = deptService.getDeptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "部门.xls", "数据", DeptRespVO.class,
                        BeanUtils.toBean(list, DeptRespVO.class));
    }

}