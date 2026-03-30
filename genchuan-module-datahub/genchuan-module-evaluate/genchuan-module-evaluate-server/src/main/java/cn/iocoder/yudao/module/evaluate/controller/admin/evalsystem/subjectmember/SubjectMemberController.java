package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember.vo.SubjectMemberPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember.vo.SubjectMemberRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember.vo.SubjectMemberSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subjectmember.SubjectMemberDO;
import cn.iocoder.yudao.module.evaluate.service.subjectmember.SubjectMemberService;
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

@Tag(name = "管理后台 - 评价主体成员")
@RestController
@RequestMapping("/evaluate/subject-member")
@Validated
public class SubjectMemberController {

    @Resource
    private SubjectMemberService subjectMemberService;

    @PostMapping("/create")
    @Operation(summary = "创建评价主体成员")
    @PreAuthorize("@ss.hasPermission('evaluate:subject-member:create')")
    public CommonResult<Long> createSubjectMember(@Valid @RequestBody SubjectMemberSaveReqVO createReqVO) {
        return success(subjectMemberService.createSubjectMember(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价主体成员")
    @PreAuthorize("@ss.hasPermission('evaluate:subject-member:update')")
    public CommonResult<Boolean> updateSubjectMember(@Valid @RequestBody SubjectMemberSaveReqVO updateReqVO) {
        subjectMemberService.updateSubjectMember(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价主体成员")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:subject-member:delete')")
    public CommonResult<Boolean> deleteSubjectMember(@RequestParam("id") Long id) {
        subjectMemberService.deleteSubjectMember(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除评价主体成员")
                @PreAuthorize("@ss.hasPermission('evaluate:subject-member:delete')")
    public CommonResult<Boolean> deleteSubjectMemberList(@RequestParam("ids") List<Long> ids) {
        subjectMemberService.deleteSubjectMemberListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价主体成员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:subject-member:query')")
    public CommonResult<SubjectMemberRespVO> getSubjectMember(@RequestParam("id") Long id) {
        SubjectMemberDO subjectMember = subjectMemberService.getSubjectMember(id);
        return success(BeanUtils.toBean(subjectMember, SubjectMemberRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价主体成员分页")
    @PreAuthorize("@ss.hasPermission('evaluate:subject-member:query')")
    public CommonResult<PageResult<SubjectMemberRespVO>> getSubjectMemberPage(@Valid SubjectMemberPageReqVO pageReqVO) {
        PageResult<SubjectMemberDO> pageResult = subjectMemberService.getSubjectMemberPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SubjectMemberRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价主体成员 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:subject-member:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSubjectMemberExcel(@Valid SubjectMemberPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SubjectMemberDO> list = subjectMemberService.getSubjectMemberPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价主体成员.xls", "数据", SubjectMemberRespVO.class,
                        BeanUtils.toBean(list, SubjectMemberRespVO.class));
    }

}