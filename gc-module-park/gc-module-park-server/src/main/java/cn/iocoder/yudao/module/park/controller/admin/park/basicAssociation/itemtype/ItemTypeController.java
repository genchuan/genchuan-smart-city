package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.itemtype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.itemtype.vo.ItemTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.itemtype.vo.ItemTypeRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.itemtype.vo.ItemTypeSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.itemtype.ItemTypeDO;
import cn.iocoder.yudao.module.park.service.park.basicAssociation.itemtype.ItemTypeService;
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

@Tag(name = "管理后台 - 管理事项类别")
@RestController
@RequestMapping("/park/item-type")
@Validated
public class ItemTypeController {

    @Resource
    private ItemTypeService itemTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项类别")
    @PreAuthorize("@ss.hasPermission('park:item-type:create')")
    public CommonResult<Long> createItemType(@Valid @RequestBody ItemTypeSaveReqVO createReqVO) {
        return success(itemTypeService.createItemType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项类别")
    @PreAuthorize("@ss.hasPermission('park:item-type:update')")
    public CommonResult<Boolean> updateItemType(@Valid @RequestBody ItemTypeSaveReqVO updateReqVO) {
        itemTypeService.updateItemType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项类别")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:item-type:delete')")
    public CommonResult<Boolean> deleteItemType(@RequestParam("id") Long id) {
        itemTypeService.deleteItemType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项类别")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:item-type:query')")
    public CommonResult<ItemTypeRespVO> getItemType(@RequestParam("id") Long id) {
        ItemTypeDO itemType = itemTypeService.getItemType(id);
        return success(BeanUtils.toBean(itemType, ItemTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项类别分页")
    @PreAuthorize("@ss.hasPermission('park:item-type:query')")
    public CommonResult<PageResult<ItemTypeRespVO>> getItemTypePage(@Valid ItemTypePageReqVO pageReqVO) {
        PageResult<ItemTypeDO> pageResult = itemTypeService.getItemTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ItemTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项类别 Excel")
    @PreAuthorize("@ss.hasPermission('park:item-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportItemTypeExcel(@Valid ItemTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ItemTypeDO> list = itemTypeService.getItemTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项类别.xls", "数据", ItemTypeRespVO.class,
                        BeanUtils.toBean(list, ItemTypeRespVO.class));
    }

}
