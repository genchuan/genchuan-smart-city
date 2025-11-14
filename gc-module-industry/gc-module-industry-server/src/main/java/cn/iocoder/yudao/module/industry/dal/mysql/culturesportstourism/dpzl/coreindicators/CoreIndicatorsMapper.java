package cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.coreindicators;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CoreIndicatorsMapper {

    /**
     * 查询文旅资源总数
     * @param timeCycle 时间周期
     * @return 总数
     */
    Integer selectTotalSceneCount(@Param("timeCycle") String timeCycle);

    /**
     * 查询当日客流峰值
     * @param timeCycle 时间周期
     * @return 峰值
     */
    Integer selectMaxCount(@Param("timeCycle") String timeCycle);

    /**
     * 查询投诉办结率
     * @param timeCycle 时间周期
     * @return 办结率(%)
     */
    Double selectCompleteRate(@Param("timeCycle") String timeCycle);

    /**
     * 查询设施完好率
     * @param timeCycle 时间周期
     * @return 完好率(%)
     */
    Double selectFacilityGoodRate(@Param("timeCycle") String timeCycle);

    /**
     * 查询活动开展数
     * @param timeCycle 时间周期
     * @return 活动数
     */
    Integer selectNewSceneCount(@Param("timeCycle") String timeCycle);
}