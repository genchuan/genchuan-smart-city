package cn.iocoder.yudao.module.evaluate.controller.admin.sys.indextype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.indextype.vo.IndexTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.indextype.vo.IndexTypeRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.indextype.vo.IndexTypeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.indextype.IndexTypeDO;
import cn.iocoder.yudao.module.evaluate.service.indextype.IndexTypeService;
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

@Tag(name = "评价体系管理 - 指标类型字典")
@RestController
@RequestMapping("/evaluate/index-type")
@Validated
public class IndexTypeController {

    @Resource
    private IndexTypeService indexTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建指标类型字典")
    @PreAuthorize("@ss.hasPermission('evaluate:index-type:create')")
    public CommonResult<Long> createIndexType(@Valid @RequestBody IndexTypeSaveReqVO createReqVO) {
        return success(indexTypeService.createIndexType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指标类型字典")
    @PreAuthorize("@ss.hasPermission('evaluate:index-type:update')")
    public CommonResult<Boolean> updateIndexType(@Valid @RequestBody IndexTypeSaveReqVO updateReqVO) {
        indexTypeService.updateIndexType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指标类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:index-type:delete')")
    public CommonResult<Boolean> deleteIndexType(@RequestParam("id") Long id) {
        indexTypeService.deleteIndexType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指标类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:index-type:query')")
    public CommonResult<IndexTypeRespVO> getIndexType(@RequestParam("id") Long id) {
        IndexTypeDO indexType = indexTypeService.getIndexType(id);
        return success(BeanUtils.toBean(indexType, IndexTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指标类型字典分页")
    @PreAuthorize("@ss.hasPermission('evaluate:index-type:query')")
    public CommonResult<PageResult<IndexTypeRespVO>> getIndexTypePage(@Valid IndexTypePageReqVO pageReqVO) {
        PageResult<IndexTypeDO> pageResult = indexTypeService.getIndexTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, IndexTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指标类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:index-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportIndexTypeExcel(@Valid IndexTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<IndexTypeDO> list = indexTypeService.getIndexTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "指标类型字典.xls", "数据", IndexTypeRespVO.class,
                        BeanUtils.toBean(list, IndexTypeRespVO.class));
    }

}