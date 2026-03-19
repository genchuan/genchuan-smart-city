package cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.UrbanVillageDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.UrbanVillagePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.UrbanVillageRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.UrbanVillageSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.UrbanVillageDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.detail.UrbanVillageDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.urbanvillage.UrbanVillageService;
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
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "环境卫生管理 - 城中村")
@RestController
@RequestMapping("/envirhealth/urban-village")
@Validated
public class UrbanVillageController {

    @Resource
    private UrbanVillageService urbanVillageService;

    @PostMapping("/create")
    @Operation(summary = "创建城中村")
    @PreAuthorize("@ss.hasPermission('envirhealth:urban-village:create')")
    public CommonResult<Long> createUrbanVillage(@Valid @RequestBody UrbanVillageSaveReqVO createReqVO) {
        return success(urbanVillageService.createUrbanVillage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新城中村")
    @PreAuthorize("@ss.hasPermission('envirhealth:urban-village:update')")
    public CommonResult<Boolean> updateUrbanVillage(@Valid @RequestBody UrbanVillageSaveReqVO updateReqVO) {
        urbanVillageService.updateUrbanVillage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除城中村")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:urban-village:delete')")
    public CommonResult<Boolean> deleteUrbanVillage(@RequestParam("id") Long id) {
        urbanVillageService.deleteUrbanVillage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得城中村")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:urban-village:query')")
    public CommonResult<UrbanVillageRespVO> getUrbanVillage(@RequestParam("id") Long id) {
        UrbanVillageDO urbanVillage = urbanVillageService.getUrbanVillage(id);
        return success(BeanUtils.toBean(urbanVillage, UrbanVillageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得城中村分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:urban-village:query')")
    public CommonResult<PageResult<UrbanVillageRespVO>> getUrbanVillagePage(@Valid UrbanVillagePageReqVO pageReqVO) {
        PageResult<UrbanVillageDO> pageResult = urbanVillageService.getUrbanVillagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UrbanVillageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出城中村 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:urban-village:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUrbanVillageExcel(@Valid UrbanVillagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UrbanVillageDO> list = urbanVillageService.getUrbanVillagePage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("城中村_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "城中村.xls", "数据", UrbanVillageRespVO.class,
                        BeanUtils.toBean(list, UrbanVillageRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得城中村详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:urban-village:query')")
    public CommonResult<PageResult<UrbanVillageDetailDO>> getPublicToiletDetailPage(
            @Valid UrbanVillagePageReqVO pageReqVO) {
        PageResult<UrbanVillageDetailDO> pageResult =
                urbanVillageService.getUrbanVillageDetailPage(pageReqVO);

        return success(pageResult);
    }

    @GetMapping("/chart/dashboard")
    @Operation(summary = "卡片/圆环图/柱状图/统计(全部)")
    @PreAuthorize("@ss.hasPermission('envirhealth:urban-village:query')")
    public CommonResult<UrbanVillageDashboardVO> getUrbanVillageDashboard() {
        return CommonResult.success(urbanVillageService.getUrbanVillageDashboard());
    }
}