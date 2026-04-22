package cn.iocoder.yudao.module.inspectop.dal.mysql.inspectuser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectuser.InspectUserDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo.*;

/**
 * 巡检人员 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface InspectUserMapper extends BaseMapperX<InspectUserDO> {

    default PageResult<InspectUserDO> selectPage(InspectUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectUserDO>()
                .likeIfPresent(InspectUserDO::getName, reqVO.getName())
                .eqIfPresent(InspectUserDO::getPhone, reqVO.getPhone())
                .eqIfPresent(InspectUserDO::getArea, reqVO.getArea())
                .eqIfPresent(InspectUserDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(InspectUserDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InspectUserDO::getOnlineStatus, reqVO.getOnlineStatus())
                .betweenIfPresent(InspectUserDO::getLastLoginTime, reqVO.getLastLoginTime())
                .eqIfPresent(InspectUserDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(InspectUserDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(InspectUserDO::getCreator, reqVO.getCreator())
                .eqIfPresent(InspectUserDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(InspectUserDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(InspectUserDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(InspectUserDO::getId));
    }

    /**
     * 查询人员区域分布数据
     *
     * @return 区域分布列表
     */
    List<InspectUserChartRespVO.AreaData> selectAreaData();

    /**
     * 查询卡片统计数据
     *
     * @return 卡片数据
     */
    InspectUserChartRespVO.CardData selectCardData();

}