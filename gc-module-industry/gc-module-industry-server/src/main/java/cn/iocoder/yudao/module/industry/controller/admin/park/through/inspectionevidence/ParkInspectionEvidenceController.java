package cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence.vo.ParkInspectionEvidencePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence.vo.ParkInspectionEvidenceRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence.vo.ParkInspectionEvidenceSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.inspectionevidence.ParkInspectionEvidenceDO;
import cn.iocoder.yudao.module.industry.service.park.through.inspectionevidence.ParkInspectionEvidenceService;

@Tag(name = "管理后台 - 稽查证据")
@RestController
@RequestMapping("/industry/park-inspection-evidence")
@Validated
public class ParkInspectionEvidenceController {

    @Resource
    private ParkInspectionEvidenceService parkInspectionEvidenceService;

    @PostMapping("/create")
    @Operation(summary = "创建稽查证据")
    @PreAuthorize("@ss.hasPermission('industry:park-inspection-evidence:create')")
    public CommonResult<Long> createParkInspectionEvidence(@Valid @RequestBody ParkInspectionEvidenceSaveReqVO createReqVO) {
        return success(parkInspectionEvidenceService.createParkInspectionEvidence(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新稽查证据")
    @PreAuthorize("@ss.hasPermission('industry:park-inspection-evidence:update')")
    public CommonResult<Boolean> updateParkInspectionEvidence(@Valid @RequestBody ParkInspectionEvidenceSaveReqVO updateReqVO) {
        parkInspectionEvidenceService.updateParkInspectionEvidence(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除稽查证据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-inspection-evidence:delete')")
    public CommonResult<Boolean> deleteParkInspectionEvidence(@RequestParam("id") Long id) {
        parkInspectionEvidenceService.deleteParkInspectionEvidence(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得稽查证据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-inspection-evidence:query')")
    public CommonResult<ParkInspectionEvidenceRespVO> getParkInspectionEvidence(@RequestParam("id") Long id) {
        ParkInspectionEvidenceDO parkInspectionEvidence = parkInspectionEvidenceService.getParkInspectionEvidence(id);
        return success(BeanUtils.toBean(parkInspectionEvidence, ParkInspectionEvidenceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得稽查证据分页")
    @PreAuthorize("@ss.hasPermission('industry:park-inspection-evidence:query')")
    public CommonResult<PageResult<ParkInspectionEvidenceRespVO>> getParkInspectionEvidencePage(@Valid ParkInspectionEvidencePageReqVO pageReqVO) {
        PageResult<ParkInspectionEvidenceDO> pageResult = parkInspectionEvidenceService.getParkInspectionEvidencePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkInspectionEvidenceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出稽查证据 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-inspection-evidence:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkInspectionEvidenceExcel(@Valid ParkInspectionEvidencePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkInspectionEvidenceDO> list = parkInspectionEvidenceService.getParkInspectionEvidencePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "稽查证据.xls", "数据", ParkInspectionEvidenceRespVO.class,
                        BeanUtils.toBean(list, ParkInspectionEvidenceRespVO.class));
    }

}