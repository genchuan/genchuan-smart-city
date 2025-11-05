package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig.vo.ManagedComponentMinorConfigPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig.vo.ManagedComponentMinorConfigRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig.vo.ManagedComponentMinorConfigSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedcomponentminorconfig.ManagedComponentMinorConfigDO;
import cn.iocoder.yudao.module.datacenter.service.mngcompcfg.managedcomponentminorconfig.ManagedComponentMinorConfigService;

@Tag(name = "管理后台 - 管理部件小类配置")
@RestController
@RequestMapping("/datacenter/managed-component-minor-config")
@Validated
public class ManagedComponentMinorConfigController {

    @Resource
    private ManagedComponentMinorConfigService managedComponentMinorConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件小类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-minor-config:create')")
    public CommonResult<Long> createManagedComponentMinorConfig(@Valid @RequestBody ManagedComponentMinorConfigSaveReqVO createReqVO) {
        return success(managedComponentMinorConfigService.createManagedComponentMinorConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件小类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-minor-config:update')")
    public CommonResult<Boolean> updateManagedComponentMinorConfig(@Valid @RequestBody ManagedComponentMinorConfigSaveReqVO updateReqVO) {
        managedComponentMinorConfigService.updateManagedComponentMinorConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件小类配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-minor-config:delete')")
    public CommonResult<Boolean> deleteManagedComponentMinorConfig(@RequestParam("id") Long id) {
        managedComponentMinorConfigService.deleteManagedComponentMinorConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件小类配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-minor-config:query')")
    public CommonResult<ManagedComponentMinorConfigRespVO> getManagedComponentMinorConfig(@RequestParam("id") Long id) {
        ManagedComponentMinorConfigDO managedComponentMinorConfig = managedComponentMinorConfigService.getManagedComponentMinorConfig(id);
        return success(BeanUtils.toBean(managedComponentMinorConfig, ManagedComponentMinorConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件小类配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-minor-config:query')")
    public CommonResult<PageResult<ManagedComponentMinorConfigRespVO>> getManagedComponentMinorConfigPage(@Valid ManagedComponentMinorConfigPageReqVO pageReqVO) {
        PageResult<ManagedComponentMinorConfigDO> pageResult = managedComponentMinorConfigService.getManagedComponentMinorConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ManagedComponentMinorConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件小类配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-component-minor-config:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportManagedComponentMinorConfigExcel(@Valid ManagedComponentMinorConfigPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ManagedComponentMinorConfigDO> list = managedComponentMinorConfigService.getManagedComponentMinorConfigPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件小类配置.xls", "数据", ManagedComponentMinorConfigRespVO.class,
                        BeanUtils.toBean(list, ManagedComponentMinorConfigRespVO.class));
    }

}