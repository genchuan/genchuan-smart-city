package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.service.studentinfo.StudentInfoService;

@Tag(name = "管理后台 - 学生信息")
@RestController
@RequestMapping("/studentmgmt/student-info")
@Validated
public class StudentInfoController {

    @Resource
    private StudentInfoService studentInfoService;

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
    public CommonResult<PageResult<StudentInfoRespVO>> getStudentInfoPage(@Valid StudentInfoPageReqVO pageReqVO) {
        PageResult<StudentInfoDO> pageResult = studentInfoService.getStudentInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StudentInfoRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出学生信息 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStudentInfoExcel(@Valid StudentInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StudentInfoDO> list = studentInfoService.getStudentInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "学生信息.xls", "数据", StudentInfoRespVO.class,
                        BeanUtils.toBean(list, StudentInfoRespVO.class));
    }


    @GetMapping("/chart")
    @Operation(summary = "卡片/圆环图/柱状图/统计(学生信息分布看板)")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:query')")
    public CommonResult<StudentInfoDashboardVO> getStudentDashboard() {
        StudentInfoDashboardVO dashboardVO = studentInfoService.getStudentInfoDashboard();
        return success(dashboardVO);
    }

    @GetMapping("/chart/distributionCount")
    @Operation(summary = "查询学生信息分布看板的核心统计数据，用于学生信息页概览展示")
    @PreAuthorize("@ss.hasPermission('studentmgmt:student-info:query')")
    public CommonResult<StudentInfoDashboardVO> getDistributionCount() {
        StudentInfoDashboardVO dashboardVO = studentInfoService.getStudentInfoDashboard();
        return success(dashboardVO);
    }

}