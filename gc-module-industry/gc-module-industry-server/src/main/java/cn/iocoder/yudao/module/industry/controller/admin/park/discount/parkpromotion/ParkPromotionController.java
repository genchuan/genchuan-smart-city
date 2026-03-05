package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkpromotion;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkpromotion.vo.ParkPromotionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkpromotion.vo.ParkPromotionRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkpromotion.vo.ParkPromotionSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkpromotion.ParkPromotionDO;
import cn.iocoder.yudao.module.industry.service.park.discount.parkpromotion.ParkPromotionService;
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


@Tag(name = "漳州停车管理-收费优惠域 - 优惠活动")
@RestController
@RequestMapping("/industry/park-promotion")
@Validated
public class ParkPromotionController {

    @Resource
    private ParkPromotionService parkPromotionService;

    @PostMapping("/create")
    @Operation(summary = "创建优惠活动")
    @PreAuthorize("@ss.hasPermission('industry:park-promotion:create')")
    public CommonResult<Long> createParkPromotion(@Valid @RequestBody ParkPromotionSaveReqVO createReqVO) {
        return success(parkPromotionService.createParkPromotion(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新优惠活动")
    @PreAuthorize("@ss.hasPermission('industry:park-promotion:update')")
    public CommonResult<Boolean> updateParkPromotion(@Valid @RequestBody ParkPromotionSaveReqVO updateReqVO) {
        parkPromotionService.updateParkPromotion(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除优惠活动")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-promotion:delete')")
    public CommonResult<Boolean> deleteParkPromotion(@RequestParam("id") Long id) {
        parkPromotionService.deleteParkPromotion(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得优惠活动")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-promotion:query')")
    public CommonResult<ParkPromotionRespVO> getParkPromotion(@RequestParam("id") Long id) {
        ParkPromotionDO parkPromotion = parkPromotionService.getParkPromotion(id);
        return success(BeanUtils.toBean(parkPromotion, ParkPromotionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得优惠活动分页")
    @PreAuthorize("@ss.hasPermission('industry:park-promotion:query')")
    public CommonResult<PageResult<ParkPromotionRespVO>> getParkPromotionPage(@Valid ParkPromotionPageReqVO pageReqVO) {
        PageResult<ParkPromotionDO> pageResult = parkPromotionService.getParkPromotionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkPromotionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出优惠活动 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-promotion:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkPromotionExcel(@Valid ParkPromotionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkPromotionDO> list = parkPromotionService.getParkPromotionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "优惠活动.xls", "数据", ParkPromotionRespVO.class,
                        BeanUtils.toBean(list, ParkPromotionRespVO.class));
    }

}
