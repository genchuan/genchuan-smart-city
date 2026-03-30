package cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow.vo.WalletFlowPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow.vo.WalletFlowRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow.vo.WalletFlowSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.trade.walletflow.WalletFlowDO;
import cn.iocoder.yudao.module.park.service.park.trade.walletflow.WalletFlowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 钱包流水")
@RestController
@RequestMapping("/park/wallet-flow")
@Validated
public class WalletFlowController {

    @Resource
    private WalletFlowService walletFlowService;

    @PostMapping("/create")
    @Operation(summary = "创建钱包流水")
//    @PreAuthorize("@ss.hasPermission('park:wallet-flow:create')")
    public CommonResult<Long> createWalletFlow(@Valid @RequestBody WalletFlowSaveReqVO createReqVO) {
        return success(walletFlowService.createWalletFlow(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新钱包流水")
//    @PreAuthorize("@ss.hasPermission('park:wallet-flow:update')")
    public CommonResult<Boolean> updateWalletFlow(@Valid @RequestBody WalletFlowSaveReqVO updateReqVO) {
        walletFlowService.updateWalletFlow(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除钱包流水")
    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('park:wallet-flow:delete')")
    public CommonResult<Boolean> deleteWalletFlow(@RequestParam("id") Long id) {
        walletFlowService.deleteWalletFlow(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得钱包流水")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('park:wallet-flow:query')")
    public CommonResult<WalletFlowRespVO> getWalletFlow(@RequestParam("id") Long id) {
        WalletFlowDO walletFlow = walletFlowService.getWalletFlow(id);
        return success(BeanUtils.toBean(walletFlow, WalletFlowRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得钱包流水分页")
//    @PreAuthorize("@ss.hasPermission('park:wallet-flow:query')")
    public CommonResult<PageResult<WalletFlowRespVO>> getWalletFlowPage(@Valid WalletFlowPageReqVO pageReqVO) {
        PageResult<WalletFlowDO> pageResult = walletFlowService.getWalletFlowPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WalletFlowRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出钱包流水 Excel")
//    @PreAuthorize("@ss.hasPermission('park:wallet-flow:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWalletFlowExcel(@Valid WalletFlowPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WalletFlowDO> list = walletFlowService.getWalletFlowPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "钱包流水.xls", "数据", WalletFlowRespVO.class,
                        BeanUtils.toBean(list, WalletFlowRespVO.class));
    }

}
