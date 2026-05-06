package cn.iocoder.yudao.module.studentmgmt.dal.mysql.newpush;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.newpush.NewPushDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush.vo.*;

import java.util.List;

/**
 * 迎新推送 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface NewPushMapper extends BaseMapperX<NewPushDO> {

    default PageResult<NewPushDO> selectPage(NewPushPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NewPushDO>()
                .likeIfPresent(NewPushDO::getTaskName, reqVO.getTaskName())
                .eqIfPresent(NewPushDO::getPushContent, reqVO.getPushContent())
                .eqIfPresent(NewPushDO::getPushNum, reqVO.getPushNum())
                .betweenIfPresent(NewPushDO::getPushTime, reqVO.getPushTime())
                .eqIfPresent(NewPushDO::getFinishRate, reqVO.getFinishRate())
                .eqIfPresent(NewPushDO::getStatus, reqVO.getStatus())
                .eqIfPresent(NewPushDO::getRemark, reqVO.getRemark())
                .eqIfPresent(NewPushDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(NewPushDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(NewPushDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(NewPushDO::getId));
    }

    NewPushChartRespVO selectTotalCount(Integer year, String status);

    List<JSONObject> selectDateCountList(Integer year);

    NewPushIndexRespVO selectPushCount(Integer year, String status);
}