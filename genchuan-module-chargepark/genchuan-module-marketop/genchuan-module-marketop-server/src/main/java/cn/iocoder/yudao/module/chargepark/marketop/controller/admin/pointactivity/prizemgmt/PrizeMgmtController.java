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
import org.springframework.web.multipart.MultipartFile;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.util.*;

@Tag(name = "管理后台 - 奖品管理")
@RestController
@RequestMapping("/marketop/prize-mgmt")
public class PrizeMgmtController {

    @Resource
    private PrizeMgmtService prizeMgmtService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "获得奖品管理分页")
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:query')")
    public CommonResult<PageResult<PrizeMgmtRespVO>> getPage(PrizeMgmtPageReqVO reqVO) {
        PageResult<PrizeMgmtDO> pageResult = prizeMgmtService.getPage(reqVO);
        PageResult<PrizeMgmtRespVO> bean = BeanUtils.toBean(pageResult, PrizeMgmtRespVO.class);
        injectUserNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得奖品详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:query')")
    public CommonResult<PrizeMgmtRespVO> get(@RequestParam("id") Long id) {
        PrizeMgmtDO prizeMgmt = prizeMgmtService.get(id);
        PrizeMgmtRespVO respVO = BeanUtils.toBean(prizeMgmt, PrizeMgmtRespVO.class);
        if (respVO != null) injectUserNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
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
    public CommonResult<Boolean> enable(@Valid @RequestBody PrizeMgmtIdReqVO reqVO) {
        prizeMgmtService.enable(reqVO.getId());
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用奖品")
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:update')")
    public CommonResult<Boolean> disable(@Valid @RequestBody PrizeMgmtIdReqVO reqVO) {
        prizeMgmtService.disable(reqVO.getId());
        return CommonResult.success(true);
    }

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入奖品模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        List<PrizeMgmtImportExcelVO> list = Arrays.asList(
                PrizeMgmtImportExcelVO.builder().name("一等奖").type("1").stock(100).warnThreshold(10).activityId(1L).description("一等奖奖品").build(),
                PrizeMgmtImportExcelVO.builder().name("二等奖").type("2").stock(500).warnThreshold(50).activityId(1L).description("二等奖奖品").build()
        );
        ExcelUtils.write(response, "奖品管理导入模板.xls", "奖品列表", PrizeMgmtImportExcelVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入奖品")
    @PreAuthorize("@ss.hasPermission('marketop:prize-mgmt:import')")
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        List<PrizeMgmtImportExcelVO> list = ExcelUtils.read(file, PrizeMgmtImportExcelVO.class);
        prizeMgmtService.importPrizeMgmtList(list);
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
    public CommonResult<PrizeMgmtChartRespVO> getChart(PrizeMgmtChartReqVO reqVO) {
        return CommonResult.success(prizeMgmtService.getChart(reqVO));
    }

    private void injectUserNames(List<PrizeMgmtRespVO> list) {
        Set<Long> userIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                userIds.add(Long.valueOf(item.getCreator()));
            }
        }
        if (userIds.isEmpty()) return;
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                AdminUserRespDTO user = userMap.get(Long.valueOf(item.getCreator()));
                if (user != null) item.setCreatorName(user.getNickname());
            }
        }
    }

}
