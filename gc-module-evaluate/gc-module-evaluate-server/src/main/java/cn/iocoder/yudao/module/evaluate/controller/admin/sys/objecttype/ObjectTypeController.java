package cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype.vo.ObjectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype.vo.ObjectTypeRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.objecttype.vo.ObjectTypeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.module.evaluate.service.objecttype.ObjectTypeService;
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

@Tag(name = "管理后台 - 对象类型字典")
@RestController
@RequestMapping("/evaluate/object-type")
@Validated
public class ObjectTypeController {

    @Resource
    private ObjectTypeService objectTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建对象类型字典")
    @PreAuthorize("@ss.hasPermission('evaluate:object-type:create')")
    public CommonResult<Long> createObjectType(@Valid @RequestBody ObjectTypeSaveReqVO createReqVO) {
        return success(objectTypeService.createObjectType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新对象类型字典")
    @PreAuthorize("@ss.hasPermission('evaluate:object-type:update')")
    public CommonResult<Boolean> updateObjectType(@Valid @RequestBody ObjectTypeSaveReqVO updateReqVO) {
        objectTypeService.updateObjectType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除对象类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:object-type:delete')")
    public CommonResult<Boolean> deleteObjectType(@RequestParam("id") Long id) {
        objectTypeService.deleteObjectType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得对象类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:object-type:query')")
    public CommonResult<ObjectTypeRespVO> getObjectType(@RequestParam("id") Long id) {
        ObjectTypeDO objectType = objectTypeService.getObjectType(id);
        return success(BeanUtils.toBean(objectType, ObjectTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得对象类型字典分页")
    @PreAuthorize("@ss.hasPermission('evaluate:object-type:query')")
    public CommonResult<PageResult<ObjectTypeRespVO>> getObjectTypePage(@Valid ObjectTypePageReqVO pageReqVO) {
        PageResult<ObjectTypeDO> pageResult = objectTypeService.getObjectTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ObjectTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出对象类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:object-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportObjectTypeExcel(@Valid ObjectTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ObjectTypeDO> list = objectTypeService.getObjectTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "对象类型字典.xls", "数据", ObjectTypeRespVO.class,
                        BeanUtils.toBean(list, ObjectTypeRespVO.class));
    }

}