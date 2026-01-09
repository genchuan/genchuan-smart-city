package cn.iocoder.yudao.module.industry.dal.mysql.park.pay.statparkpay;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPayGenReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPayPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.statparkpay.StatParkPayDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 缴费统计 Mapper
 *
 * @author lxs
 */
@Mapper
public interface StatParkPayMapper extends BaseMapperX<StatParkPayDO> {

    default PageResult<StatParkPayDO> selectPage(StatParkPayPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StatParkPayDO>()
                .eqIfPresent(StatParkPayDO::getStatCode, reqVO.getStatCode())
                .eqIfPresent(StatParkPayDO::getStatCycle, reqVO.getStatCycle())
                .betweenIfPresent(StatParkPayDO::getStatTime, reqVO.getStatTime())
                .likeIfPresent(StatParkPayDO::getRegionName, reqVO.getRegionName())
                .likeIfPresent(StatParkPayDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(StatParkPayDO::getRegionFullCode, reqVO.getRegionFullCode())
                .eqIfPresent(StatParkPayDO::getTotalOrderCount, reqVO.getTotalOrderCount())
                .eqIfPresent(StatParkPayDO::getTotalPayAmount, reqVO.getTotalPayAmount())
                .eqIfPresent(StatParkPayDO::getPaySuccessRate, reqVO.getPaySuccessRate())
                .eqIfPresent(StatParkPayDO::getWechatRatio, reqVO.getWechatRatio())
                .eqIfPresent(StatParkPayDO::getAlipayRatio, reqVO.getAlipayRatio())
                .eqIfPresent(StatParkPayDO::getCardRatio, reqVO.getCardRatio())
                .eqIfPresent(StatParkPayDO::getRegionPayRatio, reqVO.getRegionPayRatio())
                .eqIfPresent(StatParkPayDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(StatParkPayDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(StatParkPayDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(StatParkPayDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(StatParkPayDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StatParkPayDO::getId));
    }

    StatParkPayDO stat(StatParkPayGenReqVO createReqVO);

    String getRegionPayRatio(StatParkPayGenReqVO createReqVO);

    String selectAreaByFullCode(String regionFullCode);
}
