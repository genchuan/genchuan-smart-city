package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.parttype;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.parttype.vo.PartTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.parttype.vo.PartTypeRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.parttype.vo.PartTypeSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.parttype.PartTypeDO;
import cn.iocoder.yudao.module.park.service.park.basicAssociation.parttype.PartTypeService;

@Tag(name = "管理后台 - 管理部件类别")
@RestController
@RequestMapping("/park/part-type")
@Validated
public class PartTypeController {

    @Resource
    private PartTypeService partTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件类别")
    @PreAuthorize("@ss.hasPermission('park:part-type:create')")
    public CommonResult<Long> createPartType(@Valid @RequestBody PartTypeSaveReqVO createReqVO) {
        return success(partTypeService.createPartType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件类别")
    @PreAuthorize("@ss.hasPermission('park:part-type:update')")
    public CommonResult<Boolean> updatePartType(@Valid @RequestBody PartTypeSaveReqVO updateReqVO) {
        partTypeService.updatePartType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件类别")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:part-type:delete')")
    public CommonResult<Boolean> deletePartType(@RequestParam("id") Long id) {
        partTypeService.deletePartType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件类别")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:part-type:query')")
    public CommonResult<PartTypeRespVO> getPartType(@RequestParam("id") Long id) {
        PartTypeDO partType = partTypeService.getPartType(id);
        return success(BeanUtils.toBean(partType, PartTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件类别分页")
    @PreAuthorize("@ss.hasPermission('park:part-type:query')")
    public CommonResult<PageResult<PartTypeRespVO>> getPartTypePage(@Valid PartTypePageReqVO pageReqVO) {
        PageResult<PartTypeDO> pageResult = partTypeService.getPartTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PartTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件类别 Excel")
    @PreAuthorize("@ss.hasPermission('park:part-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPartTypeExcel(@Valid PartTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PartTypeDO> list = partTypeService.getPartTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件类别.xls", "数据", PartTypeRespVO.class,
                        BeanUtils.toBean(list, PartTypeRespVO.class));
    }

}