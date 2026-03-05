package cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrecord;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrecord.vo.ParkPointsRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrecord.vo.ParkPointsRecordRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrecord.vo.ParkPointsRecordSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpointsrecord.ParkPointsRecordDO;
import cn.iocoder.yudao.module.industry.service.park.marketing.parkpointsrecord.ParkPointsRecordService;
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


@Tag(name = "管理后台 - 积分变动记录")
@RestController
@RequestMapping("/industry/park-points-record")
@Validated
public class ParkPointsRecordController {

    @Resource
    private ParkPointsRecordService parkPointsRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建积分变动记录")
    @PreAuthorize("@ss.hasPermission('industry:park-points-record:create')")
    public CommonResult<Long> createParkPointsRecord(@Valid @RequestBody ParkPointsRecordSaveReqVO createReqVO) {
        return success(parkPointsRecordService.createParkPointsRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新积分变动记录")
    @PreAuthorize("@ss.hasPermission('industry:park-points-record:update')")
    public CommonResult<Boolean> updateParkPointsRecord(@Valid @RequestBody ParkPointsRecordSaveReqVO updateReqVO) {
        parkPointsRecordService.updateParkPointsRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除积分变动记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-points-record:delete')")
    public CommonResult<Boolean> deleteParkPointsRecord(@RequestParam("id") Long id) {
        parkPointsRecordService.deleteParkPointsRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得积分变动记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-points-record:query')")
    public CommonResult<ParkPointsRecordRespVO> getParkPointsRecord(@RequestParam("id") Long id) {
        ParkPointsRecordDO parkPointsRecord = parkPointsRecordService.getParkPointsRecord(id);
        return success(BeanUtils.toBean(parkPointsRecord, ParkPointsRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得积分变动记录分页")
    @PreAuthorize("@ss.hasPermission('industry:park-points-record:query')")
    public CommonResult<PageResult<ParkPointsRecordRespVO>> getParkPointsRecordPage(@Valid ParkPointsRecordPageReqVO pageReqVO) {
        PageResult<ParkPointsRecordDO> pageResult = parkPointsRecordService.getParkPointsRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkPointsRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出积分变动记录 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-points-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkPointsRecordExcel(@Valid ParkPointsRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkPointsRecordDO> list = parkPointsRecordService.getParkPointsRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "积分变动记录.xls", "数据", ParkPointsRecordRespVO.class,
                        BeanUtils.toBean(list, ParkPointsRecordRespVO.class));
    }

}
