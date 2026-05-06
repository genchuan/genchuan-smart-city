package cn.iocoder.yudao.module.usermerchant.dal.mysql.creditmgmt.usercredit;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.usercredit.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.creditmgmt.usercredit.UserCreditDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户信用 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface UserCreditMapper extends BaseMapperX<UserCreditDO> {

    default PageResult<UserCreditDO> selectPage(UserCreditPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserCreditDO>()
                .eqIfPresent(UserCreditDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UserCreditDO::getCreditScore, reqVO.getCreditScore())
                .eqIfPresent(UserCreditDO::getCreditLevel, reqVO.getCreditLevel())
                .eqIfPresent(UserCreditDO::getRuleCode, reqVO.getRuleCode())
                .eqIfPresent(UserCreditDO::getRemark, reqVO.getRemark())
                .eqIfPresent(UserCreditDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(UserCreditDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(UserCreditDO::getCreator, reqVO.getCreator())
                .eqIfPresent(UserCreditDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(UserCreditDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(UserCreditDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(UserCreditDO::getId));
    }

    List<UserCreditChartRespVO.CreditLevelDistributionVO> selectCreditLevelDistribution(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    Integer selectAvgCreditScore(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    Integer selectLowCreditUserCount(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}