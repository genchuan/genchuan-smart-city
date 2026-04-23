package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormassign.DormAssignDO;
import cn.iocoder.yudao.module.studentmgmt.service.dormassign.DormAssignService;

@Tag(name = "学生管理后台 - 宿舍分配")
@RestController
@RequestMapping("/studentmgmt/dorm-assign")
@Validated
public class DormAssignController {

    @Resource
    private DormAssignService dormAssignService;

    @PostMapping("/create")
    @Operation(summary = "创建宿舍分配")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:create')")
    public CommonResult<Long> createDormAssign(@Valid @RequestBody DormAssignSaveReqVO createReqVO) {
        return success(dormAssignService.createDormAssign(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新宿舍分配")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:update')")
    public CommonResult<Boolean> updateDormAssign(@Valid @RequestBody DormAssignSaveReqVO updateReqVO) {
        dormAssignService.updateDormAssign(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除宿舍分配")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:delete')")
    public CommonResult<Boolean> deleteDormAssign(@RequestParam("id") Long id) {
        dormAssignService.deleteDormAssign(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除宿舍分配")
                @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:delete')")
    public CommonResult<Boolean> deleteDormAssignList(@RequestParam("ids") List<Long> ids) {
        dormAssignService.deleteDormAssignListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得宿舍分配")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:query')")
    public CommonResult<DormAssignRespVO> getDormAssign(@RequestParam("id") Long id) {
        DormAssignDO dormAssign = dormAssignService.getDormAssign(id);
        return success(BeanUtils.toBean(dormAssign, DormAssignRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得宿舍分配分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:query')")
    public CommonResult<PageResult<DormAssignRespVO>> getDormAssignPage(@Valid DormAssignPageReqVO pageReqVO) {
        PageResult<DormAssignDO> pageResult = dormAssignService.getDormAssignPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DormAssignRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出宿舍分配 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDormAssignExcel(@Valid DormAssignPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DormAssignDO> list = dormAssignService.getDormAssignPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "宿舍分配.xls", "数据", DormAssignRespVO.class,
                        BeanUtils.toBean(list, DormAssignRespVO.class));
    }

}