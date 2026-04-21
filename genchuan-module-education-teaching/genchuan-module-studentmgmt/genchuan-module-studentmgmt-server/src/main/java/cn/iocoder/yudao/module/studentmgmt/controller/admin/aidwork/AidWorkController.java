package cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.aidwork.AidWorkDO;
import cn.iocoder.yudao.module.studentmgmt.service.aidwork.AidWorkService;

@Tag(name = "学生管理后台 - 奖助勤贷")
@RestController
@RequestMapping("/studentmgmt/aid-work")
@Validated
public class AidWorkController {

    @Resource
    private AidWorkService aidWorkService;

    @PostMapping("/create")
    @Operation(summary = "创建奖助勤贷")
    @PreAuthorize("@ss.hasPermission('studentmgmt:aid-work:create')")
    public CommonResult<Long> createAidWork(@Valid @RequestBody AidWorkSaveReqVO createReqVO) {
        return success(aidWorkService.createAidWork(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新奖助勤贷")
    @PreAuthorize("@ss.hasPermission('studentmgmt:aid-work:update')")
    public CommonResult<Boolean> updateAidWork(@Valid @RequestBody AidWorkSaveReqVO updateReqVO) {
        aidWorkService.updateAidWork(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除奖助勤贷")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:aid-work:delete')")
    public CommonResult<Boolean> deleteAidWork(@RequestParam("id") Long id) {
        aidWorkService.deleteAidWork(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除奖助勤贷")
                @PreAuthorize("@ss.hasPermission('studentmgmt:aid-work:delete')")
    public CommonResult<Boolean> deleteAidWorkList(@RequestParam("ids") List<Long> ids) {
        aidWorkService.deleteAidWorkListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得奖助勤贷")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:aid-work:query')")
    public CommonResult<AidWorkRespVO> getAidWork(@RequestParam("id") Long id) {
        AidWorkDO aidWork = aidWorkService.getAidWork(id);
        return success(BeanUtils.toBean(aidWork, AidWorkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得奖助勤贷分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:aid-work:query')")
    public CommonResult<PageResult<AidWorkRespVO>> getAidWorkPage(@Valid AidWorkPageReqVO pageReqVO) {
        PageResult<AidWorkDO> pageResult = aidWorkService.getAidWorkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AidWorkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出奖助勤贷 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:aid-work:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAidWorkExcel(@Valid AidWorkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AidWorkDO> list = aidWorkService.getAidWorkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "奖助勤贷.xls", "数据", AidWorkRespVO.class,
                        BeanUtils.toBean(list, AidWorkRespVO.class));
    }

    @PutMapping("/audit")
    @Operation(summary = "审核奖助勤贷")
    @PreAuthorize("@ss.hasPermission('studentmgmt:aid-work:audit')")
    public CommonResult<Boolean> audit(@Valid @RequestBody AidWorkAuditReqVO reqVO) {
        boolean isSuccess =aidWorkService.audit(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/follow")
    @Operation(summary = "流程跟进")
    @PreAuthorize("@ss.hasPermission('studentmgmt:aid-work:follow')")
    public CommonResult<Boolean> follow(@Valid @RequestBody AidWorkFollowReqVO reqVO) {
        boolean isSuccess =aidWorkService.follow(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/chart")
    @Operation(summary = "奖助勤贷统计看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:aid-work:chart')")
    public CommonResult<AidWorkChartRespVO> chart(@Valid @RequestBody AidWorkChartReqVO reqVO) {
        AidWorkChartRespVO vo = aidWorkService.chart(reqVO);
        return success(vo);
    }

    @PutMapping("/chart/applyCount")
    @Operation(summary = "各类型申请人数 / 办理完成率统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:aid-work:chart')")
    public CommonResult<List<AidWorkApplyCountRespVO>> applyCount(@Valid @RequestBody AidWorkApplyCountReqVO reqVO) {
        List<AidWorkApplyCountRespVO> vo = aidWorkService.applyCount(reqVO);
        return success(vo);
    }


}