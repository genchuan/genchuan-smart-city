package cn.iocoder.yudao.module.park.controller.admin.park.pricing.promotion;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.promotion.vo.PromotionPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.promotion.vo.PromotionRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.promotion.vo.PromotionSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.promotion.PromotionDO;
import cn.iocoder.yudao.module.park.service.park.pricing.promotion.PromotionService;
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


@Tag(name = "管理后台 - 优惠活动")
@RestController
@RequestMapping("/park/promotion")
@Validated
public class PromotionController {

    @Resource
    private PromotionService promotionService;

    @PostMapping("/create")
    @Operation(summary = "创建优惠活动")
    @PreAuthorize("@ss.hasPermission('park:promotion:create')")
    public CommonResult<Long> createPromotion(@Valid @RequestBody PromotionSaveReqVO createReqVO) {
        return success(promotionService.createPromotion(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新优惠活动")
    @PreAuthorize("@ss.hasPermission('park:promotion:update')")
    public CommonResult<Boolean> updatePromotion(@Valid @RequestBody PromotionSaveReqVO updateReqVO) {
        promotionService.updatePromotion(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除优惠活动")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:promotion:delete')")
    public CommonResult<Boolean> deletePromotion(@RequestParam("id") Long id) {
        promotionService.deletePromotion(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得优惠活动")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:promotion:query')")
    public CommonResult<PromotionRespVO> getPromotion(@RequestParam("id") Long id) {
        PromotionDO promotion = promotionService.getPromotion(id);
        return success(BeanUtils.toBean(promotion, PromotionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得优惠活动分页")
    @PreAuthorize("@ss.hasPermission('park:promotion:query')")
    public CommonResult<PageResult<PromotionRespVO>> getPromotionPage(@Valid PromotionPageReqVO pageReqVO) {
        PageResult<PromotionDO> pageResult = promotionService.getPromotionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PromotionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出优惠活动 Excel")
    @PreAuthorize("@ss.hasPermission('park:promotion:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPromotionExcel(@Valid PromotionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PromotionDO> list = promotionService.getPromotionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "优惠活动.xls", "数据", PromotionRespVO.class,
                        BeanUtils.toBean(list, PromotionRespVO.class));
    }

}
