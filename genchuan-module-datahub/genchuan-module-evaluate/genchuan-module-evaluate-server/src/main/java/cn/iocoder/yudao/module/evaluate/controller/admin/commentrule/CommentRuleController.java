package cn.iocoder.yudao.module.evaluate.controller.admin.commentrule;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRulePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRuleRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRuleSaveReqVO;
import cn.iocoder.yudao.module.evaluate.service.commentrule.CommentRuleService;
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

@Tag(name = "管理后台 - 评分规则主")
@RestController
@RequestMapping("/evaluate/comment-rule")
@Validated
public class CommentRuleController {

    @Resource
    private CommentRuleService commentRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建评分规则主")
    @PreAuthorize("@ss.hasPermission('evaluate:comment-rule:create')")
    public CommonResult<Long> createCommentRule(@Valid @RequestBody CommentRuleSaveReqVO createReqVO) {
        return success(commentRuleService.createCommentRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评分规则主")
    @PreAuthorize("@ss.hasPermission('evaluate:comment-rule:update')")
    public CommonResult<Boolean> updateCommentRule(@Valid @RequestBody CommentRuleSaveReqVO updateReqVO) {
        commentRuleService.updateCommentRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评分规则主")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:comment-rule:delete')")
    public CommonResult<Boolean> deleteCommentRule(@RequestParam("id") Long id) {
        commentRuleService.deleteCommentRule(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除评分规则主")
                @PreAuthorize("@ss.hasPermission('evaluate:comment-rule:delete')")
    public CommonResult<Boolean> deleteCommentRuleList(@RequestParam("ids") List<Long> ids) {
        commentRuleService.deleteCommentRuleListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评分规则主（含明细列表）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:comment-rule:query')")
    public CommonResult<CommentRuleRespVO> getCommentRule(@RequestParam("id") Long id) {
        CommentRuleRespVO respVO = commentRuleService.getCommentRuleWithDetails(id);
        return success(respVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得评分规则主分页")
    @PreAuthorize("@ss.hasPermission('evaluate:comment-rule:query')")
    public CommonResult<PageResult<CommentRuleRespVO>> getCommentRulePage(@Valid CommentRulePageReqVO pageReqVO) {
        return success(commentRuleService.getCommentRulePage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评分规则主 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:comment-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCommentRuleExcel(@Valid CommentRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CommentRuleRespVO> list = commentRuleService.getCommentRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评分规则主.xls", "数据", CommentRuleRespVO.class, list);
    }

}