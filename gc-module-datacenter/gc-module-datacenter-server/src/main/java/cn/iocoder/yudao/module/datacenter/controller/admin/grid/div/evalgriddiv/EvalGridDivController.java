package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo.MngGridSimpleRespVO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.div.evalgriddiv.EvalGridDivMapper;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.evalgriddiv.EvalGridDivDO;
import cn.iocoder.yudao.module.datacenter.service.grid.div.evalgriddiv.EvalGridDivService;

@Tag(name = "管理后台 - 评价网格划分")
@RestController
@RequestMapping("/datacenter/eval-grid-div")
@Validated
public class EvalGridDivController {

    @Resource
    private EvalGridDivService evalGridDivService;
    @Resource
    private EvalGridDivMapper evalGridDivMapper;

    @PostMapping("/create")
    @Operation(summary = "创建评价网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:create')")
    public CommonResult<Long> createEvalGridDiv(@Valid @RequestBody EvalGridDivSaveReqVO createReqVO) {
        return success(evalGridDivService.createEvalGridDiv(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:update')")
    public CommonResult<Boolean> updateEvalGridDiv(@Valid @RequestBody EvalGridDivSaveReqVO updateReqVO) {
        evalGridDivService.updateEvalGridDiv(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价网格划分")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:delete')")
    public CommonResult<Boolean> deleteEvalGridDiv(@RequestParam("id") Long id) {
        evalGridDivService.deleteEvalGridDiv(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价网格划分")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:query')")
    public CommonResult<EvalGridDivRespVO> getEvalGridDiv(@RequestParam("id") Long id) {
        EvalGridDivDO evalGridDiv = evalGridDivService.getEvalGridDiv(id);
        return success(BeanUtils.toBean(evalGridDiv, EvalGridDivRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价网格划分分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:query')")
    public CommonResult<PageResult<EvalGridDivRespVO>> getEvalGridDivPage(@Valid EvalGridDivPageReqVO pageReqVO) {
        PageResult<EvalGridDivDO> pageResult = evalGridDivService.getEvalGridDivPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalGridDivRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价网格划分 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalGridDivExcel(@Valid EvalGridDivPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalGridDivDO> list = evalGridDivService.getEvalGridDivPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价网格划分.xls", "数据", EvalGridDivRespVO.class,
                        BeanUtils.toBean(list, EvalGridDivRespVO.class));
    }

//    @PostMapping("/validate")
//    @Operation(summary = "校验评价网格")
//    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:create')")
//    public CommonResult<EvalGridValidateRespVO> validateEvalGrid(
//            @RequestParam("townStreetId") String townStreetId,
//            @RequestParam("mngGridIds") List<String> mngGridIds) {
//        EvalGridValidateRespVO result = evalGridDivService.validateEvalGrid(townStreetId, mngGridIds);
//        return success(result);
//    }

    @PostMapping("/validate")
    @Operation(summary = "校验评价网格")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:create')")
    public CommonResult<EvalGridValidateRespVO> validateEvalGrid(
            @RequestParam(value = "townStreetId", required = false) String townStreetId,
            @RequestParam(value = "mngGridIds", required = false) List<String> mngGridIds,
            @RequestBody(required = false) EvalGridValidateReqVO reqVO) {

        // 如果通过 @RequestBody 传递参数
        if (reqVO != null) {
            townStreetId = reqVO.getTownStreetId();
            mngGridIds = reqVO.getMngGridIds();
        }

        // 参数校验
        if (townStreetId == null || townStreetId.trim().isEmpty()) {
            throw new IllegalArgumentException("乡镇ID不能为空");
        }
        if (mngGridIds == null || mngGridIds.isEmpty()) {
            throw new IllegalArgumentException("管理网格ID列表不能为空");
        }

        EvalGridValidateRespVO result = evalGridDivService.validateEvalGrid(townStreetId, mngGridIds);
        return success(result);
    }


    // 辅助方法：获取第一个非空值
    private <T> T getFirstNonNull(T first, T second) {
        return first != null ? first : second;
    }

    @GetMapping("/mng-grid-list")
    @Operation(summary = "获取乡镇下的管理网格列表")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:query')")
    public CommonResult<List<MngGridSimpleRespVO>> getMngGridListByTown(
            @RequestParam("townStreetId") String townStreetId) {
        List<MngGridSimpleRespVO> list = evalGridDivService.getMngGridListByTown(townStreetId);
        return success(list);
    }

    @PostMapping("/calculate-area")
    @Operation(summary = "计算评价网格面积")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:create')")
    public CommonResult<Integer> calculateEvalGridArea(
            @RequestParam("mngGridIds") List<String> mngGridIds) {
        Integer area = evalGridDivService.calculateEvalGridArea(mngGridIds);
        return success(area);
    }

    @GetMapping("/distribution")
    @Operation(summary = "获取评价网格类型分布")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:query')")
    public CommonResult<Map<String, Long>> getEvalGridDistribution() {
        Map<String, Long> distribution = evalGridDivService.getEvalGridDistribution();
        return success(distribution);
    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "获取评价网格详情")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:query')")
    public CommonResult<EvalGridDivDetailRespVO> getEvalGridDivDetail(@PathVariable("id") Long id) {
        EvalGridDivDetailRespVO detail = evalGridDivService.getEvalGridDivDetail(id);
        return success(detail);
    }

    @PutMapping("/update-type-batch")
    @Operation(summary = "批量更新评价网格类型")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:update')")
    public CommonResult<Boolean> updateEvalGridTypeBatch(
            @RequestParam("ids") List<Long> ids,
            @RequestParam("gridType") String gridType) {
        evalGridDivService.updateEvalGridTypeBatch(ids, gridType);
        return success(true);
    }

    @PostMapping("/recommend-type")
    @Operation(summary = "推荐评价网格类型")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:query')")
    public CommonResult<String> recommendGridType(
            @RequestParam("townStreetId") String townStreetId,
            @RequestParam("mngGridIds") List<String> mngGridIds) {
        String recommendedType = evalGridDivService.recommendGridType(townStreetId, mngGridIds);
        return success(recommendedType);
    }


    @PostMapping("/import-mng-grids")
    @Operation(summary = "批量导入管理网格创建评价网格")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:create')")
    public CommonResult<Long> importMngGrids(
            @Valid @RequestBody EvalGridImportReqVO importReqVO) {
        // 先校验
        EvalGridValidateRespVO validateResult = evalGridDivService.validateEvalGrid(
                importReqVO.getTownStreetId(), importReqVO.getMngGridIds());

        if (!validateResult.getValid()) {
            throw new RuntimeException("评价网格校验失败: " + validateResult.getMessage());
        }

        // 创建评价网格
        EvalGridDivSaveReqVO createReqVO = new EvalGridDivSaveReqVO();
        createReqVO.setEvalGridId("EVAL_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        createReqVO.setEvalGridName(importReqVO.getEvalGridName());
        createReqVO.setTownStreetId(importReqVO.getTownStreetId());
        createReqVO.setGridType(importReqVO.getGridType());
        createReqVO.setIncludedMgIds(String.join(",", importReqVO.getMngGridIds()));
        createReqVO.setArea(validateResult.getCalculatedArea());
        createReqVO.setDivTime(java.time.LocalDateTime.now());
        createReqVO.setRemark(importReqVO.getRemark());

        EvalGridDivDO evalGridDiv = BeanUtils.toBean(createReqVO, EvalGridDivDO.class);
        evalGridDivMapper.insert(evalGridDiv);

        return success(evalGridDiv.getId());
    }

    @GetMapping("/list-by-mng-grid-count")
    @Operation(summary = "根据管理网格数量范围获取评价网格列表")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:query')")
    public CommonResult<List<EvalGridDivRespVO>> getEvalGridDivByMngGridCount(
            @RequestParam(value = "townStreetId", required = false) String townStreetId,
            @RequestParam(value = "minMngGrids", required = false) Integer minMngGrids,
            @RequestParam(value = "maxMngGrids", required = false) Integer maxMngGrids) {

        List<EvalGridDivRespVO> result = evalGridDivService.getEvalGridDivByMngGridCount(townStreetId, minMngGrids, maxMngGrids);
        return success(result);
    }

    @GetMapping("/page-with-boundary")
    @Operation(summary = "获得评价网格划分分页（包含边界坐标）")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:query')")
    public CommonResult<PageResult<EvalGridDivRespVO>> getEvalGridDivPageWithBoundary(
            @Valid EvalGridDivPageReqVO pageReqVO) {
        PageResult<EvalGridDivRespVO> pageResult = evalGridDivService.getEvalGridDivPageWithBoundary(pageReqVO);
        return success(pageResult);
    }

}