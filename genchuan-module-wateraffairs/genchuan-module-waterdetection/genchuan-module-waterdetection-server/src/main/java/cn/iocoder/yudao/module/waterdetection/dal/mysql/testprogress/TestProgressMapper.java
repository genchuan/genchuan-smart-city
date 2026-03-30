package cn.iocoder.yudao.module.waterdetection.dal.mysql.testprogress;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testprogress.TestProgressDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testprogress.vo.*;

/**
 * 检测进度跟踪 Mapper
 *
 * @author zcq
 */
@Mapper
public interface TestProgressMapper extends BaseMapperX<TestProgressDO> {

    default PageResult<TestProgressDO> selectPage(TestProgressPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TestProgressDO>()
                .eqIfPresent(TestProgressDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(TestProgressDO::getProgressPercent, reqVO.getProgressPercent())
                .eqIfPresent(TestProgressDO::getCompletedIndicators, reqVO.getCompletedIndicators())
                .eqIfPresent(TestProgressDO::getPendingIndicators, reqVO.getPendingIndicators())
                .eqIfPresent(TestProgressDO::getEstimatedCompletion, reqVO.getEstimatedCompletion())
                .eqIfPresent(TestProgressDO::getDelayReason, reqVO.getDelayReason())
                .betweenIfPresent(TestProgressDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TestProgressDO::getId));
    }

}