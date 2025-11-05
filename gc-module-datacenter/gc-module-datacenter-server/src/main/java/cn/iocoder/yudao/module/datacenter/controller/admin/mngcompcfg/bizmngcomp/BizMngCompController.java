package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp.vo.BizMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp.vo.BizMngCompRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp.vo.BizMngCompSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcomp.BizMngCompDO;
import cn.iocoder.yudao.module.datacenter.service.mngcompcfg.bizmngcomp.BizMngCompService;

@Tag(name = "管理后台 - 管理部件信息管理")
@RestController
@RequestMapping("/datacenter/biz-mng-comp")
@Validated
public class BizMngCompController {

    @Resource
    private BizMngCompService bizMngCompService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件信息管理")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp:create')")
    public CommonResult<Long> createBizMngComp(@Valid @RequestBody BizMngCompSaveReqVO createReqVO) {
        return success(bizMngCompService.createBizMngComp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件信息管理")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp:update')")
    public CommonResult<Boolean> updateBizMngComp(@Valid @RequestBody BizMngCompSaveReqVO updateReqVO) {
        bizMngCompService.updateBizMngComp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件信息管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp:delete')")
    public CommonResult<Boolean> deleteBizMngComp(@RequestParam("id") Long id) {
        bizMngCompService.deleteBizMngComp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件信息管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp:query')")
    public CommonResult<BizMngCompRespVO> getBizMngComp(@RequestParam("id") Long id) {
        BizMngCompDO bizMngComp = bizMngCompService.getBizMngComp(id);
        return success(BeanUtils.toBean(bizMngComp, BizMngCompRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件信息管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp:query')")
    public CommonResult<PageResult<BizMngCompRespVO>> getBizMngCompPage(@Valid BizMngCompPageReqVO pageReqVO) {
        PageResult<BizMngCompDO> pageResult = bizMngCompService.getBizMngCompPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BizMngCompRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件信息管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:biz-mng-comp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBizMngCompExcel(@Valid BizMngCompPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BizMngCompDO> list = bizMngCompService.getBizMngCompPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件信息管理.xls", "数据", BizMngCompRespVO.class,
                        BeanUtils.toBean(list, BizMngCompRespVO.class));
    }

}