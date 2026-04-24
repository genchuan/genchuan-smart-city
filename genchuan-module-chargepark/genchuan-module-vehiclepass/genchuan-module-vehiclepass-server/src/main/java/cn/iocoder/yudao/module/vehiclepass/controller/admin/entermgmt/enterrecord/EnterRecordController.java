package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo.*;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.enterrecord.EnterRecordDO;
import cn.iocoder.yudao.module.vehiclepass.service.entermgmt.enterrecord.EnterRecordService;
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


@Tag(name = "管理后台 - 入场记录")
@RestController
@RequestMapping("/vehiclepass/enter-record")
@Validated
public class EnterRecordController {

    @Resource
    private EnterRecordService enterRecordService;

//    @PostMapping("/create")
//    @Operation(summary = "创建入场记录")
//    @PreAuthorize("@ss.hasPermission('enter:record:create')")
//    public CommonResult<Long> createRecord(@Valid @RequestBody EnterRecordSaveReqVO createReqVO) {
//        return success(enterRecordService.createRecord(createReqVO));
//    }

    @PutMapping("/update")
    @Operation(summary = "更新入场记录")
    @PreAuthorize("@ss.hasPermission('enter:record:update')")
    public CommonResult<Boolean> updateRecord(@Valid @RequestBody EnterRecordSaveReqVO updateReqVO) {
        enterRecordService.updateRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除入场记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('enter:record:delete')")
    public CommonResult<Boolean> deleteRecord(@RequestParam("id") Long id) {
        enterRecordService.deleteRecord(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除入场记录")
    @PreAuthorize("@ss.hasPermission('enter:record:delete')")
    public CommonResult<Boolean> deleteRecordList(@RequestParam("ids") List<Long> ids) {
        enterRecordService.deleteRecordListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得入场记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('enter:record:query')")
    public CommonResult<EnterRecordRespVO> getRecord(@RequestParam("id") Long id) {
        EnterRecordDO record = enterRecordService.getRecord(id);
        return success(BeanUtils.toBean(record, EnterRecordRespVO.class));
    }

//    @GetMapping("/page")
//    @Operation(summary = "获得入场记录分页")
//    @PreAuthorize("@ss.hasPermission('enter:record:query')")
//    public CommonResult<PageResult<EnterRecordRespVO>> getRecordPage(@Valid EnterRecordPageReqVO pageReqVO) {
//        PageResult<EnterRecordDO> pageResult = enterRecordService.getRecordPage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, EnterRecordRespVO.class));
//    }

    @GetMapping("/page")
    @Operation(summary = "获得入场记录分页")
    @PreAuthorize("@ss.hasPermission('vehiclepass:enter-record:query')")
    public CommonResult<PageResult<MyEnterRecordRespVO>> page(MyEnterRecordPageReqVO reqVO) {
        PageResult<MyEnterRecordRespVO> result = enterRecordService.getEnterRecordPage(reqVO);
        return success(result);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出入场记录 Excel")
    @PreAuthorize("@ss.hasPermission('enter:record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRecordExcel(@Valid EnterRecordPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EnterRecordDO> list = enterRecordService.getRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "入场记录.xls", "数据", EnterRecordRespVO.class,
                BeanUtils.toBean(list, EnterRecordRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "人工补录入场记录")
    @PreAuthorize("@ss.hasPermission('vehiclepass:enter-record:create')")
    public CommonResult<Boolean> createEnterRecord(@Valid @RequestBody EnterRecordCreateReqVO createReqVO) {
        return CommonResult.success(enterRecordService.createEnterRecord(createReqVO));
    }

    @PutMapping("/correct")
    @Operation(summary = "修正入场记录")
    @PreAuthorize("@ss.hasPermission('vehiclepass:enter-record:update')")
    public CommonResult<Boolean> updateEnterRecord(@Valid @RequestBody EnterRecordUpdateReqVO updateReqVO) {
        return CommonResult.success(enterRecordService.updateEnterRecord(updateReqVO));
    }

    @GetMapping("/chart")
    @Operation(summary = "入场记录统计（折线+柱状+卡片）")
    @PreAuthorize("@ss.hasPermission('vehiclepass:enter-record:chart')")
    public CommonResult<EnterRecordChartRespVO> getChart(EnterRecordChartReqVO reqVO) {
        return CommonResult.success(enterRecordService.getChart(reqVO));
    }

}