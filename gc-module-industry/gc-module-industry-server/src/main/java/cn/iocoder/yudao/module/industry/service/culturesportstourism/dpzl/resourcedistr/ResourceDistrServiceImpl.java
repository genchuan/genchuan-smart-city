// ResourceDistrServiceImpl.java
package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.resourcedistr;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import javax.annotation.Resource;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistr.vo.ResourceDistrQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistr.vo.ResourceDistrRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.resourcedistr.ResourceDistrMapper;

@Service
@Validated
public class ResourceDistrServiceImpl implements ResourceDistrService {

    @Resource
    private ResourceDistrMapper resourceDistrMapper;

    @Override
    public ResourceDistrRespVO getResourceDistribution(ResourceDistrQueryReqVO queryVO) {
        ResourceDistrRespVO result = new ResourceDistrRespVO();

        // 设置各类数据
        result.setTypeDistributions(resourceDistrMapper.getTypeDistributions(queryVO));
        result.setDensityData(resourceDistrMapper.getDensityData(queryVO));
        result.setStatusRatio(resourceDistrMapper.getStatusRatio(queryVO));
        result.setTopResources(resourceDistrMapper.getTopResources(queryVO));
        result.setResourceTypes(resourceDistrMapper.getResourceTypes());

        return result;
    }
}