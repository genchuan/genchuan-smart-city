package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalquestionnaire;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalquestionnaire.vo.EvalQuestionnairePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalquestionnaire.vo.EvalQuestionnaireRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalquestionnaire.vo.EvalQuestionnaireSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalquestionnaire.EvalQuestionnaireDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.data.evalquestionnaire.EvalQuestionnaireService;

@Tag(name = "管理后台 - 问卷调查管理")
@RestController
@RequestMapping("/datacenter/eval-questionnaire")
@Validated
public class EvalQuestionnaireController {

    @Resource
    private EvalQuestionnaireService evalQuestionnaireService;

    @PostMapping("/create")
    @Operation(summary = "创建问卷调查管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-questionnaire:create')")
    public CommonResult<Long> createEvalQuestionnaire(@Valid @RequestBody EvalQuestionnaireSaveReqVO createReqVO) {
        return success(evalQuestionnaireService.createEvalQuestionnaire(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新问卷调查管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-questionnaire:update')")
    public CommonResult<Boolean> updateEvalQuestionnaire(@Valid @RequestBody EvalQuestionnaireSaveReqVO updateReqVO) {
        evalQuestionnaireService.updateEvalQuestionnaire(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除问卷调查管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-questionnaire:delete')")
    public CommonResult<Boolean> deleteEvalQuestionnaire(@RequestParam("id") Long id) {
        evalQuestionnaireService.deleteEvalQuestionnaire(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得问卷调查管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-questionnaire:query')")
    public CommonResult<EvalQuestionnaireRespVO> getEvalQuestionnaire(@RequestParam("id") Long id) {
        EvalQuestionnaireDO evalQuestionnaire = evalQuestionnaireService.getEvalQuestionnaire(id);
        return success(BeanUtils.toBean(evalQuestionnaire, EvalQuestionnaireRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得问卷调查管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-questionnaire:query')")
    public CommonResult<PageResult<EvalQuestionnaireRespVO>> getEvalQuestionnairePage(@Valid EvalQuestionnairePageReqVO pageReqVO) {
        PageResult<EvalQuestionnaireDO> pageResult = evalQuestionnaireService.getEvalQuestionnairePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalQuestionnaireRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出问卷调查管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-questionnaire:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalQuestionnaireExcel(@Valid EvalQuestionnairePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalQuestionnaireDO> list = evalQuestionnaireService.getEvalQuestionnairePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "问卷调查管理.xls", "数据", EvalQuestionnaireRespVO.class,
                        BeanUtils.toBean(list, EvalQuestionnaireRespVO.class));
    }

}