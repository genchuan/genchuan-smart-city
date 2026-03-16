package cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive.vo.RoadArchivePageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive.vo.RoadArchivePageRespVO;
import cn.iocoder.yudao.module.facility.service.road.roadarchive.RoadArchiveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 道路档案")
@RestController
@RequestMapping("/facility/road-archive")
@Validated
public class RoadArchiveController {

    @Resource
    private RoadArchiveService roadArchiveService;

    @GetMapping("/page")
    @Operation(summary = "获得道路归档分页")
    @PreAuthorize("@ss.hasPermission('facility:road-archive:query')")
    public CommonResult<PageResult<RoadArchivePageRespVO>> getArchivePage(@Valid RoadArchivePageReqVO pageReqVO) {
        PageResult<RoadArchivePageRespVO> pageResult = roadArchiveService.getArchivePage(pageReqVO);
        return success(pageResult);
    }
    @GetMapping("/export-excel")
    @Operation(summary = "导出归档 Excel")
    @PreAuthorize("@ss.hasPermission('facility:road-archive:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRoadArchiveExcel(@Valid RoadArchivePageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        // 0. 配置
        String inputFileName = "道路设施处置归档台账_";

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoadArchivePageRespVO> list = roadArchiveService.getArchivePage(pageReqVO).getList();

        // 1、强制设置响应头，确保浏览器触发下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        // 2、动态生成文件名，带上当前日期
        String dateStr = java.time.LocalDate.now().toString(); // 例如 "2026-03-10"
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8","");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        // 3、调用 ExcelUtils.write（保持原方法不改）
        ExcelUtils.write(response, "归档.xls", "数据", RoadArchivePageRespVO.class,
                BeanUtils.toBean(list, RoadArchivePageRespVO.class));
    }


//    @PostMapping("/create")
//    @Operation(summary = "创建工单")
//    @PreAuthorize("@ss.hasPermission('facility:work-order:create')")
//    public CommonResult<Long> createWorkOrder(@Valid @RequestBody WorkOrderSaveReqVO createReqVO) {
//        return success(workOrderService.createWorkOrder(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新工单")
//    @PreAuthorize("@ss.hasPermission('facility:work-order:update')")
//    public CommonResult<Boolean> updateWorkOrder(@Valid @RequestBody WorkOrderSaveReqVO updateReqVO) {
//        workOrderService.updateWorkOrder(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除工单")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('facility:work-order:delete')")
//    public CommonResult<Boolean> deleteWorkOrder(@RequestParam("id") Long id) {
//        workOrderService.deleteWorkOrder(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得工单")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('facility:work-order:query')")
//    public CommonResult<WorkOrderRespVO> getWorkOrder(@RequestParam("id") Long id) {
//        WorkOrderDO workOrder = workOrderService.getWorkOrder(id);
//        return success(BeanUtils.toBean(workOrder, WorkOrderRespVO.class));
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得工单分页")
//    @PreAuthorize("@ss.hasPermission('facility:work-order:query')")
//    public CommonResult<PageResult<WorkOrderRespVO>> getWorkOrderPage(@Valid WorkOrderPageReqVO pageReqVO) {
//        PageResult<WorkOrderDO> pageResult = workOrderService.getWorkOrderPage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, WorkOrderRespVO.class));
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出工单 Excel")
//    @PreAuthorize("@ss.hasPermission('facility:work-order:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportWorkOrderExcel(@Valid WorkOrderPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<WorkOrderDO> list = workOrderService.getWorkOrderPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "工单.xls", "数据", WorkOrderRespVO.class,
//                        BeanUtils.toBean(list, WorkOrderRespVO.class));
//    }

}
