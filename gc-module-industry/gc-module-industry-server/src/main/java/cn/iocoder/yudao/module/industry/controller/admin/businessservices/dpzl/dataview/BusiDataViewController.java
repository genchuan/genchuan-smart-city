// DataOverviewController.java
package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.dataview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.dataview.vo.BusiDataViewRespVO;
import cn.iocoder.yudao.module.industry.service.businessservices.dpzl.dataview.BusiDataViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 营商服务数据概览")
@RestController
@RequestMapping("/industry/business-services-data-overview")
@Validated
public class BusiDataViewController {

    @Resource
    private BusiDataViewService BusiDataViewService;

    @GetMapping("/get")
    @Operation(summary = "获取数据概览")
    public CommonResult<BusiDataViewRespVO> getDataOverview() {
        BusiDataViewRespVO dataOverview = BusiDataViewService.getDataOverview();
        return success(dataOverview);
    }

}