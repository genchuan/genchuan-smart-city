package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedmajorminorrel;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedmajorminorrel.vo.ManagedMajorMinorRelPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedmajorminorrel.vo.ManagedMajorMinorRelRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedmajorminorrel.vo.ManagedMajorMinorRelSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedmajorminorrel.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedmajorminorrel.ManagedMajorMinorRelDO;
import cn.iocoder.yudao.module.datacenter.service.mngcompcfg.managedmajorminorrel.ManagedMajorMinorRelService;

@Tag(name = "管理后台 - 管理部件大类小类关联")
@RestController
@RequestMapping("/datacenter/managed-major-minor-rel")
@Validated
public class ManagedMajorMinorRelController {

    @Resource
    private ManagedMajorMinorRelService managedMajorMinorRelService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件大类小类关联")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-major-minor-rel:create')")
    public CommonResult<Long> createManagedMajorMinorRel(@Valid @RequestBody ManagedMajorMinorRelSaveReqVO createReqVO) {
        return success(managedMajorMinorRelService.createManagedMajorMinorRel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件大类小类关联")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-major-minor-rel:update')")
    public CommonResult<Boolean> updateManagedMajorMinorRel(@Valid @RequestBody ManagedMajorMinorRelSaveReqVO updateReqVO) {
        managedMajorMinorRelService.updateManagedMajorMinorRel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件大类小类关联")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:managed-major-minor-rel:delete')")
    public CommonResult<Boolean> deleteManagedMajorMinorRel(@RequestParam("id") Long id) {
        managedMajorMinorRelService.deleteManagedMajorMinorRel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件大类小类关联")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-major-minor-rel:query')")
    public CommonResult<ManagedMajorMinorRelRespVO> getManagedMajorMinorRel(@RequestParam("id") Long id) {
        ManagedMajorMinorRelDO managedMajorMinorRel = managedMajorMinorRelService.getManagedMajorMinorRel(id);
        return success(BeanUtils.toBean(managedMajorMinorRel, ManagedMajorMinorRelRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件大类小类关联分页")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-major-minor-rel:query')")
    public CommonResult<PageResult<ManagedMajorMinorRelRespVO>> getManagedMajorMinorRelPage(@Valid ManagedMajorMinorRelPageReqVO pageReqVO) {
        PageResult<ManagedMajorMinorRelDO> pageResult = managedMajorMinorRelService.getManagedMajorMinorRelPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ManagedMajorMinorRelRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件大类小类关联 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:managed-major-minor-rel:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportManagedMajorMinorRelExcel(@Valid ManagedMajorMinorRelPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ManagedMajorMinorRelDO> list = managedMajorMinorRelService.getManagedMajorMinorRelPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件大类小类关联.xls", "数据", ManagedMajorMinorRelRespVO.class,
                        BeanUtils.toBean(list, ManagedMajorMinorRelRespVO.class));
    }

}