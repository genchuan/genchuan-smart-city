package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentmajorconfig;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentmajorconfig.vo.ManagedComponentMajorConfigPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentmajorconfig.vo.ManagedComponentMajorConfigRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentmajorconfig.vo.ManagedComponentMajorConfigSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentmajorconfig.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedcomponentmajorconfig.ManagedComponentMajorConfigDO;
import cn.iocoder.yudao.module.datacenter.service.mngcompcfg.managedcomponentmajorconfig.ManagedComponentMajorConfigService;

@Tag(name = "管理后台 - 管理部件大类配置表")
@RestController
@RequestMapping("/datacenter/managed-component-major-config")
@Validated
public class ManagedComponentMajorConfigController {

    @Resource
    private ManagedComponentMajorConfigService managedComponentMajorConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件大类配置表")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-major-config:create')")
    public CommonResult<Long> createManagedComponentMajorConfig(@Valid @RequestBody ManagedComponentMajorConfigSaveReqVO createReqVO) {
        return success(managedComponentMajorConfigService.createManagedComponentMajorConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件大类配置表")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-major-config:update')")
    public CommonResult<Boolean> updateManagedComponentMajorConfig(@Valid @RequestBody ManagedComponentMajorConfigSaveReqVO updateReqVO) {
        managedComponentMajorConfigService.updateManagedComponentMajorConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件大类配置表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-major-config:delete')")
    public CommonResult<Boolean> deleteManagedComponentMajorConfig(@RequestParam("id") Long id) {
        managedComponentMajorConfigService.deleteManagedComponentMajorConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件大类配置表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-major-config:query')")
    public CommonResult<ManagedComponentMajorConfigRespVO> getManagedComponentMajorConfig(@RequestParam("id") Long id) {
        ManagedComponentMajorConfigDO managedComponentMajorConfig = managedComponentMajorConfigService.getManagedComponentMajorConfig(id);
        return success(BeanUtils.toBean(managedComponentMajorConfig, ManagedComponentMajorConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件大类配置表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-major-config:query')")
    public CommonResult<PageResult<ManagedComponentMajorConfigRespVO>> getManagedComponentMajorConfigPage(@Valid ManagedComponentMajorConfigPageReqVO pageReqVO) {
        PageResult<ManagedComponentMajorConfigDO> pageResult = managedComponentMajorConfigService.getManagedComponentMajorConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ManagedComponentMajorConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件大类配置表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-major-config:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportManagedComponentMajorConfigExcel(@Valid ManagedComponentMajorConfigPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ManagedComponentMajorConfigDO> list = managedComponentMajorConfigService.getManagedComponentMajorConfigPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件大类配置表.xls", "数据", ManagedComponentMajorConfigRespVO.class,
                        BeanUtils.toBean(list, ManagedComponentMajorConfigRespVO.class));
    }

}