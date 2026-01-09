package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoCreateReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkwo.ParkWoDO;
import cn.iocoder.yudao.module.industry.service.park.pay.parkwo.ParkWoService;
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



@Tag(name = "停车管理-停车订单")
@RestController
@RequestMapping("/industry/park-wo")
@Validated
public class ParkWoController {

    @Resource
    private ParkWoService parkWoService;

    @PostMapping("/create")
    @Operation(summary = "创建停车订单")
    @PreAuthorize("@ss.hasPermission('pay:park-wo:create')")
    public CommonResult<Long> createParkWo(@Valid @RequestBody ParkWoCreateReqVO createReqVO) {
        return success(parkWoService.createParkWo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新停车订单")
    @PreAuthorize("@ss.hasPermission('pay:park-wo:update')")
    public CommonResult<Boolean> updateParkWo(@Valid @RequestBody ParkWoSaveReqVO updateReqVO) {
        parkWoService.updateParkWo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除停车订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pay:park-wo:delete')")
    public CommonResult<Boolean> deleteParkWo(@RequestParam("id") Long id) {
        parkWoService.deleteParkWo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pay:park-wo:query')")
    public CommonResult<ParkWoRespVO> getParkWo(@RequestParam("id") Long id) {
        ParkWoDO parkWo = parkWoService.getParkWo(id);
        return success(BeanUtils.toBean(parkWo, ParkWoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得停车订单分页")
    @PreAuthorize("@ss.hasPermission('pay:park-wo:query')")
    public CommonResult<PageResult<ParkWoRespVO>> getParkWoPage(@Valid ParkWoPageReqVO pageReqVO) {
        PageResult<ParkWoDO> pageResult = parkWoService.getParkWoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkWoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出停车订单 Excel")
    @PreAuthorize("@ss.hasPermission('pay:park-wo:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkWoExcel(@Valid ParkWoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkWoDO> list = parkWoService.getParkWoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "停车订单.xls", "数据", ParkWoRespVO.class,
                        BeanUtils.toBean(list, ParkWoRespVO.class));
    }

}
