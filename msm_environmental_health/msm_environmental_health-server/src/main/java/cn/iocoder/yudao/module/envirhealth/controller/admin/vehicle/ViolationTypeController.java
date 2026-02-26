/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle;

import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationtype.ViolationTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationtype.ViolationTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationtype.ViolationTypeSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.ViolationTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.vehicle.violationstatus.ViolationTypeService;

@Tag(name = "管理后台 - 违规类型字典表")
@RestController
@RequestMapping("/envirhealth/violation-type")
@Validated
public class ViolationTypeController {

    @Resource
    private ViolationTypeService violationTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建违规类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-type:create')")
    public CommonResult<Long> createViolationType(@Valid @RequestBody ViolationTypeSaveReqVO createReqVO) {
        return success(violationTypeService.createViolationType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新违规类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-type:update')")
    public CommonResult<Boolean> updateViolationType(@Valid @RequestBody ViolationTypeSaveReqVO updateReqVO) {
        violationTypeService.updateViolationType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除违规类型字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-type:delete')")
    public CommonResult<Boolean> deleteViolationType(@RequestParam("id") Long id) {
        violationTypeService.deleteViolationType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得违规类型字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-type:query')")
    public CommonResult<ViolationTypeRespVO> getViolationType(@RequestParam("id") Long id) {
        ViolationTypeDO violationType = violationTypeService.getViolationType(id);
        return success(BeanUtils.toBean(violationType, ViolationTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得违规类型字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-type:query')")
    public CommonResult<PageResult<ViolationTypeRespVO>> getViolationTypePage(@Valid ViolationTypePageReqVO pageReqVO) {
        PageResult<ViolationTypeDO> pageResult = violationTypeService.getViolationTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ViolationTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出违规类型字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:violation-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportViolationTypeExcel(@Valid ViolationTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ViolationTypeDO> list = violationTypeService.getViolationTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "违规类型字典表.xls", "数据", ViolationTypeRespVO.class,
                        BeanUtils.toBean(list, ViolationTypeRespVO.class));
    }

}*/
