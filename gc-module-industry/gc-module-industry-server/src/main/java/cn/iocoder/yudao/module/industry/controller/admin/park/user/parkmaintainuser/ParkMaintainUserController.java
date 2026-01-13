package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainuser;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainuser.vo.ParkMaintainUserPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainuser.vo.ParkMaintainUserRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainuser.vo.ParkMaintainUserSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmaintainuser.ParkMaintainUserDO;
import cn.iocoder.yudao.module.industry.service.park.user.parkmaintainuser.ParkMaintainUserService;
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


@Tag(name = "漳州停车管理-用户商户域 - 运维人员")
@RestController
@RequestMapping("/industry/park-maintain-user")
@Validated
public class ParkMaintainUserController {

    @Resource
    private ParkMaintainUserService parkMaintainUserService;

    @PostMapping("/create")
    @Operation(summary = "创建运维人员")
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-user:create')")
    public CommonResult<Long> createParkMaintainUser(@Valid @RequestBody ParkMaintainUserSaveReqVO createReqVO) {
        return success(parkMaintainUserService.createParkMaintainUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新运维人员")
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-user:update')")
    public CommonResult<Boolean> updateParkMaintainUser(@Valid @RequestBody ParkMaintainUserSaveReqVO updateReqVO) {
        parkMaintainUserService.updateParkMaintainUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除运维人员")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-user:delete')")
    public CommonResult<Boolean> deleteParkMaintainUser(@RequestParam("id") Long id) {
        parkMaintainUserService.deleteParkMaintainUser(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得运维人员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-user:query')")
    public CommonResult<ParkMaintainUserRespVO> getParkMaintainUser(@RequestParam("id") Long id) {
        ParkMaintainUserDO parkMaintainUser = parkMaintainUserService.getParkMaintainUser(id);
        return success(BeanUtils.toBean(parkMaintainUser, ParkMaintainUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得运维人员分页")
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-user:query')")
    public CommonResult<PageResult<ParkMaintainUserRespVO>> getParkMaintainUserPage(@Valid ParkMaintainUserPageReqVO pageReqVO) {
        PageResult<ParkMaintainUserDO> pageResult = parkMaintainUserService.getParkMaintainUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkMaintainUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出运维人员 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-maintain-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkMaintainUserExcel(@Valid ParkMaintainUserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkMaintainUserDO> list = parkMaintainUserService.getParkMaintainUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "运维人员.xls", "数据", ParkMaintainUserRespVO.class,
                        BeanUtils.toBean(list, ParkMaintainUserRespVO.class));
    }

}
