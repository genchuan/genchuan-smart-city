package cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.mngcomp;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.mngcomp.vo.MngCompQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.mngcomp.vo.MngCompRespVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 市政设施专题-管理部件 Mapper
 *
 */
@Mapper
public interface MngCompMapper {

    /**
     * 查询市政设施专题-管理部件
     *
     * @param mngCompQueryReqVO 查询参数
     * @return MngCompRespVO 结果
     */
    MngCompRespVO getMngComp(MngCompQueryReqVO mngCompQueryReqVO);

}
