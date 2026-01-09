package cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.fltsite;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.fltsite.vo.FltSiteQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.fltsite.vo.FltSiteRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 市政设施专题-故障设施空间数据 Mapper
 *
 */
@Mapper
public interface FltSiteMapper {

    /**
     * 查询市政设施专题-故障设施空间数据
     *
     * @param fltSiteQueryReqVO 查询参数
     * @return FltSiteRespVO 结果
     */
    List<FltSiteRespVO> getFltSite(FltSiteQueryReqVO fltSiteQueryReqVO);




}
