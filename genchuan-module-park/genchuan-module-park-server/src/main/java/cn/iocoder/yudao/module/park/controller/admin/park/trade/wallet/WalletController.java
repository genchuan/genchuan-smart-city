package cn.iocoder.yudao.module.park.controller.admin.park.trade.wallet;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.wallet.vo.WalletPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.wallet.vo.WalletRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.wallet.vo.WalletSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.trade.wallet.WalletDO;
import cn.iocoder.yudao.module.park.service.park.trade.wallet.WalletService;
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


@Tag(name = "管理后台 - 用户钱包")
@RestController
@RequestMapping("/park/wallet")
@Validated
public class WalletController {

    @Resource
    private WalletService walletService;

    @GetMapping("/getByUserId")
    @Operation(summary = "获得用户id的钱包")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
//    @PreAuthorize("@ss.hasPermission('park:wallet:getByUserId')")
    public CommonResult<WalletRespVO> getByUserId(@RequestParam("id") Long id) {
        WalletDO wallet = walletService.getByUserId(id);
        return success(BeanUtils.toBean(wallet, WalletRespVO.class));
    }
    @PostMapping("/create")
    @Operation(summary = "创建用户钱包")
//    @PreAuthorize("@ss.hasPermission('park:wallet:create')")
    public CommonResult<Long> createWallet(@Valid @RequestBody WalletSaveReqVO createReqVO) {
        return success(walletService.createWallet(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户钱包")
//    @PreAuthorize("@ss.hasPermission('park:wallet:update')")
    public CommonResult<Boolean> updateWallet(@Valid @RequestBody WalletSaveReqVO updateReqVO) {
        walletService.updateWallet(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户钱包")
    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('park:wallet:delete')")
    public CommonResult<Boolean> deleteWallet(@RequestParam("id") Long id) {
        walletService.deleteWallet(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户钱包")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('park:wallet:query')")
    public CommonResult<WalletRespVO> getWallet(@RequestParam("id") Long id) {
        WalletDO wallet = walletService.getWallet(id);
        return success(BeanUtils.toBean(wallet, WalletRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户钱包分页")
//    @PreAuthorize("@ss.hasPermission('park:wallet:query')")
    public CommonResult<PageResult<WalletRespVO>> getWalletPage(@Valid WalletPageReqVO pageReqVO) {
        PageResult<WalletDO> pageResult = walletService.getWalletPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WalletRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户钱包 Excel")
//    @PreAuthorize("@ss.hasPermission('park:wallet:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWalletExcel(@Valid WalletPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WalletDO> list = walletService.getWalletPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "用户钱包.xls", "数据", WalletRespVO.class,
                        BeanUtils.toBean(list, WalletRespVO.class));
    }

}
