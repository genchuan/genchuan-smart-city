package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.offtimerule;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.OfftimeRulePageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.OfftimeRuleRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.offtimerule.OfftimeRuleDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 错时规则 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface OfftimeRuleMapper extends BaseMapperX<OfftimeRuleDO> {

    Page<OfftimeRuleRespVO> getPage(Page<OfftimeRuleRespVO> page, @Param("pageReqVO") OfftimeRulePageReqVO pageReqVO);

    /**
     * 查询卡片统计数据
     */
    OfftimeRuleChartRespVO.CardDataVO selectCardData();

    /**
     * 查询订单趋势折线数据
     */
    List<OfftimeRuleChartRespVO.OrderLineVO> selectOrderLineList();
}
