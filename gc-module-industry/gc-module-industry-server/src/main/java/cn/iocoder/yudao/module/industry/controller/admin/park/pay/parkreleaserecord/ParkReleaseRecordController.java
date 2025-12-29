package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo.ParkReleaseRecordAddReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo.ParkReleaseRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo.ParkReleaseRecordRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo.ParkReleaseRecordSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkreleaserecord.ParkReleaseRecordDO;
import cn.iocoder.yudao.module.industry.service.park.pay.parkreleaserecord.ParkReleaseRecordService;
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



@Tag(name = "停车管理 - 放行记录")
@RestController
@RequestMapping("/industry/park-release-record")
@Validated
public class ParkReleaseRecordController {

    @Resource
    private ParkReleaseRecordService parkReleaseRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建放行记录")
    @PreAuthorize("@ss.hasPermission('industry:park-release-record:create')")
    public CommonResult<Long> createParkReleaseRecord(@Valid @RequestBody ParkReleaseRecordSaveReqVO createReqVO) {
        return success(parkReleaseRecordService.createParkReleaseRecord(createReqVO));
    }

    //一般用在创建缴费记录时自动创建
    @PostMapping("/add")
    @Operation(summary = "创建放行记录(暂时先别在前端用)")
    @PreAuthorize("@ss.hasPermission('industry:park-release-record:add')")
    public CommonResult<Long> addParkReleaseRecord(@Valid @RequestBody ParkReleaseRecordAddReqVO addReqVO) {
        return success(parkReleaseRecordService.addParkReleaseRecord(addReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新放行记录")
    @PreAuthorize("@ss.hasPermission('industry:park-release-record:update')")
    public CommonResult<Boolean> updateParkReleaseRecord(@Valid @RequestBody ParkReleaseRecordSaveReqVO updateReqVO) {
        parkReleaseRecordService.updateParkReleaseRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除放行记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-release-record:delete')")
    public CommonResult<Boolean> deleteParkReleaseRecord(@RequestParam("id") Long id) {
        parkReleaseRecordService.deleteParkReleaseRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得放行记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-release-record:query')")
    public CommonResult<ParkReleaseRecordRespVO> getParkReleaseRecord(@RequestParam("id") Long id) {
        ParkReleaseRecordDO parkReleaseRecord = parkReleaseRecordService.getParkReleaseRecord(id);
        return success(BeanUtils.toBean(parkReleaseRecord, ParkReleaseRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得放行记录分页")
    @PreAuthorize("@ss.hasPermission('industry:park-release-record:query')")
    public CommonResult<PageResult<ParkReleaseRecordRespVO>> getParkReleaseRecordPage(@Valid ParkReleaseRecordPageReqVO pageReqVO) {
        PageResult<ParkReleaseRecordDO> pageResult = parkReleaseRecordService.getParkReleaseRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkReleaseRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出放行记录 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-release-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkReleaseRecordExcel(@Valid ParkReleaseRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkReleaseRecordDO> list = parkReleaseRecordService.getParkReleaseRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "放行记录.xls", "数据", ParkReleaseRecordRespVO.class,
                        BeanUtils.toBean(list, ParkReleaseRecordRespVO.class));
    }

}
