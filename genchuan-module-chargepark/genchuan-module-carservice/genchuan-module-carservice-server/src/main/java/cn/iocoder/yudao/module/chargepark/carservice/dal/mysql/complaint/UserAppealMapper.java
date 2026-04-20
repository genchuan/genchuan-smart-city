package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.UserAppealDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户申诉 Mapper
 *
 * @author carservice
 */
@Mapper
public interface UserAppealMapper extends BaseMapperX<UserAppealDO> {

    default PageResult<UserAppealDO> selectPage(UserAppealPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserAppealDO>()
                .eqIfPresent(UserAppealDO::getUserId, reqVO.getUserId())
                .eqIfPresent(UserAppealDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(UserAppealDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(UserAppealDO::getSubmitTime, reqVO.getSubmitTime())
                .orderByDesc(UserAppealDO::getId));
    }

}
