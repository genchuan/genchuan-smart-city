package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletConsumableDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletConsumableDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletconsumable.ToiletConsumableService;
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

@Tag(name = "环境卫生管理 - 公厕耗材配置")
@RestController
@RequestMapping("/envirhealth/toilet-consumable")
@Validated
public class ToiletConsumableController {

    @Resource
    private ToiletConsumableService toiletConsumableService;

    @PostMapping("/create")
    @Operation(summary = "创建公厕耗材配置")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-consumable:create')")
    public CommonResult<Long> createToiletConsumable(@Valid @RequestBody ToiletConsumableSaveReqVO createReqVO) {
        return success(toiletConsumableService.createToiletConsumable(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公厕耗材配置")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-consumable:update')")
    public CommonResult<Boolean> updateToiletConsumable(@Valid @RequestBody ToiletConsumableSaveReqVO updateReqVO) {
        toiletConsumableService.updateToiletConsumable(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公厕耗材配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-consumable:delete')")
    public CommonResult<Boolean> deleteToiletConsumable(@RequestParam("id") Long id) {
        toiletConsumableService.deleteToiletConsumable(id);
        return success(true);
    }

    @DeleteMapping("/delete-batch")
    @Operation(summary = "批量删除公厕耗材配置")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('health:toilet-consumable:delete')")
    public CommonResult<Boolean> deleteToiletConsumableBatch(@RequestBody List<Long> ids) {
        toiletConsumableService.deleteToiletConsumableBatch(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公厕耗材配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-consumable:query')")
    public CommonResult<ToiletConsumableRespVO> getToiletConsumable(@RequestParam("id") Long id) {
        ToiletConsumableDO toiletConsumable = toiletConsumableService.getToiletConsumable(id);
        return success(BeanUtils.toBean(toiletConsumable, ToiletConsumableRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公厕耗材配置分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-consumable:query')")
    public CommonResult<PageResult<ToiletConsumableRespVO>> getToiletConsumablePage(@Valid ToiletConsumablePageReqVO pageReqVO) {
        PageResult<ToiletConsumableDO> pageResult = toiletConsumableService.getToiletConsumablePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ToiletConsumableRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公厕耗材配置 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-consumable:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportToiletConsumableExcel(@Valid ToiletConsumablePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ToiletConsumableDO> list = toiletConsumableService.getToiletConsumablePage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("公厕耗材配置_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "公厕耗材配置.xls", "数据", ToiletConsumableRespVO.class,
                        BeanUtils.toBean(list, ToiletConsumableRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得公厕待补充详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-consumable:query')")
    public CommonResult<PageResult<ToiletConsumableDetailDO>> getToiletConsumableDetailPage(
            @Valid ToiletConsumablePageReqVO pageReqVO) {
        PageResult<ToiletConsumableDetailDO> pageResult =
                toiletConsumableService.getToiletConsumableDetailPage(pageReqVO);

        return success(pageResult);
    }

    @PostMapping("/batch-supply")
    @Operation(summary = "批量补充登记公厕耗材")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-consumable:update')")
    public CommonResult<Boolean> batchSupplyToiletConsumable(
            @Valid @RequestBody ToiletConsumableBatchSupplyReqVO reqVO) {
        toiletConsumableService.batchSupplyToiletConsumable(reqVO);
        return success(true);
    }

    @PostMapping("/supply")
    @Operation(summary = "补充登记公厕耗材")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-consumable:update')")
    public CommonResult<Boolean> supplyToiletConsumable(@Valid @RequestBody ToiletConsumableSupplyReqVO reqVO) {
        toiletConsumableService.supplyToiletConsumable(reqVO);
        return success(true);
    }

    @GetMapping("/chart/pending")
    @Operation(summary = "卡片/圆环图/柱状图统计(待补充)")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-consumable:query')")
    public CommonResult<ToiletConsumablePendingRespVO> getPending() {
        return success(toiletConsumableService.getPending());
    }
}