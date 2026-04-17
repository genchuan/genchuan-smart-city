package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar;

import io.swagger.v3.oas.annotations.Parameters;
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

import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.usercar.UserCarDO;
import cn.iocoder.yudao.module.usermerchant.service.usermgmt.usercar.UserCarService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 用户车辆")
@RestController
@RequestMapping("/usermerchant/user-car")
@Validated
public class UserCarController {

    @Resource
    private UserCarService userCarService;

    @GetMapping("/page")
    @Operation(summary = "获得用户车辆分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-car:query')")
    public CommonResult<PageResult<UserCarPageRespVO>> getUserCarPage(@Valid UserCarPageReqVO pageReqVO) {
        PageResult<UserCarDO> pageResult = userCarService.getUserCarPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserCarPageRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建用户车辆")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-car:create')")
    public CommonResult<Boolean> createUserCar(@Valid @RequestBody UserCarCreateReqVO createReqVO) {
        return success(userCarService.createUserCar(createReqVO));
    }

    @PostMapping("/import")
    @Operation(summary = "导入用户车辆")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('usermerchant:user-car:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<UserCarImportExcelVO> list = ExcelUtils.read(file, UserCarImportExcelVO.class);
        return success(userCarService.importUserCar(list, updateSupport));
    }

    @GetMapping("/export")
    @Operation(summary = "导出用户车辆")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-car:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserCarExcel(@Valid UserCarPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserCarDO> list = userCarService.getUserCarPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "用户车辆.xls", "数据", UserCarPageRespVO.class,
                        BeanUtils.toBean(list, UserCarPageRespVO.class));
    }

    @PutMapping("/approve")
    @Operation(summary = "审核通过")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-car:approve')")
    public CommonResult<Boolean> updateUserCar(@Valid @RequestBody UserCarApproveReqVO reqVO) {
        userCarService.auditUserCar(reqVO.getIds(),reqVO.getAuditRemark(), "已绑定");
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "审核驳回")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-car:reject')")
    public CommonResult<Boolean> updateUserCar(@Valid @RequestBody UserCarRejectReqVO reqVO) {
        userCarService.auditUserCar(reqVO.getIds(),reqVO.getAuditRemark(), "已驳回");
        return success(true);
    }

    @PutMapping("/unbind")
    @Operation(summary = "车辆解绑")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-car:unbind')")
    public CommonResult<Boolean> updateUserCar(@Valid @RequestBody UserCarUnbindReqVO reqVO) {
        userCarService.auditUserCar(reqVO.getIds(),null, "已解绑");
        return success(true);
    }

    @PutMapping("/rebind")
    @Operation(summary = "车辆重绑")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-car:rebind')")
    public CommonResult<Boolean> updateUserCar(@Valid @RequestBody UserCarRebindReqVO reqVO) {
        userCarService.auditUserCar(reqVO.getIds(),null, "待审核");
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户车辆")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-car:query')")
    public CommonResult<UserCarPageRespVO> getUserCar(@RequestParam("id") Long id) {
        UserCarDO userCar = userCarService.getUserCar(id);
        return success(BeanUtils.toBean(userCar, UserCarPageRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户车辆")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-car:update')")
    public CommonResult<Boolean> updateUserCar(@Valid @RequestBody UserCarUpdateReqVO updateReqVO) {
        userCarService.updateUserCar(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "用户车辆统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:user-car:query')")
    public CommonResult<UserCarChartRespVO> getUserCarChart(@Valid UserCarChartReqVO chartReqVO) {
        return success(userCarService.getUserCarChart(chartReqVO));
    }

}