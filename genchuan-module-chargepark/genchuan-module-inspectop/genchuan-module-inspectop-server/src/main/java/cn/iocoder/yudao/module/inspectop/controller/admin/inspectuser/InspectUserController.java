package cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectuser.InspectUserDO;
import cn.iocoder.yudao.module.inspectop.service.inspectuser.InspectUserService;

@Tag(name = "巡查巡检 - 巡检人员")
@RestController
@RequestMapping("/inspectop/inspect-user")
@Validated
public class InspectUserController {

    @Resource
    private InspectUserService inspectUserService;

    @PostMapping("/create")
    @Operation(summary = "创建巡检人员")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:create')")
    public CommonResult<Long> createInspectUser(@Valid @RequestBody InspectUserSaveReqVO createReqVO) {
        return success(inspectUserService.createInspectUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡检人员")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:update')")
    public CommonResult<Boolean> updateInspectUser(@Valid @RequestBody InspectUserSaveReqVO updateReqVO) {
        inspectUserService.updateInspectUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡检人员")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:delete')")
    public CommonResult<Boolean> deleteInspectUser(@RequestParam("id") Long id) {
        inspectUserService.deleteInspectUser(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除巡检人员")
                @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:delete')")
    public CommonResult<Boolean> deleteInspectUserList(@RequestParam("ids") List<Long> ids) {
        inspectUserService.deleteInspectUserListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡检人员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:query')")
    public CommonResult<InspectUserRespVO> getInspectUser(@RequestParam("id") Long id) {
        InspectUserDO inspectUser = inspectUserService.getInspectUser(id);
        return success(BeanUtils.toBean(inspectUser, InspectUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡检人员分页")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:query')")
    public CommonResult<PageResult<InspectUserRespVO>> getInspectUserPage(@Valid InspectUserPageReqVO pageReqVO) {
        PageResult<InspectUserDO> pageResult = inspectUserService.getInspectUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检人员 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectUserExcel(@Valid InspectUserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectUserDO> list = inspectUserService.getInspectUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡检人员.xls", "数据", InspectUserRespVO.class,
                        BeanUtils.toBean(list, InspectUserRespVO.class));
    }

}