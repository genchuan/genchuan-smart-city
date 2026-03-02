package cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.relatedobject;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.relatedobject.vo.RelatedObjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.relatedobject.vo.RelatedObjectRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.relatedobject.vo.RelatedObjectSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.baseinfo.relatedobject.RelatedObjectDO;
import cn.iocoder.yudao.module.evaluate.service.baseinfo.relatedobject.RelatedObjectService;
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

@Tag(name = "管理后台 - 关联对象")
@RestController
@RequestMapping("/evaluate/related-object")
@Validated
public class RelatedObjectController {

    @Resource
    private RelatedObjectService relatedObjectService;

    @PostMapping("/create")
    @Operation(summary = "创建关联对象")
    @PreAuthorize("@ss.hasPermission('evaluate:related-object:create')")
    public CommonResult<Long> createRelatedObject(@Valid @RequestBody RelatedObjectSaveReqVO createReqVO) {
        return success(relatedObjectService.createRelatedObject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新关联对象")
    @PreAuthorize("@ss.hasPermission('evaluate:related-object:update')")
    public CommonResult<Boolean> updateRelatedObject(@Valid @RequestBody RelatedObjectSaveReqVO updateReqVO) {
        relatedObjectService.updateRelatedObject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除关联对象")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:related-object:delete')")
    public CommonResult<Boolean> deleteRelatedObject(@RequestParam("id") Long id) {
        relatedObjectService.deleteRelatedObject(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得关联对象")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:related-object:query')")
    public CommonResult<RelatedObjectRespVO> getRelatedObject(@RequestParam("id") Long id) {
        RelatedObjectDO relatedObject = relatedObjectService.getRelatedObject(id);
        return success(BeanUtils.toBean(relatedObject, RelatedObjectRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得关联对象分页")
    @PreAuthorize("@ss.hasPermission('evaluate:related-object:query')")
    public CommonResult<PageResult<RelatedObjectRespVO>> getRelatedObjectPage(@Valid RelatedObjectPageReqVO pageReqVO) {
        PageResult<RelatedObjectDO> pageResult = relatedObjectService.getRelatedObjectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RelatedObjectRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出关联对象 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:related-object:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRelatedObjectExcel(@Valid RelatedObjectPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RelatedObjectDO> list = relatedObjectService.getRelatedObjectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "关联对象.xls", "数据", RelatedObjectRespVO.class,
                        BeanUtils.toBean(list, RelatedObjectRespVO.class));
    }

}