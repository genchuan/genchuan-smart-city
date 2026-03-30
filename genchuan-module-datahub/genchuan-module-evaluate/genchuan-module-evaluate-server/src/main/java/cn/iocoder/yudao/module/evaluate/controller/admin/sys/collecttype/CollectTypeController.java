package cn.iocoder.yudao.module.evaluate.controller.admin.sys.collecttype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.collecttype.vo.CollectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.collecttype.vo.CollectTypeRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.collecttype.vo.CollectTypeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.collecttype.CollectTypeDO;
import cn.iocoder.yudao.module.evaluate.service.collecttype.CollectTypeService;
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

@Tag(name = "管理后台 - 采集方式字典")
@RestController
@RequestMapping("/evaluate/collect-type")
@Validated
public class CollectTypeController {

    @Resource
    private CollectTypeService collectTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建采集方式字典")
    @PreAuthorize("@ss.hasPermission('evaluate:collect-type:create')")
    public CommonResult<Long> createCollectType(@Valid @RequestBody CollectTypeSaveReqVO createReqVO) {
        return success(collectTypeService.createCollectType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新采集方式字典")
    @PreAuthorize("@ss.hasPermission('evaluate:collect-type:update')")
    public CommonResult<Boolean> updateCollectType(@Valid @RequestBody CollectTypeSaveReqVO updateReqVO) {
        collectTypeService.updateCollectType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除采集方式字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:collect-type:delete')")
    public CommonResult<Boolean> deleteCollectType(@RequestParam("id") Long id) {
        collectTypeService.deleteCollectType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得采集方式字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:collect-type:query')")
    public CommonResult<CollectTypeRespVO> getCollectType(@RequestParam("id") Long id) {
        CollectTypeDO collectType = collectTypeService.getCollectType(id);
        return success(BeanUtils.toBean(collectType, CollectTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得采集方式字典分页")
    @PreAuthorize("@ss.hasPermission('evaluate:collect-type:query')")
    public CommonResult<PageResult<CollectTypeRespVO>> getCollectTypePage(@Valid CollectTypePageReqVO pageReqVO) {
        PageResult<CollectTypeDO> pageResult = collectTypeService.getCollectTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CollectTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出采集方式字典 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:collect-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCollectTypeExcel(@Valid CollectTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CollectTypeDO> list = collectTypeService.getCollectTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "采集方式字典.xls", "数据", CollectTypeRespVO.class,
                        BeanUtils.toBean(list, CollectTypeRespVO.class));
    }

}