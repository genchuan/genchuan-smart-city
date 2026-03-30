package cn.iocoder.yudao.module.evaluate.controller.admin.objectscore;

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

import cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objectscore.ObjectScoreDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.objectscore.ObjectScoreMapper;
import cn.iocoder.yudao.module.evaluate.service.objectscore.ObjectScoreService;

@Tag(name = "管理后台 - 公司得分")
@RestController
@RequestMapping("/evaluate/object-score")
@Validated
public class ObjectScoreController {

    @Resource
    private ObjectScoreService objectScoreService;

    @Resource
    private ObjectScoreMapper objectScoreMapper;

    @PostMapping("/create")
    @Operation(summary = "创建公司得分")
    @PreAuthorize("@ss.hasPermission('evaluate:object-score:create')")
    public CommonResult<Long> createObjectScore(@Valid @RequestBody ObjectScoreSaveReqVO createReqVO) {
        return success(objectScoreService.createObjectScore(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公司得分")
    @PreAuthorize("@ss.hasPermission('evaluate:object-score:update')")
    public CommonResult<Boolean> updateObjectScore(@Valid @RequestBody ObjectScoreSaveReqVO updateReqVO) {
        objectScoreService.updateObjectScore(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公司得分")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:object-score:delete')")
    public CommonResult<Boolean> deleteObjectScore(@RequestParam("id") Long id) {
        objectScoreService.deleteObjectScore(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除公司得分")
                @PreAuthorize("@ss.hasPermission('evaluate:object-score:delete')")
    public CommonResult<Boolean> deleteObjectScoreList(@RequestParam("ids") List<Long> ids) {
        objectScoreService.deleteObjectScoreListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公司得分（包含计算明细）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:object-score:query')")
    public CommonResult<ObjectScoreCalculateRespVO> getObjectScore(@RequestParam("id") Long id) {
        return success(objectScoreService.calculateScore(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公司得分分页")
    @PreAuthorize("@ss.hasPermission('evaluate:object-score:query')")
    public CommonResult<PageResult<ObjectScoreRespVO>> getObjectScorePage(@Valid ObjectScorePageReqVO pageReqVO) {
        return success(objectScoreService.getObjectScorePage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公司得分 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:object-score:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportObjectScoreExcel(@Valid ObjectScorePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ObjectScoreDO> list = objectScoreMapper.selectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "公司得分.xls", "数据", ObjectScoreRespVO.class,
                        BeanUtils.toBean(list, ObjectScoreRespVO.class));
    }

    @GetMapping("/calculate")
    @Operation(summary = "根据公司得分记录ID计算加权得分")
    @Parameter(name = "id", description = "公司得分记录ID（eval_object_score表主键）", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('evaluate:object-score:query')")
    public CommonResult<ObjectScoreCalculateRespVO> calculateScore(@RequestParam("id") Long id) {
        return success(objectScoreService.calculateScore(id));
    }

    @PostMapping("/refresh")
    @Operation(summary = "刷新公司得分表数据")
    @PreAuthorize("@ss.hasPermission('evaluate:object-score:refresh')")
    public CommonResult<Integer> refreshScoreTable() {
        return success(objectScoreService.refreshScoreTable());
    }

}