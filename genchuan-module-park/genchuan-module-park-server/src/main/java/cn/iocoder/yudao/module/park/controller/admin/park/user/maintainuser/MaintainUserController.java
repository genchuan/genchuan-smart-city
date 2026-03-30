package cn.iocoder.yudao.module.park.controller.admin.park.user.maintainuser;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainuser.vo.MaintainUserPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainuser.vo.MaintainUserRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainuser.vo.MaintainUserSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.maintainuser.MaintainUserDO;
import cn.iocoder.yudao.module.park.service.park.user.maintainuser.MaintainUserService;
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


@Tag(name = "管理后台 - 运维人员")
@RestController
@RequestMapping("/park/maintain-user")
@Validated
public class MaintainUserController {

    @Resource
    private MaintainUserService maintainUserService;

    @PostMapping("/create")
    @Operation(summary = "创建运维人员")
    @PreAuthorize("@ss.hasPermission('park:maintain-user:create')")
    public CommonResult<Long> createMaintainUser(@Valid @RequestBody MaintainUserSaveReqVO createReqVO) {
        return success(maintainUserService.createMaintainUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新运维人员")
    @PreAuthorize("@ss.hasPermission('park:maintain-user:update')")
    public CommonResult<Boolean> updateMaintainUser(@Valid @RequestBody MaintainUserSaveReqVO updateReqVO) {
        maintainUserService.updateMaintainUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除运维人员")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:maintain-user:delete')")
    public CommonResult<Boolean> deleteMaintainUser(@RequestParam("id") Long id) {
        maintainUserService.deleteMaintainUser(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得运维人员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:maintain-user:query')")
    public CommonResult<MaintainUserRespVO> getMaintainUser(@RequestParam("id") Long id) {
        MaintainUserDO maintainUser = maintainUserService.getMaintainUser(id);
        return success(BeanUtils.toBean(maintainUser, MaintainUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得运维人员分页")
    @PreAuthorize("@ss.hasPermission('park:maintain-user:query')")
    public CommonResult<PageResult<MaintainUserRespVO>> getMaintainUserPage(@Valid MaintainUserPageReqVO pageReqVO) {
        PageResult<MaintainUserDO> pageResult = maintainUserService.getMaintainUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MaintainUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出运维人员 Excel")
    @PreAuthorize("@ss.hasPermission('park:maintain-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMaintainUserExcel(@Valid MaintainUserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MaintainUserDO> list = maintainUserService.getMaintainUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "运维人员.xls", "数据", MaintainUserRespVO.class,
                        BeanUtils.toBean(list, MaintainUserRespVO.class));
    }

}
