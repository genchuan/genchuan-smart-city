package cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount.vo.ParkResourceAccountPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount.vo.ParkResourceAccountRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount.vo.ParkResourceAccountSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.resourceaccount.ParkResourceAccountDO;
import cn.iocoder.yudao.module.industry.service.park.asset.resourceaccount.ParkResourceAccountService;

@Tag(name = "管理后台 - 资源台账")
@RestController
@RequestMapping("/industry/park-resource-account")
@Validated
public class ParkResourceAccountController {

    @Resource
    private ParkResourceAccountService parkResourceAccountService;

    @PostMapping("/create")
    @Operation(summary = "创建资源台账")
    @PreAuthorize("@ss.hasPermission('industry:park-resource-account:create')")
    public CommonResult<Long> createParkResourceAccount(@Valid @RequestBody ParkResourceAccountSaveReqVO createReqVO) {
        return success(parkResourceAccountService.createParkResourceAccount(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资源台账")
    @PreAuthorize("@ss.hasPermission('industry:park-resource-account:update')")
    public CommonResult<Boolean> updateParkResourceAccount(@Valid @RequestBody ParkResourceAccountSaveReqVO updateReqVO) {
        parkResourceAccountService.updateParkResourceAccount(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资源台账")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-resource-account:delete')")
    public CommonResult<Boolean> deleteParkResourceAccount(@RequestParam("id") Long id) {
        parkResourceAccountService.deleteParkResourceAccount(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资源台账")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-resource-account:query')")
    public CommonResult<ParkResourceAccountRespVO> getParkResourceAccount(@RequestParam("id") Long id) {
        ParkResourceAccountDO parkResourceAccount = parkResourceAccountService.getParkResourceAccount(id);
        return success(BeanUtils.toBean(parkResourceAccount, ParkResourceAccountRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资源台账分页")
    @PreAuthorize("@ss.hasPermission('industry:park-resource-account:query')")
    public CommonResult<PageResult<ParkResourceAccountRespVO>> getParkResourceAccountPage(@Valid ParkResourceAccountPageReqVO pageReqVO) {
        PageResult<ParkResourceAccountDO> pageResult = parkResourceAccountService.getParkResourceAccountPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkResourceAccountRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资源台账 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-resource-account:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkResourceAccountExcel(@Valid ParkResourceAccountPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkResourceAccountDO> list = parkResourceAccountService.getParkResourceAccountPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资源台账.xls", "数据", ParkResourceAccountRespVO.class,
                        BeanUtils.toBean(list, ParkResourceAccountRespVO.class));
    }

}