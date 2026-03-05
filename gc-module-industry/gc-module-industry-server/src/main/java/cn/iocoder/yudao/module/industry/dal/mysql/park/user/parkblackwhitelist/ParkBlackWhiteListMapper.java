package cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkblackwhitelist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkblackwhitelist.vo.ParkBlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkblackwhitelist.ParkBlackWhiteListDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 黑白名单 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkBlackWhiteListMapper extends BaseMapperX<ParkBlackWhiteListDO> {

    default PageResult<ParkBlackWhiteListDO> selectPage(ParkBlackWhiteListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkBlackWhiteListDO>()
                .eqIfPresent(ParkBlackWhiteListDO::getListType, reqVO.getListType())
                .eqIfPresent(ParkBlackWhiteListDO::getTargetType, reqVO.getTargetType())
                .eqIfPresent(ParkBlackWhiteListDO::getTargetId, reqVO.getTargetId())
                .eqIfPresent(ParkBlackWhiteListDO::getTargetCarNumber, reqVO.getTargetCarNumber())
                .eqIfPresent(ParkBlackWhiteListDO::getReason, reqVO.getReason())
                .betweenIfPresent(ParkBlackWhiteListDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ParkBlackWhiteListDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ParkBlackWhiteListDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkBlackWhiteListDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkBlackWhiteListDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkBlackWhiteListDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkBlackWhiteListDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkBlackWhiteListDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(ParkBlackWhiteListDO::getRemark, reqVO.getRemark())
                .orderByDesc(ParkBlackWhiteListDO::getId));
    }

}
