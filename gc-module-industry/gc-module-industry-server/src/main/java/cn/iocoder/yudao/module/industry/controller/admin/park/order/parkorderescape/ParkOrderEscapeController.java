package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.WarningAlertListTableImportExcelVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo.ParkOrderEscapePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo.ParkOrderEscapeRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo.ParkOrderEscapeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderescape.ParkOrderEscapeDO;
import cn.iocoder.yudao.module.industry.service.park.order.parkorderescape.ParkOrderEscapeService;
import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import org.springframework.http.MediaType;
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

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import org.springframework.web.multipart.MultipartFile;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.PARK_GUIDANCE_NOT_EXISTS;


@Tag(name = "管理后台 - 逃费订单")
@RestController
@RequestMapping("/industry/park-order-escape")
@Validated
public class ParkOrderEscapeController {

    @Resource
    private ParkOrderEscapeService orderEscapeService;

    @PostMapping("/create")
    @Operation(summary = "创建逃费订单")
    @PreAuthorize("@ss.hasPermission('park:order-escape:create')")
    public CommonResult<Long> createOrderEscape(@Valid @RequestBody ParkOrderEscapeSaveReqVO createReqVO) {
        return success(orderEscapeService.createOrderEscape(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新逃费订单")
    @PreAuthorize("@ss.hasPermission('park:order-escape:update')")
    public CommonResult<Boolean> updateOrderEscape(@Valid @RequestBody ParkOrderEscapeSaveReqVO updateReqVO) {
        orderEscapeService.updateOrderEscape(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除逃费订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:order-escape:delete')")
    public CommonResult<Boolean> deleteOrderEscape(@RequestParam("id") Long id) {
        orderEscapeService.deleteOrderEscape(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得逃费订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:order-escape:query')")
    public CommonResult<ParkOrderEscapeRespVO> getOrderEscape(@RequestParam("id") Long id) {
        ParkOrderEscapeDO orderEscape = orderEscapeService.getOrderEscape(id);
        return success(BeanUtils.toBean(orderEscape, ParkOrderEscapeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得逃费订单分页")
    @PreAuthorize("@ss.hasPermission('park:order-escape:query')")
    public CommonResult<PageResult<ParkOrderEscapeRespVO>> getOrderEscapePage(@Valid ParkOrderEscapePageReqVO pageReqVO) {
        PageResult<ParkOrderEscapeDO> pageResult = orderEscapeService.getOrderEscapePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkOrderEscapeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出逃费订单 Excel")
    @PreAuthorize("@ss.hasPermission('park:order-escape:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderEscapeExcel(@Valid ParkOrderEscapePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkOrderEscapeDO> list = orderEscapeService.getOrderEscapePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "逃费订单.xls", "数据", ParkOrderEscapeRespVO.class,
                        BeanUtils.toBean(list, ParkOrderEscapeRespVO.class));
    }

    @PostMapping(
            value = "/import-excel",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Operation(summary = "导入逃费订单 Excel")
    @PreAuthorize("@ss.hasPermission('park:order-escape:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<List<ParkOrderEscapeSaveReqVO>> importOrderEscapeExcel(
            @Parameter(description = "Excel 文件", required = true)
            @RequestPart("file") MultipartFile file  // <-- 这里改成 @RequestPart
    ) throws IOException {

        if (file == null || file.isEmpty()) {
            throw exception(new ErrorCode(500, "上传文件不能为空"));
        }


        List<ParkOrderEscapeSaveReqVO> importList =EasyExcel.read(file.getInputStream())
                    .head(WarningAlertListTableImportExcelVO.class)
//                    .autoCloseStream(false)
                    .sheet(0) // 第一个Sheet
                    .headRowNumber(1) // 第一行作为表头
                    .doReadSync();

        // 1. EasyExcel 直接解析
//        List<ParkOrderEscapeSaveReqVO> importList =
//                EasyExcel.read(file.getInputStream())
//                        .head(ParkOrderEscapeSaveReqVO.class)
//                        .sheet()
//                        .doReadSync();

        if (importList.isEmpty()) {
            throw exception(new ErrorCode(500, "Excel 中没有数据"));
        }

        return success(importList);
        // 2. VO -> DO
//        List<ParkOrderEscapeDO> doList = BeanUtils.toBean(importList, ParkOrderEscapeDO.class);

        // 3. 批量入库
//        orderEscapeService.createBatch(doList);

//        return CommonResult.success("成功导入 " + doList.size() + " 条数据");
        // 2. 直接返回给前端展示（不入库）
//        return success(doList);
    }

}
