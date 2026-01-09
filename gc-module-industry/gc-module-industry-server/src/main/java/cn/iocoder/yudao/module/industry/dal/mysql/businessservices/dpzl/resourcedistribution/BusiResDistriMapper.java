package cn.iocoder.yudao.module.industry.dal.mysql.businessservices.dpzl.resourcedistribution;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.BusiResDistriRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.BusiResDistriQueryReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BusiResDistriMapper {

    /* ---------- 4 组数据一次性查回 ---------- */
    List<BusiResDistriRespVO.RegionEntCount> selectRegionEntCount(@Param("req") BusiResDistriQueryReqVO req);

    List<BusiResDistriRespVO.IndustryEntCount> selectIndustryEntCount(@Param("req") BusiResDistriQueryReqVO req);

    List<BusiResDistriRespVO.KeyEnt> selectKeyEntList(@Param("req") BusiResDistriQueryReqVO req);

    List<BusiResDistriRespVO.ScaleEntCount> selectScaleEntCount(@Param("req") BusiResDistriQueryReqVO req);
}