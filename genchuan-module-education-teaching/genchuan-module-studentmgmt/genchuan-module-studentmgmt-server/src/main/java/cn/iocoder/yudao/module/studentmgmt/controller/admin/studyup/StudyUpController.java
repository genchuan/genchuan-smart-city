package cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studyup.StudyUpDO;
import cn.iocoder.yudao.module.studentmgmt.service.studyup.StudyUpService;

@Tag(name = "学生管理后台 - 升学管理")
@RestController
@RequestMapping("/studentmgmt/study-up")
@Validated
public class StudyUpController {

    @Resource
    private StudyUpService studyUpService;

    @PostMapping("/create")
    @Operation(summary = "创建升学管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:create')")
    public CommonResult<Long> createStudyUp(@Valid @RequestBody StudyUpSaveReqVO createReqVO) {
        return success(studyUpService.createStudyUp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新升学管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:update')")
    public CommonResult<Boolean> updateStudyUp(@Valid @RequestBody StudyUpSaveReqVO updateReqVO) {
        studyUpService.updateStudyUp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除升学管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:delete')")
    public CommonResult<Boolean> deleteStudyUp(@RequestParam("id") Long id) {
        studyUpService.deleteStudyUp(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除升学管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:delete')")
    public CommonResult<Boolean> deleteStudyUpList(@RequestParam("ids") List<Long> ids) {
        studyUpService.deleteStudyUpListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得升学管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:query')")
    public CommonResult<StudyUpRespVO> getStudyUp(@RequestParam("id") Long id) {
        StudyUpDO studyUp = studyUpService.getStudyUp(id);
        return success(BeanUtils.toBean(studyUp, StudyUpRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得升学管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:query')")
    public CommonResult<PageResult<StudyUpRespVO>> getStudyUpPage(@Valid StudyUpPageReqVO pageReqVO) {
        PageResult<StudyUpDO> pageResult = studyUpService.getStudyUpPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StudyUpRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出升学管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStudyUpExcel(@Valid StudyUpPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StudyUpDO> list = studyUpService.getStudyUpPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "升学管理.xls", "数据", StudyUpRespVO.class,
                        BeanUtils.toBean(list, StudyUpRespVO.class));
    }

}