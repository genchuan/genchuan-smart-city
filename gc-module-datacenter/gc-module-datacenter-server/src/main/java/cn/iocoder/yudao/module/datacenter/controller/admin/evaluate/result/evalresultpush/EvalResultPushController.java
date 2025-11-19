package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush.vo.EvalResultPushPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush.vo.EvalResultPushRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush.vo.EvalResultPushSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultpush.EvalResultPushDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultpush.EvalResultPushService;

@Tag(name = "管理后台 - 结果关联推送")
@RestController
@RequestMapping("/datacenter/eval-result-push")
@Validated
public class EvalResultPushController {

    @Resource
    private EvalResultPushService evalResultPushService;

    @PostMapping("/create")
    @Operation(summary = "创建结果关联推送")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-push:create')")
    public CommonResult<Long> createEvalResultPush(@Valid @RequestBody EvalResultPushSaveReqVO createReqVO) {
        return success(evalResultPushService.createEvalResultPush(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结果关联推送")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-push:update')")
    public CommonResult<Boolean> updateEvalResultPush(@Valid @RequestBody EvalResultPushSaveReqVO updateReqVO) {
        evalResultPushService.updateEvalResultPush(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结果关联推送")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-push:delete')")
    public CommonResult<Boolean> deleteEvalResultPush(@RequestParam("id") Long id) {
        evalResultPushService.deleteEvalResultPush(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结果关联推送")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-push:query')")
    public CommonResult<EvalResultPushRespVO> getEvalResultPush(@RequestParam("id") Long id) {
        EvalResultPushDO evalResultPush = evalResultPushService.getEvalResultPush(id);
        return success(BeanUtils.toBean(evalResultPush, EvalResultPushRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结果关联推送分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-push:query')")
    public CommonResult<PageResult<EvalResultPushRespVO>> getEvalResultPushPage(@Valid EvalResultPushPageReqVO pageReqVO) {
        PageResult<EvalResultPushDO> pageResult = evalResultPushService.getEvalResultPushPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalResultPushRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出结果关联推送 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-push:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalResultPushExcel(@Valid EvalResultPushPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalResultPushDO> list = evalResultPushService.getEvalResultPushPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "结果关联推送.xls", "数据", EvalResultPushRespVO.class,
                        BeanUtils.toBean(list, EvalResultPushRespVO.class));
    }

}