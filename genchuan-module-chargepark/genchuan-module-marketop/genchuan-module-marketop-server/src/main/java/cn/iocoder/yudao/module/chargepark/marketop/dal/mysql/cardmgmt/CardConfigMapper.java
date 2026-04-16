package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardConfigMapper extends BaseMapperX<CardConfigDO> {

    default PageResult<CardConfigDO> selectPage(CardConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CardConfigDO>()
                .likeIfPresent(CardConfigDO::getName, reqVO.getName())
                .eqIfPresent(CardConfigDO::getType, reqVO.getType())
                .eqIfPresent(CardConfigDO::getScope, reqVO.getScope())
                .eqIfPresent(CardConfigDO::getStatus, reqVO.getStatus())
                .orderByDesc(CardConfigDO::getId));
    }

}
