package cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.accessapply.AccessApplyDO;
import cn.iocoder.yudao.module.studentmgmt.service.accessapply.AccessApplyService;

@Tag(name = "学生管理后台 - 出入申请")
@RestController
@RequestMapping("/studentmgmt/access-apply")
@Validated
public class AccessApplyController {

    @Resource
    private AccessApplyService accessApplyService;

    @PostMapping("/create")
    @Operation(summary = "创建出入申请")
    @PreAuthorize("@ss.hasPermission('studentmgmt:access-apply:create')")
    public CommonResult<Long> createAccessApply(@Valid @RequestBody AccessApplySaveReqVO createReqVO) {
        return success(accessApplyService.createAccessApply(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新出入申请")
    @PreAuthorize("@ss.hasPermission('studentmgmt:access-apply:update')")
    public CommonResult<Boolean> updateAccessApply(@Valid @RequestBody AccessApplySaveReqVO updateReqVO) {
        accessApplyService.updateAccessApply(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除出入申请")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:access-apply:delete')")
    public CommonResult<Boolean> deleteAccessApply(@RequestParam("id") Long id) {
        accessApplyService.deleteAccessApply(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除出入申请")
                @PreAuthorize("@ss.hasPermission('studentmgmt:access-apply:delete')")
    public CommonResult<Boolean> deleteAccessApplyList(@RequestParam("ids") List<Long> ids) {
        accessApplyService.deleteAccessApplyListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得出入申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:access-apply:query')")
    public CommonResult<AccessApplyRespVO> getAccessApply(@RequestParam("id") Long id) {
        AccessApplyDO accessApply = accessApplyService.getAccessApply(id);
        return success(BeanUtils.toBean(accessApply, AccessApplyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得出入申请分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:access-apply:query')")
    public CommonResult<PageResult<AccessApplyRespVO>> getAccessApplyPage(@Valid AccessApplyPageReqVO pageReqVO) {
        PageResult<AccessApplyDO> pageResult = accessApplyService.getAccessApplyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AccessApplyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出出入申请 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:access-apply:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAccessApplyExcel(@Valid AccessApplyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AccessApplyDO> list = accessApplyService.getAccessApplyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "出入申请.xls", "数据", AccessApplyRespVO.class,
                        BeanUtils.toBean(list, AccessApplyRespVO.class));
    }

}