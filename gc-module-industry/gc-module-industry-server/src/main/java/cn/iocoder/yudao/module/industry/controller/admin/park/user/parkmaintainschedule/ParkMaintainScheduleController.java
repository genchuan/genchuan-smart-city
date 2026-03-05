package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule.vo.ParkMaintainSchedulePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule.vo.ParkMaintainScheduleRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule.vo.ParkMaintainScheduleSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmaintainschedule.ParkMaintainScheduleDO;
import cn.iocoder.yudao.module.industry.service.park.user.parkmaintainschedule.ParkMaintainScheduleService;
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


@Tag(name = "漳州停车管理-用户商户域 - 运维排班")
@RestController
@RequestMapping("/industry/park-maintain-schedule")
@Validated
public class ParkMaintainScheduleController {

    @Resource
    private ParkMaintainScheduleService parkMaintainScheduleService;

    @PostMapping("/create")
    @Operation(summary = "创建运维排班")
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-schedule:create')")
    public CommonResult<Long> createParkMaintainSchedule(@Valid @RequestBody ParkMaintainScheduleSaveReqVO createReqVO) {
        return success(parkMaintainScheduleService.createParkMaintainSchedule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新运维排班")
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-schedule:update')")
    public CommonResult<Boolean> updateParkMaintainSchedule(@Valid @RequestBody ParkMaintainScheduleSaveReqVO updateReqVO) {
        parkMaintainScheduleService.updateParkMaintainSchedule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除运维排班")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-schedule:delete')")
    public CommonResult<Boolean> deleteParkMaintainSchedule(@RequestParam("id") Long id) {
        parkMaintainScheduleService.deleteParkMaintainSchedule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得运维排班")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-schedule:query')")
    public CommonResult<ParkMaintainScheduleRespVO> getParkMaintainSchedule(@RequestParam("id") Long id) {
        ParkMaintainScheduleDO parkMaintainSchedule = parkMaintainScheduleService.getParkMaintainSchedule(id);
        return success(BeanUtils.toBean(parkMaintainSchedule, ParkMaintainScheduleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得运维排班分页")
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-schedule:query')")
    public CommonResult<PageResult<ParkMaintainScheduleRespVO>> getParkMaintainSchedulePage(@Valid ParkMaintainSchedulePageReqVO pageReqVO) {
        PageResult<ParkMaintainScheduleDO> pageResult = parkMaintainScheduleService.getParkMaintainSchedulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkMaintainScheduleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出运维排班 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-schedule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkMaintainScheduleExcel(@Valid ParkMaintainSchedulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkMaintainScheduleDO> list = parkMaintainScheduleService.getParkMaintainSchedulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "运维排班.xls", "数据", ParkMaintainScheduleRespVO.class,
                        BeanUtils.toBean(list, ParkMaintainScheduleRespVO.class));
    }

}
