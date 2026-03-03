package cn.iocoder.yudao.module.envirhealth.controller.admin.park;

import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park.ParkPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park.ParkRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park.ParkSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.detail.ParkDetailDO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.ParkDO;
import cn.iocoder.yudao.module.envirhealth.service.park.park.ParkService;

@Tag(name = "环境卫生管理 - 公园")
@RestController
@RequestMapping("/envirhealth/park")
@Validated
public class ParkController {

    @Resource
    private ParkService parkService;

    @PostMapping("/create")
    @Operation(summary = "创建公园")
    @PreAuthorize("@ss.hasPermission('envirhealth:park:create')")
    public CommonResult<Long> createPark(@Valid @RequestBody ParkSaveReqVO createReqVO) {
        return success(parkService.createPark(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公园")
    @PreAuthorize("@ss.hasPermission('envirhealth:park:update')")
    public CommonResult<Boolean> updatePark(@Valid @RequestBody ParkSaveReqVO updateReqVO) {
        parkService.updatePark(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公园")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:park:delete')")
    public CommonResult<Boolean> deletePark(@RequestParam("id") Long id) {
        parkService.deletePark(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公园")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:park:query')")
    public CommonResult<ParkRespVO> getPark(@RequestParam("id") Long id) {
        ParkDO park = parkService.getPark(id);
        return success(BeanUtils.toBean(park, ParkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公园分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:park:query')")
    public CommonResult<PageResult<ParkRespVO>> getParkPage(@Valid ParkPageReqVO pageReqVO) {
        PageResult<ParkDO> pageResult = parkService.getParkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公园 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:park:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkExcel(@Valid ParkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkDO> list = parkService.getParkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "公园.xls", "数据", ParkRespVO.class,
                        BeanUtils.toBean(list, ParkRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得公园详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:park:query')")
    public CommonResult<PageResult<ParkDetailDO>> getParkDetailPage(
            @Valid ParkPageReqVO pageReqVO) {
        PageResult<ParkDetailDO> pageResult =
                parkService.getParkDetailPage(pageReqVO);

        return success(pageResult);
    }
}