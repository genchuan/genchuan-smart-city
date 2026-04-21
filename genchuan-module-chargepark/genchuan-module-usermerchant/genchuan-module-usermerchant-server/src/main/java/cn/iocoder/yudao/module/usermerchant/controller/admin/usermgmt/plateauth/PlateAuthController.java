package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth;

import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarChartReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarChartRespVO;
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

import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.plateauth.PlateAuthDO;
import cn.iocoder.yudao.module.usermerchant.service.usermgmt.plateauth.PlateAuthService;

@Tag(name = "管理后台 - 车牌认证")
@RestController
@RequestMapping("/usermerchant/plate-auth")
@Validated
public class PlateAuthController {

    @Resource
    private PlateAuthService plateAuthService;

    @GetMapping("/page")
    @Operation(summary = "获得车牌认证分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:query')")
    public CommonResult<PageResult<PlateAuthPageRespVO>> getPlateAuthPage(@Valid PlateAuthPageReqVO pageReqVO) {
        PageResult<PlateAuthDO> pageResult = plateAuthService.getPlateAuthPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PlateAuthPageRespVO.class));
    }

    @PutMapping("/batch-audit")
    @Operation(summary = "批量审核车牌")
    @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:batch-audit')")
    public CommonResult<Boolean> batchAuditPlate(@Valid @RequestBody PlateAuthSaveReqVO updateReqVO) {
        plateAuthService.batchUpdatePlateAuth(updateReqVO);
        return success(true);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车牌认证 Excel")
    @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPlateAuthExcel(@Valid PlateAuthPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PlateAuthDO> list = plateAuthService.getPlateAuthPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车牌认证.xls", "数据", PlateAuthPageRespVO.class,
                BeanUtils.toBean(list, PlateAuthPageRespVO.class));
    }

    @PutMapping("/approve")
    @Operation(summary = "审核通过")
    @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:approve')")
    public CommonResult<Boolean> approve(@Valid @RequestBody PlateAuthSaveReqVO reqVO) {
        reqVO.setAuditResult("通过");
        plateAuthService.batchUpdatePlateAuth(reqVO);
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "审核驳回")
    @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:reject')")
    public CommonResult<Boolean> reject(@Valid @RequestBody PlateAuthSaveReqVO reqVO) {
        reqVO.setAuditResult("驳回");
        plateAuthService.batchUpdatePlateAuth(reqVO);
        return success(true);
    }

    @PutMapping("/reauth")
    @Operation(summary = "重新认证")
    @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:reauth')")
    public CommonResult<Boolean> reauth(@Valid @RequestBody PlateAuthSaveReqVO reqVO) {
        reqVO.setAuditResult("待审核");
        reqVO.setAuditRemark(null);
        plateAuthService.batchUpdatePlateAuth(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "车牌认证统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:query')")
    public CommonResult<PlateAuthChartRespVO> getPlateAuthChart(@Valid PlateAuthChartReqVO chartReqVO) {
        return success(plateAuthService.getPlateAuthChart(chartReqVO));
    }
//——————————————————————————————————————————————————————//
//    @PostMapping("/create")
//    @Operation(summary = "创建车牌认证")
//    @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:create')")
//    public CommonResult<Long> createPlateAuth(@Valid @RequestBody PlateAuthSaveReqVO createReqVO) {
//        return success(plateAuthService.createPlateAuth(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新车牌认证")
//    @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:update')")
//    public CommonResult<Boolean> updatePlateAuth(@Valid @RequestBody PlateAuthSaveReqVO updateReqVO) {
//        plateAuthService.updatePlateAuth(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除车牌认证")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:delete')")
//    public CommonResult<Boolean> deletePlateAuth(@RequestParam("id") Long id) {
//        plateAuthService.deletePlateAuth(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除车牌认证")
//                @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:delete')")
//    public CommonResult<Boolean> deletePlateAuthList(@RequestParam("ids") List<Long> ids) {
//        plateAuthService.deletePlateAuthListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得车牌认证")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('usermerchant:plate-auth:query')")
//    public CommonResult<PlateAuthPageRespVO> getPlateAuth(@RequestParam("id") Long id) {
//        PlateAuthDO plateAuth = plateAuthService.getPlateAuth(id);
//        return success(BeanUtils.toBean(plateAuth, PlateAuthPageRespVO.class));
//    }

}