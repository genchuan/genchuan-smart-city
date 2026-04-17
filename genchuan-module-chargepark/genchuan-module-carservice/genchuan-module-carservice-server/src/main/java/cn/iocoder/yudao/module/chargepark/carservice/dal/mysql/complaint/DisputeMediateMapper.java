package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediatePageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.DisputeMediateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 纠纷调解 Mapper
 *
 * @author carservice
 */
@Mapper
public interface DisputeMediateMapper extends BaseMapperX<DisputeMediateDO> {

    default PageResult<DisputeMediateDO> selectPage(DisputeMediatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DisputeMediateDO>()
                .eqIfPresent(DisputeMediateDO::getUserId, reqVO.getUserId())
                .eqIfPresent(DisputeMediateDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(DisputeMediateDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DisputeMediateDO::getSubmitTime, reqVO.getSubmitTime())
                .orderByDesc(DisputeMediateDO::getId));
    }

}
