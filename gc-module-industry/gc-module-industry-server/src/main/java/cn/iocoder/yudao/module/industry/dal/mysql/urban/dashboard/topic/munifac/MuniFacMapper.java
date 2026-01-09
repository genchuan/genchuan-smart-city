package cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac;



import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.FacOverviewReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.FacOverviewRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.MuniFacQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.MuniFacRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

/**
 * 市政设施专题 Mapper
 *
 * @author lxs
 */
@Mapper
public interface MuniFacMapper {

        /**
         * 查询市政设施专题
         *
         * @param muniFacQueryReqVO 查询参数
         * @return MuniFacRespVO 结果
         */
        MuniFacRespVO getMuniFac(MuniFacQueryReqVO muniFacQueryReqVO);

//    FacOverviewRespVO getMuniFacOverview(MuniFacQueryReqVO muniFacQueryReqVO);

    /**
     * 查询紧急告警数量
     *
     * @param facOverviewReqVO 查询条件
     * @return 紧急告警数量
     */
    Integer getEmergencyCount(FacOverviewReqVO facOverviewReqVO);

    /**
     * 查询一般告警数量
     *
     * @param facOverviewReqVO 查询条件
     * @return 一般告警数量
     */
    Integer getNormalCount(FacOverviewReqVO facOverviewReqVO);

    /**
     * 查询故障总数
     *
     * @param facOverviewReqVO 查询条件
     * @return 故障总数
     */
    Integer getTotalFault(FacOverviewReqVO facOverviewReqVO);

    /**
     * 查询已处理数量
     *
     * @param facOverviewReqVO 查询条件
     * @return 已处理数量
     */
    Integer getHandledCount(FacOverviewReqVO facOverviewReqVO);

    /**
     * 查询处理率
     *
     * @param facOverviewReqVO 查询条件
     * @return 处理率（已处理数 / 故障总数）
     */
    BigDecimal getHandlingRate(FacOverviewReqVO facOverviewReqVO);

    /**
     * 查询超时数量
     *
     * @param facOverviewReqVO 查询条件
     * @return 超时数量
     */
    Integer getTimeoutCount(FacOverviewReqVO facOverviewReqVO);

}
