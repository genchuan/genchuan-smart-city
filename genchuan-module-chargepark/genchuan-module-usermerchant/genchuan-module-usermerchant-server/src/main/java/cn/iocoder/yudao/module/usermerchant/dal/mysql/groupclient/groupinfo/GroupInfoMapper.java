package cn.iocoder.yudao.module.usermerchant.dal.mysql.groupclient.groupinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupinfo.GroupInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 集团信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface GroupInfoMapper extends BaseMapperX<GroupInfoDO> {

    default PageResult<GroupInfoDO> selectPage(GroupInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GroupInfoDO>()
                .likeIfPresent(GroupInfoDO::getName, reqVO.getName())
                .eqIfPresent(GroupInfoDO::getContact, reqVO.getContact())
                .eqIfPresent(GroupInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(GroupInfoDO::getGroupType, reqVO.getGroupType())
                .eqIfPresent(GroupInfoDO::getAddress, reqVO.getAddress())
                .betweenIfPresent(GroupInfoDO::getRegisterTime, reqVO.getRegisterTime())
                .eqIfPresent(GroupInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(GroupInfoDO::getWalletBalance, reqVO.getWalletBalance())
                .eqIfPresent(GroupInfoDO::getAuditorId, reqVO.getAuditorId())
                .eqIfPresent(GroupInfoDO::getAuditRemark, reqVO.getAuditRemark())
                .betweenIfPresent(GroupInfoDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(GroupInfoDO::getRemark, reqVO.getRemark())
                .orderByDesc(GroupInfoDO::getId));
    }

    List<GroupInfoChartRespVO.GroupGrowthTrendVO> selectGroupGrowthTrend(LocalDateTime start, LocalDateTime end, String granularity);

    Long selectTotalGroupCount(LocalDateTime start, LocalDateTime end);

    Long selectNewGroupCount(LocalDateTime start, LocalDateTime end);
}