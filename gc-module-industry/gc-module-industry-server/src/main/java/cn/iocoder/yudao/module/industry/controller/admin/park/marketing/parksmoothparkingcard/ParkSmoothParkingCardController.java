package cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parksmoothparkingcard;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parksmoothparkingcard.vo.ParkSmoothParkingCardPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parksmoothparkingcard.vo.ParkSmoothParkingCardRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parksmoothparkingcard.vo.ParkSmoothParkingCardSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parksmoothparkingcard.ParkSmoothParkingCardDO;
import cn.iocoder.yudao.module.industry.service.park.marketing.parksmoothparkingcard.ParkSmoothParkingCardService;
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


@Tag(name = "管理后台 - 畅停卡")
@RestController
@RequestMapping("/industry/park-smooth-parking-card")
@Validated
public class ParkSmoothParkingCardController {

    @Resource
    private ParkSmoothParkingCardService parkSmoothParkingCardService;

    @PostMapping("/create")
    @Operation(summary = "创建畅停卡")
    @PreAuthorize("@ss.hasPermission('industry:park-smooth-parking-card:create')")
    public CommonResult<Long> createParkSmoothParkingCard(@Valid @RequestBody ParkSmoothParkingCardSaveReqVO createReqVO) {
        return success(parkSmoothParkingCardService.createParkSmoothParkingCard(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新畅停卡")
    @PreAuthorize("@ss.hasPermission('industry:park-smooth-parking-card:update')")
    public CommonResult<Boolean> updateParkSmoothParkingCard(@Valid @RequestBody ParkSmoothParkingCardSaveReqVO updateReqVO) {
        parkSmoothParkingCardService.updateParkSmoothParkingCard(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除畅停卡")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-smooth-parking-card:delete')")
    public CommonResult<Boolean> deleteParkSmoothParkingCard(@RequestParam("id") Long id) {
        parkSmoothParkingCardService.deleteParkSmoothParkingCard(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得畅停卡")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-smooth-parking-card:query')")
    public CommonResult<ParkSmoothParkingCardRespVO> getParkSmoothParkingCard(@RequestParam("id") Long id) {
        ParkSmoothParkingCardDO parkSmoothParkingCard = parkSmoothParkingCardService.getParkSmoothParkingCard(id);
        return success(BeanUtils.toBean(parkSmoothParkingCard, ParkSmoothParkingCardRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得畅停卡分页")
    @PreAuthorize("@ss.hasPermission('industry:park-smooth-parking-card:query')")
    public CommonResult<PageResult<ParkSmoothParkingCardRespVO>> getParkSmoothParkingCardPage(@Valid ParkSmoothParkingCardPageReqVO pageReqVO) {
        PageResult<ParkSmoothParkingCardDO> pageResult = parkSmoothParkingCardService.getParkSmoothParkingCardPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkSmoothParkingCardRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出畅停卡 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-smooth-parking-card:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkSmoothParkingCardExcel(@Valid ParkSmoothParkingCardPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkSmoothParkingCardDO> list = parkSmoothParkingCardService.getParkSmoothParkingCardPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "畅停卡.xls", "数据", ParkSmoothParkingCardRespVO.class,
                        BeanUtils.toBean(list, ParkSmoothParkingCardRespVO.class));
    }

}
