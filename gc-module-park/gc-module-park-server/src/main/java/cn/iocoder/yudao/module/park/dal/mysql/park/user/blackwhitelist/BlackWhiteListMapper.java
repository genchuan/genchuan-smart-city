package cn.iocoder.yudao.module.park.dal.mysql.park.user.blackwhitelist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist.vo.BlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.blackwhitelist.BlackWhiteListDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 黑白名单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BlackWhiteListMapper extends BaseMapperX<BlackWhiteListDO> {

    default PageResult<BlackWhiteListDO> selectPage(BlackWhiteListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BlackWhiteListDO>()
                .eqIfPresent(BlackWhiteListDO::getListType, reqVO.getListType())
                .eqIfPresent(BlackWhiteListDO::getTargetType, reqVO.getTargetType())
                .eqIfPresent(BlackWhiteListDO::getTargetId, reqVO.getTargetId())
                .eqIfPresent(BlackWhiteListDO::getListReason, reqVO.getListReason())
                .betweenIfPresent(BlackWhiteListDO::getEffectTime, reqVO.getEffectTime())
                .betweenIfPresent(BlackWhiteListDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(BlackWhiteListDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BlackWhiteListDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(BlackWhiteListDO::getRemark, reqVO.getRemark())
                .eqIfPresent(BlackWhiteListDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(BlackWhiteListDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(BlackWhiteListDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(BlackWhiteListDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(BlackWhiteListDO::getId));
    }

}
