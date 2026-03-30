package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.questionnaire;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.questionnaire.vo.QuestionnairePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.questionnaire.vo.QuestionnaireRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.questionnaire.vo.QuestionnaireSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.questionnaire.QuestionnaireDO;
import cn.iocoder.yudao.module.evaluate.service.questionnaire.QuestionnaireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 问卷")
@RestController
@RequestMapping("/evaluate/questionnaire")
@Validated
public class QuestionnaireController {

    @Resource
    private QuestionnaireService questionnaireService;

    @PostMapping("/create")
    @Operation(summary = "创建问卷")
    @PreAuthorize("@ss.hasPermission('evaluate:questionnaire:create')")
    public CommonResult<Long> createQuestionnaire(@Valid @RequestBody QuestionnaireSaveReqVO createReqVO) {
        return success(questionnaireService.createQuestionnaire(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新问卷")
    @PreAuthorize("@ss.hasPermission('evaluate:questionnaire:update')")
    public CommonResult<Boolean> updateQuestionnaire(@Valid @RequestBody QuestionnaireSaveReqVO updateReqVO) {
        questionnaireService.updateQuestionnaire(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除问卷")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:questionnaire:delete')")
    public CommonResult<Boolean> deleteQuestionnaire(@RequestParam("id") Long id) {
        questionnaireService.deleteQuestionnaire(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得问卷")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:questionnaire:query')")
    public CommonResult<QuestionnaireRespVO> getQuestionnaire(@RequestParam("id") Long id) {
        QuestionnaireDO questionnaire = questionnaireService.getQuestionnaire(id);
        return success(BeanUtils.toBean(questionnaire, QuestionnaireRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得问卷分页")
    @PreAuthorize("@ss.hasPermission('evaluate:questionnaire:query')")
    public CommonResult<PageResult<QuestionnaireRespVO>> getQuestionnairePage(@Valid QuestionnairePageReqVO pageReqVO) {
        PageResult<QuestionnaireDO> pageResult = questionnaireService.getQuestionnairePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, QuestionnaireRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出问卷 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:questionnaire:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportQuestionnaireExcel(@Valid QuestionnairePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<QuestionnaireDO> list = questionnaireService.getQuestionnairePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "问卷.xls", "数据", QuestionnaireRespVO.class,
                        BeanUtils.toBean(list, QuestionnaireRespVO.class));
    }

}