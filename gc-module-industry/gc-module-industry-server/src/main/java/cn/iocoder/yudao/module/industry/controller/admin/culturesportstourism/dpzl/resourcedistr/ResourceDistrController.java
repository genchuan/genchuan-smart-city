// ResourceDistrController.java
package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistr;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistr.vo.ResourceDistrQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistr.vo.ResourceDistrRespVO;
import cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.resourcedistr.ResourceDistrService;

@Tag(name = "管理后台 - 文旅资源分布")
@RestController
@RequestMapping("/industry/culture-tourism-resource-distr")
@Validated
public class ResourceDistrController {

    @Resource
    private ResourceDistrService resourceDistrService;

    @GetMapping("/get")
    @Operation(summary = "获取文旅资源分布数据")
    public CommonResult<ResourceDistrRespVO> getResourceDistribution(ResourceDistrQueryReqVO queryVO) {
        return CommonResult.success(resourceDistrService.getResourceDistribution(queryVO));
    }
}