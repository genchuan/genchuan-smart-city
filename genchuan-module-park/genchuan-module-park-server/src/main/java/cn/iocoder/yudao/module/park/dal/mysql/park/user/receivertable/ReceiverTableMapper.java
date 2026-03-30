package cn.iocoder.yudao.module.park.dal.mysql.park.user.receivertable;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable.vo.ReceiverTablePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.receivertable.ReceiverTableDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 接收方 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ReceiverTableMapper extends BaseMapperX<ReceiverTableDO> {

    default PageResult<ReceiverTableDO> selectPage(ReceiverTablePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ReceiverTableDO>()
                .likeIfPresent(ReceiverTableDO::getReceiverName, reqVO.getReceiverName())
                .eqIfPresent(ReceiverTableDO::getReceiverType, reqVO.getReceiverType())
                .eqIfPresent(ReceiverTableDO::getRelatedId, reqVO.getRelatedId())
                .likeIfPresent(ReceiverTableDO::getAccountName, reqVO.getAccountName())
                .likeIfPresent(ReceiverTableDO::getBankName, reqVO.getBankName())
                .eqIfPresent(ReceiverTableDO::getBankAccount, reqVO.getBankAccount())
                .eqIfPresent(ReceiverTableDO::getContactPhone, reqVO.getContactPhone())
                .betweenIfPresent(ReceiverTableDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ReceiverTableDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ReceiverTableDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ReceiverTableDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ReceiverTableDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ReceiverTableDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ReceiverTableDO::getId));
    }

}
