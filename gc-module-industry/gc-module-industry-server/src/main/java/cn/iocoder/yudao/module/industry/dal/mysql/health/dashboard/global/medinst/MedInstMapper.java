package cn.iocoder.yudao.module.industry.dal.mysql.health.dashboard.global.medinst;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.MedInstQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.MedInstRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.TreatTrendVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MedInstMapper {

        /** 分类数量卡片 */
        MedInstRespVO.InstSortCountVO selectInstSortCount(MedInstQueryReqVO reqVO);

        /** 地图机构列表 */
        List<MedInstRespVO.InstDetailVO> selectInstList(MedInstQueryReqVO reqVO);

        /** 热门TOP5 */
        List<MedInstRespVO.InstDetailVO> selectInstTop5(MedInstQueryReqVO reqVO);

        List<TreatTrendVO> selectInstTreatTrend(@Param("orgId") Long orgId);

}
