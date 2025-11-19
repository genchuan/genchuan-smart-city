package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal.vo.EvalAppealPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal.vo.EvalAppealRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal.vo.EvalAppealSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalappeal.EvalAppealDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.review.evalappeal.EvalAppealService;

@Tag(name = "管理后台 - 申诉管理")
@RestController
@RequestMapping("/datacenter/eval-appeal")
@Validated
public class EvalAppealController {

    @Resource
    private EvalAppealService evalAppealService;

    @PostMapping("/create")
    @Operation(summary = "创建申诉管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-appeal:create')")
    public CommonResult<Long> createEvalAppeal(@Valid @RequestBody EvalAppealSaveReqVO createReqVO) {
        return success(evalAppealService.createEvalAppeal(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新申诉管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-appeal:update')")
    public CommonResult<Boolean> updateEvalAppeal(@Valid @RequestBody EvalAppealSaveReqVO updateReqVO) {
        evalAppealService.updateEvalAppeal(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除申诉管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-appeal:delete')")
    public CommonResult<Boolean> deleteEvalAppeal(@RequestParam("id") Long id) {
        evalAppealService.deleteEvalAppeal(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得申诉管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-appeal:query')")
    public CommonResult<EvalAppealRespVO> getEvalAppeal(@RequestParam("id") Long id) {
        EvalAppealDO evalAppeal = evalAppealService.getEvalAppeal(id);
        return success(BeanUtils.toBean(evalAppeal, EvalAppealRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得申诉管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-appeal:query')")
    public CommonResult<PageResult<EvalAppealRespVO>> getEvalAppealPage(@Valid EvalAppealPageReqVO pageReqVO) {
        PageResult<EvalAppealDO> pageResult = evalAppealService.getEvalAppealPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalAppealRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出申诉管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-appeal:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalAppealExcel(@Valid EvalAppealPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalAppealDO> list = evalAppealService.getEvalAppealPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "申诉管理.xls", "数据", EvalAppealRespVO.class,
                        BeanUtils.toBean(list, EvalAppealRespVO.class));
    }

}