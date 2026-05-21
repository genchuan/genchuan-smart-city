package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo;

import cn.iocoder.yudao.module.usermerchant.api.usermgmt.userinfo.dto.UserInfoRespDTO;
import cn.iocoder.yudao.module.usermerchant.api.usermgmt.userinfo.dto.UserSimpleRespDTO;
import com.mzt.logapi.starter.annotation.LogRecord;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import java.util.stream.Collectors;

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
    @ApiAccessLog(operateType = GET)
    public CommonResult<PageResult<UserInfoPageRespVO>> getUserInfoPage(@Valid UserInfoPageReqVO pageReqVO) {
        PageResult<UserInfoDO> pageResult = userInfoService.getUserInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserInfoPageRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "新增用户信息")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:create')")
    @ApiAccessLog(operateType = CREATE)
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
    @ApiAccessLog(operateType = OTHER)
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
        ExcelUtils.write(response, "用户信息.xls", "数据", UserInfoExportRespVO.class,
                        BeanUtils.toBean(list, UserInfoExportRespVO.class));
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用用户")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:disable')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> disableUserInfo(@Valid @RequestBody UserInfoDisableReqVO reqVO) {
        userInfoService.updateUserStatus(reqVO.getIds(), "禁用");
        return success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "启用用户")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:enable')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> enableUserInfo(@Valid @RequestBody UserInfoEnableReqVO reqVO) {
        userInfoService.updateUserStatus(reqVO.getIds(), "正常");
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:query')")
    @ApiAccessLog(operateType = GET)
    public CommonResult<UserInfoPageRespVO> getUserInfo(@RequestParam("id") Long id) {
        UserInfoDO userInfo = userInfoService.getUserInfo(id);
        return success(BeanUtils.toBean(userInfo, UserInfoPageRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户信息")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:update')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> updateUserInfo(@Valid @RequestBody UserInfoUpdateReqVO updateReqVO) {
        userInfoService.updateUserInfo(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "用户信息统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-info:query')")
    @ApiAccessLog(operateType = OTHER)
    public CommonResult<UserInfoChartRespVO> getUserInfoChart(@Valid UserInfoChartReqVO chartReqVO) {
        return success(userInfoService.getUserInfoChart(chartReqVO));
    }

    @GetMapping("/list-simple")
    @Operation(summary = "[RPC]获取所有用户简要列表")
    public CommonResult<List<UserSimpleRespDTO>> getSimpleUserList() {
        // 注意：这里需要查询所有未删除的用户，需要扩展 Service 方法或直接使用 Mapper
        List<UserInfoDO> userList = userInfoService.getAllUsers(); // 需要新增方法
        List<UserSimpleRespDTO> result = userList.stream()
                .map(user -> new UserSimpleRespDTO()
                        .setId(user.getId())
                        .setNickname(user.getNickname()))
                .collect(Collectors.toList());
        return success(result);
    }

    @GetMapping("/get-detail")
    @Operation(summary = "[RPC]获取单个用户完整信息")
    public CommonResult<UserInfoRespDTO> getDetailUser(@RequestParam("userId") Long userId) {
        UserInfoDO user = userInfoService.getUserInfo(userId);
        if (user == null) {
            return CommonResult.error(404, "用户不存在");
        }
        UserInfoRespDTO resp = BeanUtils.toBean(user, UserInfoRespDTO.class);
        return success(resp);
    }

}