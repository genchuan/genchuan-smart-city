package cn.iocoder.yudao.module.ordertrade.dal.mysql.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.DebtIdentifyPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.DebtIdentifyDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * DebtIdentify Mapper
 * @author genchuan
 */
@Mapper
public interface DebtIdentifyMapper extends BaseMapperX<DebtIdentifyDO> {

    IPage<DebtIdentifyDO> selectPageJoinStation(IPage<DebtIdentifyDO> page, @Param("req") DebtIdentifyPageReqVO reqVO);

    default PageResult<DebtIdentifyDO> selectPage(DebtIdentifyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DebtIdentifyDO>()
                .likeIfPresent(DebtIdentifyDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(DebtIdentifyDO::getStatus, reqVO.getStatus())
                .likeIfPresent(DebtIdentifyDO::getStationName, reqVO.getStationName())
                .geIfPresent(DebtIdentifyDO::getIdentifyTime, reqVO.getIdentifyTimeStart())
                .leIfPresent(DebtIdentifyDO::getIdentifyTime, reqVO.getIdentifyTimeEnd())
                .orderByDesc(DebtIdentifyDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(identify_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM debt_identify WHERE deleted = 0 " +
            "<if test='startTime != null'> AND identify_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND identify_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(identify_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT status, COUNT(*) AS count FROM debt_identify WHERE deleted = 0 " +
            "<if test='startTime != null'> AND identify_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND identify_time &lt;= #{endTime}   </if>" +
            "GROUP BY status" +
            "</script>")
    List<Map<String, Object>> selectGroupByStatus(@Param("startTime") LocalDateTime startTime,
                                                  @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM debt_identify WHERE deleted = 0 AND identify_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);


    @Select("<script>" +
            "SELECT COUNT(*) FROM debt_identify WHERE deleted = 0 " +
            "<if test='status != null'> AND status = #{status} </if>" +
            "</script>")
    Long selectCountByStatus(@Param("status") String status);

    @Select("<script>" +
            "SELECT COUNT(*) FROM debt_identify WHERE deleted = 0 " +
            "<if test='startTime != null'> AND identify_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND identify_time &lt;= #{endTime}   </if>" +
            "<if test='status != null'> AND status = #{status} </if>" +
            "</script>")
    Long selectCountByStatusAndTime(@Param("status") String status,
                                     @Param("startTime") LocalDateTime startTime,
                                     @Param("endTime") LocalDateTime endTime);

}
