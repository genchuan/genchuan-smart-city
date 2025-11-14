package cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand;

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand.vo.CoopCommandPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand.vo.CoopCommandRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand.vo.CoopCommandSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopcommand.CoopCommandDO;
import cn.iocoder.yudao.module.datacenter.service.coopcfg.coopcommand.CoopCommandService;

@Tag(name = "管理后台 - 联动指令")
@RestController
@RequestMapping("/datacenter/coop-command")
@Validated
public class CoopCommandController {

    @Resource
    private CoopCommandService coopCommandService;

    @PostMapping("/create")
    @Operation(summary = "创建联动指令")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-command:create')")
    public CommonResult<Long> createCoopCommand(@Valid @RequestBody CoopCommandSaveReqVO createReqVO) {
        return success(coopCommandService.createCoopCommand(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新联动指令")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-command:update')")
    public CommonResult<Boolean> updateCoopCommand(@Valid @RequestBody CoopCommandSaveReqVO updateReqVO) {
        coopCommandService.updateCoopCommand(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除联动指令")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:coop-command:delete')")
    public CommonResult<Boolean> deleteCoopCommand(@RequestParam("id") Long id) {
        coopCommandService.deleteCoopCommand(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得联动指令")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-command:query')")
    public CommonResult<CoopCommandRespVO> getCoopCommand(@RequestParam("id") Long id) {
        CoopCommandDO coopCommand = coopCommandService.getCoopCommand(id);
        return success(BeanUtils.toBean(coopCommand, CoopCommandRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得联动指令分页")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-command:query')")
    public CommonResult<PageResult<CoopCommandRespVO>> getCoopCommandPage(@Valid CoopCommandPageReqVO pageReqVO) {
        PageResult<CoopCommandDO> pageResult = coopCommandService.getCoopCommandPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CoopCommandRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出联动指令 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-command:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCoopCommandExcel(@Valid CoopCommandPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CoopCommandDO> list = coopCommandService.getCoopCommandPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "联动指令.xls", "数据", CoopCommandRespVO.class,
                        BeanUtils.toBean(list, CoopCommandRespVO.class));
    }

}