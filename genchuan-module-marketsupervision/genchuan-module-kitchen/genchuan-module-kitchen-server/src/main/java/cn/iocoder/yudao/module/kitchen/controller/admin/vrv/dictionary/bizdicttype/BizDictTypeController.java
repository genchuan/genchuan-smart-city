package cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype;

import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.BizDictTypePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.BizDictTypeRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.BizDictTypeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.vrv.dictionary.bizdicttype.BizDictTypeDO;
import cn.iocoder.yudao.module.kitchen.service.vrv.dictionary.bizdicttype.BizDictTypeService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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


@Tag(name = "管理后台 - 业务字典分类")
@RestController
@RequestMapping("/kitchen/biz-dict-type")
@Validated
public class BizDictTypeController {

    @Resource
    private BizDictTypeService bizDictTypeService;

    @PostMapping("/add")
    @Operation(summary = "创建业务字典分类")
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-type:create')")
    public CommonResult<Long> addBizDictType(@Valid @RequestBody BizDictTypeSaveReqVO createReqVO) {
        return success(bizDictTypeService.createBizDictType(createReqVO));
    }


    @GetMapping("/page")
    @Operation(summary = "获得业务字典分类分页")
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-type:query')")
    public CommonResult<PageResult<BizDictTypeRespVO>> getBizDictTypePage(@Valid BizDictTypePageReqVO pageReqVO) {
        PageResult<BizDictTypeDO> pageResult = bizDictTypeService.getBizDictTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BizDictTypeRespVO.class));
    }
    @GetMapping("/get")
    @Operation(summary = "获得业务字典分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-type:query')")
    public CommonResult<BizDictTypeRespVO> getBizDictType(@RequestParam("id") Long id) {
        BizDictTypeDO bizDictType = bizDictTypeService.getBizDictType(id);
        return success(BeanUtils.toBean(bizDictType, BizDictTypeRespVO.class));
    }
    //==============================================以下暂时屏蔽=======================
    @PostMapping("/create")
    @Operation(summary = "创建业务字典分类",hidden = true)
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-type:create')")
    public CommonResult<Long> createBizDictType(@Valid @RequestBody BizDictTypeSaveReqVO createReqVO) {
        return success(bizDictTypeService.createBizDictType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新业务字典分类",hidden = true)
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-type:update')")
    public CommonResult<Boolean> updateBizDictType(@Valid @RequestBody BizDictTypeSaveReqVO updateReqVO) {
        bizDictTypeService.updateBizDictType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除业务字典分类",hidden = true)
    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-type:delete')")
    public CommonResult<Boolean> deleteBizDictType(@RequestParam("id") Long id) {
        bizDictTypeService.deleteBizDictType(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除业务字典分类",hidden = true)
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-type:delete')")
    public CommonResult<Boolean> deleteBizDictTypeList(@RequestParam("ids") List<Long> ids) {
        bizDictTypeService.deleteBizDictTypeListByIds(ids);
        return success(true);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出业务字典分类 Excel",hidden = true)
//    @PreAuthorize("@ss.hasPermission('kitchen:biz-dict-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBizDictTypeExcel(@Valid BizDictTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BizDictTypeDO> list = bizDictTypeService.getBizDictTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "业务字典分类.xls", "数据", BizDictTypeRespVO.class,
                        BeanUtils.toBean(list, BizDictTypeRespVO.class));
    }

}
