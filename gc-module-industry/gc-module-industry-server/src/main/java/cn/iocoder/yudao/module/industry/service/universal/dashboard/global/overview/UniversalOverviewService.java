package cn.iocoder.yudao.module.industry.service.universal.dashboard.global.overview;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.overview.vo.UniversalOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.overview.vo.UniversalOverviewRespVO;

import java.util.List;

/**
 * 通用大屏全域数据地图 Service 接口
 *
 */
public interface UniversalOverviewService {

    /**
     * 查询通用大屏全域数据地图
     *
     * @param universalOverviewQueryReqVO 查询条件
     * @return 查询结果
     */
    List<UniversalOverviewRespVO> listUniversalOverview(UniversalOverviewQueryReqVO universalOverviewQueryReqVO);
}
