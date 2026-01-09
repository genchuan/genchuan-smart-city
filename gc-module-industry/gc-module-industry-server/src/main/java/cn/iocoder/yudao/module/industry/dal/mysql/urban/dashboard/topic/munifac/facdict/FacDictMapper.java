package cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.facdict;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.facdict.vo.FacDictQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.facdict.vo.FacDictRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 市政设施专题-设施类型字典 Mapper
 *
 */
@Mapper
public interface FacDictMapper {

    /**
     * 查询市政设施专题-设施类型字典
     *
     * @param facDictQueryReqVO 查询参数
     * @return FacDictRespVO 结果
     */
    List<FacDictRespVO> getFacDict(FacDictQueryReqVO facDictQueryReqVO);

}
