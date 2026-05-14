package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.*;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.*;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo.UserInfoDO;
import cn.iocoder.yudao.module.usermerchant.service.usermgmt.userinfo.UserInfoService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 用户信息")
@RestController
@RequestMapping("/usermerchant/user-info")
@Validated
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/page")
    @Operation(summary = "获得用户信息分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:query')")
    public CommonResult<PageResult<UserInfoPageRespVO>> getUserInfoPage(@Valid UserInfoPageReqVO pageReqVO) {
        PageResult<UserInfoDO> pageResult = userInfoService.getUserInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserInfoPageRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "新增用户信息")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:create')")
    public CommonResult<Boolean> createUserInfo(@Valid @RequestBody UserInfoCreateReqVO createReqVO) {
        return success(userInfoService.createUserInfo(createReqVO));
    }

    @PostMapping("/import")
    @Operation(summary = "导入用户信息")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file,
                                                          @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<UserInfoImportExcelVO> list = ExcelUtils.read(file, UserInfoImportExcelVO.class);
        return success(userInfoService.importUsers(list, updateSupport));
    }

    @GetMapping("/template")
    @Operation(summary = "下载用户信息导入模板")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:import')")
    public void downloadImportTemplate(HttpServletResponse response) throws IOException {
        List<UserInfoImportExcelVO> emptyList = Collections.emptyList();
        ExcelUtils.write(response, "用户信息导入模板.xlsx", "用户信息", UserInfoImportExcelVO.class, emptyList);
    }

    @GetMapping("/export")
    @Operation(summary = "导出用户信息")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserInfoExcel(@Valid UserInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserInfoDO> list = userInfoService.getUserInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "用户信息.xls", "数据", UserInfoPageRespVO.class,
                        BeanUtils.toBean(list, UserInfoPageRespVO.class));
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用用户")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:disable')")
    public CommonResult<Boolean> disableUserInfo(@Valid @RequestBody UserInfoDisableReqVO reqVO) {
        userInfoService.updateUserStatus(reqVO.getIds(), "禁用");
        return success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "启用用户")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:enable')")
    public CommonResult<Boolean> enableUserInfo(@Valid @RequestBody UserInfoEnableReqVO reqVO) {
        userInfoService.updateUserStatus(reqVO.getIds(), "正常");
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:query')")
    public CommonResult<UserInfoPageRespVO> getUserInfo(@RequestParam("id") Long id) {
        UserInfoDO userInfo = userInfoService.getUserInfo(id);
        return success(BeanUtils.toBean(userInfo, UserInfoPageRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户信息")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:update')")
    public CommonResult<Boolean> updateUserInfo(@Valid @RequestBody UserInfoUpdateReqVO updateReqVO) {
        userInfoService.updateUserInfo(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "用户信息统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:query')")
    public CommonResult<UserInfoChartRespVO> getUserInfoChart(@Valid UserInfoChartReqVO chartReqVO) {
        return success(userInfoService.getUserInfoChart(chartReqVO));
    }

}