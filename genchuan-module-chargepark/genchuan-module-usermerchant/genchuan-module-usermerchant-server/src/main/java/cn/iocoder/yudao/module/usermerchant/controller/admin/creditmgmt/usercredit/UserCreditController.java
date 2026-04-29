package cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.usercredit;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.usercredit.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.creditmgmt.usercredit.UserCreditDO;
import cn.iocoder.yudao.module.usermerchant.service.creditmgmt.usercredit.UserCreditService;
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

@Tag(name = "管理后台 - 用户信用")
@RestController
@RequestMapping("/usermerchant/user-credit")
@Validated
public class UserCreditController {

    @Resource
    private UserCreditService userCreditService;

    @GetMapping("/page")
    @Operation(summary = "获得用户信用分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-credit:query')")
    public CommonResult<PageResult<UserCreditPageRespVO>> getUserCreditPage(@Valid UserCreditPageReqVO pageReqVO) {
        PageResult<UserCreditDO> pageResult = userCreditService.getUserCreditPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserCreditPageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户信用 Excel")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-credit:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserCreditExcel(@Valid UserCreditPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserCreditDO> list = userCreditService.getUserCreditPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "用户信用.xls", "数据", UserCreditPageRespVO.class,
                BeanUtils.toBean(list, UserCreditPageRespVO.class));
    }

    @PutMapping("/remind")
    @Operation(summary = "用户信用提醒（模拟）")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-credit:remind')")
    public CommonResult<Boolean> remind(@Valid @RequestBody UserCreditRemindReqVO reqVO) {
        userCreditService.remindUserCredit(reqVO.getIds());
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户信用")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-credit:query')")
    public CommonResult<UserCreditPageRespVO> getUserCredit(@RequestParam("id") Long id) {
        UserCreditDO userCredit = userCreditService.getUserCredit(id);
        return success(BeanUtils.toBean(userCredit, UserCreditPageRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "用户信用统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-credit:query')")
    public CommonResult<UserCreditChartRespVO> getUserCreditChart(@Valid UserCreditChartReqVO chartReqVO) {
        return success(userCreditService.getUserCreditChart(chartReqVO));
    }

//    @PostMapping("/create")
//    @Operation(summary = "创建用户信用")
//    @PreAuthorize("@ss.hasPermission('usermerchant:user-credit:create')")
//    public CommonResult<Long> createUserCredit(@Valid @RequestBody UserCreditSaveReqVO createReqVO) {
//        return success(userCreditService.createUserCredit(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新用户信用")
//    @PreAuthorize("@ss.hasPermission('usermerchant:user-credit:update')")
//    public CommonResult<Boolean> updateUserCredit(@Valid @RequestBody UserCreditSaveReqVO updateReqVO) {
//        userCreditService.updateUserCredit(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除用户信用")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:user-credit:delete')")
//    public CommonResult<Boolean> deleteUserCredit(@RequestParam("id") Long id) {
//        userCreditService.deleteUserCredit(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除用户信用")
//                @PreAuthorize("@ss.hasPermission('usermerchant:user-credit:delete')")
//    public CommonResult<Boolean> deleteUserCreditList(@RequestParam("ids") List<Long> ids) {
//        userCreditService.deleteUserCreditListByIds(ids);
//        return success(true);
//    }

}