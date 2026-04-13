package cn.iocoder.yudao.module.studentmgmt.dal.mysql.comparemgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.comparemgmt.CompareMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo.*;

/**
 * 评比管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CompareMgmtMapper extends BaseMapperX<CompareMgmtDO> {

    default PageResult<CompareMgmtDO> selectPage(CompareMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompareMgmtDO>()
                .likeIfPresent(CompareMgmtDO::getClassName, reqVO.getClassName())
                .eqIfPresent(CompareMgmtDO::getCycle, reqVO.getCycle())
                .eqIfPresent(CompareMgmtDO::getTotalScore, reqVO.getTotalScore())
                .eqIfPresent(CompareMgmtDO::getRank, reqVO.getRank())
                .likeIfPresent(CompareMgmtDO::getAwardName, reqVO.getAwardName())
                .betweenIfPresent(CompareMgmtDO::getAwardTime, reqVO.getAwardTime())
                .eqIfPresent(CompareMgmtDO::getScoreUser, reqVO.getScoreUser())
                .eqIfPresent(CompareMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CompareMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CompareMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(CompareMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(CompareMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CompareMgmtDO::getId));
    }

}