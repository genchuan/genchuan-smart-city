package cn.iocoder.yudao.module.ordertrade.dal.mysql.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.PayAppPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayAppDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PayAppMapper extends BaseMapperX<PayAppDO> {

    default PageResult<PayAppDO> selectPage(PayAppPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PayAppDO>()
                .likeIfPresent(PayAppDO::getName, reqVO.getName())
                .eqIfPresent(PayAppDO::getStatus, reqVO.getStatus())
                .orderByDesc(PayAppDO::getId));
    }

    @Select("SELECT COUNT(*) FROM pay_app WHERE status = 1")
    Long selectEnabledCount();

    @Select("SELECT COUNT(*) FROM pay_app")
    Long selectTotalCount();
}
