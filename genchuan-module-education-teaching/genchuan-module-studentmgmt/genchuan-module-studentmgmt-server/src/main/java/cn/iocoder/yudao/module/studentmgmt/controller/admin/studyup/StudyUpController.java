package cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studyup.StudyUpDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.studyup.StudyUpService;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
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

@Tag(name = "学生管理后台 - 升学管理")
@RestController
@RequestMapping("/studentmgmt/study-up")
@Validated
public class StudyUpController {

    @Resource
    private StudyUpService studyUpService;
    @Resource
    private DictDataApi dictDataApi;

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
//        PageResult<StudyUpDO> pageResult = studyUpService.getStudyUpPage(pageReqVO);
        return success(studyUpService.getStudyUpJoinPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出升学管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStudyUpExcel(@Valid StudyUpPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StudyUpDO> list = studyUpService.getStudyUpPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> typeDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.STUDY_UP_SCHOOL_TYPE.getType());
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.STUDY_UP_STATUS.getType());
        list = list.stream().map(item -> {
            String schoolType = item.getSchoolType();
            if (typeDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : typeDictDataList.getData()) {
                    if (dictData.getValue().equals(schoolType)) {
                        schoolType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setSchoolType(schoolType);
            String status = item.getStatus();
            if (statusDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : statusDictDataList.getData()) {
                    if (dictData.getValue().equals(status)) {
                        status = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setStatus(status);
            return item;
        }).toList();
        // 导出 Excel
        ExcelUtils.write(response, "升学管理.xls", "数据", StudyUpRespVO.class,
                BeanUtils.toBean(list, StudyUpRespVO.class));
    }

    @GetMapping("/query")
    @Operation(summary = "查询")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:query')")
    public CommonResult<StudyUpQueryRespVO> query(@Valid @RequestBody StudyUpQueryReqVO reqVO) {
        return success(studyUpService.query(reqVO));
    }

    @PutMapping("/select")
    @Operation(summary = "选择")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:select')")
    public CommonResult<Boolean> select(@Valid @RequestBody StudyUpSelectReqVO reqVO) {
        return success(studyUpService.select(reqVO));
    }

    @PutMapping("/plan")
    @Operation(summary = "规划")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:plan')")
    public CommonResult<Boolean> plan(@Valid @RequestBody StudyUpPlanReqVO reqVO) {
        return success(studyUpService.plan(reqVO));
    }

    @PutMapping("/record")
    @Operation(summary = "记录")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:record')")
    public CommonResult<Boolean> record(@Valid @RequestBody StudyUpRecordReqVO reqVO) {
        return success(studyUpService.record(reqVO));
    }

    @GetMapping("/chart")
    @Operation(summary = "学生升学统计看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:chart')")
    public CommonResult<StudyUpChartRespVO> chart(@Valid BaseChartReqVO reqVO) {
        return success(studyUpService.chart(reqVO));
    }

    @GetMapping("/chart/studyCount")
    @Operation(summary = "升学意向 / 院校选择统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:study-up:query')")
    public CommonResult<StudyUpStudyCountRespVO> studyCount(@Valid BaseChartReqVO reqVO) {
        return success(studyUpService.studyCount(reqVO));
    }
}