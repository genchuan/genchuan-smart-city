package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentRecordDO;
import cn.iocoder.yudao.module.ordertrade.service.agentpay.AgentRecordService;
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

@Tag(name = "订单交易 - 代付管理 - 代付记录")
@RestController
@RequestMapping("/ordertrade/agent-record")
@Validated
public class AgentRecordController {

    @Resource
    private AgentRecordService agentRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建代付记录")
    public CommonResult<Long> createAgentRecord(@Valid @RequestBody AgentRecordSaveReqVO createReqVO) {
        return success(agentRecordService.createAgentRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新代付记录")
    public CommonResult<Boolean> updateAgentRecord(@Valid @RequestBody AgentRecordSaveReqVO updateReqVO) {
        agentRecordService.updateAgentRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除代付记录")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteAgentRecord(@RequestParam("id") Long id) {
        agentRecordService.deleteAgentRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得代付记录详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<AgentRecordRespVO> getAgentRecord(@RequestParam("id") Long id) {
        AgentRecordDO obj = agentRecordService.getAgentRecord(id);
        return success(BeanUtils.toBean(obj, AgentRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得代付记录分页列表")
    public CommonResult<PageResult<AgentRecordRespVO>> getAgentRecordPage(@Valid AgentRecordPageReqVO pageReqVO) {
       /* PageResult<AgentRecordDO> pageResult = agentRecordService.getAgentRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AgentRecordRespVO.class));*/

        return success(agentRecordService.getAgentRecordPage(pageReqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出代付记录 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAgentRecordExcel(@Valid AgentRecordPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AgentRecordRespVO> list = agentRecordService.getAgentRecordPage(pageReqVO).getList();
        ExcelUtils.write(response, "代付记录.xls", "数据", AgentRecordRespVO.class,
                BeanUtils.toBean(list, AgentRecordRespVO.class));
    }

    @PostMapping("/check")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "核查代付记录")
    public CommonResult<Boolean> checkAgentRecord(@Valid @RequestBody IdReqVO reqVO) {
        agentRecordService.checkAgentRecord(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得代付记录统计图表数据")
    public CommonResult<AgentRecordChartRespVO> getAgentRecordChart(@Valid AgentRecordChartReqVO chartReqVO) {
        return success(agentRecordService.getAgentRecordChart(chartReqVO));
    }
}
