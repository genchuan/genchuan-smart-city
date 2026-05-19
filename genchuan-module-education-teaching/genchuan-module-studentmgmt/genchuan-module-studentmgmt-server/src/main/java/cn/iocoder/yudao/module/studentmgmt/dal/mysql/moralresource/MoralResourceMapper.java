package cn.iocoder.yudao.module.studentmgmt.dal.mysql.moralresource;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo.MoralResourcePageReqVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralresource.MoralResourceDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 德育资源 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MoralResourceMapper extends BaseMapperX<MoralResourceDO> {

    default PageResult<MoralResourceDO> selectPage(MoralResourcePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MoralResourceDO>()
                .likeIfPresent(MoralResourceDO::getResourceName, reqVO.getResourceName())
                .eqIfPresent(MoralResourceDO::getResourceType, reqVO.getResourceType())
                .eqIfPresent(MoralResourceDO::getResourceUrl, reqVO.getResourceUrl())
                .eqIfPresent(MoralResourceDO::getLearnNum, reqVO.getLearnNum())
                .eqIfPresent(MoralResourceDO::getLearnRate, reqVO.getLearnRate())
                .betweenIfPresent(MoralResourceDO::getPublishTime, reqVO.getPublishTime())
                .betweenIfPresent(MoralResourceDO::getOffTime, reqVO.getOffTime())
                .eqIfPresent(MoralResourceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MoralResourceDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MoralResourceDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(MoralResourceDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(MoralResourceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MoralResourceDO::getId));
    }

    List<JSONObject> selectTypeCount(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectStatusCount(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectLearnTrend(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectRateTrend(LocalDateTime startTime, LocalDateTime endTime);

    Long selectTypeCountByType(LocalDateTime startTime, LocalDateTime endTime, String resourceType);

    BigDecimal selectLearnCountByType(LocalDateTime startTime, LocalDateTime endTime, String resourceType);
}