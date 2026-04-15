package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.ArrearRecordDO;
import cn.iocoder.yudao.module.ordertrade.service.debtcollect.ArrearRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 欠费记录
 *
 * @author genchuan
 */
@Tag(name = "管理后台 -联合追缴 - 欠费记录")
@RestController
@RequestMapping("/ordertrade/arrear-record")
@Validated
public class ArrearRecordController {

    @Resource
    private ArrearRecordService arrearRecordService;

    // ==================== ① 标准CRUD ====================

    /*@PostMapping("/create")
    @Operation(summary = "创建欠费记录")
    public CommonResult<Long> createArrearRecord(@Valid @RequestBody ArrearRecordSaveReqVO createReqVO) {
        return success(arrearRecordService.createArrearRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新欠费记录")
    public CommonResult<Boolean> updateArrearRecord(@Valid @RequestBody ArrearRecordSaveReqVO updateReqVO) {
        arrearRecordService.updateArrearRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除欠费记录")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteArrearRecord(@RequestParam("id") Long id) {
        arrearRecordService.deleteArrearRecord(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除欠费记录")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteArrearRecordList(@RequestParam("ids") List<Long> ids) {
        arrearRecordService.deleteArrearRecordListByIds(ids);
        return success(true);
    }*/

    @GetMapping("/get")
    @Operation(summary = "获得欠费记录详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<ArrearRecordRespVO> getArrearRecord(@RequestParam("id") Long id) {
        ArrearRecordDO obj = arrearRecordService.getArrearRecord(id);
        return success(BeanUtils.toBean(obj, ArrearRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得欠费记录分页列表")
    public CommonResult<PageResult<ArrearRecordRespVO>> getArrearRecordPage(@Valid ArrearRecordPageReqVO pageReqVO) {
        PageResult<ArrearRecordDO> pageResult = arrearRecordService.getArrearRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ArrearRecordRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出欠费记录 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportArrearRecordExcel(@Valid ArrearRecordPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ArrearRecordDO> list = arrearRecordService.getArrearRecordPage(pageReqVO).getList();
        ExcelUtils.write(response, "欠费记录.xls", "数据", ArrearRecordRespVO.class,
                BeanUtils.toBean(list, ArrearRecordRespVO.class));
    }

   /* @GetMapping("/batch-export")
    @Operation(summary = "批量导出欠费记录 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportArrearRecordExcel(@Valid ArrearRecordPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ArrearRecordDO> list = arrearRecordService.getArrearRecordPage(pageReqVO).getList();
        ExcelUtils.write(response, "欠费记录批量导出.xls", "数据", ArrearRecordRespVO.class,
                BeanUtils.toBean(list, ArrearRecordRespVO.class));
    }*/

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/remind")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "催缴")
    public CommonResult<Boolean> remindArrearRecord(@Valid @RequestBody IdReqVO reqVO) {
        arrearRecordService.remindArrearRecord(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得欠费记录统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<ArrearRecordChartRespVO> getArrearRecordChart(@Valid ArrearRecordChartReqVO chartReqVO) {
        return success(arrearRecordService.getArrearRecordChart(chartReqVO));
    }
}
