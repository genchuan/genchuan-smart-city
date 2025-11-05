package cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.overview;

import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview.vo.EmergOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview.vo.EmergOverviewRespVO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 应急全域数据概览 Mapper
 *
 * @author lxs
 */
@Mapper
public interface EmergOverviewMapper {
    EmergOverviewRespVO getEmergOverview(EmergOverviewQueryReqVO emergOverviewQueryReqVO);
}
