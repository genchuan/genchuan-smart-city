package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem.vo.VetoItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem.vo.VetoItemRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem.vo.VetoItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.vetoitem.VetoItemDO;
import cn.iocoder.yudao.module.evaluate.service.vetoitem.VetoItemService;
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

@Tag(name = "管理后台 - 否决项")
@RestController
@RequestMapping("/evaluate/veto-item")
@Validated
public class VetoItemController {

    @Resource
    private VetoItemService vetoItemService;

    @PostMapping("/create")
    @Operation(summary = "创建否决项")
    @PreAuthorize("@ss.hasPermission('evaluate:veto-item:create')")
    public CommonResult<Long> createVetoItem(@Valid @RequestBody VetoItemSaveReqVO createReqVO) {
        return success(vetoItemService.createVetoItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新否决项")
    @PreAuthorize("@ss.hasPermission('evaluate:veto-item:update')")
    public CommonResult<Boolean> updateVetoItem(@Valid @RequestBody VetoItemSaveReqVO updateReqVO) {
        vetoItemService.updateVetoItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除否决项")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:veto-item:delete')")
    public CommonResult<Boolean> deleteVetoItem(@RequestParam("id") Long id) {
        vetoItemService.deleteVetoItem(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除否决项")
                @PreAuthorize("@ss.hasPermission('evaluate:veto-item:delete')")
    public CommonResult<Boolean> deleteVetoItemList(@RequestParam("ids") List<Long> ids) {
        vetoItemService.deleteVetoItemListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得否决项")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:veto-item:query')")
    public CommonResult<VetoItemRespVO> getVetoItem(@RequestParam("id") Long id) {
        VetoItemDO vetoItem = vetoItemService.getVetoItem(id);
        return success(BeanUtils.toBean(vetoItem, VetoItemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得否决项分页")
    @PreAuthorize("@ss.hasPermission('evaluate:veto-item:query')")
    public CommonResult<PageResult<VetoItemRespVO>> getVetoItemPage(@Valid VetoItemPageReqVO pageReqVO) {
        PageResult<VetoItemDO> pageResult = vetoItemService.getVetoItemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, VetoItemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出否决项 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:veto-item:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportVetoItemExcel(@Valid VetoItemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<VetoItemDO> list = vetoItemService.getVetoItemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "否决项.xls", "数据", VetoItemRespVO.class,
                        BeanUtils.toBean(list, VetoItemRespVO.class));
    }

}