package cn.iocoder.yudao.module.park.controller.admin.park.user.governmentdepartment;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.governmentdepartment.vo.GovernmentDepartmentPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.governmentdepartment.vo.GovernmentDepartmentRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.governmentdepartment.vo.GovernmentDepartmentSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.governmentdepartment.GovernmentDepartmentDO;
import cn.iocoder.yudao.module.park.service.park.user.governmentdepartment.GovernmentDepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 政府部门")
@RestController
@RequestMapping("/park/government-department")
@Validated
public class GovernmentDepartmentController {

    @Resource
    private GovernmentDepartmentService governmentDepartmentService;

    @PostMapping("/create")
    @Operation(summary = "创建政府部门")
    @PreAuthorize("@ss.hasPermission('park:government-department:create')")
    public CommonResult<Long> createGovernmentDepartment(@Valid @RequestBody GovernmentDepartmentSaveReqVO createReqVO) {
        return success(governmentDepartmentService.createGovernmentDepartment(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新政府部门")
    @PreAuthorize("@ss.hasPermission('park:government-department:update')")
    public CommonResult<Boolean> updateGovernmentDepartment(@Valid @RequestBody GovernmentDepartmentSaveReqVO updateReqVO) {
        governmentDepartmentService.updateGovernmentDepartment(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除政府部门")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:government-department:delete')")
    public CommonResult<Boolean> deleteGovernmentDepartment(@RequestParam("id") Long id) {
        governmentDepartmentService.deleteGovernmentDepartment(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得政府部门")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:government-department:query')")
    public CommonResult<GovernmentDepartmentRespVO> getGovernmentDepartment(@RequestParam("id") Long id) {
        GovernmentDepartmentDO governmentDepartment = governmentDepartmentService.getGovernmentDepartment(id);
        return success(BeanUtils.toBean(governmentDepartment, GovernmentDepartmentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得政府部门分页")
    @PreAuthorize("@ss.hasPermission('park:government-department:query')")
    public CommonResult<PageResult<GovernmentDepartmentRespVO>> getGovernmentDepartmentPage(@Valid GovernmentDepartmentPageReqVO pageReqVO) {
        PageResult<GovernmentDepartmentDO> pageResult = governmentDepartmentService.getGovernmentDepartmentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GovernmentDepartmentRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出政府部门 Excel")
    @PreAuthorize("@ss.hasPermission('park:government-department:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGovernmentDepartmentExcel(@Valid GovernmentDepartmentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GovernmentDepartmentDO> list = governmentDepartmentService.getGovernmentDepartmentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "政府部门.xls", "数据", GovernmentDepartmentRespVO.class,
                        BeanUtils.toBean(list, GovernmentDepartmentRespVO.class));
    }

}
