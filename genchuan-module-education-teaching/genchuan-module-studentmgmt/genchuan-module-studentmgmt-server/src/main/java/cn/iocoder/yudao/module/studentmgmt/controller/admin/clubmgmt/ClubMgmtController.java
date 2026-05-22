package cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.clubmgmt.ClubMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.clubmgmt.ClubMgmtService;
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

@Tag(name = "管理后台 - 社团管理")
@RestController
@RequestMapping("/studentmgmt/club-mgmt")
@Validated
public class ClubMgmtController {

    @Resource
    private ClubMgmtService clubMgmtService;
    @Resource
    private DictDataApi dictDataApi;
    @PostMapping("/create")
    @Operation(summary = "创建社团管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:create')")
    public CommonResult<Long> createClubMgmt(@Valid @RequestBody ClubMgmtSaveReqVO createReqVO) {
        return success(clubMgmtService.createClubMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新社团管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:update')")
    public CommonResult<Boolean> updateClubMgmt(@Valid @RequestBody ClubMgmtSaveReqVO updateReqVO) {
        clubMgmtService.updateClubMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除社团管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:delete')")
    public CommonResult<Boolean> deleteClubMgmt(@RequestParam("id") Long id) {
        clubMgmtService.deleteClubMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除社团管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:delete')")
    public CommonResult<Boolean> deleteClubMgmtList(@RequestParam("ids") List<Long> ids) {
        clubMgmtService.deleteClubMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得社团管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:query')")
    public CommonResult<ClubMgmtRespVO> getClubMgmt(@RequestParam("id") Long id) {
        ClubMgmtDO clubMgmt = clubMgmtService.getClubMgmt(id);
        return success(BeanUtils.toBean(clubMgmt, ClubMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得社团管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:query')")
    public CommonResult<PageResult<ClubMgmtRespVO>> getClubMgmtPage(@Valid ClubMgmtPageReqVO pageReqVO) {
//        PageResult<ClubMgmtDO> pageResult = clubMgmtService.getClubMgmtPage(pageReqVO);
        return success(clubMgmtService.getClubMgmtJoinPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出社团管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportClubMgmtExcel(@Valid ClubMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ClubMgmtDO> list = clubMgmtService.getClubMgmtPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> typeDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.CLUB_MGMT_CLUB_TYPE.getType());
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.CLUB_MGMT_STATUS.getType());
        CommonResult<List<DictDataRespDTO>> venueApplyStatusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.CLUB_MGMT_VENUE_APPLY_STATUS.getType());
        list = list.stream().map(item -> {
            String clubType = item.getClubType();
            if (typeDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : typeDictDataList.getData()) {
                    if (dictData.getValue().equals(clubType)) {
                        clubType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setClubType(clubType);
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
            String venueApplyStatus = item.getVenueApplyStatus();
            if (venueApplyStatusDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : venueApplyStatusDictDataList.getData()) {
                    if (dictData.getValue().equals(venueApplyStatus)) {
                        venueApplyStatus = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setVenueApplyStatus(venueApplyStatus);
            return item;
        }).toList();
        // 导出 Excel
        ExcelUtils.write(response, "社团管理.xls", "数据", ClubMgmtRespVO.class,
                        BeanUtils.toBean(list, ClubMgmtRespVO.class));
    }

    @PutMapping("/audit")
    @Operation(summary = "审核社团")
    @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:audit')")
    public CommonResult<Boolean> audit(@Valid @RequestBody ClubMgmtAuditReqVO reqVO) {
        boolean isSuccess = clubMgmtService.audit(reqVO);
        return success(isSuccess);
    }
    @PutMapping("/archive")
    @Operation(summary = "建档")
    @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:archive')")
    public CommonResult<Boolean> archive(@Valid @RequestBody ClubMgmtArchiveReqVO reqVO) {
        boolean isSuccess = clubMgmtService.archive(reqVO);
        return success(isSuccess);
    }
    @PutMapping("/venueApply")
    @Operation(summary = "场馆申请")
    @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:venueApply')")
    public CommonResult<Boolean> venueApply(@Valid ClubMgmtVenueApplyReqVO reqVO) {
        boolean isSuccess = clubMgmtService.venueApply(reqVO);
        return success(isSuccess);
    }

    @GetMapping("/chart")
    @Operation(summary = "社团运营统计看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:query')")
    public CommonResult<ClubMgmtChartRespVO> chart(@Valid ClubMgmtChartReqVO reqVO) {
        ClubMgmtChartRespVO vo = clubMgmtService.chart(reqVO);
        return success(vo);
    }

    @GetMapping("/chart/clubDistribution")
    @Operation(summary = "各社团人数 / 类型分布统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:club-mgmt:query')")
    public CommonResult<ClubMgmtClubDistributionRespVO> clubDistribution(@Valid ClubMgmtChartReqVO reqVO) {
        ClubMgmtClubDistributionRespVO vo = clubMgmtService.clubDistribution(reqVO);
        return success(vo);
    }


}