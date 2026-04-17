package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PrizeMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.prizemgmt.PrizeMgmtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "管理后台 - 奖品管理")
@RestController
@RequestMapping("/marketop/prize-mgmt")
public class PrizeMgmtController {

    @Resource
    private PrizeMgmtService prizeMgmtService;

    @GetMapping("/page")
    @Operation(summary = "获得奖品管理分页")
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:query')")
    public CommonResult<PageResult<PrizeMgmtRespVO>> getPage(PrizeMgmtPageReqVO reqVO) {
        PageResult<PrizeMgmtDO> pageResult = prizeMgmtService.getPage(reqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, PrizeMgmtRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得奖品详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:query')")
    public CommonResult<PrizeMgmtRespVO> get(@RequestParam("id") Long id) {
        PrizeMgmtDO prizeMgmt = prizeMgmtService.get(id);
        return CommonResult.success(BeanUtils.toBean(prizeMgmt, PrizeMgmtRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建奖品")
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:create')")
    public CommonResult<Long> create(@Valid @RequestBody PrizeMgmtCreateReqVO reqVO) {
        return CommonResult.success(prizeMgmtService.create(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新奖品")
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody PrizeMgmtUpdateReqVO reqVO) {
        prizeMgmtService.update(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "启用奖品")
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:update')")
    public CommonResult<Boolean> enable(@RequestParam("id") Long id) {
        prizeMgmtService.enable(id);
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用奖品")
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:update')")
    public CommonResult<Boolean> disable(@RequestParam("id") Long id) {
        prizeMgmtService.disable(id);
        return CommonResult.success(true);
    }

    @PostMapping("/import")
    @Operation(summary = "导入奖品")
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:import')")
    public CommonResult<Boolean> importExcel() {
        // TODO: 实现导入逻辑
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出奖品")
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:query')")
    public void export(PrizeMgmtPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<PrizeMgmtDO> pageResult = prizeMgmtService.getPage(reqVO);
        List<PrizeMgmtRespVO> list = BeanUtils.toBean(pageResult.getList(), PrizeMgmtRespVO.class);
        ExcelUtils.write(response, "奖品管理.xlsx", "数据", PrizeMgmtRespVO.class, list);
    }

    @GetMapping("/chart")
    @Operation(summary = "奖品管理图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:query')")
    public CommonResult<PrizeMgmtChartRespVO> getChart(@RequestParam(value = "timeRange", required = false) String timeRange) {
        return CommonResult.success(prizeMgmtService.getChart(timeRange));
    }

}
