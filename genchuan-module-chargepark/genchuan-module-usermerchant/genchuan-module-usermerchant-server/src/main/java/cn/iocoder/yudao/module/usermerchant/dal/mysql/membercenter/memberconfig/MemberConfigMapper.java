package cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.MemberConfigChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.MemberConfigPageReqVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 积分设置 Mapper
 *
 * @author QingX
 */
@Mapper
@DS("member")
public interface MemberConfigMapper extends BaseMapperX<MemberConfigDO> {

    default PageResult<MemberConfigDO> selectPage(MemberConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberConfigDO>()
                .eqIfPresent(MemberConfigDO::getPointTradeDeductEnable, reqVO.getPointTradeDeductEnable())
                .eqIfPresent(MemberConfigDO::getPointTradeDeductUnitPrice, reqVO.getPointTradeDeductUnitPrice())
                .eqIfPresent(MemberConfigDO::getPointTradeDeductMaxPrice, reqVO.getPointTradeDeductMaxPrice())
                .eqIfPresent(MemberConfigDO::getPointTradeGivePoint, reqVO.getPointTradeGivePoint())
                .eqIfPresent(MemberConfigDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(MemberConfigDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MemberConfigDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(MemberConfigDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(MemberConfigDO::getId));
    }

    List<MemberConfigChartRespVO.ConfigTypeDistributionVO> selectConfigTypeDistribution(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    Long selectEffectConfigCount(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    BigDecimal selectMemberMatchRate(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

}
