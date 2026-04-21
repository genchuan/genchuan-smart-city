package cn.iocoder.yudao.module.ordertrade.dal.mysql.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.SplitRatePageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SplitRateDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SplitRateMapper extends BaseMapperX<SplitRateDO> {

    default PageResult<SplitRateDO> selectPage(SplitRatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SplitRateDO>()
                .eqIfPresent(SplitRateDO::getPartnerId, reqVO.getPartnerId())
                .eqIfPresent(SplitRateDO::getSplitMode, reqVO.getSplitMode())
                .eqIfPresent(SplitRateDO::getStatus, reqVO.getStatus())
                .orderByDesc(SplitRateDO::getId));
    }

    @Select("SELECT split_mode, COUNT(*) AS count FROM split_rate WHERE deleted = 0 GROUP BY split_mode")
    List<Map<String, Object>> selectGroupBySplitMode();

    @Select("SELECT COUNT(*) FROM split_rate WHERE deleted = 0 AND status = 'enabled'")
    Long selectEnabledCount();
}
