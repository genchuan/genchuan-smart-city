// ResourceDistrController.java
package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistribution;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistribution.vo.CultureResDistriQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistribution.vo.CultureResDistriRespVO;
import cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.resourcedistribution.CultureResDistriService;

@Tag(name = "管理后台 - 文旅资源分布")
@RestController
@RequestMapping("/industry/culture-tourism-resource-distr")
@Validated
public class CultureResDistriController {

    @Resource
    private CultureResDistriService CultureResDistriService;

    @GetMapping("/get")
    @Operation(summary = "获取文旅资源分布数据")
    public CommonResult<CultureResDistriRespVO> getResourceDistribution(CultureResDistriQueryReqVO queryVO) {
        return CommonResult.success(CultureResDistriService.getResourceDistribution(queryVO));
    }
}