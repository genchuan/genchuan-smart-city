package cn.iocoder.yudao.module.industry.service.health.dashboard.global.keypop;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.keypop.vo.KeyPopQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.keypop.vo.KeyPopRespVO;

/**
 * 重点人群健康视图 Service 接口
 *
 * 提供重点人群健康视图数据，包括：
 * 1. 左侧卡片统计（老年人、慢病、孕产妇、高风险）
 * 2. 随访率指标
 * 3. 各社区重点人群分布
 * 4. 高风险人群列表
 *
 * 可按人群类型与社区名称筛选
 *
 * author: lxs
 */
public interface KeyPopService {

        /**
         * 获取重点人群健康视图数据（左侧卡片 + 地图 + 高风险列表）
         *
         * @return KeyPopRespVO
         */
        KeyPopRespVO getKeyPopOverview(KeyPopQueryReqVO keyPopQueryReqVO);


}
