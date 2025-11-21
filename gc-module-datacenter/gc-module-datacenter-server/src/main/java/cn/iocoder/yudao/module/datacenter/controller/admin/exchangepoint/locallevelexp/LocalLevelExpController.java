package cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp;

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp.vo.LocalLevelExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp.vo.LocalLevelExpRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp.vo.LocalLevelExpSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.locallevelexp.LocalLevelExpDO;
import cn.iocoder.yudao.module.datacenter.service.exchangepoint.locallevelexp.LocalLevelExpService;

@Tag(name = "管理后台 - 本级经验分享")
@RestController
@RequestMapping("/datacenter/local-level-exp")
@Validated
public class LocalLevelExpController {

    @Resource
    private LocalLevelExpService localLevelExpService;

    @PostMapping("/create")
    @Operation(summary = "创建本级经验分享")
    @PreAuthorize("@ss.hasPermission('datacenter:local-level-exp:create')")
    public CommonResult<Long> createLocalLevelExp(@Valid @RequestBody LocalLevelExpSaveReqVO createReqVO) {
        return success(localLevelExpService.createLocalLevelExp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新本级经验分享")
    @PreAuthorize("@ss.hasPermission('datacenter:local-level-exp:update')")
    public CommonResult<Boolean> updateLocalLevelExp(@Valid @RequestBody LocalLevelExpSaveReqVO updateReqVO) {
        localLevelExpService.updateLocalLevelExp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除本级经验分享")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:local-level-exp:delete')")
    public CommonResult<Boolean> deleteLocalLevelExp(@RequestParam("id") Long id) {
        localLevelExpService.deleteLocalLevelExp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得本级经验分享")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:local-level-exp:query')")
    public CommonResult<LocalLevelExpRespVO> getLocalLevelExp(@RequestParam("id") Long id) {
        LocalLevelExpDO localLevelExp = localLevelExpService.getLocalLevelExp(id);
        return success(BeanUtils.toBean(localLevelExp, LocalLevelExpRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得本级经验分享分页")
    @PreAuthorize("@ss.hasPermission('datacenter:local-level-exp:query')")
    public CommonResult<PageResult<LocalLevelExpRespVO>> getLocalLevelExpPage(@Valid LocalLevelExpPageReqVO pageReqVO) {
        PageResult<LocalLevelExpDO> pageResult = localLevelExpService.getLocalLevelExpPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LocalLevelExpRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出本级经验分享 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:local-level-exp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLocalLevelExpExcel(@Valid LocalLevelExpPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LocalLevelExpDO> list = localLevelExpService.getLocalLevelExpPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "本级经验分享.xls", "数据", LocalLevelExpRespVO.class,
                        BeanUtils.toBean(list, LocalLevelExpRespVO.class));
    }

}