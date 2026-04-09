package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo.StudentInfoCoreIndexReqVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.assessmgmt.AssessMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.assessmgmt.AssessMgmtService;
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

@Tag(name = "管理后台 - 考评管理")
@RestController
@RequestMapping("/studentmgmt/assess-mgmt")
@Validated
public class AssessMgmtController {

    @Resource
    private AssessMgmtService assessMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建考评管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:assess-mgmt:create')")
    public CommonResult<Long> createAssessMgmt(@Valid @RequestBody AssessMgmtSaveReqVO createReqVO) {
        return success(assessMgmtService.createAssessMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新考评管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:assess-mgmt:update')")
    public CommonResult<Boolean> updateAssessMgmt(@Valid @RequestBody AssessMgmtSaveReqVO updateReqVO) {
        assessMgmtService.updateAssessMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除考评管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:assess-mgmt:delete')")
    public CommonResult<Boolean> deleteAssessMgmt(@RequestParam("id") Long id) {
        assessMgmtService.deleteAssessMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除考评管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:assess-mgmt:delete')")
    public CommonResult<Boolean> deleteAssessMgmtList(@RequestParam("ids") List<Long> ids) {
        assessMgmtService.deleteAssessMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得考评管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:assess-mgmt:query')")
    public CommonResult<AssessMgmtRespVO> getAssessMgmt(@RequestParam("id") Long id) {
        AssessMgmtDO assessMgmt = assessMgmtService.getAssessMgmt(id);
        return success(BeanUtils.toBean(assessMgmt, AssessMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得考评管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:assess-mgmt:query')")
    public CommonResult<PageResult<AssessMgmtRespVO>> getAssessMgmtPage(@Valid AssessMgmtPageReqVO pageReqVO) {
        PageResult<AssessMgmtDO> pageResult = assessMgmtService.getAssessMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssessMgmtRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出考评管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:assess-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssessMgmtExcel(@Valid AssessMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssessMgmtDO> list = assessMgmtService.getAssessMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "考评管理.xls", "数据", AssessMgmtRespVO.class,
                        BeanUtils.toBean(list, AssessMgmtRespVO.class));
    }

    @PutMapping("/publish")
    @Operation(summary = "发布")
    @PreAuthorize("@ss.hasPermission('studentmgmt:assess-mgmt:update')")
    public CommonResult<Boolean> publish(@Valid @RequestBody AssessMgmtPublishReqVO publishReqVO) {
        boolean isPublish = assessMgmtService.publishAssessMgmt(publishReqVO);
        return success(isPublish);
    }

    @PutMapping("/chart")
    @Operation(summary = "考评信息分布看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:assess-mgmt:query')")
    public CommonResult<AssessMgmtChartRespVO> chart(@Valid @RequestBody AssessMgmtChartReqVO reqVO) {
        AssessMgmtChartRespVO respVO = assessMgmtService.chart(reqVO);
        return success(respVO);
    }

    @PutMapping("/chart/typeCount")
    @Operation(summary = "考评信息分布看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:assess-mgmt:query')")
    public CommonResult<AssessMgmtTypeCountRespVO> typeCount(@Valid @RequestBody AssessMgmtChartReqVO reqVO) {
        AssessMgmtTypeCountRespVO respVO = assessMgmtService.typeCount(reqVO);
        return success(respVO);
    }

    @GetMapping("/chart/coreIndex")
    @Operation(summary = "按考评核心指标统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:assess-info:query')")
    public CommonResult<AssessMgmtCoreIndexReqVO> getCoreIndex(@Valid @RequestBody StudentInfoCoreIndexReqVO reqVO) {
        AssessMgmtCoreIndexReqVO dashboardVO = assessMgmtService.getCoreIndex(reqVO);
        return success(dashboardVO);
    }

}
