package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkrealtime;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkrealtime.vo.ParkRealTimePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkrealtime.vo.ParkRealTimeRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkrealtime.vo.ParkRealTimeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkrealtime.ParkRealTimeDO;
import cn.iocoder.yudao.module.industry.service.park.pay.parkrealtime.ParkRealTimeService;
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



@Tag(name = "暂时不用-停车管理-泊位实时状态")
@RestController
@RequestMapping("/industry/park-real-time")
@Validated
public class ParkRealTimeController {

    @Resource
    private ParkRealTimeService parkRealTimeService;

    @PostMapping("/create")
    @Operation(summary = "创建停车泊位实时状态")
    @PreAuthorize("@ss.hasPermission('industry:park-real-time:create')")
    public CommonResult<Long> createParkRealTime(@Valid @RequestBody ParkRealTimeSaveReqVO createReqVO) {
        return success(parkRealTimeService.createParkRealTime(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新停车泊位实时状态")
    @PreAuthorize("@ss.hasPermission('industry:park-real-time:update')")
    public CommonResult<Boolean> updateParkRealTime(@Valid @RequestBody ParkRealTimeSaveReqVO updateReqVO) {
        parkRealTimeService.updateParkRealTime(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除停车泊位实时状态")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-real-time:delete')")
    public CommonResult<Boolean> deleteParkRealTime(@RequestParam("id") Long id) {
        parkRealTimeService.deleteParkRealTime(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车泊位实时状态")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-real-time:query')")
    public CommonResult<ParkRealTimeRespVO> getParkRealTime(@RequestParam("id") Long id) {
        ParkRealTimeDO parkRealTime = parkRealTimeService.getParkRealTime(id);
        return success(BeanUtils.toBean(parkRealTime, ParkRealTimeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得停车泊位实时状态分页")
    @PreAuthorize("@ss.hasPermission('industry:park-real-time:query')")
    public CommonResult<PageResult<ParkRealTimeRespVO>> getParkRealTimePage(@Valid ParkRealTimePageReqVO pageReqVO) {
        PageResult<ParkRealTimeDO> pageResult = parkRealTimeService.getParkRealTimePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkRealTimeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出停车泊位实时状态 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-real-time:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkRealTimeExcel(@Valid ParkRealTimePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkRealTimeDO> list = parkRealTimeService.getParkRealTimePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "停车泊位实时状态.xls", "数据", ParkRealTimeRespVO.class,
                        BeanUtils.toBean(list, ParkRealTimeRespVO.class));
    }

}
