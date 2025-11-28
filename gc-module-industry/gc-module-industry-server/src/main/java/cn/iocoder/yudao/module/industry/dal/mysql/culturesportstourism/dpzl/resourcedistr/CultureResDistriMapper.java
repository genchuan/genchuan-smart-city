// ResourceDistrMapper.java
package cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.resourcedistr;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistribution.vo.CultureResDistriQueryReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistribution.vo.CultureResDistriRespVO;
import java.util.List;

@Mapper
public interface CultureResDistriMapper {

    /**
     * 获取资源类型分布
     */
    List<CultureResDistriRespVO.ResourceTypeDistribution> getTypeDistributions(@Param("query") CultureResDistriQueryReqVO query);

    /**
     * 获取资源密度热力图数据
     */
    List<CultureResDistriRespVO.ResourceDensity> getDensityData(@Param("query") CultureResDistriQueryReqVO query);

    /**
     * 获取资源状态占比
     */
    CultureResDistriRespVO.ResourceStatusRatio getStatusRatio(@Param("query") CultureResDistriQueryReqVO query);

    /**
     * 获取重点资源TOP5
     */
    List<CultureResDistriRespVO.TopResource> getTopResources(@Param("query") CultureResDistriQueryReqVO query);

    /**
     * 获取资源类型列表(用于筛选下拉框)
     */
    List<CultureResDistriRespVO.ResourceType> getResourceTypes();
}