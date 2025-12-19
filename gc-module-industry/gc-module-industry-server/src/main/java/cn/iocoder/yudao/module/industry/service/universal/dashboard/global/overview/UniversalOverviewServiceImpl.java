package cn.iocoder.yudao.module.industry.service.universal.dashboard.global.overview;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.overview.vo.UniversalOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.overview.vo.UniversalOverviewRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.global.overview.UniversalOverviewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 通用大屏全域数据地图 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 UniversalOverviewService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
public class UniversalOverviewServiceImpl implements UniversalOverviewService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private UniversalOverviewMapper universalOverviewMapper;

    /**
     * 查询通用大屏全域数据地图数据
     *
     * @param universalOverviewQueryReqVO 查询条件 VO 对象
     * @return UniversalOverviewRespVO 查询结果 VO 对象
     */
    @Override
    public List<UniversalOverviewRespVO> listUniversalOverview(UniversalOverviewQueryReqVO universalOverviewQueryReqVO) {
        // 调用 Mapper 方法查询数据库并返回结果
        return universalOverviewMapper.listUniversalOverview(universalOverviewQueryReqVO);
    }
}
