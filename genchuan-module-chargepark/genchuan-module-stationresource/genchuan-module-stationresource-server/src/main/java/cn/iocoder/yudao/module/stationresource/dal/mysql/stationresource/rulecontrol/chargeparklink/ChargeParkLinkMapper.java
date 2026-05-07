package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.chargeparklink;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ChargeParkLinkPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ChargeParkLinkRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.chargeparklink.ChargeParkLinkDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 充停联动 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ChargeParkLinkMapper extends BaseMapperX<ChargeParkLinkDO> {

    Page<ChargeParkLinkRespVO> getPage(Page<ChargeParkLinkRespVO> page, @Param("pageReqVO") ChargeParkLinkPageReqVO pageReqVO);

    // 图表卡片
    ChargeParkLinkChartRespVO.CardDataVO selectCardData();

    // 折线图
    List<ChargeParkLinkChartRespVO.DiscountLineVO> selectDiscountLineList();

    // 柱状图
    List<ChargeParkLinkChartRespVO.OrderBarVO> selectOrderBarList();
}
