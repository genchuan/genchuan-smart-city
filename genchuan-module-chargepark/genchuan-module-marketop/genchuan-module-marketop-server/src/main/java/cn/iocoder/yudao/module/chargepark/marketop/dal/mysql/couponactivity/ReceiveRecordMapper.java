package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo.ReceiveRecordPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ReceiveRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReceiveRecordMapper extends BaseMapperX<ReceiveRecordDO> {

    default PageResult<ReceiveRecordDO> selectPage(ReceiveRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ReceiveRecordDO>()
                .eqIfPresent(ReceiveRecordDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ReceiveRecordDO::getCouponId, reqVO.getCouponId())
                .eqIfPresent(ReceiveRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ReceiveRecordDO::getSyncStatus, reqVO.getSyncStatus())
                .betweenIfPresent(ReceiveRecordDO::getReceiveTime, reqVO.getReceiveTime())
                .orderByDesc(ReceiveRecordDO::getId));
    }

    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS count " +
            "FROM receive_record " +
            "WHERE deleted = 0 AND create_time >= #{startTime} " +
            "GROUP BY DATE(create_time) " +
            "ORDER BY DATE(create_time) ASC")
    List<Map<String, Object>> selectCountByDay(@Param("startTime") LocalDateTime startTime);

}
