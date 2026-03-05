package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subject.SubjectDO;
import cn.iocoder.yudao.module.evaluate.service.subject.SubjectService;
import com.alibaba.nacos.api.model.v2.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.IMPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "评价体系管理 - 评价主体***")
@RestController
@RequestMapping("/evaluate/subject")
@Validated
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    @PostMapping("/create")
    @Operation(summary = "创建评价主体")
    @PreAuthorize("@ss.hasPermission('evaluate:subject:create')")
    public CommonResult<Long> createSubject(@Valid @RequestBody SubjectSaveReqVO createReqVO) {
        return success(subjectService.createSubject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价主体")
    @PreAuthorize("@ss.hasPermission('evaluate:subject:update')")
    public CommonResult<Boolean> updateSubject(@Valid @RequestBody SubjectSaveReqVO updateReqVO) {
        subjectService.updateSubject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价主体")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:subject:delete')")
    public CommonResult<Boolean> deleteSubject(@RequestParam("id") Long id) {
        subjectService.deleteSubject(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除评价主体")
                @PreAuthorize("@ss.hasPermission('evaluate:subject:delete')")
    public CommonResult<Boolean> deleteSubjectList(@RequestParam("ids") List<Long> ids) {
        subjectService.deleteSubjectListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价主体")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:subject:query')")
    public CommonResult<SubjectRespVO> getSubject(@RequestParam("id") Long id) {
        SubjectDO subject = subjectService.getSubject(id);
        return success(BeanUtils.toBean(subject, SubjectRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价主体分页")
    @PreAuthorize("@ss.hasPermission('evaluate:subject:query')")
    public CommonResult<PageResult<SubjectRespVO>> getSubjectPage(@Valid SubjectPageReqVO pageReqVO) {
        PageResult<SubjectDO> pageResult = subjectService.getSubjectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SubjectRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价主体 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:subject:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSubjectExcel(@Valid SubjectPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SubjectDO> list = subjectService.getSubjectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价主体.xls", "数据", SubjectRespVO.class,
                        BeanUtils.toBean(list, SubjectRespVO.class));
    }
    @PostMapping("/import-excel")
    @Operation(summary = "导入评价主体 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:subject:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<List<SubjectImportRespVO>> importSubjectExcel(
            @RequestParam("file") MultipartFile file) {
        List<SubjectImportRespVO> result = subjectService.importSubjectExcel(file);
        return success(result);
    }
    // -------------------------- 新增联表查询接口 --------------------------

//    /**
//     * 联表分页查询评价主体（列表页展示，含关联表名称字段）
//     * 支持模糊查询、分页，返回主体类型名称、联系人姓名等联表字段
//     */
//    @Operation(summary = "联表分页查询评价主体")
//    @GetMapping("/page-with-join")
//    public CommonResult<PageResult<SubjectRespVO>> getSubjectPageWithJoin(@Valid SubjectPageReqVO pageReqVO) {
//        PageResult<SubjectRespVO> pageResult = subjectService.getSubjectPageWithJoin(pageReqVO);
//        return CommonResult.success(pageResult);
//    }
//
//    /**
//     * 联表查询评价主体详情（含成员列表）
//     * 人工主体返回成员列表，系统主体隐藏成员字段
//     */
//    @Operation(summary = "联表查询评价主体详情")
//    @GetMapping("/detail-with-join")
//    public CommonResult<SubjectRespVO> getSubjectDetailWithJoin(@Valid SubjectDetailReqVO detailReqVO) {
//        SubjectRespVO subjectDetail = subjectService.getSubjectDetailWithJoin(detailReqVO);
//        return CommonResult.success(subjectDetail);
//    }
//
//    /**
//     * 查询评价主体统计指标（总数量、人工/系统主体数、启用数）
//     * 适配前端统计卡片展示需求
//     */
//    @Operation(summary = "查询评价主体统计指标")
//    @GetMapping("/stat")
//    public CommonResult<SubjectStatRespVO> getSubjectStat() {
//        SubjectStatRespVO statResult = subjectService.getSubjectStat();
//        return CommonResult.success(statResult);
//    }
    @GetMapping("/allpage")
    @Operation(summary = "获得评价主体分页（全部/启用/停用）*")
    @PreAuthorize("@ss.hasPermission('evaluate:subject:query')")
    public CommonResult<PageResult<SubjectRespVO>> getSubjectJoinPage(SubjectPageReqVO reqVO) {
        PageResult<SubjectRespVO> pageResult = subjectService.getSubjectJoinPage(reqVO);
        return CommonResult.success(pageResult);
    }

    @GetMapping("/status-count")
    @Operation(summary = "获取status_id统计数据*")
    public Result<SubjectRespVO> getStatusCount(
            @RequestParam(required = false) Integer statusId) {
        SubjectRespVO respVO = subjectService.getStatusCount(statusId);
        return Result.success(respVO);
    }

    @GetMapping("/overview")
    @Operation(summary = "获取评价主体全局概览数据**")
    public CommonResult<EvalSubjectOverviewVO> getEvalSubjectOverview() {
        return CommonResult.success(subjectService.getEvalSubjectOverview());
    }
}