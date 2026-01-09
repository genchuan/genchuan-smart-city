package cn.iocoder.yudao.module.system.controller.admin.user;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.datapermission.core.annotation.DataPermission;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileUpdateAvatarReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import cn.iocoder.yudao.module.system.convert.user.UserConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.PostDO;
import cn.iocoder.yudao.module.system.dal.dataobject.permission.RoleDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.framework.file.FileUploadService;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.dept.PostService;
import cn.iocoder.yudao.module.system.service.permission.PermissionService;
import cn.iocoder.yudao.module.system.service.permission.RoleService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 用户个人中心")
@RestController
@RequestMapping("/system/user/profile")
@Validated
@Slf4j
public class UserProfileController {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PostService postService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;

    @Resource
    private FileUploadService fileUploadService;

    @GetMapping("/get")
    @Operation(summary = "获得登录用户信息")
    @DataPermission(enable = false) // 关闭数据权限，避免只查看自己时，查询不到部门。
    public CommonResult<UserProfileRespVO> getUserProfile() {
        // 获得用户基本信息
        AdminUserDO user = userService.getUser(getLoginUserId());
        // 获得用户角色
        List<RoleDO> userRoles = roleService.getRoleListFromCache(permissionService.getUserRoleIdListByUserId(user.getId()));
        // 获得部门信息
        DeptDO dept = user.getDeptId() != null ? deptService.getDept(user.getDeptId()) : null;
        // 获得岗位信息
        List<PostDO> posts = CollUtil.isNotEmpty(user.getPostIds()) ? postService.getPostList(user.getPostIds()) : null;
        return success(UserConvert.INSTANCE.convert(user, userRoles, dept, posts));
    }

    @PutMapping("/update")
    @Operation(summary = "修改用户个人信息")
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateReqVO reqVO) {
        userService.updateUserProfile(getLoginUserId(), reqVO);
        return success(true);
    }

    @PutMapping("/update-password")
    @Operation(summary = "修改用户个人密码")
    public CommonResult<Boolean> updateUserProfilePassword(@Valid @RequestBody UserProfileUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(getLoginUserId(), reqVO);
        return success(true);
    }

    @PostMapping("/update-avatar")
    @Operation(summary = "上传用户头像")
    public CommonResult<String> updateUserAvatar(@RequestParam("avatarFile") MultipartFile avatarFile) {
        try {
            // 1. 上传文件到MinIO
            String avatarUrl = fileUploadService.uploadAvatar(avatarFile);

            // 2. 更新用户头像
            userService.updateUserAvatar(getLoginUserId(), avatarUrl);

            return success(avatarUrl);
        } catch (Exception e) {
            log.error("上传头像失败", e);
            return CommonResult.error(500, "上传头像失败: " + e.getMessage());
        }
    }

    @PostMapping("/update-avatar-url")
    @Operation(summary = "通过URL更新用户头像")
    public CommonResult<Boolean> updateUserAvatarByUrl(@Valid @RequestBody UserProfileUpdateAvatarReqVO reqVO) {
        userService.updateUserAvatar(getLoginUserId(), reqVO.getAvatarUrl());
        return success(true);
    }

}
