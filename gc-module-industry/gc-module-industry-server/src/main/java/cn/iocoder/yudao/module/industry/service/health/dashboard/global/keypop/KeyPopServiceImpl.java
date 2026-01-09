package cn.iocoder.yudao.module.industry.service.health.dashboard.global.keypop;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.keypop.vo.KeyPopQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.keypop.vo.KeyPopRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.health.dashboard.global.keypop.KeyPopMapper;
import org.checkerframework.checker.units.qual.K;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KeyPopServiceImpl implements KeyPopService {

    @Resource
    private KeyPopMapper keyPopMapper;

    /**
     * 获取重点人群健康视图数据（左侧卡片 + 中间地图 + 右侧高风险列表）
     *
     * @return KeyPopRespVO
     */
    @Override
    public KeyPopRespVO getKeyPopOverview(KeyPopQueryReqVO keyPopQueryReqVO) {

        KeyPopRespVO respVO = new KeyPopRespVO();

        // ----------------------------
        // 1. 左侧卡片统计数据
        // ----------------------------
        KeyPopRespVO summary = keyPopMapper.selectKeyPopSummary();
        if (summary != null) {
            respVO.setElderlyCount(summary.getElderlyCount());
            respVO.setChronicPatientCount(summary.getChronicPatientCount());
            respVO.setMaternalCount(summary.getMaternalCount());
            respVO.setHighRiskCount(summary.getHighRiskCount());
        }

        // ----------------------------
        // 2. 左侧 - 随访率
        // ----------------------------
        KeyPopRespVO rate = keyPopMapper.selectFollowRate();
        if (rate != null) {
            respVO.setKeyPopFollowRate(rate.getKeyPopFollowRate());
        }

        // ----------------------------
        // 3. 中间地图 - 各社区人群分布
        // ----------------------------
        List<KeyPopRespVO.CommunityKeyPop> communityList =
                keyPopMapper.selectCommunityKeyPopList(keyPopQueryReqVO);
        respVO.setCommunityKeyPopList(communityList);

        // ----------------------------
        // 4. 右侧列表 - 高风险人群
        // ----------------------------
        List<KeyPopRespVO.HighRiskPerson> riskList =
                keyPopMapper.selectHighRiskPersonList(keyPopQueryReqVO);
        respVO.setHighRiskPersonList(riskList);

        return respVO;
    }


}
