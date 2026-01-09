package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.BusiResDistriQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.BusiResDistriRespVO;
import cn.iocoder.yudao.module.industry.service.businessservices.dpzl.resourcedistribution.BusiResDistriService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/industry/business-services-resource-distribution")
@Tag(name = "管理后台 - 营商服务资源分布")
@Validated
public class BusiResDistriController {

    @Resource
    private BusiResDistriService busiResDistriService;

    @GetMapping("/get")
    @Operation(summary = "获取资源分布数据")
    public BusiResDistriRespVO list(@Valid BusiResDistriQueryReqVO req) {
        return busiResDistriService.getDistribution(req);
    }
}