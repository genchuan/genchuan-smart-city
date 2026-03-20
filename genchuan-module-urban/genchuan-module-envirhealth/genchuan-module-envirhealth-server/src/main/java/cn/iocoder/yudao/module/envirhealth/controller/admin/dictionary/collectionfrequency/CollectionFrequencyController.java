package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.collectionfrequency;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.collectionfrequency.vo.CollectionFrequencyOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.collectionfrequency.vo.CollectionFrequencyPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.collectionfrequency.vo.CollectionFrequencyRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.collectionfrequency.vo.CollectionFrequencySaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.CollectionFrequencyDO;
import cn.iocoder.yudao.module.envirhealth.service.dictionary.collectionfrequency.CollectionFrequencyService;
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

@Tag(name = "字典表 - 收运频次字典")
@RestController
@RequestMapping("/envirhealth/collection-frequency")
@Validated
public class CollectionFrequencyController {

    @Resource
    private CollectionFrequencyService collectionFrequencyService;

    @PostMapping("/create")
    @Operation(summary = "创建收运频次字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-frequency:create')")
    public CommonResult<Long> createCollectionFrequency(@Valid @RequestBody CollectionFrequencySaveReqVO createReqVO) {
        return success(collectionFrequencyService.createCollectionFrequency(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新收运频次字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-frequency:update')")
    public CommonResult<Boolean> updateCollectionFrequency(@Valid @RequestBody CollectionFrequencySaveReqVO updateReqVO) {
        collectionFrequencyService.updateCollectionFrequency(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除收运频次字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-frequency:delete')")
    public CommonResult<Boolean> deleteCollectionFrequency(@RequestParam("id") Long id) {
        collectionFrequencyService.deleteCollectionFrequency(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得收运频次字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-frequency:query')")
    public CommonResult<CollectionFrequencyRespVO> getCollectionFrequency(@RequestParam("id") Long id) {
        CollectionFrequencyDO collectionFrequency = collectionFrequencyService.getCollectionFrequency(id);
        return success(BeanUtils.toBean(collectionFrequency, CollectionFrequencyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得收运频次字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-frequency:query')")
    public CommonResult<PageResult<CollectionFrequencyRespVO>> getCollectionFrequencyPage(@Valid CollectionFrequencyPageReqVO pageReqVO) {
        PageResult<CollectionFrequencyDO> pageResult = collectionFrequencyService.getCollectionFrequencyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CollectionFrequencyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出收运频次字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:collection-frequency:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCollectionFrequencyExcel(@Valid CollectionFrequencyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CollectionFrequencyDO> list = collectionFrequencyService.getCollectionFrequencyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "收运频次字典.xls", "数据", CollectionFrequencyRespVO.class,
                        BeanUtils.toBean(list, CollectionFrequencyRespVO.class));
    }

    /**
     * 获得收运频次字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得收运频次字典(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:collection-frequency:query')")
    public CommonResult<List<CollectionFrequencyOptionVO>> getCollectionFrequencyOptions() {
        return success(collectionFrequencyService.getCollectionFrequencyOptions());
    }
}