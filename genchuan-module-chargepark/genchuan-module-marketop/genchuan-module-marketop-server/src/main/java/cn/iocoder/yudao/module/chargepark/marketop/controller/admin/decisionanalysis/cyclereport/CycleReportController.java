package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.decisionanalysis.CycleReportDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.cyclereport.CycleReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.util.*;

@Tag(name = "管理后台 - 营销运营周期报表")
@RestController
@RequestMapping("/marketop/cycle-report")
@Validated
public class CycleReportController {

    @Resource
    private CycleReportService cycleReportService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "获得周期报表分页")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> getPage(CycleReportPageReqVO reqVO) {
        PageResult<CycleReportDO> pageResult = cycleReportService.getPage(reqVO);
        PageResult<CycleReportRespVO> bean = BeanUtils.toBean(pageResult, CycleReportRespVO.class);
        injectUserNames(bean.getList());
        return CommonResult.success(bean);
    }

    @PostMapping("/create")
    @Operation(summary = "生成周期报表")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:create')")
    public CommonResult<CycleReportRespVO> create(@Valid CycleReportCreateReqVO reqVO) {
        Long id = cycleReportService.create(reqVO);
        CycleReportDO report = cycleReportService.get(id);
        CycleReportRespVO respVO = BeanUtils.toBean(report, CycleReportRespVO.class);
        if (respVO != null) injectUserNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
    }

    @GetMapping("/get")
    @Operation(summary = "获得周期报表详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<CycleReportRespVO> get(@RequestParam("id") Long id) {
        CycleReportDO report = cycleReportService.get(id);
        CycleReportRespVO respVO = BeanUtils.toBean(report, CycleReportRespVO.class);
        if (respVO != null) injectUserNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
    }

    @GetMapping("/export")
    @Operation(summary = "导出周期报表")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:export')")
    public void export(CycleReportPageReqVO reqVO, HttpServletResponse response) throws IOException {
        List<CycleReportDO> list = cycleReportService.getList(reqVO);
        ExcelUtils.write(response, "周期报表.xls", "报表数据", CycleReportRespVO.class,
                BeanUtils.toBean(list, CycleReportRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "获得周期报表图表数据")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<CycleReportChartRespVO> getChart(CycleReportChartReqVO reqVO) {
        return CommonResult.success(cycleReportService.getChart(reqVO));
    }

    private void injectUserNames(List<CycleReportRespVO> list) {
        Set<Long> userIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                Long id = safeParseLong(item.getCreator());
                if (id != null) userIds.add(id);
            }
        }
        if (userIds.isEmpty()) return;
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                AdminUserRespDTO user = userMap.get(safeParseLong(item.getCreator()));
                if (user != null) item.setCreatorName(user.getNickname());
            }
        }
    }

    private Long safeParseLong(String s) {
        if (s == null) return null;
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
