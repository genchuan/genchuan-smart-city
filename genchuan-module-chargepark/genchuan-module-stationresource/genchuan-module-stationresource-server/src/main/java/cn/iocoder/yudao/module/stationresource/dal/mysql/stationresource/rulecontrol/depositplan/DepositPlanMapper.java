package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.depositplan;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.DepositPlanPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.DepositPlanRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.depositplan.DepositPlanDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 押金方案 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DepositPlanMapper extends BaseMapperX<DepositPlanDO> {

    Page<DepositPlanRespVO> getPage(Page<DepositPlanRespVO> page, @Param("pageReqVO") DepositPlanPageReqVO pageReqVO);

    DepositPlanChartRespVO.CardDataVO selectCardData();

    List<DepositPlanChartRespVO.SceneBarVO> selectSceneBarList();
}
