package cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot.vo.ParkLotPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot.vo.ParkLotRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot.vo.ParkLotSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.lot.ParkLotDO;
import cn.iocoder.yudao.module.industry.service.park.asset.lot.ParkLotService;

@Tag(name = "管理后台 - 车场信息")
@RestController
@RequestMapping("/industry/park-lot")
@Validated
public class ParkLotController {

    @Resource
    private ParkLotService parkLotService;

    @PostMapping("/create")
    @Operation(summary = "创建车场信息")
    @PreAuthorize("@ss.hasPermission('industry:park-lot:create')")
    public CommonResult<Long> createParkLot(@Valid @RequestBody ParkLotSaveReqVO createReqVO) {
        return success(parkLotService.createParkLot(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车场信息")
    @PreAuthorize("@ss.hasPermission('industry:park-lot:update')")
    public CommonResult<Boolean> updateParkLot(@Valid @RequestBody ParkLotSaveReqVO updateReqVO) {
        parkLotService.updateParkLot(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车场信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-lot:delete')")
    public CommonResult<Boolean> deleteParkLot(@RequestParam("id") Long id) {
        parkLotService.deleteParkLot(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车场信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-lot:query')")
    public CommonResult<ParkLotRespVO> getParkLot(@RequestParam("id") Long id) {
        ParkLotDO parkLot = parkLotService.getParkLot(id);
        return success(BeanUtils.toBean(parkLot, ParkLotRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车场信息分页")
    @PreAuthorize("@ss.hasPermission('industry:park-lot:query')")
    public CommonResult<PageResult<ParkLotRespVO>> getParkLotPage(@Valid ParkLotPageReqVO pageReqVO) {
        PageResult<ParkLotDO> pageResult = parkLotService.getParkLotPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkLotRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车场信息 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-lot:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkLotExcel(@Valid ParkLotPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkLotDO> list = parkLotService.getParkLotPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车场信息.xls", "数据", ParkLotRespVO.class,
                        BeanUtils.toBean(list, ParkLotRespVO.class));
    }

}