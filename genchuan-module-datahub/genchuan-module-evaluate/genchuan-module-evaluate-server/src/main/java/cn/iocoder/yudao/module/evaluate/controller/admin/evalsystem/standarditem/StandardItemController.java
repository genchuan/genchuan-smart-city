package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standarditem;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standarditem.vo.StandardItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standarditem.vo.StandardItemRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standarditem.vo.StandardItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standarditem.StandardItemDO;
import cn.iocoder.yudao.module.evaluate.service.standarditem.StandardItemService;
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

@Tag(name = "管理后台 - 标准项")
@RestController
@RequestMapping("/evaluate/standard-item")
@Validated
public class StandardItemController {

    @Resource
    private StandardItemService standardItemService;

    @PostMapping("/create")
    @Operation(summary = "创建标准项")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-item:create')")
    public CommonResult<Long> createStandardItem(@Valid @RequestBody StandardItemSaveReqVO createReqVO) {
        return success(standardItemService.createStandardItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新标准项")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-item:update')")
    public CommonResult<Boolean> updateStandardItem(@Valid @RequestBody StandardItemSaveReqVO updateReqVO) {
        standardItemService.updateStandardItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除标准项")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:standard-item:delete')")
    public CommonResult<Boolean> deleteStandardItem(@RequestParam("id") Long id) {
        standardItemService.deleteStandardItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得标准项")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-item:query')")
    public CommonResult<StandardItemRespVO> getStandardItem(@RequestParam("id") Long id) {
        StandardItemDO standardItem = standardItemService.getStandardItem(id);
        return success(BeanUtils.toBean(standardItem, StandardItemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得标准项分页")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-item:query')")
    public CommonResult<PageResult<StandardItemRespVO>> getStandardItemPage(@Valid StandardItemPageReqVO pageReqVO) {
        PageResult<StandardItemDO> pageResult = standardItemService.getStandardItemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StandardItemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出标准项 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-item:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStandardItemExcel(@Valid StandardItemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StandardItemDO> list = standardItemService.getStandardItemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "标准项.xls", "数据", StandardItemRespVO.class,
                        BeanUtils.toBean(list, StandardItemRespVO.class));
    }

}