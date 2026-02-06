package cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.marketing.smoothstopcard.SmoothStopCardDO;
import cn.iocoder.yudao.module.park.service.park.marketing.smoothstopcard.SmoothStopCardService;
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


@Tag(name = "管理后台 - 畅停卡")
@RestController
@RequestMapping("/park/smooth-stop-card")
@Validated
public class SmoothStopCardController {

    @Resource
    private SmoothStopCardService smoothStopCardService;



    //验证该用户该车牌此次停车是否有畅停卡生效
    @PostMapping("/verify-order-free-by-smooth-card")
    @Operation(summary = "（前端不接）验证该用户该车牌此次停车是否有畅停卡生效")
    @PreAuthorize("@ss.hasPermission('park:smooth-stop-card:verify-order-free-by-smooth-card')")
    public CommonResult<VerifyOrderFreeRespVO> verifyOrderFreeBySmoothCard(@Valid @RequestBody VerifyOrderFreeReqVO reqVO) {
        return success(smoothStopCardService.verifyOrderFreeBySmoothCard(reqVO));
    }
    @PostMapping("/create")
    @Operation(summary = "创建畅停卡")
    @PreAuthorize("@ss.hasPermission('park:smooth-stop-card:create')")
    public CommonResult<Long> createSmoothStopCard(@Valid @RequestBody SmoothStopCardSaveReqVO createReqVO) {
        return success(smoothStopCardService.createSmoothStopCard(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新畅停卡")
    @PreAuthorize("@ss.hasPermission('park:smooth-stop-card:update')")
    public CommonResult<Boolean> updateSmoothStopCard(@Valid @RequestBody SmoothStopCardSaveReqVO updateReqVO) {
        smoothStopCardService.updateSmoothStopCard(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除畅停卡")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:smooth-stop-card:delete')")
    public CommonResult<Boolean> deleteSmoothStopCard(@RequestParam("id") Long id) {
        smoothStopCardService.deleteSmoothStopCard(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得畅停卡")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:smooth-stop-card:query')")
    public CommonResult<SmoothStopCardRespVO> getSmoothStopCard(@RequestParam("id") Long id) {
        SmoothStopCardDO smoothStopCard = smoothStopCardService.getSmoothStopCard(id);
        return success(BeanUtils.toBean(smoothStopCard, SmoothStopCardRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得畅停卡分页")
    @PreAuthorize("@ss.hasPermission('park:smooth-stop-card:query')")
    public CommonResult<PageResult<SmoothStopCardRespVO>> getSmoothStopCardPage(@Valid SmoothStopCardPageReqVO pageReqVO) {
        PageResult<SmoothStopCardDO> pageResult = smoothStopCardService.getSmoothStopCardPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SmoothStopCardRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出畅停卡 Excel")
    @PreAuthorize("@ss.hasPermission('park:smooth-stop-card:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSmoothStopCardExcel(@Valid SmoothStopCardPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SmoothStopCardDO> list = smoothStopCardService.getSmoothStopCardPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "畅停卡.xls", "数据", SmoothStopCardRespVO.class,
                        BeanUtils.toBean(list, SmoothStopCardRespVO.class));
    }

}
