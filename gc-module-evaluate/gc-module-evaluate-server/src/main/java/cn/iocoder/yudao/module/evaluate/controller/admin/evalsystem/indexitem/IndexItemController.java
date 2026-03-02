package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.module.evaluate.service.indexitem.IndexItemService;
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

@Tag(name = "管理后台 - 指标项")
@RestController
@RequestMapping("/evaluate/index-item")
@Validated
public class IndexItemController {

    @Resource
    private IndexItemService indexItemService;

    @PostMapping("/create")
    @Operation(summary = "创建指标项")
    @PreAuthorize("@ss.hasPermission('evaluate:index-item:create')")
    public CommonResult<Long> createIndexItem(@Valid @RequestBody IndexItemSaveReqVO createReqVO) {
        return success(indexItemService.createIndexItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指标项")
    @PreAuthorize("@ss.hasPermission('evaluate:index-item:update')")
    public CommonResult<Boolean> updateIndexItem(@Valid @RequestBody IndexItemSaveReqVO updateReqVO) {
        indexItemService.updateIndexItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指标项")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:index-item:delete')")
    public CommonResult<Boolean> deleteIndexItem(@RequestParam("id") Long id) {
        indexItemService.deleteIndexItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指标项")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:index-item:query')")
    public CommonResult<IndexItemRespVO> getIndexItem(@RequestParam("id") Long id) {
        IndexItemDO indexItem = indexItemService.getIndexItem(id);
        return success(BeanUtils.toBean(indexItem, IndexItemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指标项分页")
    @PreAuthorize("@ss.hasPermission('evaluate:index-item:query')")
    public CommonResult<PageResult<IndexItemRespVO>> getIndexItemPage(@Valid IndexItemPageReqVO pageReqVO) {
        PageResult<IndexItemDO> pageResult = indexItemService.getIndexItemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, IndexItemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指标项 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:index-item:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportIndexItemExcel(@Valid IndexItemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<IndexItemDO> list = indexItemService.getIndexItemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "指标项.xls", "数据", IndexItemRespVO.class,
                        BeanUtils.toBean(list, IndexItemRespVO.class));
    }

}