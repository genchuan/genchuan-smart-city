package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchant;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchant.vo.ParkMerchantPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchant.vo.ParkMerchantRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchant.vo.ParkMerchantSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmerchant.ParkMerchantDO;
import cn.iocoder.yudao.module.industry.service.park.user.parkmerchant.ParkMerchantService;
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


@Tag(name = "漳州停车管理 - 商户")
@RestController
@RequestMapping("/industry/park-merchant")
@Validated
public class ParkMerchantController {

    @Resource
    private ParkMerchantService parkMerchantService;

    @PostMapping("/create")
    @Operation(summary = "创建商户")
    @PreAuthorize("@ss.hasPermission('industry:park-merchant:create')")
    public CommonResult<Long> createParkMerchant(@Valid @RequestBody ParkMerchantSaveReqVO createReqVO) {
        return success(parkMerchantService.createParkMerchant(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商户")
    @PreAuthorize("@ss.hasPermission('industry:park-merchant:update')")
    public CommonResult<Boolean> updateParkMerchant(@Valid @RequestBody ParkMerchantSaveReqVO updateReqVO) {
        parkMerchantService.updateParkMerchant(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-merchant:delete')")
    public CommonResult<Boolean> deleteParkMerchant(@RequestParam("id") Long id) {
        parkMerchantService.deleteParkMerchant(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-merchant:query')")
    public CommonResult<ParkMerchantRespVO> getParkMerchant(@RequestParam("id") Long id) {
        ParkMerchantDO parkMerchant = parkMerchantService.getParkMerchant(id);
        return success(BeanUtils.toBean(parkMerchant, ParkMerchantRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商户分页")
    @PreAuthorize("@ss.hasPermission('industry:park-merchant:query')")
    public CommonResult<PageResult<ParkMerchantRespVO>> getParkMerchantPage(@Valid ParkMerchantPageReqVO pageReqVO) {
        PageResult<ParkMerchantDO> pageResult = parkMerchantService.getParkMerchantPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkMerchantRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出商户 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-merchant:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkMerchantExcel(@Valid ParkMerchantPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkMerchantDO> list = parkMerchantService.getParkMerchantPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "商户.xls", "数据", ParkMerchantRespVO.class,
                        BeanUtils.toBean(list, ParkMerchantRespVO.class));
    }

}
