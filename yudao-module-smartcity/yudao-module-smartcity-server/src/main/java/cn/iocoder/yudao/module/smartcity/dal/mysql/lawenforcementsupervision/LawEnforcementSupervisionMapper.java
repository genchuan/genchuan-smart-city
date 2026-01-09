package cn.iocoder.yudao.module.smartcity.dal.mysql.lawenforcementsupervision;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcementsupervision.LawEnforcementSupervisionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementsupervision.vo.*;

/**
 * 执法监督 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface LawEnforcementSupervisionMapper extends BaseMapperX<LawEnforcementSupervisionDO> {

    default PageResult<LawEnforcementSupervisionDO> selectPage(LawEnforcementSupervisionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LawEnforcementSupervisionDO>()
                .eqIfPresent(LawEnforcementSupervisionDO::getSupervisionId, reqVO.getSupervisionId())
                .eqIfPresent(LawEnforcementSupervisionDO::getEventNumber, reqVO.getEventNumber())
                .eqIfPresent(LawEnforcementSupervisionDO::getOfficials, reqVO.getOfficials())
                .betweenIfPresent(LawEnforcementSupervisionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LawEnforcementSupervisionDO::getId));
    }

}