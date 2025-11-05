package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.mnggridattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.mnggridattr.vo.MngGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.mnggridattr.vo.MngGridAttrRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.mnggridattr.vo.MngGridAttrSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.mnggridattr.MngGridAttrDO;
import cn.iocoder.yudao.module.datacenter.service.grid.data.mnggridattr.MngGridAttrService;

@Tag(name = "管理后台 - 管理网格属性")
@RestController
@RequestMapping("/datacenter/mng-grid-attr")
@Validated
public class MngGridAttrController {

    @Resource
    private MngGridAttrService mngGridAttrService;

    @PostMapping("/create")
    @Operation(summary = "创建管理网格属性")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-attr:create')")
    public CommonResult<Long> createMngGridAttr(@Valid @RequestBody MngGridAttrSaveReqVO createReqVO) {
        return success(mngGridAttrService.createMngGridAttr(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理网格属性")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-attr:update')")
    public CommonResult<Boolean> updateMngGridAttr(@Valid @RequestBody MngGridAttrSaveReqVO updateReqVO) {
        mngGridAttrService.updateMngGridAttr(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理网格属性")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-attr:delete')")
    public CommonResult<Boolean> deleteMngGridAttr(@RequestParam("id") Long id) {
        mngGridAttrService.deleteMngGridAttr(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理网格属性")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-attr:query')")
    public CommonResult<MngGridAttrRespVO> getMngGridAttr(@RequestParam("id") Long id) {
        MngGridAttrDO mngGridAttr = mngGridAttrService.getMngGridAttr(id);
        return success(BeanUtils.toBean(mngGridAttr, MngGridAttrRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理网格属性分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-attr:query')")
    public CommonResult<PageResult<MngGridAttrRespVO>> getMngGridAttrPage(@Valid MngGridAttrPageReqVO pageReqVO) {
        PageResult<MngGridAttrDO> pageResult = mngGridAttrService.getMngGridAttrPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MngGridAttrRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理网格属性 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-attr:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMngGridAttrExcel(@Valid MngGridAttrPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MngGridAttrDO> list = mngGridAttrService.getMngGridAttrPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理网格属性.xls", "数据", MngGridAttrRespVO.class,
                        BeanUtils.toBean(list, MngGridAttrRespVO.class));
    }

}