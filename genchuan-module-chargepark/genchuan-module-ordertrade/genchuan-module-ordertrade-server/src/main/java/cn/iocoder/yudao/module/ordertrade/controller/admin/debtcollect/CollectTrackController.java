package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.CollectTrackDO;
import cn.iocoder.yudao.module.ordertrade.service.debtcollect.CollectTrackService;
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
 * 管理后台 - 追缴跟踪
 *
 * @author genchuan
 */
@Tag(name = "订单交易 -联合追缴- 追缴跟踪")
@RestController
@RequestMapping("/ordertrade/collect-track")
@Validated
public class CollectTrackController {

    @Resource
    private CollectTrackService collectTrackService;

    // ==================== ① 标准CRUD ====================
/*

    @PostMapping("/create")
    @Operation(summary = "创建追缴跟踪")
    public CommonResult<Long> createCollectTrack(@Valid @RequestBody CollectTrackSaveReqVO createReqVO) {
        return success(collectTrackService.createCollectTrack(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新追缴跟踪")
    public CommonResult<Boolean> updateCollectTrack(@Valid @RequestBody CollectTrackSaveReqVO updateReqVO) {
        collectTrackService.updateCollectTrack(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除追缴跟踪")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteCollectTrack(@RequestParam("id") Long id) {
        collectTrackService.deleteCollectTrack(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除追缴跟踪")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteCollectTrackList(@RequestParam("ids") List<Long> ids) {
        collectTrackService.deleteCollectTrackListByIds(ids);
        return success(true);
    }
*/

    @GetMapping("/get")
    @Operation(summary = "获得追缴跟踪详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<CollectTrackRespVO> getCollectTrack(@RequestParam("id") Long id) {
        CollectTrackDO obj = collectTrackService.getCollectTrack(id);
        return success(BeanUtils.toBean(obj, CollectTrackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得追缴跟踪分页列表")
    public CommonResult<PageResult<CollectTrackRespVO>> getCollectTrackPage(@Valid CollectTrackPageReqVO pageReqVO) {
        PageResult<CollectTrackDO> pageResult = collectTrackService.getCollectTrackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CollectTrackRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出追缴跟踪 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCollectTrackExcel(@Valid CollectTrackPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CollectTrackDO> list = collectTrackService.getCollectTrackPage(pageReqVO).getList();
        ExcelUtils.write(response, "追缴跟踪.xls", "数据", CollectTrackRespVO.class,
                BeanUtils.toBean(list, CollectTrackRespVO.class));
    }

    /*@GetMapping("/batch-export")
    @Operation(summary = "批量导出追缴跟踪 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportCollectTrackExcel(@Valid CollectTrackPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CollectTrackDO> list = collectTrackService.getCollectTrackPage(pageReqVO).getList();
        ExcelUtils.write(response, "追缴跟踪批量导出.xls", "数据", CollectTrackRespVO.class,
                BeanUtils.toBean(list, CollectTrackRespVO.class));
    }*/

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/push")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "推送追缴")
    public CommonResult<Boolean> pushCollectTrack(@Valid @RequestBody IdReqVO reqVO) {
        collectTrackService.pushCollectTrack(reqVO);
        return success(true);
    }
    @PutMapping("/update-progress")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "更新追缴进度")
    public CommonResult<Boolean> updateProgressCollectTrack(@Valid @RequestBody IdReqVO reqVO) {
        collectTrackService.updateProgressCollectTrack(reqVO);
        return success(true);
    }
    @PutMapping("/transfer")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "转派")
    public CommonResult<Boolean> transferCollectTrack(@Valid @RequestBody CollectTrackTransferReqVO reqVO) {
        collectTrackService.transferCollectTrack(reqVO);
        return success(true);
    }
    @PutMapping("/archive")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "归档")
    public CommonResult<Boolean> archiveCollectTrack(@Valid @RequestBody IdReqVO reqVO) {
        collectTrackService.archiveCollectTrack(reqVO);
        return success(true);
    }
    @PostMapping("/batch-push")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "批量推送")
    public CommonResult<Boolean> batchPushCollectTrack(@Valid @RequestBody IdsReqVO reqVO) {
        collectTrackService.batchPushCollectTrack(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得追缴跟踪统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<CollectTrackChartRespVO> getCollectTrackChart(@Valid CollectTrackChartReqVO chartReqVO) {
        return success(collectTrackService.getCollectTrackChart(chartReqVO));
    }
}
