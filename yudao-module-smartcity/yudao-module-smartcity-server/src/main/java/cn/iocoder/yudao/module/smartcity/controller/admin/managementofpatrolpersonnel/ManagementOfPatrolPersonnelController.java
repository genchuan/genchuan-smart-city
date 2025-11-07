package cn.iocoder.yudao.module.smartcity.controller.admin.managementofpatrolpersonnel;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.managementofpatrolpersonnel.vo.ManagementOfPatrolPersonnelPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.managementofpatrolpersonnel.vo.ManagementOfPatrolPersonnelRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.managementofpatrolpersonnel.vo.ManagementOfPatrolPersonnelSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.managementofpatrolpersonnel.ManagementOfPatrolPersonnelDO;
import cn.iocoder.yudao.module.smartcity.service.managementofpatrolpersonnel.ManagementOfPatrolPersonnelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 巡査人员管理")
@RestController
@RequestMapping("/smartcity/management-of-patrol-personnel")
@Validated
public class ManagementOfPatrolPersonnelController {

    @Resource
    private ManagementOfPatrolPersonnelService managementOfPatrolPersonnelService;

    @PostMapping("/create")
    @Operation(summary = "创建巡査人员管理")
    @PreAuthorize("@ss.hasPermission('smartcity:management-of-patrol-personnel:create')")
    public CommonResult<Long> createManagementOfPatrolPersonnel(@Valid @RequestBody ManagementOfPatrolPersonnelSaveReqVO createReqVO) {
        return success(managementOfPatrolPersonnelService.createManagementOfPatrolPersonnel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡査人员管理")
    @PreAuthorize("@ss.hasPermission('smartcity:management-of-patrol-personnel:update')")
    public CommonResult<Boolean> updateManagementOfPatrolPersonnel(@Valid @RequestBody ManagementOfPatrolPersonnelSaveReqVO updateReqVO) {
        managementOfPatrolPersonnelService.updateManagementOfPatrolPersonnel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡査人员管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:management-of-patrol-personnel:delete')")
    public CommonResult<Boolean> deleteManagementOfPatrolPersonnel(@RequestParam("id") Long id) {
        managementOfPatrolPersonnelService.deleteManagementOfPatrolPersonnel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡査人员管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:management-of-patrol-personnel:query')")
    public CommonResult<ManagementOfPatrolPersonnelRespVO> getManagementOfPatrolPersonnel(@RequestParam("id") Long id) {
        ManagementOfPatrolPersonnelDO managementOfPatrolPersonnel = managementOfPatrolPersonnelService.getManagementOfPatrolPersonnel(id);
        return success(BeanUtils.toBean(managementOfPatrolPersonnel, ManagementOfPatrolPersonnelRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡査人员管理分页")
    @PreAuthorize("@ss.hasPermission('smartcity:management-of-patrol-personnel:query')")
    public CommonResult<PageResult<ManagementOfPatrolPersonnelRespVO>> getManagementOfPatrolPersonnelPage(@Valid ManagementOfPatrolPersonnelPageReqVO pageReqVO) {
        PageResult<ManagementOfPatrolPersonnelDO> pageResult = managementOfPatrolPersonnelService.getManagementOfPatrolPersonnelPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ManagementOfPatrolPersonnelRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡査人员管理 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:management-of-patrol-personnel:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportManagementOfPatrolPersonnelExcel(@Valid ManagementOfPatrolPersonnelPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ManagementOfPatrolPersonnelDO> list = managementOfPatrolPersonnelService.getManagementOfPatrolPersonnelPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡査人员管理.xls", "数据", ManagementOfPatrolPersonnelRespVO.class,
                        BeanUtils.toBean(list, ManagementOfPatrolPersonnelRespVO.class));
    }

}