package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletFacilityRepairDetailDO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletFacilityRepairDO;
import cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletfacilityrepair.ToiletFacilityRepairService;

@Tag(name = "环境卫生管理 - 公厕设施维修")
@RestController
@RequestMapping("/envirhealth/toilet-facility-repair")
@Validated
public class ToiletFacilityRepairController {

    @Resource
    private ToiletFacilityRepairService toiletFacilityRepairService;

    @PostMapping("/create")
    @Operation(summary = "创建公厕设施维修")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-facility-repair:create')")
    public CommonResult<Long> createToiletFacilityRepair(@Valid @RequestBody ToiletFacilityRepairSaveReqVO createReqVO) {
        return success(toiletFacilityRepairService.createToiletFacilityRepair(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公厕设施维修")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-facility-repair:update')")
    public CommonResult<Boolean> updateToiletFacilityRepair(@Valid @RequestBody ToiletFacilityRepairSaveReqVO updateReqVO) {
        toiletFacilityRepairService.updateToiletFacilityRepair(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公厕设施维修")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-facility-repair:delete')")
    public CommonResult<Boolean> deleteToiletFacilityRepair(@RequestParam("id") Long id) {
        toiletFacilityRepairService.deleteToiletFacilityRepair(id);
        return success(true);
    }

    @DeleteMapping("/delete-batch")
    @Operation(summary = "批量删除公厕设施维修")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('health:toilet-facility-repair:delete')")
    public CommonResult<Boolean> deleteToiletFacilityRepairBatch(@RequestBody List<Long> ids) {
        toiletFacilityRepairService.deleteToiletFacilityRepairBatch(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公厕设施维修")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-facility-repair:query')")
    public CommonResult<ToiletFacilityRepairRespVO> getToiletFacilityRepair(@RequestParam("id") Long id) {
        ToiletFacilityRepairDO toiletFacilityRepair = toiletFacilityRepairService.getToiletFacilityRepair(id);
        return success(BeanUtils.toBean(toiletFacilityRepair, ToiletFacilityRepairRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公厕设施维修分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-facility-repair:query')")
    public CommonResult<PageResult<ToiletFacilityRepairRespVO>> getToiletFacilityRepairPage(@Valid ToiletFacilityRepairPageReqVO pageReqVO) {
        PageResult<ToiletFacilityRepairDO> pageResult = toiletFacilityRepairService.getToiletFacilityRepairPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ToiletFacilityRepairRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公厕设施维修 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-facility-repair:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportToiletFacilityRepairExcel(@Valid ToiletFacilityRepairPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ToiletFacilityRepairDO> list = toiletFacilityRepairService.getToiletFacilityRepairPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "公厕设施维修.xls", "数据", ToiletFacilityRepairRespVO.class,
                        BeanUtils.toBean(list, ToiletFacilityRepairRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得公厕设施维修详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-facility-repair:query')")
    public CommonResult<PageResult<ToiletFacilityRepairDetailDO>> getToiletFacilityRepairDetailPage(
            @Valid ToiletFacilityRepairPageReqVO pageReqVO) {
        PageResult<ToiletFacilityRepairDetailDO> pageResult =
                toiletFacilityRepairService.getToiletFacilityRepairDetailPage(pageReqVO);

        return success(pageResult);
    }


}