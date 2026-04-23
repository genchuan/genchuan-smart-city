package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtChartReqVO;
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

    @Select("<script>" +
            "SELECT type, COUNT(*) as count FROM prize_mgmt " +
            "<where>" +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime}</if>" +
            "<if test='endTime != null'> AND create_time &lt;= #{endTime}</if>" +
            "</where>" +
            "GROUP BY type" +
            "</script>")
    List<java.util.Map<String, Object>> selectTypeCountList(PrizeMgmtChartReqVO reqVO);

}
