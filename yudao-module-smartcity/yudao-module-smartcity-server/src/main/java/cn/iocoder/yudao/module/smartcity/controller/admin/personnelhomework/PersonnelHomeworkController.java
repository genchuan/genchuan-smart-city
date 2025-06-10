package cn.iocoder.yudao.module.smartcity.controller.admin.personnelhomework;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.personnelhomework.vo.PersonnelHomeworkPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.personnelhomework.vo.PersonnelHomeworkRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.personnelhomework.vo.PersonnelHomeworkSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.personnelhomework.PersonnelHomeworkDO;
import cn.iocoder.yudao.module.smartcity.service.personnelhomework.PersonnelHomeworkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 人员作业")
@RestController
@RequestMapping("/smartcity/personnel-homework")
@Validated
public class PersonnelHomeworkController {

    @Resource
    private PersonnelHomeworkService personnelHomeworkService;

    @PostMapping("/create")
    @Operation(summary = "创建人员作业")
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-homework:create')")
    public CommonResult<Long> createPersonnelHomework(@Valid @RequestBody PersonnelHomeworkSaveReqVO createReqVO) {
        return success(personnelHomeworkService.createPersonnelHomework(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新人员作业")
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-homework:update')")
    public CommonResult<Boolean> updatePersonnelHomework(@Valid @RequestBody PersonnelHomeworkSaveReqVO updateReqVO) {
        personnelHomeworkService.updatePersonnelHomework(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人员作业")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-homework:delete')")
    public CommonResult<Boolean> deletePersonnelHomework(@RequestParam("id") Long id) {
        personnelHomeworkService.deletePersonnelHomework(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得人员作业")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-homework:query')")
    public CommonResult<PersonnelHomeworkRespVO> getPersonnelHomework(@RequestParam("id") Long id) {
        PersonnelHomeworkDO personnelHomework = personnelHomeworkService.getPersonnelHomework(id);
        return success(BeanUtils.toBean(personnelHomework, PersonnelHomeworkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得人员作业分页")
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-homework:query')")
    public CommonResult<PageResult<PersonnelHomeworkRespVO>> getPersonnelHomeworkPage(@Valid PersonnelHomeworkPageReqVO pageReqVO) {
        PageResult<PersonnelHomeworkDO> pageResult = personnelHomeworkService.getPersonnelHomeworkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PersonnelHomeworkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出人员作业 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-homework:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPersonnelHomeworkExcel(@Valid PersonnelHomeworkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PersonnelHomeworkDO> list = personnelHomeworkService.getPersonnelHomeworkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "人员作业.xls", "数据", PersonnelHomeworkRespVO.class,
                        BeanUtils.toBean(list, PersonnelHomeworkRespVO.class));
    }

}