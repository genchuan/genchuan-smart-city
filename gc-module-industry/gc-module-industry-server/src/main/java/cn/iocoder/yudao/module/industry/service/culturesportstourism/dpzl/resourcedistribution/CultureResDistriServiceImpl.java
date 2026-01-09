// ResourceDistrServiceImpl.java
package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.resourcedistribution;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import javax.annotation.Resource;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistribution.vo.CultureResDistriQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistribution.vo.CultureResDistriRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.resourcedistr.CultureResDistriMapper;

@Service
@Validated
public class CultureResDistriServiceImpl implements CultureResDistriService {

    @Resource
    private CultureResDistriMapper resourceDistrMapper;

    @Override
    public CultureResDistriRespVO getResourceDistribution(CultureResDistriQueryReqVO queryVO) {
        CultureResDistriRespVO result = new CultureResDistriRespVO();

        // 设置各类数据
        result.setTypeDistributions(resourceDistrMapper.getTypeDistributions(queryVO));
        result.setDensityData(resourceDistrMapper.getDensityData(queryVO));
        result.setStatusRatio(resourceDistrMapper.getStatusRatio(queryVO));
        result.setTopResources(resourceDistrMapper.getTopResources(queryVO));
        result.setResourceTypes(resourceDistrMapper.getResourceTypes());

        return result;
    }
}