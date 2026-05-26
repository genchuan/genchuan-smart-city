package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.studentinfo.StudentInfoService;
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

@Tag(name = "学生管理后台 - 学生信息")
@RestController
@RequestMapping("/studentmgmt/student-info")
@Validated
public class StudentInfoController {

    @Resource
    private StudentInfoService studentInfoService;
    @Resource
    private DictDataApi dictDataApi;

    @PostMapping("/create")
    @Operation(summary = "创建学生信息")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:create')")
    public CommonResult<Long> createStudentInfo(@Valid @RequestBody StudentInfoSaveReqVO createReqVO) {
        return success(studentInfoService.createStudentInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新学生信息")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:update')")
    public CommonResult<Boolean> updateStudentInfo(@Valid @RequestBody StudentInfoSaveReqVO updateReqVO) {
        studentInfoService.updateStudentInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除学生信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:delete')")
    public CommonResult<Boolean> deleteStudentInfo(@RequestParam("id") Long id) {
        studentInfoService.deleteStudentInfo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除学生信息")
                @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:delete')")
    public CommonResult<Boolean> deleteStudentInfoList(@RequestParam("ids") List<Long> ids) {
        studentInfoService.deleteStudentInfoListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得学生信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:query')")
    public CommonResult<StudentInfoRespVO> getStudentInfo(@RequestParam("id") Long id) {
        StudentInfoDO studentInfo = studentInfoService.getStudentInfo(id);
        return success(BeanUtils.toBean(studentInfo, StudentInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得学生信息分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:query')")
    public CommonResult<PageResult<StudentInfoPageRespVO>> getStudentInfoPage(@Valid StudentInfoPageReqVO pageReqVO) {
        PageResult<StudentInfoDO> pageResult = studentInfoService.getStudentInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StudentInfoPageRespVO.class));
    }

    @GetMapping("/getAll")
    @Operation(summary = "获得全部学生信息")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:query')")
    public CommonResult<List<StudentInfoBaseVO>> getAll() {
        List<StudentInfoBaseVO> list = studentInfoService.getAll();
        return success(list);
    }

    @GetMapping("/export")
    @Operation(summary = "导出学生信息 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStudentInfoExcel(@Valid StudentInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StudentInfoDO> list = studentInfoService.getStudentInfoPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> educationLevelDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.STUDENT_INFO_EDUCATION_LEVEL.getType());
        CommonResult<List<DictDataRespDTO>> studyFormDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.STUDENT_INFO_STUDY_FORM.getType());
        CommonResult<List<DictDataRespDTO>> studentTypeDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.STUDENT_INFO_STUDENT_TYPE.getType());
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.STUDENT_INFO_STATUS.getType());
        list = list.stream().map(item -> {
            String educationLevel = item.getEducationLevel();
            if (educationLevelDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : educationLevelDictDataList.getData()) {
                    if (dictData.getValue().equals(educationLevel)) {
                        educationLevel = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setEducationLevel(educationLevel);
            String studyForm = item.getStudyForm();
            if (studyFormDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : studyFormDictDataList.getData()) {
                    if (dictData.getValue().equals(studyForm)) {
                        studyForm = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setStudyForm(studyForm);
            String studentType = item.getStudentType();
            if (studentTypeDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : studentTypeDictDataList.getData()) {
                    if (dictData.getValue().equals(studentType)) {
                        studentType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setStudentType(studentType);
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
        ExcelUtils.write(response, "学生信息.xls", "数据", StudentInfoRespVO.class,
                        BeanUtils.toBean(list, StudentInfoRespVO.class));
    }


    @GetMapping("/chart")
    @Operation(summary = "学生信息分布看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:query')")
    public CommonResult<StudentInfoDashboardVO> getStudentDashboard(@Valid StudentInfoChartReqVO reqVO) {
        StudentInfoDashboardVO dashboardVO = studentInfoService.getStudentInfoDashboard(reqVO);
        return success(dashboardVO);
    }

    @GetMapping("/chart/distributionCount")
    @Operation(summary = "按年级 / 专业 / 班级分布统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:query')")
    public CommonResult<List<StudentInfoDistributionCountRespVO>> getDistributionCount(@Valid StudentInfoDistributionCountReqVO reqVO) {
        List<StudentInfoDistributionCountRespVO> dashboardVO = studentInfoService.getDistributionCount(reqVO);
        return success(dashboardVO);
    }

    @GetMapping("/chart/coreIndex")
    @Operation(summary = "按学生核心指标统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:query')")
    public CommonResult<List<StudentInfoCoreIndexRespVO>> getCoreIndex(@Valid StudentInfoCoreIndexReqVO reqVO) {
        List<StudentInfoCoreIndexRespVO> dashboardVO = studentInfoService.getCoreIndex(reqVO);
        return success(dashboardVO);
    }


}