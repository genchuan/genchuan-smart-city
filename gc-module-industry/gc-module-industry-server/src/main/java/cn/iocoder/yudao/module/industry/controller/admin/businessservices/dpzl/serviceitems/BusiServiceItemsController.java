// ServiceItemsController.java
package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.serviceitems;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.serviceitems.vo.BusiServiceItemsRespVO;
import cn.iocoder.yudao.module.industry.service.businessservices.dpzl.serviceitems.BusiServiceItemsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 营商服务服务事项统计")
@RestController
@RequestMapping("/industry/business-services-service-items")
@Validated
public class BusiServiceItemsController {

    @Resource
    private BusiServiceItemsService busiServiceItemsService;

    @GetMapping("/get")
    @Operation(summary = "获取服务事项统计概览")
    public CommonResult<BusiServiceItemsRespVO> getServiceItemsOverview() {
        BusiServiceItemsRespVO overview = busiServiceItemsService.getServiceItemsOverview();
        return success(overview);
    }

}