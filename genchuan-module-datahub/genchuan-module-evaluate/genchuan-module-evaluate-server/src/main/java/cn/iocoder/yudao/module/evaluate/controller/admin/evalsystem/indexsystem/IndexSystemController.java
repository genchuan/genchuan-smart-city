package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.service.indexsystem.IndexSystemService;
import com.alibaba.nacos.api.model.v2.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "评价体系管理 - 指标体系管理***")
@RestController
@RequestMapping("/evaluate/index-system")
@Validated
public class IndexSystemController {

    @Resource
    private IndexSystemService indexSystemService;

    @PostMapping("/create")
    @Operation(summary = "创建指标体系")
    @PreAuthorize("@ss.hasPermission('evaluate:index-system:create')")
    public CommonResult<Long> createIndexSystem(@Valid @RequestBody IndexSystemSaveReqVO createReqVO) {
        return success(indexSystemService.createIndexSystem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指标体系")
    @PreAuthorize("@ss.hasPermission('evaluate:index-system:update')")
    public CommonResult<Boolean> updateIndexSystem(@Valid @RequestBody IndexSystemSaveReqVO updateReqVO) {
        indexSystemService.updateIndexSystem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指标体系")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:index-system:delete')")
    public CommonResult<Boolean> deleteIndexSystem(@RequestParam("id") Long id) {
        indexSystemService.deleteIndexSystem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指标体系")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:index-system:query')")
    public CommonResult<IndexSystemRespVO> getIndexSystem(@RequestParam("id") Long id) {
        IndexSystemDO indexSystem = indexSystemService.getIndexSystem(id);
        return success(BeanUtils.toBean(indexSystem, IndexSystemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指标体系分页")
    @PreAuthorize("@ss.hasPermission('evaluate:index-system:query')")
    public CommonResult<PageResult<IndexSystemRespVO>> getIndexSystemPage(@Valid IndexSystemPageReqVO pageReqVO) {
        PageResult<IndexSystemDO> pageResult = indexSystemService.getIndexSystemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, IndexSystemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指标体系 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:index-system:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportIndexSystemExcel(@Valid IndexSystemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<IndexSystemDO> list = indexSystemService.getIndexSystemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "指标体系.xls", "数据", IndexSystemRespVO.class,
                        BeanUtils.toBean(list, IndexSystemRespVO.class));
    }
//-------------------------------新增--------------------------
    @GetMapping("/page-with-join")
    @Operation(summary = "分页查询指标体系列表（带关联信息）")
    public CommonResult<PageResult<IndexSystemPageItemVO>> getIndexSystemPageWithJoin(@Valid IndexSystemPageReqVO pageVO) {
        PageResult<IndexSystemPageItemVO> pageResult = indexSystemService.getIndexSystemPageWithJoin(pageVO);
        return success(pageResult);
    }

    @GetMapping("/detail/{systemId}")
    @Operation(summary = "查询指标体系详情（树形结构）")
    @Parameter(name = "systemId", description = "指标体系ID（UUID）", required = true, example = "system_001")
    public CommonResult<IndexSystemDetailVO> getIndexSystemDetail(@PathVariable("systemId") String systemId) {
        IndexSystemDetailVO detail = indexSystemService.getIndexSystemDetail(systemId);
        return success(detail);
    }

    @PostMapping("/check-weight")
    @Operation(summary = "权重校验")
    public CommonResult<WeightCheckRespVO> checkWeight(@Valid @RequestBody WeightCheckReqVO reqVO) {
        WeightCheckRespVO respVO = indexSystemService.checkWeight(reqVO);
        return success(respVO);
    }
    //-----------------改------------------------------
    @GetMapping("/allpage")
    @Operation(summary = "获得指标体系分页（全部/启用/停用）")
    @PreAuthorize("@ss.hasPermission('evaluate:index-system:query')")
    public CommonResult<PageResult<IndexSystemRespVO>> getIndexSystemJoinPage(IndexSystemPageReqVO reqVO) {
        PageResult<IndexSystemRespVO> pageResult = indexSystemService.getIndexSystemJoinPage(reqVO);
        return CommonResult.success(pageResult);
    }
    @GetMapping("/status-count")
    @Operation(summary = "获取status_id统计数据*")
    public Result<IndexSystemRespVO> getStatusCount(
            @RequestParam(required = false) Integer statusId) {
        IndexSystemRespVO respVO = indexSystemService.getStatusCount(statusId);
        return Result.success(respVO);
    }
    @GetMapping("/overview")
    @Operation(summary = "指标体系全局概览")
    public CommonResult<IndexSystemOverviewVO> getOverview() {
        return CommonResult.success(indexSystemService.getOverview());
    }
}