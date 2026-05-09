package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttrack.InspectTrackDO;
import cn.iocoder.yudao.module.inspectop.service.inspecttrack.InspectTrackService;

@Tag(name = "巡查巡检 - 巡检轨迹")
@RestController
@RequestMapping("/inspectop/inspect-track")
@Validated
public class InspectTrackController {

    @Resource
    private InspectTrackService inspectTrackService;

    @PostMapping("/create")
    @Operation(summary = "创建巡检轨迹")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:create')")
    public CommonResult<Long> createInspectTrack(@Valid @RequestBody InspectTrackSaveReqVO createReqVO) {
        return success(inspectTrackService.createInspectTrack(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡检轨迹")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:update')")
    public CommonResult<Boolean> updateInspectTrack(@Valid @RequestBody InspectTrackSaveReqVO updateReqVO) {
        inspectTrackService.updateInspectTrack(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡检轨迹")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:delete')")
    public CommonResult<Boolean> deleteInspectTrack(@RequestParam("id") Long id) {
        inspectTrackService.deleteInspectTrack(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除巡检轨迹")
                @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:delete')")
    public CommonResult<Boolean> deleteInspectTrackList(@RequestParam("ids") List<Long> ids) {
        inspectTrackService.deleteInspectTrackListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡检轨迹")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:query')")
    public CommonResult<InspectTrackRespVO> getInspectTrack(@RequestParam("id") Long id) {
        InspectTrackDO inspectTrack = inspectTrackService.getInspectTrack(id);
        return success(BeanUtils.toBean(inspectTrack, InspectTrackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡检轨迹分页")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:query')")
    public CommonResult<PageResult<InspectTrackRespVO>> getInspectTrackPage(@Valid InspectTrackPageReqVO pageReqVO) {
        PageResult<InspectTrackRespVO> pageResult = inspectTrackService.getInspectTrackPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/replay")
    @Operation(summary = "回放")
    @Parameter(name = "id", description = "轨迹ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:replay')")
    public CommonResult<InspectTrackReplayRespVO> getInspectTrackReplay(@RequestParam("id") Long id) {
        InspectTrackReplayRespVO replayRespVO = inspectTrackService.getInspectTrackReplay(id);
        return success(replayRespVO);
    }

    @PutMapping("/check")
    @Operation(summary = "核查")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:check')")
    public CommonResult<Boolean> checkInspectTrack(@Valid @RequestBody InspectTrackCheckReqVO checkReqVO) {
        Boolean result = inspectTrackService.checkInspectTrack(checkReqVO);
        return success(result);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得巡检轨迹图表统计数据")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:chart')")
    public CommonResult<InspectTrackChartRespVO> getInspectTrackChart(@Valid InspectTrackChartReqVO reqVO) {
        InspectTrackChartRespVO chartData = inspectTrackService.getInspectTrackChart(reqVO);
        return success(chartData);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检轨迹 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectTrackExcel(@Valid InspectTrackPageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        // 1. 获取数据列表
        List<InspectTrackRespVO> list = inspectTrackService.getInspectTrackPage(pageReqVO).getList();

        // 【修改】2. 对VO列表中的字典值进行转换（数字 -> 中文）
        // 现在需要转换两个字段：status 和 checkStatus
        convertDictValues(list);

        // 3. 导出 Excel
        ExcelUtils.write(response, "巡检轨迹.xls", "数据", InspectTrackRespVO.class, list);
    }

    /**
     * 【修改】转换字典值为中文显示
     * 此方法会修改传入的 voList 中每个对象的 status 和 checkStatusStr 字段。
     * @param voList 巡检轨迹响应VO列表
     */
    private void convertDictValues(List<InspectTrackRespVO> voList) {
        if (voList == null || voList.isEmpty()) {
            return;
        }
        for (InspectTrackRespVO vo : voList) {
            // 转换轨迹状态（String -> String）
            vo.setStatus(convertTrackStatus(vo.getStatus()));
            // 【新增】转换核查状态（Integer -> String）
            vo.setCheckStatus(convertCheckStatus(vo.getCheckStatus()));
        }
    }

    /**
     * 【新增】转换核查状态字典值
     * 根据映射：0-未核查 1-已核查 2-核查中
     * @param statusCode 状态编码（例如 0, 1, 2）
     * @return 对应的中文状态描述
     */
    private String convertCheckStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode) {
            case "0":
                return "未核查";
            case "1":
                return "已核查";
            case "2":
                return "核查中";
            default:
                // 如果遇到未知编码，返回原编码的字符串形式以便排查
                return statusCode;
        }
    }

    /**
     * 转换巡检轨迹状态字典值
     * 根据映射：1-正常，2-异常
     * @param statusCode 状态编码（例如 "1", "2"）
     * @return 对应的中文状态描述
     */
    private String convertTrackStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode.trim()) {
            case "1":
                return "正常";
            case "2":
                return "异常";
            default:
                return statusCode;
        }
    }

}