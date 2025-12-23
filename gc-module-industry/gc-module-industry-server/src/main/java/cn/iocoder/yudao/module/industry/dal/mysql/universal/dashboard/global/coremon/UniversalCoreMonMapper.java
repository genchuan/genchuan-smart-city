package cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.global.coremon;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremon.vo.UniversalCoreMonQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremon.vo.UniversalCoreMonRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 通用大屏核心要素运行监测 Mapper
 *
 */
@Mapper
public interface UniversalCoreMonMapper {

    /**
     * 查询通用大屏核心要素运行监测
     *
     * @param universalCoreMonQueryReqVO 查询参数
     * @return UniversalCoreMonRespVO 结果
     */
    List<UniversalCoreMonRespVO> listUniversalCoreMon(UniversalCoreMonQueryReqVO universalCoreMonQueryReqVO);

}
