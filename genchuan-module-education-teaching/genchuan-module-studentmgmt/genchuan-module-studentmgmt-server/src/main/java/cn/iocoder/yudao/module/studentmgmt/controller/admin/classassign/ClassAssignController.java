package cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.classassign.ClassAssignDO;
import cn.iocoder.yudao.module.studentmgmt.service.classassign.ClassAssignService;

@Tag(name = "学生管理后台 - 分班管理")
@RestController
@RequestMapping("/studentmgmt/class-assign")
@Validated
public class ClassAssignController {

    @Resource
    private ClassAssignService classAssignService;

    @PostMapping("/create")
    @Operation(summary = "创建分班管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:create')")
    public CommonResult<Long> createClassAssign(@Valid @RequestBody ClassAssignSaveReqVO createReqVO) {
        return success(classAssignService.createClassAssign(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分班管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:update')")
    public CommonResult<Boolean> updateClassAssign(@Valid @RequestBody ClassAssignSaveReqVO updateReqVO) {
        classAssignService.updateClassAssign(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除分班管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:delete')")
    public CommonResult<Boolean> deleteClassAssign(@RequestParam("id") Long id) {
        classAssignService.deleteClassAssign(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除分班管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:delete')")
    public CommonResult<Boolean> deleteClassAssignList(@RequestParam("ids") List<Long> ids) {
        classAssignService.deleteClassAssignListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分班管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:query')")
    public CommonResult<ClassAssignRespVO> getClassAssign(@RequestParam("id") Long id) {
        ClassAssignDO classAssign = classAssignService.getClassAssign(id);
        return success(BeanUtils.toBean(classAssign, ClassAssignRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分班管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:query')")
    public CommonResult<PageResult<ClassAssignRespVO>> getClassAssignPage(@Valid ClassAssignPageReqVO pageReqVO) {
        PageResult<ClassAssignDO> pageResult = classAssignService.getClassAssignPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ClassAssignRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分班管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportClassAssignExcel(@Valid ClassAssignPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ClassAssignDO> list = classAssignService.getClassAssignPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "分班管理.xls", "数据", ClassAssignRespVO.class,
                        BeanUtils.toBean(list, ClassAssignRespVO.class));
    }

}