package cn.iocoder.yudao.module.studentmgmt.dal.mysql.communicatemgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.communicatemgmt.CommunicateMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt.vo.*;

/**
 * 沟通管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CommunicateMgmtMapper extends BaseMapperX<CommunicateMgmtDO> {

    default PageResult<CommunicateMgmtDO> selectPage(CommunicateMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommunicateMgmtDO>()
                .eqIfPresent(CommunicateMgmtDO::getTitle, reqVO.getTitle())
                .eqIfPresent(CommunicateMgmtDO::getContent, reqVO.getContent())
                .eqIfPresent(CommunicateMgmtDO::getSendUser, reqVO.getSendUser())
                .betweenIfPresent(CommunicateMgmtDO::getSendTime, reqVO.getSendTime())
                .eqIfPresent(CommunicateMgmtDO::getReplyContent, reqVO.getReplyContent())
                .betweenIfPresent(CommunicateMgmtDO::getReplyTime, reqVO.getReplyTime())
                .eqIfPresent(CommunicateMgmtDO::getInteractRate, reqVO.getInteractRate())
                .eqIfPresent(CommunicateMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CommunicateMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CommunicateMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(CommunicateMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(CommunicateMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CommunicateMgmtDO::getId));
    }

}