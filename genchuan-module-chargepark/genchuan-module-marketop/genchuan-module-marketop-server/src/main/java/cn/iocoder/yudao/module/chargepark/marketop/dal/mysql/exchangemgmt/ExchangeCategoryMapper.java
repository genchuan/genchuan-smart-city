package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeCategoryDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExchangeCategoryMapper extends BaseMapperX<ExchangeCategoryDO> {

    default PageResult<ExchangeCategoryDO> selectPage(ExchangeCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExchangeCategoryDO>()
                .likeIfPresent(ExchangeCategoryDO::getName, reqVO.getName())
                .eqIfPresent(ExchangeCategoryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ExchangeCategoryDO::getScope, reqVO.getScope())
                .orderByAsc(ExchangeCategoryDO::getSort));
    }

}
