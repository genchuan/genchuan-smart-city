package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo.PointLotteryPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointLotteryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface PointLotteryMapper extends BaseMapperX<PointLotteryDO> {

    default PageResult<PointLotteryDO> selectPage(PointLotteryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PointLotteryDO>()
                .likeIfPresent(PointLotteryDO::getNo, reqVO.getNo())
                .eqIfPresent(PointLotteryDO::getUserId, reqVO.getUserId())
                .eqIfPresent(PointLotteryDO::getPrizeId, reqVO.getPrizeId())
                .eqIfPresent(PointLotteryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PointLotteryDO::getSyncStatus, reqVO.getSyncStatus())
                .betweenIfPresent(PointLotteryDO::getLotteryTime, reqVO.getLotteryTime())
                .orderByDesc(PointLotteryDO::getId));
    }

    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS count " +
            "FROM point_lottery " +
            "WHERE create_time >= #{startTime} " +
            "GROUP BY DATE(create_time) " +
            "ORDER BY DATE(create_time) ASC")
    List<Map<String, Object>> selectCountByDay(LocalDateTime startTime);

    @Select("SELECT COUNT(*) FROM point_lottery")
    Long selectTotalCount();

    @Select("SELECT COUNT(*) FROM point_lottery WHERE prize_id = 0")
    Long selectWinCount();

}
