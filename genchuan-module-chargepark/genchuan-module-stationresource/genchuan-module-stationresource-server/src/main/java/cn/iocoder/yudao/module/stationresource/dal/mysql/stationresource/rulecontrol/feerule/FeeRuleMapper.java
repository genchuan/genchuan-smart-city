package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.feerule;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.FeeRulePageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.FeeRuleRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.FeeRuleChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.feerule.FeeRuleDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收费规则 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface FeeRuleMapper extends BaseMapperX<FeeRuleDO> {

    Page<FeeRuleRespVO> getPage(Page<FeeRuleRespVO> page, @Param("pageReqVO") FeeRulePageReqVO pageReqVO);

    FeeRuleChartRespVO.CardDataVO selectCardData();

    List<FeeRuleChartRespVO.StationBarVO> selectStationBarList();
}
