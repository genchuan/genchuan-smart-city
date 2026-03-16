package cn.iocoder.yudao.module.facility.controller.admin.sysuser;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.facility.controller.admin.sysuser.vo.SysUserPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.sysuser.vo.SysUserRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.sysuser.vo.SysUserSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysuser.SysUserDO;
import cn.iocoder.yudao.module.facility.service.sysuser.SysUserService;
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


@Tag(name = "管理后台 - 系统用户")
@RestController
@RequestMapping("/facility/sys-user")
@Validated
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping("/create")
    @Operation(summary = "创建系统用户")
    @PreAuthorize("@ss.hasPermission('facility:sys-user:create')")
    public CommonResult<Long> createSysUser(@Valid @RequestBody SysUserSaveReqVO createReqVO) {
        return success(sysUserService.createSysUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新系统用户")
    @PreAuthorize("@ss.hasPermission('facility:sys-user:update')")
    public CommonResult<Boolean> updateSysUser(@Valid @RequestBody SysUserSaveReqVO updateReqVO) {
        sysUserService.updateSysUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除系统用户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('facility:sys-user:delete')")
    public CommonResult<Boolean> deleteSysUser(@RequestParam("id") Long id) {
        sysUserService.deleteSysUser(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得系统用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('facility:sys-user:query')")
    public CommonResult<SysUserRespVO> getSysUser(@RequestParam("id") Long id) {
        SysUserDO sysUser = sysUserService.getSysUser(id);
        return success(BeanUtils.toBean(sysUser, SysUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得系统用户分页")
    @PreAuthorize("@ss.hasPermission('facility:sys-user:query')")
    public CommonResult<PageResult<SysUserRespVO>> getSysUserPage(@Valid SysUserPageReqVO pageReqVO) {
        PageResult<SysUserDO> pageResult = sysUserService.getSysUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SysUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出系统用户 Excel")
    @PreAuthorize("@ss.hasPermission('facility:sys-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSysUserExcel(@Valid SysUserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SysUserDO> list = sysUserService.getSysUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "系统用户.xls", "数据", SysUserRespVO.class,
                        BeanUtils.toBean(list, SysUserRespVO.class));
    }

}
