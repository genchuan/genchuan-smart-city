package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectiontimeperiod.CollectionTimePeriodOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectiontimeperiod.CollectionTimePeriodPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectiontimeperiod.CollectionTimePeriodRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectiontimeperiod.CollectionTimePeriodSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.CollectionTimePeriodDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagecollection.collectiontimeperiod.CollectionTimePeriodService;
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

@Tag(name = "字典表 - 收运时段字典")
@RestController
@RequestMapping("/envirhealth/collection-time-period")
@Validated
public class CollectionTimePeriodController {

    @Resource
    private CollectionTimePeriodService collectionTimePeriodService;

    @PostMapping("/create")
    @Operation(summary = "创建收运时段字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-time-period:create')")
    public CommonResult<Long> createCollectionTimePeriod(@Valid @RequestBody CollectionTimePeriodSaveReqVO createReqVO) {
        return success(collectionTimePeriodService.createCollectionTimePeriod(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新收运时段字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-time-period:update')")
    public CommonResult<Boolean> updateCollectionTimePeriod(@Valid @RequestBody CollectionTimePeriodSaveReqVO updateReqVO) {
        collectionTimePeriodService.updateCollectionTimePeriod(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除收运时段字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-time-period:delete')")
    public CommonResult<Boolean> deleteCollectionTimePeriod(@RequestParam("id") Long id) {
        collectionTimePeriodService.deleteCollectionTimePeriod(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得收运时段字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-time-period:query')")
    public CommonResult<CollectionTimePeriodRespVO> getCollectionTimePeriod(@RequestParam("id") Long id) {
        CollectionTimePeriodDO collectionTimePeriod = collectionTimePeriodService.getCollectionTimePeriod(id);
        return success(BeanUtils.toBean(collectionTimePeriod, CollectionTimePeriodRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得收运时段字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-time-period:query')")
    public CommonResult<PageResult<CollectionTimePeriodRespVO>> getCollectionTimePeriodPage(@Valid CollectionTimePeriodPageReqVO pageReqVO) {
        PageResult<CollectionTimePeriodDO> pageResult = collectionTimePeriodService.getCollectionTimePeriodPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CollectionTimePeriodRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出收运时段字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-time-period:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCollectionTimePeriodExcel(@Valid CollectionTimePeriodPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CollectionTimePeriodDO> list = collectionTimePeriodService.getCollectionTimePeriodPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "收运时段字典.xls", "数据", CollectionTimePeriodRespVO.class,
                        BeanUtils.toBean(list, CollectionTimePeriodRespVO.class));
    }

    /**
     * 获得收运时段下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得收运时段字典(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:collection-time-period:query')")
    public CommonResult<List<CollectionTimePeriodOptionVO>> getCollectionTimePeriodOptions() {
        return success(collectionTimePeriodService.getCollectionTimePeriodOptions());
    }
}