package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkblackwhitelist;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkblackwhitelist.vo.ParkBlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkblackwhitelist.vo.ParkBlackWhiteListRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkblackwhitelist.vo.ParkBlackWhiteListSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkblackwhitelist.ParkBlackWhiteListDO;
import cn.iocoder.yudao.module.industry.service.park.user.parkblackwhitelist.ParkBlackWhiteListService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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


@Tag(name = "漳州停车管理-用户商户域 - 黑白名单")
@RestController
@RequestMapping("/industry/park-black-white-list")
@Validated
public class ParkBlackWhiteListController {

    @Resource
    private ParkBlackWhiteListService parkBlackWhiteListService;

    @PostMapping("/create")
    @Operation(summary = "创建黑白名单")
    @PreAuthorize("@ss.hasPermission('industry:park-black-white-list:create')")
    public CommonResult<Long> createParkBlackWhiteList(@Valid @RequestBody ParkBlackWhiteListSaveReqVO createReqVO) {
        return success(parkBlackWhiteListService.createParkBlackWhiteList(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新黑白名单")
    @PreAuthorize("@ss.hasPermission('industry:park-black-white-list:update')")
    public CommonResult<Boolean> updateParkBlackWhiteList(@Valid @RequestBody ParkBlackWhiteListSaveReqVO updateReqVO) {
        parkBlackWhiteListService.updateParkBlackWhiteList(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除黑白名单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-black-white-list:delete')")
    public CommonResult<Boolean> deleteParkBlackWhiteList(@RequestParam("id") Long id) {
        parkBlackWhiteListService.deleteParkBlackWhiteList(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得黑白名单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-black-white-list:query')")
    public CommonResult<ParkBlackWhiteListRespVO> getParkBlackWhiteList(@RequestParam("id") Long id) {
        ParkBlackWhiteListDO parkBlackWhiteList = parkBlackWhiteListService.getParkBlackWhiteList(id);
        return success(BeanUtils.toBean(parkBlackWhiteList, ParkBlackWhiteListRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得黑白名单分页")
    @PreAuthorize("@ss.hasPermission('industry:park-black-white-list:query')")
    public CommonResult<PageResult<ParkBlackWhiteListRespVO>> getParkBlackWhiteListPage(@Valid ParkBlackWhiteListPageReqVO pageReqVO) {
        PageResult<ParkBlackWhiteListDO> pageResult = parkBlackWhiteListService.getParkBlackWhiteListPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkBlackWhiteListRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出黑白名单 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-black-white-list:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkBlackWhiteListExcel(@Valid ParkBlackWhiteListPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkBlackWhiteListDO> list = parkBlackWhiteListService.getParkBlackWhiteListPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "黑白名单.xls", "数据", ParkBlackWhiteListRespVO.class,
                        BeanUtils.toBean(list, ParkBlackWhiteListRespVO.class));
    }

}
