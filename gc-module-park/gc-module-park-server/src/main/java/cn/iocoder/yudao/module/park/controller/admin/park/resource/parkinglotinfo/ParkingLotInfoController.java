package cn.iocoder.yudao.module.park.controller.admin.park.resource.parkinglotinfo;

import cn.iocoder.yudao.module.park.controller.admin.park.resource.parkinglotinfo.vo.ParkingLotInfoPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.parkinglotinfo.vo.ParkingLotInfoRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.parkinglotinfo.vo.ParkingLotInfoSaveReqVO;
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

import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.parkinglotinfo.ParkingLotInfoDO;
import cn.iocoder.yudao.module.park.service.park.resource.parkinglotinfo.ParkingLotInfoService;

@Tag(name = "管理后台 - 停车场信息管理")
@RestController
@RequestMapping("/park/ing-lot-info")
@Validated
public class ParkingLotInfoController {

    @Resource
    private ParkingLotInfoService ingLotInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建停车场信息管理")
//    @PreAuthorize("@ss.hasPermission('park:ing-lot-info:create')")
    public CommonResult<Long> createingLotInfo(@Valid @RequestBody ParkingLotInfoSaveReqVO createReqVO) {
        return success(ingLotInfoService.createingLotInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新停车场信息管理")
//    @PreAuthorize("@ss.hasPermission('park:ing-lot-info:update')")
    public CommonResult<Boolean> updateingLotInfo(@Valid @RequestBody ParkingLotInfoSaveReqVO updateReqVO) {
        ingLotInfoService.updateingLotInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除停车场信息管理")
    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('park:ing-lot-info:delete')")
    public CommonResult<Boolean> deleteingLotInfo(@RequestParam("id") Long id) {
        ingLotInfoService.deleteingLotInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得停车场信息管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('park:ing-lot-info:query')")
    public CommonResult<ParkingLotInfoRespVO> getingLotInfo(@RequestParam("id") Long id) {
        ParkingLotInfoDO ingLotInfo = ingLotInfoService.getingLotInfo(id);
        return success(BeanUtils.toBean(ingLotInfo, ParkingLotInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得停车场信息管理分页")
//    @PreAuthorize("@ss.hasPermission('park:ing-lot-info:query')")
    public CommonResult<PageResult<ParkingLotInfoRespVO>> getingLotInfoPage(@Valid ParkingLotInfoPageReqVO pageReqVO) {
        PageResult<ParkingLotInfoDO> pageResult = ingLotInfoService.getingLotInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkingLotInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出停车场信息管理 Excel")
//    @PreAuthorize("@ss.hasPermission('park:ing-lot-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportingLotInfoExcel(@Valid ParkingLotInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkingLotInfoDO> list = ingLotInfoService.getingLotInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "停车场信息管理.xls", "数据", ParkingLotInfoRespVO.class,
                        BeanUtils.toBean(list, ParkingLotInfoRespVO.class));
    }

}