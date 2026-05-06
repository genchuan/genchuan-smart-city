package cn.iocoder.yudao.module.usermerchant.dal.mysql.creditmgmt.creditconfig;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarChartRespVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.creditmgmt.creditconfig.CreditConfigDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.creditconfig.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 信用配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CreditConfigMapper extends BaseMapperX<CreditConfigDO> {

    default PageResult<CreditConfigDO> selectPage(CreditConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CreditConfigDO>()
                .eqIfPresent(CreditConfigDO::getRuleDesc, reqVO.getRuleDesc())
                .eqIfPresent(CreditConfigDO::getLevelThreshold, reqVO.getLevelThreshold())
                .eqIfPresent(CreditConfigDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CreditConfigDO::getEffectTime, reqVO.getEffectTime())
                .eqIfPresent(CreditConfigDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CreditConfigDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(CreditConfigDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(CreditConfigDO::getCreator, reqVO.getCreator())
                .eqIfPresent(CreditConfigDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(CreditConfigDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(CreditConfigDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(CreditConfigDO::getId));
    }

    List<CreditConfigChartRespVO.ConfigTypeDistributionVO> selectConfigTypeDistribution(@Param("start") LocalDateTime start,
                                                                             @Param("end") LocalDateTime end);

    Long selectEffectConfigCount(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

//    BigDecimal setCreditScoreAccuracy(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}