package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.object.ObjectDO;
import cn.iocoder.yudao.module.evaluate.service.object.ObjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "评价体系管理 - 评价对象")
@RestController
@RequestMapping("/evaluate/object")
@Validated
@Slf4j
public class ObjectController {

    @Resource
    private ObjectService objectService;

    @PostMapping("/create")
    @Operation(summary = "创建评价对象")
    @PreAuthorize("@ss.hasPermission('evaluate:object:create')")
    public CommonResult<Long> createObject(@Valid @RequestBody ObjectSaveReqVO createReqVO) {
        return success(objectService.createObject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价对象")
    @PreAuthorize("@ss.hasPermission('evaluate:object:update')")
    public CommonResult<Boolean> updateObject(@Valid @RequestBody ObjectSaveReqVO updateReqVO) {
        objectService.updateObject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价对象")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:object:delete')")
    public CommonResult<Boolean> deleteObject(@RequestParam("id") Long id) {
        objectService.deleteObject(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价对象")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:object:query')")
    public CommonResult<ObjectRespVO> getObject(@RequestParam("id") Long id) {
        ObjectDO object = objectService.getObject(id);
        return success(BeanUtils.toBean(object, ObjectRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价对象分页")
    @PreAuthorize("@ss.hasPermission('evaluate:object:query')")
    public CommonResult<PageResult<ObjectRespVO>> getObjectPage(@Valid ObjectPageReqVO pageReqVO) {
        PageResult<ObjectDO> pageResult = objectService.getObjectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ObjectRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价对象 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:object:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportObjectExcel(@Valid ObjectPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ObjectDO> list = objectService.getObjectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价对象.xls", "数据", ObjectRespVO.class,
                        BeanUtils.toBean(list, ObjectRespVO.class));
    }
    @PostMapping("/import")
    @Operation(summary = "批量导入评价对象")
    public CommonResult<Integer> importObjects(@RequestParam("file") MultipartFile file) throws IOException {
        // 解析Excel文件
        List<ObjectSaveReqVO> importList = ExcelUtils.read(file, ObjectSaveReqVO.class);

        // 导入数据
        objectService.importObjects(importList);

        return success(importList.size());
    }
    @GetMapping("/evalpage")
    @Operation(summary = "分页查询对象列表", description = "支持分页查询对象信息，包含关联数据")
    public PageResult<ObjectRespVO> pageJoinQuery(@Valid ObjectPageReqVO pageReqVO) {
        return objectService.pageJoinQuery(pageReqVO);
    }

    @GetMapping("/{objectId}")
    @Operation(summary = "根据ID获取对象详情", description = "获取指定对象的完整详细信息")
    public ObjectRespVO getDetailById(
            @Parameter(description = "对象ID", required = true)
            @PathVariable String objectId) {
        return objectService.getDetailById(objectId);
    }

    @GetMapping("/validate/name-unique")
    @Operation(summary = "验证名称唯一性", description = "检查指定区域内对象名称是否唯一")
    public void validateNameUnique(
            @Parameter(description = "对象名称", required = true)
            @RequestParam @NotBlank String name,

            @Parameter(description = "区域编码", required = true)
            @RequestParam @NotBlank String areaCode,

            @Parameter(description = "排除的对象ID（用于更新时排除自身）")
            @RequestParam(required = false) String excludeObjectId) {

        objectService.validateNameUnique(name, areaCode, excludeObjectId);
    }
//新改mpl

    /**
     *
     * @param pageParam
     * @return
     */
    @GetMapping("/allpage")
    @Operation(summary = "评价对象全量联表查询")
    public CommonResult<PageResult<ObjectRespVO>> getAllObjectPage(PageParam pageParam) {
        PageResult<ObjectRespVO> pageResult = objectService.getAllObjectPage(pageParam);
        return success(pageResult);
    }
}