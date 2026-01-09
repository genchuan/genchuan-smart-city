package cn.iocoder.yudao.module.smartcity.controller.admin.maintenancepersonnel;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.maintenancepersonnel.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenancepersonnel.MaintenancePersonnelDO;
import cn.iocoder.yudao.module.smartcity.service.maintenancepersonnel.MaintenancePersonnelService;

@Tag(name = "管理后台 - 养护人员")
@RestController
@RequestMapping("/smartcity/maintenance-personnel")
@Validated
public class MaintenancePersonnelController {

    @Resource
    private MaintenancePersonnelService maintenancePersonnelService;

    @PostMapping("/create")
    @Operation(summary = "创建养护人员")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-personnel:create')")
    public CommonResult<Long> createMaintenancePersonnel(@Valid @RequestBody MaintenancePersonnelSaveReqVO createReqVO) {
        return success(maintenancePersonnelService.createMaintenancePersonnel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新养护人员")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-personnel:update')")
    public CommonResult<Boolean> updateMaintenancePersonnel(@Valid @RequestBody MaintenancePersonnelSaveReqVO updateReqVO) {
        maintenancePersonnelService.updateMaintenancePersonnel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除养护人员")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-personnel:delete')")
    public CommonResult<Boolean> deleteMaintenancePersonnel(@RequestParam("id") Long id) {
        maintenancePersonnelService.deleteMaintenancePersonnel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得养护人员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-personnel:query')")
    public CommonResult<MaintenancePersonnelRespVO> getMaintenancePersonnel(@RequestParam("id") Long id) {
        MaintenancePersonnelDO maintenancePersonnel = maintenancePersonnelService.getMaintenancePersonnel(id);
        return success(BeanUtils.toBean(maintenancePersonnel, MaintenancePersonnelRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得养护人员分页")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-personnel:query')")
    public CommonResult<PageResult<MaintenancePersonnelRespVO>> getMaintenancePersonnelPage(@Valid MaintenancePersonnelPageReqVO pageReqVO) {
        PageResult<MaintenancePersonnelDO> pageResult = maintenancePersonnelService.getMaintenancePersonnelPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MaintenancePersonnelRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出养护人员 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-personnel:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMaintenancePersonnelExcel(@Valid MaintenancePersonnelPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MaintenancePersonnelDO> list = maintenancePersonnelService.getMaintenancePersonnelPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "养护人员.xls", "数据", MaintenancePersonnelRespVO.class,
                        BeanUtils.toBean(list, MaintenancePersonnelRespVO.class));
    }

}