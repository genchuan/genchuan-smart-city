package cn.iocoder.yudao.module.evaluate.dal.mysql.objectscore;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objectscore.ObjectScoreDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo.*;

/**
 * 公司得分 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ObjectScoreMapper extends BaseMapperX<ObjectScoreDO> {

    default PageResult<ObjectScoreDO> selectPage(ObjectScorePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ObjectScoreDO>()
                .eqIfPresent(ObjectScoreDO::getObjectId, reqVO.getObjectId())
                .eqIfPresent(ObjectScoreDO::getSystemId, reqVO.getSystemId())
                .eqIfPresent(ObjectScoreDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ObjectScoreDO::getScore, reqVO.getScore())
                .eqIfPresent(ObjectScoreDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ObjectScoreDO::getDetails, reqVO.getDetails())
                .eqIfPresent(ObjectScoreDO::getCreator, reqVO.getCreator())
                .eqIfPresent(ObjectScoreDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(ObjectScoreDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ObjectScoreDO::getUpdateTime, reqVO.getUpdateTime())
                .eqIfPresent(ObjectScoreDO::getChangeLog, reqVO.getChangeLog())
                .orderByDesc(ObjectScoreDO::getId));
    }

}