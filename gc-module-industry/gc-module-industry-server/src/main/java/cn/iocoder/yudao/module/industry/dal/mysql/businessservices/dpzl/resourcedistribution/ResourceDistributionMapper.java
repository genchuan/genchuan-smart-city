package cn.iocoder.yudao.module.industry.dal.mysql.businessservices.dpzl.resourcedistribution;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.ResourceDistributionRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.ResourceDistributionQueryReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceDistributionMapper {

    /* ---------- 4 组数据一次性查回 ---------- */
    List<ResourceDistributionRespVO.RegionEntCount> selectRegionEntCount(@Param("req") ResourceDistributionQueryReqVO req);

    List<ResourceDistributionRespVO.IndustryEntCount> selectIndustryEntCount(@Param("req") ResourceDistributionQueryReqVO req);

    List<ResourceDistributionRespVO.KeyEnt> selectKeyEntList(@Param("req") ResourceDistributionQueryReqVO req);

    List<ResourceDistributionRespVO.ScaleEntCount> selectScaleEntCount(@Param("req") ResourceDistributionQueryReqVO req);
}