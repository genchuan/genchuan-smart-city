package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkuser;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkuser.vo.ParkUserPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkuser.vo.ParkUserRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkuser.vo.ParkUserSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkuser.ParkUserDO;
import cn.iocoder.yudao.module.industry.service.park.user.parkuser.ParkUserService;
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



@Tag(name = "漳州停车管理 - 停车系统用户")
@RestController
@RequestMapping("/industry/park-user")
@Validated
public class ParkUserController {

    @Resource
    private ParkUserService parkUserService;

    @PostMapping("/create")
    @Operation(summary = "创建停车系统用户")
    @PreAuthorize("@ss.hasPermission('industry:park-user:create')")
    public CommonResult<Long> createParkUser(@Valid @RequestBody ParkUserSaveReqVO createReqVO) {
        return success(parkUserService.createParkUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新停车系统用户")
    @PreAuthorize("@ss.hasPermission('industry:park-user:update')")
    public CommonResult<Boolean> updateParkUser(@Valid @RequestBody ParkUserSaveReqVO updateReqVO) {
        parkUserService.updateParkUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除停车系统用户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-user:delete')")
    public CommonResult<Boolean> deleteParkUser(@RequestParam("id") Long id) {
        parkUserService.deleteParkUser(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车系统用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-user:query')")
    public CommonResult<ParkUserRespVO> getParkUser(@RequestParam("id") Long id) {
        ParkUserDO parkUser = parkUserService.getParkUser(id);
        return success(BeanUtils.toBean(parkUser, ParkUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得停车系统用户分页")
    @PreAuthorize("@ss.hasPermission('industry:park-user:query')")
    public CommonResult<PageResult<ParkUserRespVO>> getParkUserPage(@Valid ParkUserPageReqVO pageReqVO) {
        PageResult<ParkUserDO> pageResult = parkUserService.getParkUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出停车系统用户 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkUserExcel(@Valid ParkUserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkUserDO> list = parkUserService.getParkUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "停车系统用户.xls", "数据", ParkUserRespVO.class,
                        BeanUtils.toBean(list, ParkUserRespVO.class));
    }

}
