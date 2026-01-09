package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity.vo.ParkDiscountActivityPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity.vo.ParkDiscountActivityRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity.vo.ParkDiscountActivitySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkdiscountactivity.ParkDiscountActivityDO;
import cn.iocoder.yudao.module.industry.service.park.pay.parkdiscountactivity.ParkDiscountActivityService;
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



@Tag(name = "管理后台 - 优惠活动")
@RestController
@RequestMapping("/industry/park-discount-activity")
@Validated
public class ParkDiscountActivityController {

    @Resource
    private ParkDiscountActivityService parkDiscountActivityService;

    @PostMapping("/create")
    @Operation(summary = "创建优惠活动")
    @PreAuthorize("@ss.hasPermission('industry:park-discount-activity:create')")
    public CommonResult<Long> createParkDiscountActivity(@Valid @RequestBody ParkDiscountActivitySaveReqVO createReqVO) {
        return success(parkDiscountActivityService.createParkDiscountActivity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新优惠活动")
    @PreAuthorize("@ss.hasPermission('industry:park-discount-activity:update')")
    public CommonResult<Boolean> updateParkDiscountActivity(@Valid @RequestBody ParkDiscountActivitySaveReqVO updateReqVO) {
        parkDiscountActivityService.updateParkDiscountActivity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除优惠活动")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-discount-activity:delete')")
    public CommonResult<Boolean> deleteParkDiscountActivity(@RequestParam("id") Long id) {
        parkDiscountActivityService.deleteParkDiscountActivity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得优惠活动")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-discount-activity:query')")
    public CommonResult<ParkDiscountActivityRespVO> getParkDiscountActivity(@RequestParam("id") Long id) {
        ParkDiscountActivityDO parkDiscountActivity = parkDiscountActivityService.getParkDiscountActivity(id);
        return success(BeanUtils.toBean(parkDiscountActivity, ParkDiscountActivityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得优惠活动分页")
    @PreAuthorize("@ss.hasPermission('industry:park-discount-activity:query')")
    public CommonResult<PageResult<ParkDiscountActivityRespVO>> getParkDiscountActivityPage(@Valid ParkDiscountActivityPageReqVO pageReqVO) {
        PageResult<ParkDiscountActivityDO> pageResult = parkDiscountActivityService.getParkDiscountActivityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkDiscountActivityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出优惠活动 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-discount-activity:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkDiscountActivityExcel(@Valid ParkDiscountActivityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkDiscountActivityDO> list = parkDiscountActivityService.getParkDiscountActivityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "优惠活动.xls", "数据", ParkDiscountActivityRespVO.class,
                        BeanUtils.toBean(list, ParkDiscountActivityRespVO.class));
    }

}
