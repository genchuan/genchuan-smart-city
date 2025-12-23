package cn.iocoder.yudao.module.industry.service.universal.dashboard.global.coremon;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremon.vo.UniversalCoreMonQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremon.vo.UniversalCoreMonRespVO;

import java.util.List;

/**
 * 通用大屏核心要素运行监测 Service 接口
 *
 */
public interface UniversalCoreMonService {

    /**
     * 查询通用大屏核心要素运行监测
     *
     * @param universalCoreMonQueryReqVO 查询条件
     * @return 查询结果
     */
    List<UniversalCoreMonRespVO> listUniversalCoreMon(UniversalCoreMonQueryReqVO universalCoreMonQueryReqVO);
}
