package cn.iocoder.yudao.module.envir.controller.admin.personstatus;

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

import cn.iocoder.yudao.module.envir.controller.admin.personstatus.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.personstatus.PersonStatusDO;
import cn.iocoder.yudao.module.envir.service.personstatus.PersonStatusService;

@Tag(name = "管理后台 - 人员状态字典")
@RestController
@RequestMapping("/envir/person-status")
@Validated
public class PersonStatusController {

    @Resource
    private PersonStatusService personStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建人员状态字典")
    @PreAuthorize("@ss.hasPermission('envir:person-status:create')")
    public CommonResult<Long> createPersonStatus(@Valid @RequestBody PersonStatusSaveReqVO createReqVO) {
        return success(personStatusService.createPersonStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新人员状态字典")
    @PreAuthorize("@ss.hasPermission('envir:person-status:update')")
    public CommonResult<Boolean> updatePersonStatus(@Valid @RequestBody PersonStatusSaveReqVO updateReqVO) {
        personStatusService.updatePersonStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人员状态字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:person-status:delete')")
    public CommonResult<Boolean> deletePersonStatus(@RequestParam("id") Long id) {
        personStatusService.deletePersonStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得人员状态字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:person-status:query')")
    public CommonResult<PersonStatusRespVO> getPersonStatus(@RequestParam("id") Long id) {
        PersonStatusDO personStatus = personStatusService.getPersonStatus(id);
        return success(BeanUtils.toBean(personStatus, PersonStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得人员状态字典分页")
    @PreAuthorize("@ss.hasPermission('envir:person-status:query')")
    public CommonResult<PageResult<PersonStatusRespVO>> getPersonStatusPage(@Valid PersonStatusPageReqVO pageReqVO) {
        PageResult<PersonStatusDO> pageResult = personStatusService.getPersonStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PersonStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出人员状态字典 Excel")
    @PreAuthorize("@ss.hasPermission('envir:person-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPersonStatusExcel(@Valid PersonStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PersonStatusDO> list = personStatusService.getPersonStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "人员状态字典.xls", "数据", PersonStatusRespVO.class,
                        BeanUtils.toBean(list, PersonStatusRespVO.class));
    }

}