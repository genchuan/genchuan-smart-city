package cn.iocoder.yudao.module.studentmgmt.dal.mysql.dormcompare;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcompare.DormCompareDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo.*;

/**
 * 宿舍评比 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DormCompareMapper extends BaseMapperX<DormCompareDO> {

    default PageResult<DormCompareDO> selectPage(DormComparePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DormCompareDO>()
                .eqIfPresent(DormCompareDO::getDormId, reqVO.getDormId())
                .eqIfPresent(DormCompareDO::getDormNum, reqVO.getDormNum())
                .eqIfPresent(DormCompareDO::getCycle, reqVO.getCycle())
                .eqIfPresent(DormCompareDO::getScore, reqVO.getScore())
                .eqIfPresent(DormCompareDO::getRankNo, reqVO.getRankNo())
                .eqIfPresent(DormCompareDO::getScoreUser, reqVO.getScoreUser())
                .betweenIfPresent(DormCompareDO::getSumTime, reqVO.getSumTime())
                .betweenIfPresent(DormCompareDO::getPushTime, reqVO.getPushTime())
                .eqIfPresent(DormCompareDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DormCompareDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DormCompareDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(DormCompareDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(DormCompareDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DormCompareDO::getId));
    }

}