package cn.iocoder.yudao.module.industry.dal.mysql.health.dashboard.global.overview;


import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.overview.vo.HealthOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.overview.vo.HealthOverviewRespVO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 卫健全域数据概览 Mapper
 *
 * @author lxs
 */
@Mapper
public interface HealthOverviewMapper {

        /**
         * 查询卫健全域数据概览
         *
         * @param healthOverviewQueryReqVO 查询参数
         * @return HealthOverviewRespVO 结果
         */
        HealthOverviewRespVO getHealthOverview(HealthOverviewQueryReqVO healthOverviewQueryReqVO);

}
