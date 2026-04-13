package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dutymgmt.DutyMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.dutymgmt.DutyMgmtService;

@Tag(name = "学生管理后台 - 值班管理")
@RestController
@RequestMapping("/studentmgmt/duty-mgmt")
@Validated
public class DutyMgmtController {

    @Resource
    private DutyMgmtService dutyMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建值班管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:create')")
    public CommonResult<Long> createDutyMgmt(@Valid @RequestBody DutyMgmtSaveReqVO createReqVO) {
        return success(dutyMgmtService.createDutyMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新值班管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:update')")
    public CommonResult<Boolean> updateDutyMgmt(@Valid @RequestBody DutyMgmtSaveReqVO updateReqVO) {
        dutyMgmtService.updateDutyMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除值班管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:delete')")
    public CommonResult<Boolean> deleteDutyMgmt(@RequestParam("id") Long id) {
        dutyMgmtService.deleteDutyMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除值班管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:delete')")
    public CommonResult<Boolean> deleteDutyMgmtList(@RequestParam("ids") List<Long> ids) {
        dutyMgmtService.deleteDutyMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得值班管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:query')")
    public CommonResult<DutyMgmtRespVO> getDutyMgmt(@RequestParam("id") Long id) {
        DutyMgmtDO dutyMgmt = dutyMgmtService.getDutyMgmt(id);
        return success(BeanUtils.toBean(dutyMgmt, DutyMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得值班管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:query')")
    public CommonResult<PageResult<DutyMgmtRespVO>> getDutyMgmtPage(@Valid DutyMgmtPageReqVO pageReqVO) {
        PageResult<DutyMgmtDO> pageResult = dutyMgmtService.getDutyMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DutyMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出值班管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDutyMgmtExcel(@Valid DutyMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DutyMgmtDO> list = dutyMgmtService.getDutyMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "值班管理.xls", "数据", DutyMgmtRespVO.class,
                        BeanUtils.toBean(list, DutyMgmtRespVO.class));
    }

}