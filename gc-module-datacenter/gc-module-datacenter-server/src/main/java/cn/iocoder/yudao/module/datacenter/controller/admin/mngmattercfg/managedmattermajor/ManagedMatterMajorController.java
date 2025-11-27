package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.managedmattermajor;


import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.managedmattermajor.vo.ManagedMatterMajorListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.managedmattermajor.vo.ManagedMatterMajorRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.managedmattermajor.vo.ManagedMatterMajorSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.managedmattermajor.ManagedMatterMajorDO;
import cn.iocoder.yudao.module.datacenter.service.mngmattercfg.managedmattermajor.ManagedMatterMajorService;
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

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 管理事项分类表（含大类和小类）")
@RestController
@RequestMapping("/datacenter/managed-matter-major")
@Validated
public class ManagedMatterMajorController {

    @Resource
    private ManagedMatterMajorService managedMatterMajorService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项分类表（含大类和小类）")
    @PreAuthorize("@ss.hasPermission('gc:managed-matter-major:create')")
    public CommonResult<Long> createManagedMatterMajor(@Valid @RequestBody ManagedMatterMajorSaveReqVO createReqVO) {
        return success(managedMatterMajorService.createManagedMatterMajor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项分类表（含大类和小类）")
    @PreAuthorize("@ss.hasPermission('gc:managed-matter-major:update')")
    public CommonResult<Boolean> updateManagedMatterMajor(@Valid @RequestBody ManagedMatterMajorSaveReqVO updateReqVO) {
        managedMatterMajorService.updateManagedMatterMajor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项分类表（含大类和小类）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('gc:managed-matter-major:delete')")
    public CommonResult<Boolean> deleteManagedMatterMajor(@RequestParam("id") Long id) {
        managedMatterMajorService.deleteManagedMatterMajor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项分类表（含大类和小类）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('gc:managed-matter-major:query')")
    public CommonResult<ManagedMatterMajorRespVO> getManagedMatterMajor(@RequestParam("id") Long id) {
        ManagedMatterMajorDO managedMatterMajor = managedMatterMajorService.getManagedMatterMajor(id);
        return success(BeanUtils.toBean(managedMatterMajor, ManagedMatterMajorRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得管理事项分类表（含大类和小类）")
//    @PreAuthorize("@ss.hasPermission('gc:managed-matter-major:query')")
    public CommonResult<List<ManagedMatterMajorDO>> getManagedMatterMajorPage(@Valid ManagedMatterMajorListReqVO ReqVO) {
        List<ManagedMatterMajorDO> list = managedMatterMajorService.getManagedMatterMajorList(ReqVO);
        return success(BeanUtils.toBean(list, ManagedMatterMajorDO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项分类表（含大类和小类） Excel")
    @PreAuthorize("@ss.hasPermission('gc:managed-matter-major:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportManagedMatterMajorExcel(@Valid ManagedMatterMajorListReqVO pageReqVO,
                                              HttpServletResponse response) throws IOException {
        List<ManagedMatterMajorDO> list = managedMatterMajorService.getManagedMatterMajorList(pageReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "管理事项分类表（含大类和小类）.xls", "数据", ManagedMatterMajorRespVO.class,
                BeanUtils.toBean(list, ManagedMatterMajorRespVO.class));
    }

}
