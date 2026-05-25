package cn.iocoder.yudao.module.ordertrade.dal.mysql.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.CollectConfigPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.CollectConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * CollectConfig Mapper
 * @author genchuan
 */
@Mapper
public interface CollectConfigMapper extends BaseMapperX<CollectConfigDO> {

    default PageResult<CollectConfigDO> selectPage(CollectConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CollectConfigDO>()
                .eqIfPresent(CollectConfigDO::getCollectMethod, reqVO.getCollectMethod())
                .eqIfPresent(CollectConfigDO::getStatus, reqVO.getStatus())
                .orderByDesc(CollectConfigDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM collect_config WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT status, COUNT(*) AS count FROM collect_config WHERE deleted = 0 GROUP BY status")
    List<Map<String, Object>> selectGroupByStatus();

    @Select("SELECT COUNT(*) FROM collect_config WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);


    @Select("<script>" +
            "SELECT COUNT(*) FROM collect_config WHERE deleted = 0 " +
            "<if test='status != null'> AND status = #{status} </if>" +
            "</script>")
    Long selectCountByStatus(@Param("status") String status);

    @Select("SELECT collect_method AS method, COUNT(*) AS count FROM collect_config WHERE deleted = 0 GROUP BY collect_method")
    List<Map<String, Object>> selectGroupByCollectMethod();

    default CollectConfigDO selectByConfigNo(String configNo, Long excludeId) {
        return selectOne(new LambdaQueryWrapperX<CollectConfigDO>()
                .eq(CollectConfigDO::getConfigNo, configNo)
                .neIfPresent(CollectConfigDO::getId, excludeId));
    }

}
