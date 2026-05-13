package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkPayReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkConfirmReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkCancelReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.endpark.EndParkDO;
import cn.iocoder.yudao.module.vehiclepass.service.siteinput.endpark.EndParkService;
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

@Tag(name = "管理后台 - 结束停车")
@RestController
@RequestMapping("/vehiclepass/end-park")
@Validated
public class EndParkController {

    @Resource
    private EndParkService parkService;

    @PostMapping("/create")
    @Operation(summary = "创建结束停车")
    @PreAuthorize("@ss.hasPermission('end:park:create')")
    public CommonResult<Long> createPark(@Valid @RequestBody EndParkSaveReqVO createReqVO) {
        return success(parkService.createPark(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结束停车")
    @PreAuthorize("@ss.hasPermission('end:park:update')")
    public CommonResult<Boolean> updatePark(@Valid @RequestBody EndParkSaveReqVO updateReqVO) {
        parkService.updatePark(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结束停车")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('end:park:delete')")
    public CommonResult<Boolean> deletePark(@RequestParam("id") Long id) {
        parkService.deletePark(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除结束停车")
    @PreAuthorize("@ss.hasPermission('end:park:delete')")
    public CommonResult<Boolean> deleteParkList(@RequestParam("ids") List<Long> ids) {
        parkService.deleteParkListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结束停车")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('end:park:query')")
    public CommonResult<EndParkRespVO> getPark(@RequestParam("id") Long id) {
        EndParkDO park = parkService.getPark(id);
        return success(BeanUtils.toBean(park, EndParkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结束停车分页")
    @PreAuthorize("@ss.hasPermission('vehiclepass:end-park:query')")
    public CommonResult<PageResult<EndParkRespVO>> getParkPage(@Valid EndParkPageReqVO pageReqVO) {
        return success(parkService.getParkPageWithJoin(pageReqVO));
    }

    @PutMapping("/pay")
    @Operation(summary = "支付结束停车")
    @PreAuthorize("@ss.hasPermission('vehiclepass:end-park:pay')")
    public CommonResult<Boolean> pay(@Valid @RequestBody EndParkPayReqVO payReqVO) {
        parkService.pay(payReqVO);
        return success(true);
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认结束停车")
    @PreAuthorize("@ss.hasPermission('vehiclepass:end-park:confirm')")
    public CommonResult<Boolean> confirm(@Valid @RequestBody EndParkConfirmReqVO confirmReqVO) {
        parkService.confirm(confirmReqVO);
        return success(true);
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消结束停车")
    @PreAuthorize("@ss.hasPermission('vehiclepass:end-park:cancel')")
    public CommonResult<Boolean> cancel(@Valid @RequestBody EndParkCancelReqVO cancelReqVO) {
        parkService.cancel(cancelReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取结束停车统计")
    @PreAuthorize("@ss.hasPermission('vehiclepass:end-park:chart')")
    public CommonResult<EndParkChartRespVO> getChart(@Valid EndParkChartReqVO chartReqVO) {
        return success(parkService.getChart(chartReqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出结束停车 Excel")
    @PreAuthorize("@ss.hasPermission('end:park:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkExcel(@Valid EndParkPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EndParkDO> list = parkService.getParkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "结束停车.xls", "数据", EndParkRespVO.class,
                BeanUtils.toBean(list, EndParkRespVO.class));
    }

}