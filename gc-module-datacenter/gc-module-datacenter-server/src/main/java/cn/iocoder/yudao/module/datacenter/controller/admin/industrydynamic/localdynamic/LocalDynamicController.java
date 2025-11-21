package cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic;

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic.vo.LocalDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic.vo.LocalDynamicRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic.vo.LocalDynamicSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.localdynamic.LocalDynamicDO;
import cn.iocoder.yudao.module.datacenter.service.industrydynamic.localdynamic.LocalDynamicService;

@Tag(name = "管理后台 - 本地行业动态")
@RestController
@RequestMapping("/datacenter/local-dynamic")
@Validated
public class LocalDynamicController {

    @Resource
    private LocalDynamicService localDynamicService;

    @PostMapping("/create")
    @Operation(summary = "创建本地行业动态")
    @PreAuthorize("@ss.hasPermission('datacenter:local-dynamic:create')")
    public CommonResult<Long> createLocalDynamic(@Valid @RequestBody LocalDynamicSaveReqVO createReqVO) {
        return success(localDynamicService.createLocalDynamic(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新本地行业动态")
    @PreAuthorize("@ss.hasPermission('datacenter:local-dynamic:update')")
    public CommonResult<Boolean> updateLocalDynamic(@Valid @RequestBody LocalDynamicSaveReqVO updateReqVO) {
        localDynamicService.updateLocalDynamic(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除本地行业动态")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:local-dynamic:delete')")
    public CommonResult<Boolean> deleteLocalDynamic(@RequestParam("id") Long id) {
        localDynamicService.deleteLocalDynamic(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得本地行业动态")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:local-dynamic:query')")
    public CommonResult<LocalDynamicRespVO> getLocalDynamic(@RequestParam("id") Long id) {
        LocalDynamicDO localDynamic = localDynamicService.getLocalDynamic(id);
        return success(BeanUtils.toBean(localDynamic, LocalDynamicRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得本地行业动态分页")
    @PreAuthorize("@ss.hasPermission('datacenter:local-dynamic:query')")
    public CommonResult<PageResult<LocalDynamicRespVO>> getLocalDynamicPage(@Valid LocalDynamicPageReqVO pageReqVO) {
        PageResult<LocalDynamicDO> pageResult = localDynamicService.getLocalDynamicPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LocalDynamicRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出本地行业动态 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:local-dynamic:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLocalDynamicExcel(@Valid LocalDynamicPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LocalDynamicDO> list = localDynamicService.getLocalDynamicPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "本地行业动态.xls", "数据", LocalDynamicRespVO.class,
                        BeanUtils.toBean(list, LocalDynamicRespVO.class));
    }

}