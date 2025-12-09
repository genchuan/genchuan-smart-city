package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.overview;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.*;

import java.util.List;

/**
 * 执法全域数据概览 Service 接口
 *
 * @author lxs
 */
public interface LawOverviewService {

        /**
         * 查询执法全域数据概览
         *
         * @param lawOverviewQueryReqVO 查询条件
         * @return 查询结果
         */
        LawOverviewRespVO getLawOverview(LawOverviewQueryReqVO lawOverviewQueryReqVO);

        /**
         * 查询执法全域数据所涉及的行政区列表
         */
        List<LawOverviewArea> getLawOverviewArea();

        /**
         * 获取行政区域列表（名称和地区码）
         *
         * 功能说明：
         * 1. 若未指定上级行政区（areaReqVO.regionShortCode为空），返回所有省级行政区。
         * 2. 若指定省级行政区（regionShortCode末尾为"0000"），返回该省下的所有市级行政区（不包含省本身）。
         * 3. 若指定市级行政区（regionShortCode末尾为"00"但不为"0000"），返回该市下的所有县级行政区（不包含市本身）。
         * 4. 若指定县级行政区（其他情况），返回该县本身。
         *
         * 用途：
         * - 可用于前端行政区级联选择、数据筛选等场景。
         *
         * @param areaReqVO 查询条件对象，包含上级行政区编码 regionShortCode
         * @return 返回行政区列表，每个对象包含名称和地区码
         */
        List<AreaRespVO> getAllArea(AreaReqVO areaReqVO);

}
