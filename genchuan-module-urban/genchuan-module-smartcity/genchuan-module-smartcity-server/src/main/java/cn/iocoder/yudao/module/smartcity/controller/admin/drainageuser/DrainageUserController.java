package cn.iocoder.yudao.module.smartcity.controller.admin.drainageuser;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.drainageuser.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainageuser.DrainageUserDO;
import cn.iocoder.yudao.module.smartcity.service.drainageuser.DrainageUserService;

@Tag(name = "管理后台 - 排水户信息")
@RestController
@RequestMapping("/smartcity/drainage-user")
@Validated
public class DrainageUserController {

    @Resource
    private DrainageUserService drainageUserService;

    @PostMapping("/create")
    @Operation(summary = "创建排水户信息")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-user:create')")
    public CommonResult<Long> createDrainageUser(@Valid @RequestBody DrainageUserSaveReqVO createReqVO) {
        return success(drainageUserService.createDrainageUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新排水户信息")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-user:update')")
    public CommonResult<Boolean> updateDrainageUser(@Valid @RequestBody DrainageUserSaveReqVO updateReqVO) {
        drainageUserService.updateDrainageUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除排水户信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-user:delete')")
    public CommonResult<Boolean> deleteDrainageUser(@RequestParam("id") Long id) {
        drainageUserService.deleteDrainageUser(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得排水户信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-user:query')")
    public CommonResult<DrainageUserRespVO> getDrainageUser(@RequestParam("id") Long id) {
        DrainageUserDO drainageUser = drainageUserService.getDrainageUser(id);
        return success(BeanUtils.toBean(drainageUser, DrainageUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得排水户信息分页")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-user:query')")
    public CommonResult<PageResult<DrainageUserRespVO>> getDrainageUserPage(@Valid DrainageUserPageReqVO pageReqVO) {
        PageResult<DrainageUserDO> pageResult = drainageUserService.getDrainageUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DrainageUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出排水户信息 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDrainageUserExcel(@Valid DrainageUserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DrainageUserDO> list = drainageUserService.getDrainageUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "排水户信息.xls", "数据", DrainageUserRespVO.class,
                        BeanUtils.toBean(list, DrainageUserRespVO.class));
    }

}