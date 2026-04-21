package cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo.BizDictItemPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo.BizDictItemRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo.BizDictItemSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo.ops.*;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.vrv.dictionary.bizdictitem.BizDictItemDO;
import cn.iocoder.yudao.module.stationresource.service.vrv.dictionary.bizdictitem.BizDictItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 业务字典项")
@RestController
@RequestMapping("/stationresource/biz-dict-item")
@Validated
public class BizDictItemController {

    @Resource
    private BizDictItemService bizDictItemService;


    @GetMapping("/list-by-type-fuzzy")
    @Operation(summary = "AAA-字典项-严格模糊搜索（按类型校验）")
    public CommonResult<List<ListByTypeResp>> listByTypeFuzzy(@Valid ListByTypeFuzzyReq reqVO) {
        List<ListByTypeResp> result = bizDictItemService.listByTypeFuzzy(reqVO);
        return success(result);
    }
    @PostMapping("/batch-add")
    @Operation(summary = "批量新增业务字典项")
    public CommonResult<BatchResult> batchAddBizDictItem(@RequestBody List<AddReq> addReqList) {
        BatchResult result = bizDictItemService.batchAddBizDictItem(addReqList);
        return success(result);
    }
    /**
     * 更新业务字典项
     * 可更新字段：dict_key、dict_label、color、sort、description、remark、status
     */
    @PutMapping("/update-biz")
    @Operation(summary = "业务更新-字典项")
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-item:update')")
    public CommonResult<Boolean> updateBiz(@Valid @RequestBody UpdateReq updateReqVO) {
        bizDictItemService.updateBiz(updateReqVO);
        return success(true);
    }

    /**
     * 新增业务字典项
     * 自动排序，默认启用，默认颜色#1890ff，校验类型存在、键唯一
     */
    @PostMapping("/add")
    @Operation(summary = "创建业务字典项")
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-item:create')")
    public CommonResult<Long> createBizDictItem(@Valid @RequestBody AddReq createReqVO) {
        Long id = bizDictItemService.addBizDictItem(createReqVO);
        return success(id);
    }

    /**
     * 根据类型编码获取启用的字典项列表
     * 按sort正序、创建时间正序排列
     */
    @GetMapping("/list-by-type")
    @Operation(summary = "AAA-获取该类型的全部字典项")
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-item:query')")
    public CommonResult<List<ListByTypeResp>> ListByType(@Valid ListByTypeReq req) {
        List<ListByTypeResp> result = bizDictItemService.listByType(req);
        return success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获得业务字典项")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-item:query')")
    public CommonResult<BizDictItemRespVO> getBizDictItem(@RequestParam("id") Long id) {
        BizDictItemDO bizDictItem = bizDictItemService.getBizDictItem(id);
        return success(BeanUtils.toBean(bizDictItem, BizDictItemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得业务字典项分页")
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-item:query')")
    public CommonResult<PageResult<BizDictItemRespVO>> getBizDictItemPage(@Valid BizDictItemPageReqVO pageReqVO) {
        PageResult<BizDictItemDO> pageResult = bizDictItemService.getBizDictItemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BizDictItemRespVO.class));
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除业务字典项")
    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-item:delete')")
    public CommonResult<Boolean> deleteBizDictItem(@RequestParam("id") Long id) {
        bizDictItemService.deleteBizDictItem(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除业务字典项")
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-item:delete')")
    public CommonResult<Boolean> deleteBizDictItemList(@RequestParam("ids") List<Long> ids) {
        bizDictItemService.deleteBizDictItemListByIds(ids);
        return success(true);
    }


//    ================================暂时不用=========================================
    @PostMapping("/create")
    @Operation(summary = "(勿用)创建业务字典项",hidden = true)
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-item:create')")
    public CommonResult<Long> createBizDictItem(@Valid @RequestBody BizDictItemSaveReqVO createReqVO) {
        return success(bizDictItemService.createBizDictItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新业务字典项",hidden = true)
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-item:update')")
    public CommonResult<Boolean> updateBizDictItem(@Valid @RequestBody BizDictItemSaveReqVO updateReqVO) {
        bizDictItemService.updateBizDictItem(updateReqVO);
        return success(true);
    }





    @GetMapping("/export-excel")
    @Operation(summary = "导出业务字典项 Excel",hidden = true)
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-item:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBizDictItemExcel(@Valid BizDictItemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BizDictItemDO> list = bizDictItemService.getBizDictItemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "业务字典项.xls", "数据", BizDictItemRespVO.class,
                        BeanUtils.toBean(list, BizDictItemRespVO.class));
    }

}
