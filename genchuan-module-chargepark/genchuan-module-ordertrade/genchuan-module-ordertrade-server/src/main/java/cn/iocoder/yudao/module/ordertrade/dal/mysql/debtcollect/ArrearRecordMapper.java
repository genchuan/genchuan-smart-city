package cn.iocoder.yudao.module.ordertrade.dal.mysql.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.ArrearRecordPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.ArrearRecordDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * ArrearRecord Mapper
 * @author genchuan
 */
@Mapper
public interface ArrearRecordMapper extends BaseMapperX<ArrearRecordDO> {

    IPage<ArrearRecordDO> selectPageJoinStation(IPage<ArrearRecordDO> page, @Param("req") ArrearRecordPageReqVO reqVO);

    default PageResult<ArrearRecordDO> selectPage(ArrearRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ArrearRecordDO>()
                .likeIfPresent(ArrearRecordDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(ArrearRecordDO::getStatus, reqVO.getStatus())
                .likeIfPresent(ArrearRecordDO::getStationName, reqVO.getStationName())
                .geIfPresent(ArrearRecordDO::getCreateTime, reqVO.getCreateTimeStart())
                .leIfPresent(ArrearRecordDO::getCreateTime, reqVO.getCreateTimeEnd())
                .orderByDesc(ArrearRecordDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM arrear_record WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT status, COUNT(*) AS count FROM arrear_record WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY status" +
            "</script>")
    List<Map<String, Object>> selectGroupByStatus(@Param("startTime") LocalDateTime startTime,
                                                  @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM arrear_record WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);


    @Select("<script>" +
            "SELECT COUNT(*) FROM arrear_record WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "<if test='status != null'> AND status = #{status} </if>" +
            "</script>")
    Long selectCountByStatus(@Param("status") String status,
                             @Param("startTime") LocalDateTime startTime,
                             @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT IFNULL(SUM(arrear_amount), 0) FROM arrear_record WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "</script>")
    BigDecimal selectTotalArrearAmount(@Param("startTime") LocalDateTime startTime,
                                       @Param("endTime") LocalDateTime endTime);

}
