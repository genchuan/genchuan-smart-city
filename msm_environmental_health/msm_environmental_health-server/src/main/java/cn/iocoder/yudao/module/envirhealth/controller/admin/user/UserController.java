package cn.iocoder.yudao.module.envirhealth.controller.admin.user;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user.UserOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user.UserPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user.UserRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user.UserSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehicleOptionVO;
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
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.module.envirhealth.service.user.user.UserService;

@Tag(name = "环境卫生管理 - 系统用户")
@RestController
@RequestMapping("/envirhealth/user")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/create")
    @Operation(summary = "创建系统用户")
    @PreAuthorize("@ss.hasPermission('health:user:create')")
    public CommonResult<Long> createUser(@Valid @RequestBody UserSaveReqVO createReqVO) {
        return success(userService.createUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新系统用户")
    @PreAuthorize("@ss.hasPermission('health:user:update')")
    public CommonResult<Boolean> updateUser(@Valid @RequestBody UserSaveReqVO updateReqVO) {
        userService.updateUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除系统用户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('health:user:delete')")
    public CommonResult<Boolean> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得系统用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('health:user:query')")
    public CommonResult<UserRespVO> getUser(@RequestParam("id") Long id) {
        UserDO user = userService.getUser(id);
        return success(BeanUtils.toBean(user, UserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得系统用户分页")
    @PreAuthorize("@ss.hasPermission('health:user:query')")
    public CommonResult<PageResult<UserRespVO>> getUserPage(@Valid UserPageReqVO pageReqVO) {
        PageResult<UserDO> pageResult = userService.getUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出系统用户 Excel")
    @PreAuthorize("@ss.hasPermission('health:user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserExcel(@Valid UserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserDO> list = userService.getUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "系统用户.xls", "数据", UserRespVO.class,
                        BeanUtils.toBean(list, UserRespVO.class));
    }

    /**
     * 获得系统用户下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得系统用户(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:user:query')")
    public CommonResult<List<UserOptionVO>> getUserOptions() {
        return success(userService.getUserOptions());
    }
}
