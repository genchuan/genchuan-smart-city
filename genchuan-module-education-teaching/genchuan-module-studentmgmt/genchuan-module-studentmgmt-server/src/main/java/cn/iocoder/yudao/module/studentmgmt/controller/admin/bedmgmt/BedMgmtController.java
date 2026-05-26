package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.bedmgmt.BedMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.bedmgmt.BedMgmtService;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
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

@Tag(name = "学生管理后台 - 床位管理")
@RestController
@RequestMapping("/studentmgmt/bed-mgmt")
@Validated
public class BedMgmtController {

    @Resource
    private BedMgmtService bedMgmtService;
    @Resource
    private DictDataApi dictDataApi;

    @PostMapping("/create")
    @Operation(summary = "创建床位管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:create')")
    public CommonResult<Long> createBedMgmt(@Valid @RequestBody BedMgmtSaveReqVO createReqVO) {
        return success(bedMgmtService.createBedMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新床位管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:update')")
    public CommonResult<Boolean> updateBedMgmt(@Valid @RequestBody BedMgmtSaveReqVO updateReqVO) {
        bedMgmtService.updateBedMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除床位管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:delete')")
    public CommonResult<Boolean> deleteBedMgmt(@RequestParam("id") Long id) {
        bedMgmtService.deleteBedMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除床位管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:delete')")
    public CommonResult<Boolean> deleteBedMgmtList(@RequestParam("ids") List<Long> ids) {
        bedMgmtService.deleteBedMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得床位管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:query')")
    public CommonResult<BedMgmtRespVO> getBedMgmt(@RequestParam("id") Long id) {
        BedMgmtDO bedMgmt = bedMgmtService.getBedMgmt(id);
        return success(BeanUtils.toBean(bedMgmt, BedMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得床位管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:query')")
    public CommonResult<PageResult<BedMgmtRespVO>> getBedMgmtPage(@Valid BedMgmtPageReqVO pageReqVO) {
//        PageResult<BedMgmtDO> pageResult = bedMgmtService.getBedMgmtPage(pageReqVO);
        return success(bedMgmtService.getBedMgmtJoinPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出床位管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBedMgmtExcel(@Valid BedMgmtPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BedMgmtDO> list = bedMgmtService.getBedMgmtPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.BED_MGMT_STATUS.getType());
        list = list.stream().map(item -> {

            String status = item.getStatus();
            if (statusDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : statusDictDataList.getData()) {
                    if (dictData.getValue().equals(status)) {
                        status = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setStatus(status);
            return item;
        }).toList();
        // 导出 Excel
        ExcelUtils.write(response, "床位管理.xls", "数据", BedMgmtRespVO.class,
                BeanUtils.toBean(list, BedMgmtRespVO.class));
    }

    @PutMapping("/assign")
    @Operation(summary = "分配")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:assign')")
    public CommonResult<Boolean> assign(@Valid @RequestBody BedMgmtAssignReqVO reqVO) {
        boolean isSuccess = bedMgmtService.assign(reqVO);
        return success(isSuccess);
    }
    @PutMapping("/adjust")
    @Operation(summary = "调整床位")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:adjust')")
    public CommonResult<Boolean> adjust(@Valid BedMgmtAdjustReqVO reqVO) {
        boolean isSuccess = bedMgmtService.adjust(reqVO);
        return success(isSuccess);
    }

    @GetMapping("/chart")
    @Operation(summary = "宿舍床位分布看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:query')")
    public CommonResult<BedMgmtChartRespVO> chart(@Valid BedMgmtChartReqVO reqVO) {
        BedMgmtChartRespVO vo = bedMgmtService.chart(reqVO);
        return success(vo);
    }

    @GetMapping("/chart/bedDistribution")
    @Operation(summary = "楼栋床位占比统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:query')")
    public CommonResult<BedMgmtBedDistributionRespVO> bedDistribution() {
        BedMgmtBedDistributionRespVO vo = bedMgmtService.bedDistribution();
        return success(vo);
    }

    @GetMapping("/chart/bedIndex")
    @Operation(summary = "床位核心指标统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:bed-mgmt:query')")
    public CommonResult<BedMgmtBedIndexRespVO> bedIndex() {
        BedMgmtBedIndexRespVO vo = bedMgmtService.bedIndex();
        return success(vo);
    }

}