package cn.iocoder.yudao.module.inspectop.dal.mysql.fencemgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.fencemgmt.FenceMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.fencemgmt.vo.*;

/**
 * 电子围栏 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface FenceMgmtMapper extends BaseMapperX<FenceMgmtDO> {

    default PageResult<FenceMgmtDO> selectPage(FenceMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FenceMgmtDO>()
                .likeIfPresent(FenceMgmtDO::getName, reqVO.getName())
                .eqIfPresent(FenceMgmtDO::getArea, reqVO.getArea())
                .eqIfPresent(FenceMgmtDO::getUserId, reqVO.getUserId())
                .eqIfPresent(FenceMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(FenceMgmtDO::getAlarmCount, reqVO.getAlarmCount())
                .eqIfPresent(FenceMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(FenceMgmtDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(FenceMgmtDO::getCreator, reqVO.getCreator())
                .eqIfPresent(FenceMgmtDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(FenceMgmtDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(FenceMgmtDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(FenceMgmtDO::getId));
    }

}