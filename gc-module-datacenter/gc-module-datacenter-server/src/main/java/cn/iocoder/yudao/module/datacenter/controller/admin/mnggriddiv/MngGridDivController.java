package cn.iocoder.yudao.module.datacenter.controller.admin.mnggriddiv;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.mnggriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mnggriddiv.MngGridDivDO;
import cn.iocoder.yudao.module.datacenter.service.mnggriddiv.MngGridDivService;

@Tag(name = "管理后台 - 管理网格划分")
@RestController
@RequestMapping("/datacenter/mng-grid-div")
@Validated
public class MngGridDivController {

    @Resource
    private MngGridDivService mngGridDivService;

    @PostMapping("/create")
    @Operation(summary = "创建管理网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:create')")
    public CommonResult<Long> createMngGridDiv(@Valid @RequestBody MngGridDivSaveReqVO createReqVO) {
        return success(mngGridDivService.createMngGridDiv(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:update')")
    public CommonResult<Boolean> updateMngGridDiv(@Valid @RequestBody MngGridDivSaveReqVO updateReqVO) {
        mngGridDivService.updateMngGridDiv(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理网格划分")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:delete')")
    public CommonResult<Boolean> deleteMngGridDiv(@RequestParam("id") Long id) {
        mngGridDivService.deleteMngGridDiv(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理网格划分")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:query')")
    public CommonResult<MngGridDivRespVO> getMngGridDiv(@RequestParam("id") Long id) {
        MngGridDivDO mngGridDiv = mngGridDivService.getMngGridDiv(id);
        return success(BeanUtils.toBean(mngGridDiv, MngGridDivRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理网格划分分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:query')")
    public CommonResult<PageResult<MngGridDivRespVO>> getMngGridDivPage(@Valid MngGridDivPageReqVO pageReqVO) {
        PageResult<MngGridDivDO> pageResult = mngGridDivService.getMngGridDivPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MngGridDivRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理网格划分 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMngGridDivExcel(@Valid MngGridDivPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MngGridDivDO> list = mngGridDivService.getMngGridDivPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理网格划分.xls", "数据", MngGridDivRespVO.class,
                        BeanUtils.toBean(list, MngGridDivRespVO.class));
    }

    @GetMapping("/list-by-town")
    @Operation(summary = "获取乡镇下的管理网格列表")
    @Parameter(name = "townStreetId", description = "乡镇ID", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:query')")
    public CommonResult<List<MngGridDivRespVO>> getMngGridDivListByTown(
            @RequestParam("townStreetId") @NotEmpty(message = "乡镇ID不能为空") String townStreetId) {
        List<MngGridDivRespVO> list = mngGridDivService.getMngGridDivListByTown(townStreetId);
        return success(list);
    }

    @PostMapping("/validate-unit-grids")
    @Operation(summary = "校验单元网格是否可以集成")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:query')")
    public CommonResult<MngGridValidateRespVO> validateUnitGrids(
            @Valid @RequestBody MngGridValidateReqVO validateReqVO) {
        MngGridValidateRespVO result = mngGridDivService.validateUnitGrids(
                validateReqVO.getUnitGridIds(), validateReqVO.getTownStreetId());
        return success(result);
    }

    @PostMapping("/calculate-area")
    @Operation(summary = "计算管理网格面积")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:query')")
    public CommonResult<Integer> calculateArea(
            @RequestParam("unitGridIds") @NotEmpty(message = "单元网格ID列表不能为空") List<String> unitGridIds) {
        Integer area = mngGridDivService.calculateArea(unitGridIds);
        return success(area);
    }

    @PostMapping("/import-unit-grids")
    @Operation(summary = "批量导入单元网格创建管理网格")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:create')")
    public CommonResult<Long> importUnitGrids(
            @Valid @RequestBody MngGridImportReqVO importReqVO) {
        Long id = mngGridDivService.importUnitGrids(importReqVO);
        return success(id);
    }

    @GetMapping("/list-by-unit-count")
    @Operation(summary = "根据所含单元网格数量范围筛选管理网格")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:query')")
    public CommonResult<List<MngGridDivRespVO>> getMngGridDivByUnitCount(
            @RequestParam("townStreetId") @NotEmpty(message = "乡镇ID不能为空") String townStreetId,
            @RequestParam(value = "minUnits", required = false) Integer minUnits,
            @RequestParam(value = "maxUnits", required = false) Integer maxUnits) {
        List<MngGridDivRespVO> list = mngGridDivService.getMngGridDivByUnitCount(townStreetId, minUnits, maxUnits);
        return success(list);
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获取管理网格简单列表（用于下拉选择）")
    @Parameter(name = "townStreetId", description = "乡镇ID", required = false)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:query')")
    public CommonResult<List<MngGridSimpleRespVO>> getMngGridSimpleList(
            @RequestParam(value = "townStreetId", required = false) String townStreetId) {
        List<MngGridDivDO> mngGridList;

        if (townStreetId != null && !townStreetId.isEmpty()) {
            // 查询指定乡镇下的管理网格
            mngGridList = mngGridDivService.getMngGridDivListByTown(townStreetId).stream()
                    .map(respVO -> {
                        MngGridDivDO doObj = new MngGridDivDO();
                        doObj.setId(respVO.getId());
                        doObj.setMngGridId(respVO.getMngGridId());
                        doObj.setMngGridName(respVO.getMngGridName());
                        doObj.setTownStreetId(respVO.getTownStreetId());
                        doObj.setIncludedUnitIds(respVO.getIncludedUnitIds());
                        doObj.setArea(respVO.getArea());
                        return doObj;
                    })
                    .collect(java.util.stream.Collectors.toList());
        } else {
            // 查询所有管理网格
            mngGridList = mngGridDivService.getMngGridDivPage(new MngGridDivPageReqVO()).getList();
        }

        List<MngGridSimpleRespVO> simpleList = mngGridList.stream()
                .map(mngGrid -> {
                    MngGridSimpleRespVO respVO = new MngGridSimpleRespVO();
                    respVO.setId(mngGrid.getId());
                    respVO.setMngGridId(mngGrid.getMngGridId());
                    respVO.setMngGridName(mngGrid.getMngGridName());
                    respVO.setTownStreetId(mngGrid.getTownStreetId());
                    respVO.setIncludedUnitIds(mngGrid.getIncludedUnitIds());
                    respVO.setArea(mngGrid.getArea());

                    // 计算单元网格数量
                    if (mngGrid.getIncludedUnitIds() != null) {
                        int unitCount = mngGrid.getIncludedUnitIds().split(",").length;
                        respVO.setUnitGridCount(unitCount);
                    }

                    // 获取网格员ID
                    respVO.setStaffId(mngGrid.getExtCommon1());

                    return respVO;
                })
                .collect(java.util.stream.Collectors.toList());

        return success(simpleList);
    }

    @GetMapping("/unit-grid-detail")
    @Operation(summary = "获取管理网格所含单元网格详情")
    @Parameter(name = "id", description = "管理网格ID", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:query')")
    public CommonResult<MngGridUnitDetailRespVO> getUnitGridDetail(@RequestParam("id") Long id) {
        MngGridDivDO mngGridDiv = mngGridDivService.getMngGridDiv(id);
        if (mngGridDiv == null) {
            return success(null);
        }

        MngGridUnitDetailRespVO detailRespVO = new MngGridUnitDetailRespVO();
        detailRespVO.setId(mngGridDiv.getId());
        detailRespVO.setMngGridName(mngGridDiv.getMngGridName());
        detailRespVO.setTownStreetId(mngGridDiv.getTownStreetId());
        detailRespVO.setArea(mngGridDiv.getArea());
        detailRespVO.setStaffId(mngGridDiv.getExtCommon1());

        // 解析单元网格IDs
        if (mngGridDiv.getIncludedUnitIds() != null) {
            String[] unitIds = mngGridDiv.getIncludedUnitIds().split(",");
            detailRespVO.setUnitGridIds(Arrays.asList(unitIds));
            detailRespVO.setUnitGridCount(unitIds.length);

            // 获取真实的单元网格详情
            List<UnitGridSimpleInfo> unitGridInfos = mngGridDivService.getUnitGridDetails(Arrays.asList(unitIds));
            detailRespVO.setUnitGridInfos(unitGridInfos);
        }

        return success(detailRespVO);
    }

    @PostMapping("/update-staff")
    @Operation(summary = "更新管理网格的网格员")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-div:update')")
    public CommonResult<Boolean> updateMngGridStaff(
            @RequestParam("id") Long id,
            @RequestParam(value = "staffId", required = false) String staffId) {
        MngGridDivDO mngGridDiv = mngGridDivService.getMngGridDiv(id);
        if (mngGridDiv == null) {
            throw new RuntimeException("管理网格不存在");
        }

        // 更新网格员ID到扩展字段
        MngGridDivSaveReqVO updateReqVO = new MngGridDivSaveReqVO();
        updateReqVO.setId(id);
        updateReqVO.setMngGridId(mngGridDiv.getMngGridId());
        updateReqVO.setMngGridName(mngGridDiv.getMngGridName());
        updateReqVO.setTownStreetId(mngGridDiv.getTownStreetId());
        updateReqVO.setIncludedUnitIds(mngGridDiv.getIncludedUnitIds());
        updateReqVO.setArea(mngGridDiv.getArea());
        updateReqVO.setDivTime(mngGridDiv.getDivTime());
        updateReqVO.setRemark(mngGridDiv.getRemark());
        updateReqVO.setExtCommon1(staffId); // 更新网格员ID
        updateReqVO.setExtCommon2(mngGridDiv.getExtCommon2());
        updateReqVO.setUpdateTimeSys(java.time.LocalDateTime.now());

        mngGridDivService.updateMngGridDiv(updateReqVO);
        return success(true);
    }

}