package cn.iocoder.yudao.module.park.controller.admin.park.user.address;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.address.vo.AddressPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.address.vo.AddressRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.address.vo.AddressSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.address.AddressDO;
import cn.iocoder.yudao.module.park.service.park.user.address.AddressService;
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


@Tag(name = "管理后台 - 地址")
@RestController
@RequestMapping("/park/address")
@Validated
public class AddressController {

    @Resource
    private AddressService addressService;

    @PostMapping("/create")
    @Operation(summary = "创建地址")
    @PreAuthorize("@ss.hasPermission('park:address:create')")
    public CommonResult<Long> createAddress(@Valid @RequestBody AddressSaveReqVO createReqVO) {
        return success(addressService.createAddress(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新地址")
    @PreAuthorize("@ss.hasPermission('park:address:update')")
    public CommonResult<Boolean> updateAddress(@Valid @RequestBody AddressSaveReqVO updateReqVO) {
        addressService.updateAddress(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除地址")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:address:delete')")
    public CommonResult<Boolean> deleteAddress(@RequestParam("id") Long id) {
        addressService.deleteAddress(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得地址")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:address:query')")
    public CommonResult<AddressRespVO> getAddress(@RequestParam("id") Long id) {
        AddressDO address = addressService.getAddress(id);
        return success(BeanUtils.toBean(address, AddressRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得地址分页")
    @PreAuthorize("@ss.hasPermission('park:address:query')")
    public CommonResult<PageResult<AddressRespVO>> getAddressPage(@Valid AddressPageReqVO pageReqVO) {
        PageResult<AddressDO> pageResult = addressService.getAddressPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AddressRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出地址 Excel")
    @PreAuthorize("@ss.hasPermission('park:address:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAddressExcel(@Valid AddressPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AddressDO> list = addressService.getAddressPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "地址.xls", "数据", AddressRespVO.class,
                        BeanUtils.toBean(list, AddressRespVO.class));
    }

}
