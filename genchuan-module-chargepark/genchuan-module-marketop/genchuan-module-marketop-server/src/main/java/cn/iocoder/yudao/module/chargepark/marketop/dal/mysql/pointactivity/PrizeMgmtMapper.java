package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PrizeMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PrizeMgmtMapper extends BaseMapperX<PrizeMgmtDO> {

    default PageResult<PrizeMgmtDO> selectPage(PrizeMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PrizeMgmtDO>()
                .likeIfPresent(PrizeMgmtDO::getName, reqVO.getName())
                .eqIfPresent(PrizeMgmtDO::getType, reqVO.getType())
                .eqIfPresent(PrizeMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PrizeMgmtDO::getActivityId, reqVO.getActivityId())
                .orderByDesc(PrizeMgmtDO::getId));
    }

    @Select("SELECT type, COUNT(*) as count FROM prize_mgmt GROUP BY type")
    List<java.util.Map<String, Object>> selectTypeCountList();

    @Select("SELECT COUNT(*) FROM prize_mgmt")
    int selectPrizeCount();

    @Select("SELECT IFNULL(SUM(send_count), 0) FROM prize_mgmt")
    int selectTotalSendCount();

}
