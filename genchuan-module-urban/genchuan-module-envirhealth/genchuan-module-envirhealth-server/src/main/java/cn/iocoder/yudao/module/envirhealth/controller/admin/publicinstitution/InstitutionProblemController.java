package cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutionproblem.InstitutionProblemPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutionproblem.InstitutionProblemRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutionproblem.InstitutionProblemSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionProblemDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionProblemDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.publicinstitution.institutionproblem.InstitutionProblemService;
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
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "环境卫生管理 - 公共机构问题")
@RestController
@RequestMapping("/envirhealth/institution-problem")
@Validated
public class InstitutionProblemController {

    @Resource
    private InstitutionProblemService institutionProblemService;

    @PostMapping("/create")
    @Operation(summary = "创建公共机构问题")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-problem:create')")
    public CommonResult<Long> createInstitutionProblem(@Valid @RequestBody InstitutionProblemSaveReqVO createReqVO) {
        return success(institutionProblemService.createInstitutionProblem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公共机构问题")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-problem:update')")
    public CommonResult<Boolean> updateInstitutionProblem(@Valid @RequestBody InstitutionProblemSaveReqVO updateReqVO) {
        institutionProblemService.updateInstitutionProblem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公共机构问题")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-problem:delete')")
    public CommonResult<Boolean> deleteInstitutionProblem(@RequestParam("id") Long id) {
        institutionProblemService.deleteInstitutionProblem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公共机构问题")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-problem:query')")
    public CommonResult<InstitutionProblemRespVO> getInstitutionProblem(@RequestParam("id") Long id) {
        InstitutionProblemDO institutionProblem = institutionProblemService.getInstitutionProblem(id);
        return success(BeanUtils.toBean(institutionProblem, InstitutionProblemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公共机构问题分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-problem:query')")
    public CommonResult<PageResult<InstitutionProblemRespVO>> getInstitutionProblemPage(@Valid InstitutionProblemPageReqVO pageReqVO) {
        PageResult<InstitutionProblemDO> pageResult = institutionProblemService.getInstitutionProblemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InstitutionProblemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公共机构问题 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-problem:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInstitutionProblemExcel(@Valid InstitutionProblemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InstitutionProblemDO> list = institutionProblemService.getInstitutionProblemPage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("公共机构问题_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "公共机构问题.xls", "数据", InstitutionProblemRespVO.class,
                        BeanUtils.toBean(list, InstitutionProblemRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得公共机构问题详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-problem:query')")
    public CommonResult<PageResult<InstitutionProblemDetailDO>> getInstitutionProblemDetailPage(
            @Valid InstitutionProblemPageReqVO pageReqVO) {
        PageResult<InstitutionProblemDetailDO> pageResult =
                institutionProblemService.getInstitutionProblemDetailPage(pageReqVO);

        return success(pageResult);
    }
}