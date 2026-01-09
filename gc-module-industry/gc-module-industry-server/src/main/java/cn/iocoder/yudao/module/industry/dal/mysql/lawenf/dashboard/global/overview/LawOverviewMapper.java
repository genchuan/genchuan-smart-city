package cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.overview;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 执法全域数据概览 Mapper
 *
 * @author lxs
 */
@Mapper
public interface LawOverviewMapper {

        /**
         * 查询执法全域数据概览
         *
         * @param lawOverviewQueryReqVO 查询参数
         * @return LawOverviewRespVO 结果
         */
        LawOverviewRespVO getLawOverview(LawOverviewQueryReqVO lawOverviewQueryReqVO);

        /**
         * 查询执法全域数据中涉及的行政区列表
         * 返回行政区名称与地区码，用于前端筛选组件
         */
        List<LawOverviewArea> getLawOverviewArea();

        /**
         * 获取行政区域列表
         *
         * 功能说明：
         * 1. 若未指定上级行政区，则返回所有省级行政区。
         * 2. 若指定省级行政区，则返回该省下的所有市级行政区（不包含省本身）。
         * 3. 若指定市级行政区，则返回该市下的所有县级行政区（不包含市本身）。
         * 4. 若指定县级行政区，则可返回对应县级行政区（根据业务需求）。
         *
         * @param areaReqVO 查询条件，包含上级行政区编码 regionShortCode
         * @return 匹配的行政区域列表，每项包含区域名称和区域码
         */
        List<AreaRespVO> getAllArea(AreaReqVO areaReqVO);

}
