package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.blackwhitelist;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.BlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.BlackWhiteListRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.blackwhitelist.BlackWhiteListDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 黑白名单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BlackWhiteListMapper extends BaseMapperX<BlackWhiteListDO> {

    Page<BlackWhiteListRespVO> getPage(Page<BlackWhiteListRespVO> page, @Param("pageReqVO") BlackWhiteListPageReqVO pageReqVO);

    // 卡片统计
    BlackWhiteListChartRespVO.CardDataVO selectCardData();

    // 饼图统计
    List<BlackWhiteListChartRespVO.TypePieVO> selectTypePieList();
}
