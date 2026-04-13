package cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.honormgmt.HonorMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.honormgmt.HonorMgmtService;

@Tag(name = "学生管理后台 - 荣誉管理")
@RestController
@RequestMapping("/studentmgmt/honor-mgmt")
@Validated
public class HonorMgmtController {

    @Resource
    private HonorMgmtService honorMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建荣誉管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:honor-mgmt:create')")
    public CommonResult<Long> createHonorMgmt(@Valid @RequestBody HonorMgmtSaveReqVO createReqVO) {
        return success(honorMgmtService.createHonorMgmt(createReqVO));
    }


    @PutMapping("/push")
    @Operation(summary = "推送荣誉")
    @PreAuthorize("@ss.hasPermission('studentmgmt:honor-mgmt:update')")
    public CommonResult<Boolean> pushHonorMgmt(@Valid @RequestBody HonorMgmtPushReqVO reqVO) {
        boolean isSuccess = honorMgmtService.pushHonorMgmt(reqVO);
        if (isSuccess) {
            return success(true);
        }
        else {
            return error(500, "推送失败！请检查数据！");
        }

    }


    @PutMapping("/update")
    @Operation(summary = "更新荣誉管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:honor-mgmt:update')")
    public CommonResult<Boolean> updateHonorMgmt(@Valid @RequestBody HonorMgmtSaveReqVO updateReqVO) {
        honorMgmtService.updateHonorMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除荣誉管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:honor-mgmt:delete')")
    public CommonResult<Boolean> deleteHonorMgmt(@RequestParam("id") Long id) {
        honorMgmtService.deleteHonorMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除荣誉管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:honor-mgmt:delete')")
    public CommonResult<Boolean> deleteHonorMgmtList(@RequestParam("ids") List<Long> ids) {
        honorMgmtService.deleteHonorMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得荣誉管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:honor-mgmt:query')")
    public CommonResult<HonorMgmtRespVO> getHonorMgmt(@RequestParam("id") Long id) {
        HonorMgmtDO honorMgmt = honorMgmtService.getHonorMgmt(id);
        return success(BeanUtils.toBean(honorMgmt, HonorMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得荣誉管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:honor-mgmt:query')")
    public CommonResult<PageResult<HonorMgmtPageRespVO>> getHonorMgmtPage(@Valid HonorMgmtPageReqVO pageReqVO) {
        PageResult<HonorMgmtPageRespVO> pageResult = honorMgmtService.getHonorMgmtJoinPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/chart")
    @Operation(summary = "荣誉信息分布看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:honor-mgmt:query')")
    public CommonResult<HonorMgmtChartRespVO> getChart(@Valid HonorMgmtChartReqVO reqVO) {
        HonorMgmtChartRespVO respVO = honorMgmtService.getChart(reqVO);
        return success(respVO);
    }


    @PutMapping("/audit'")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "审核荣誉")
    @PreAuthorize("@ss.hasPermission('studentmgmt:honor-mgmt:audit')")
    public CommonResult<Boolean> audit(@Valid HonorMgmtAuditReqVO reqVO) {
        LoginUser loginUser = getLoginUser();
        boolean isSuccess = honorMgmtService.audit(reqVO, loginUser);
        return success(isSuccess);
    }

    @GetMapping("/export")
    @Operation(summary = "导出荣誉管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:honor-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHonorMgmtExcel(@Valid HonorMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HonorMgmtDO> list = honorMgmtService.getHonorMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "荣誉管理.xls", "数据", HonorMgmtRespVO.class,
                        BeanUtils.toBean(list, HonorMgmtRespVO.class));
    }

}