package cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.overview;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.LawOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.LawOverviewRespVO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 执法全域数据概览 Mapper
 *
 * @author lxs
 */
@Mapper
public interface LawOverviewMapper {

        /**
         * 查询执法全域数据概览
         *
         * @param lawOverviewQueryReqVO 查询参数
         * @return LawOverviewRespVO 结果
         */
        LawOverviewRespVO getLawOverview(LawOverviewQueryReqVO lawOverviewQueryReqVO);

}
