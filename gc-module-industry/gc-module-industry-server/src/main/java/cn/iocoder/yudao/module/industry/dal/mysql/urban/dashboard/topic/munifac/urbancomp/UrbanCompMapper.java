package cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.urbancomp;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.dal.dataobject.urban.dashboard.topic.munifac.urbancomp.UrbanCompDO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.urbancomp.vo.UrbanCompQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.urbancomp.vo.UrbanCompRespVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 市政设施专题-市政设施管理部件 Mapper
 *
 */
@Mapper
public interface UrbanCompMapper  extends BaseMapperX<UrbanCompDO> {

    /**
     * 查询市政设施专题-市政设施管理部件
     *
     * @param urbanCompQueryReqVO 查询参数
     * @return UrbanCompRespVO 结果
     */
    UrbanCompRespVO getUrbanComp(UrbanCompQueryReqVO urbanCompQueryReqVO);

}
