package cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.global.overview;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.overview.vo.UniversalOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.overview.vo.UniversalOverviewRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 通用大屏全域数据地图 Mapper
 *
 */
@Mapper
public interface UniversalOverviewMapper {

    /**
     * 查询通用大屏全域数据地图
     *
     * @param universalOverviewQueryReqVO 查询参数
     * @return UniversalOverviewRespVO 结果
     */
    List<UniversalOverviewRespVO> listUniversalOverview(UniversalOverviewQueryReqVO universalOverviewQueryReqVO);

}
