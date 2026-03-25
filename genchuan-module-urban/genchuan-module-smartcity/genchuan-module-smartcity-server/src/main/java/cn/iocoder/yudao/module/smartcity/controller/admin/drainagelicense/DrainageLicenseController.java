package cn.iocoder.yudao.module.smartcity.controller.admin.drainagelicense;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.drainagelicense.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagelicense.DrainageLicenseDO;
import cn.iocoder.yudao.module.smartcity.service.drainagelicense.DrainageLicenseService;

@Tag(name = "管理后台 - 排水电子许可证信息")
@RestController
@RequestMapping("/smartcity/drainage-license")
@Validated
public class DrainageLicenseController {

    @Resource
    private DrainageLicenseService drainageLicenseService;

    @PostMapping("/create")
    @Operation(summary = "创建排水电子许可证信息")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-license:create')")
    public CommonResult<Long> createDrainageLicense(@Valid @RequestBody DrainageLicenseSaveReqVO createReqVO) {
        return success(drainageLicenseService.createDrainageLicense(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新排水电子许可证信息")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-license:update')")
    public CommonResult<Boolean> updateDrainageLicense(@Valid @RequestBody DrainageLicenseSaveReqVO updateReqVO) {
        drainageLicenseService.updateDrainageLicense(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除排水电子许可证信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-license:delete')")
    public CommonResult<Boolean> deleteDrainageLicense(@RequestParam("id") Long id) {
        drainageLicenseService.deleteDrainageLicense(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得排水电子许可证信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-license:query')")
    public CommonResult<DrainageLicenseRespVO> getDrainageLicense(@RequestParam("id") Long id) {
        DrainageLicenseDO drainageLicense = drainageLicenseService.getDrainageLicense(id);
        return success(BeanUtils.toBean(drainageLicense, DrainageLicenseRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得排水电子许可证信息分页")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-license:query')")
    public CommonResult<PageResult<DrainageLicenseRespVO>> getDrainageLicensePage(@Valid DrainageLicensePageReqVO pageReqVO) {
        PageResult<DrainageLicenseDO> pageResult = drainageLicenseService.getDrainageLicensePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DrainageLicenseRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出排水电子许可证信息 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-license:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDrainageLicenseExcel(@Valid DrainageLicensePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DrainageLicenseDO> list = drainageLicenseService.getDrainageLicensePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "排水电子许可证信息.xls", "数据", DrainageLicenseRespVO.class,
                        BeanUtils.toBean(list, DrainageLicenseRespVO.class));
    }

}