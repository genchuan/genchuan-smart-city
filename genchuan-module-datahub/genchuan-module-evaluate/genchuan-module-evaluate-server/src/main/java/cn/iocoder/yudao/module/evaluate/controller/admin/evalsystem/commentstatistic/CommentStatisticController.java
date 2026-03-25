package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo.CommentStatisticPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo.CommentStatisticRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo.CommentStatisticSaveReqVO;
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
import java.util.List;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentstatistic.CommentStatisticDO;
import cn.iocoder.yudao.module.evaluate.service.commentstatistic.CommentStatisticService;

@Tag(name = "管理后台 - 巡查巡检统计")
@RestController
@RequestMapping("/evaluate/comment-statistic")
@Validated
public class CommentStatisticController {

    @Resource
    private CommentStatisticService commentStatisticService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检统计")
    @PreAuthorize("@ss.hasPermission('evaluate:comment-statistic:create')")
    public CommonResult<Long> createCommentStatistic(@Valid @RequestBody CommentStatisticSaveReqVO createReqVO) {
        return success(commentStatisticService.createCommentStatistic(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检统计")
    @PreAuthorize("@ss.hasPermission('evaluate:comment-statistic:update')")
    public CommonResult<Boolean> updateCommentStatistic(@Valid @RequestBody CommentStatisticSaveReqVO updateReqVO) {
        commentStatisticService.updateCommentStatistic(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:comment-statistic:delete')")
    public CommonResult<Boolean> deleteCommentStatistic(@RequestParam("id") Long id) {
        commentStatisticService.deleteCommentStatistic(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:comment-statistic:query')")
    public CommonResult<CommentStatisticRespVO> getCommentStatistic(@RequestParam("id") Long id) {
        CommentStatisticDO commentStatistic = commentStatisticService.getCommentStatistic(id);
        return success(BeanUtils.toBean(commentStatistic, CommentStatisticRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检统计分页")
    @PreAuthorize("@ss.hasPermission('evaluate:comment-statistic:query')")
    public CommonResult<PageResult<CommentStatisticRespVO>> getCommentStatisticPage(@Valid CommentStatisticPageReqVO pageReqVO) {
        return success(commentStatisticService.getCommentStatisticPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检统计 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:comment-statistic:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCommentStatisticExcel(@Valid CommentStatisticPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        List<CommentStatisticRespVO> list = commentStatisticService.getAllCommentStatisticList(pageReqVO);
        ExcelUtils.write(response, "巡查巡检统计.xls", "数据", CommentStatisticRespVO.class, list);
    }

    @PostMapping("/reconcile")
    @Operation(summary = "全量对账：修正统计表与巡查表数据一致性")
    @PreAuthorize("@ss.hasPermission('evaluate:comment-statistic:reconcile')")
    public CommonResult<Integer> reconcileCommentStatistic() {
        int fixedCount = commentStatisticService.reconcileAll();
        return success(fixedCount);
    }

}