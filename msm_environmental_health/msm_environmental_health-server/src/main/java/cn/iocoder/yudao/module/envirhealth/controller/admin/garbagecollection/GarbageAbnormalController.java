package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.card.abnormal.GarbageAbnormalCardAbnormalRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.circle.all.GarbageCollectionCircleAllVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageAbnormalDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.util.circle.vo.CircleVO;
import cn.iocoder.yudao.module.envirhealth.util.column.vo.ColumnVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageAbnormalDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbageabnormal.GarbageAbnormalService;

@Tag(name = "环境卫生管理 - 垃圾异常记录")
@RestController
@RequestMapping("/envirhealth/garbage-abnormal")
@Validated
public class GarbageAbnormalController {

    @Resource
    private GarbageAbnormalService garbageAbnormalService;

    @PostMapping("/create")
    @Operation(summary = "创建垃圾异常记录")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:create')")
    public CommonResult<Long> createGarbageAbnormal(@Valid @RequestBody GarbageAbnormalSaveReqVO createReqVO) {
        return success(garbageAbnormalService.createGarbageAbnormal(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新垃圾异常记录")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:update')")
    public CommonResult<Boolean> updateGarbageAbnormal(@Valid @RequestBody GarbageAbnormalSaveReqVO updateReqVO) {
        garbageAbnormalService.updateGarbageAbnormal(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除垃圾异常记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:delete')")
    public CommonResult<Boolean> deleteGarbageAbnormal(@RequestParam("id") Long id) {
        garbageAbnormalService.deleteGarbageAbnormal(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得垃圾异常记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<GarbageAbnormalRespVO> getGarbageAbnormal(@RequestParam("id") Long id) {
        GarbageAbnormalDO garbageAbnormal = garbageAbnormalService.getGarbageAbnormal(id);
        return success(BeanUtils.toBean(garbageAbnormal, GarbageAbnormalRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得垃圾异常记录分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<PageResult<GarbageAbnormalRespVO>> getGarbageAbnormalPage(@Valid GarbageAbnormalPageReqVO pageReqVO) {
        PageResult<GarbageAbnormalDO> pageResult = garbageAbnormalService.getGarbageAbnormalPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GarbageAbnormalRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出垃圾异常记录 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGarbageAbnormalExcel(@Valid GarbageAbnormalPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GarbageAbnormalDO> list = garbageAbnormalService.getGarbageAbnormalPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "垃圾异常记录.xls", "数据", GarbageAbnormalRespVO.class,
                        BeanUtils.toBean(list, GarbageAbnormalRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获取垃圾异常记录详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<PageResult<GarbageAbnormalDetailDO>> getGarbageAbnormalDetailPage(
            @Valid GarbageAbnormalPageReqVO pageReqVO) {
        PageResult<GarbageAbnormalDetailDO> pageResult =
                garbageAbnormalService.getGarbageAbnormalDetailPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/chart/card-abnormal")
    @Operation(summary = "获取异常统计卡片数据(卡片-异常)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<GarbageAbnormalCardAbnormalRespVO> getGarbageAbnormalCardAbnormal() {
        return success(garbageAbnormalService.getGarbageAbnormalCardAbnormal());
    }

    @GetMapping("/chart/circle-abnormal-type")
    @Operation(summary = "获取异常类型占比(环状图-异常)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<List<CircleVO>> getGarbageAbnormalTypeCircle() {
        return success(garbageAbnormalService.getGarbageAbnormalTypeCircleAbnormal());
    }

    @GetMapping("/chart/circle-area-distribution")
    @Operation(summary = "获取区域分布占比(环状图-异常)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<List<CircleVO>> getGarbageAbnormalAreaDistributionCircle() {
        return success(garbageAbnormalService.getGarbageAbnormalAreaDistributionCircle());
    }

    @GetMapping("/chart/column-abnormal")
    @Operation(summary = "获取不同责任人待处置异常数量对比(柱状图)")
    @PreAuthorize("@ss.hasPermission('envirhealth:garbage-abnormal:query')")
    public CommonResult<List<ColumnVO>> getHandlerAbnormalColumn() {
        return success(garbageAbnormalService.getHandlerAbnormalColumn());
    }
}
