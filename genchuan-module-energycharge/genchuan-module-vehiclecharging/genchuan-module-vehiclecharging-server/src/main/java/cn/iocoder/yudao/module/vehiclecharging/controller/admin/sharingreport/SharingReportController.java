package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport;

import cn.iocoder.yudao.framework.common.pojo.*;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.SharingReportPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.SharingReportPageRespVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingreport.SharingReportDO;
import cn.iocoder.yudao.module.vehiclecharging.service.sharingreport.SharingReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 分账报表")
@RestController
@RequestMapping("/vehiclecharging/sharing-report")
@Validated
public class SharingReportController {

    @Resource
    private SharingReportService sharingReportService;

    @GetMapping("/page")
    @Operation(summary = "获得分账报表分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-report:query')")
    public CommonResult<PageResult<SharingReportPageRespVO>> getSharingReportPage(@Valid SharingReportPageReqVO pageReqVO) {
        PageResult<SharingReportDO> pageResult = sharingReportService.getSharingReportPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SharingReportPageRespVO.class));
    }

}
