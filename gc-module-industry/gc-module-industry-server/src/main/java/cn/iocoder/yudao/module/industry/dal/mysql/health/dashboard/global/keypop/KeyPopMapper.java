package cn.iocoder.yudao.module.industry.dal.mysql.health.dashboard.global.keypop;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.keypop.vo.KeyPopQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.keypop.vo.KeyPopRespVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KeyPopMapper {

        // -------------------------
        // 左侧卡片 - 汇总统计
        // -------------------------
        KeyPopRespVO selectKeyPopSummary();

        // -------------------------
        // 左侧卡片 - 随访率
        // -------------------------
        // 从 stat_health_public_metric 中取 key_pop_follow_rate
        KeyPopRespVO selectFollowRate();

        // -------------------------
        // 中间地图 - 社区分布数据
        // -------------------------
        List<KeyPopRespVO.CommunityKeyPop> selectCommunityKeyPopList(KeyPopQueryReqVO keyPopQueryReqVO);

        // -------------------------
        // 右侧列表 - 高风险人群
        // -------------------------
        List<KeyPopRespVO.HighRiskPerson> selectHighRiskPersonList(KeyPopQueryReqVO keyPopQueryReqVO);
}
