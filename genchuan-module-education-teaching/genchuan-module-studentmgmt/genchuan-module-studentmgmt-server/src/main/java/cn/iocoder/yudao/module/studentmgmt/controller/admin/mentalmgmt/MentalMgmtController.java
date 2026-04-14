package cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt;

import cn.iocoder.yudao.framework.security.core.LoginUser;
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
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.mentalmgmt.MentalMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.mentalmgmt.MentalMgmtService;

@Tag(name = "学生管理后台 - 心理管理")
@RestController
@RequestMapping("/studentmgmt/mental-mgmt")
@Validated
public class MentalMgmtController {

    @Resource
    private MentalMgmtService mentalMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建心理管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:mental-mgmt:create')")
    public CommonResult<Long> createMentalMgmt(@Valid @RequestBody MentalMgmtSaveReqVO createReqVO) {
        return success(mentalMgmtService.createMentalMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新心理管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:mental-mgmt:update')")
    public CommonResult<Boolean> updateMentalMgmt(@Valid @RequestBody MentalMgmtSaveReqVO updateReqVO) {
        mentalMgmtService.updateMentalMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除心理管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:mental-mgmt:delete')")
    public CommonResult<Boolean> deleteMentalMgmt(@RequestParam("id") Long id) {
        mentalMgmtService.deleteMentalMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除心理管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:mental-mgmt:delete')")
    public CommonResult<Boolean> deleteMentalMgmtList(@RequestParam("ids") List<Long> ids) {
        mentalMgmtService.deleteMentalMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得心理管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:mental-mgmt:query')")
    public CommonResult<MentalMgmtRespVO> getMentalMgmt(@RequestParam("id") Long id) {
        MentalMgmtDO mentalMgmt = mentalMgmtService.getMentalMgmt(id);
        return success(BeanUtils.toBean(mentalMgmt, MentalMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得心理管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:mental-mgmt:query')")
    public CommonResult<PageResult<MentalMgmtJoinPageRespVO>> getMentalMgmtPage(@Valid MentalMgmtPageReqVO pageReqVO) {
        PageResult<MentalMgmtJoinPageRespVO> pageResult = mentalMgmtService.getMentalMgmtJoinPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MentalMgmtJoinPageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出心理管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:mental-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMentalMgmtExcel(@Valid MentalMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MentalMgmtDO> list = mentalMgmtService.getMentalMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "心理管理.xls", "数据", MentalMgmtRespVO.class,
                        BeanUtils.toBean(list, MentalMgmtRespVO.class));
    }

    @PutMapping("/consult")
    @Operation(summary = "预约")
    @PreAuthorize("@ss.hasPermission('studentmgmt:mental-mgmt:consult')")
    public CommonResult<Boolean> consult(@Valid @RequestBody MentalMgmtConsultReqVO reqVO) {
        // 获取当前用户
        LoginUser loginUser = getLoginUser();
        boolean isSuccess = mentalMgmtService.consult(reqVO, loginUser);
        return success(isSuccess);
    }

    @PutMapping("/intervene")
    @Operation(summary = "跟进")
    @PreAuthorize("@ss.hasPermission('studentmgmt:mental-mgmt:intervene')")
    public CommonResult<Boolean> intervene(@Valid @RequestBody MentalMgmtInterveneReqVO reqVO) {
        boolean isSuccess = mentalMgmtService.intervene(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/updateStatus")
    @Operation(summary = "状态更新")
    @PreAuthorize("@ss.hasPermission('studentmgmt:mental-mgmt:intervene')")
    public CommonResult<Boolean> updateStatus(@Valid @RequestBody MentalMgmtUpdateStatusReqVO reqVO) {
        boolean isSuccess = mentalMgmtService.updateStatus(reqVO);
        return success(isSuccess);
    }


}