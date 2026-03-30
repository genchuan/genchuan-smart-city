package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.garbagetransfer.GarbageTransferService;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.StatisticsRespVO;
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

@Tag(name = "环境卫生管理 - 垃圾转运站")
@RestController
@RequestMapping("/envirhealth/garbage-transfer")
@Validated
public class GarbageTransferController {

    @Resource
    private GarbageTransferService garbageTransferService;

    @PostMapping("/create")
    @Operation(summary = "创建垃圾转运站")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:create')")
    public CommonResult<Long> createGarbageTransfer(@Valid @RequestBody GarbageTransferSaveReqVO createReqVO) {
        return success(garbageTransferService.createGarbageTransfer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新垃圾转运站")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:update')")
    public CommonResult<Boolean> updateGarbageTransfer(@Valid @RequestBody GarbageTransferSaveReqVO updateReqVO) {
        garbageTransferService.updateGarbageTransfer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除垃圾转运站")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:delete')")
    public CommonResult<Boolean> deleteGarbageTransfer(@RequestParam("id") Long id) {
        garbageTransferService.deleteGarbageTransfer(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得垃圾转运站")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:query')")
    public CommonResult<GarbageTransferRespVO> getGarbageTransfer(@RequestParam("id") Long id) {
        GarbageTransferDO garbageTransfer = garbageTransferService.getGarbageTransfer(id);
        return success(BeanUtils.toBean(garbageTransfer, GarbageTransferRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得垃圾转运站分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:query')")
    public CommonResult<PageResult<GarbageTransferRespVO>> getGarbageTransferPage(@Valid GarbageTransferPageReqVO pageReqVO) {
        PageResult<GarbageTransferDO> pageResult = garbageTransferService.getGarbageTransferPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GarbageTransferRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出垃圾转运站 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGarbageTransferExcel(@Valid GarbageTransferPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GarbageTransferDO> list = garbageTransferService.getGarbageTransferPage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("垃圾转运站_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "垃圾转运站.xls", "数据", GarbageTransferRespVO.class,
                        BeanUtils.toBean(list, GarbageTransferRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得垃圾转运站详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:query')")
    public CommonResult<PageResult<GarbageTransferDetailDO>> getPublicToiletDetailPage(
            @Valid GarbageTransferPageReqVO pageReqVO) {
        PageResult<GarbageTransferDetailDO> pageResult =
                garbageTransferService.getGarbageTransferDetailPage(pageReqVO);

        return success(pageResult);
    }

    @GetMapping("/chart/dashboard")
    @Operation(summary = "卡片/圆环图/柱状图/折线图统计(全部)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:query')")
    public CommonResult<GarbageTransferDashboardRespVO> getDashboardStats() {
        return success(garbageTransferService.getDashboardStats());
    }

    @GetMapping("/chart/statistics")
    @Operation(summary = "获取垃圾转运站统计数据(按状态分组)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:query')")
    public CommonResult<StatisticsRespVO> getGarbageTransferStatistics() {
        return success(garbageTransferService.getGarbageTransferStatistics());
    }

    /**
     * 获得垃圾转运站下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得垃圾转运站(下拉框)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-transfer:query')")
    public CommonResult<List<OptionVO>> getGarbageTransferOptions() {
        return success(garbageTransferService.getGarbageTransferOptions());
    }
}