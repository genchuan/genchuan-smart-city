package cn.iocoder.yudao.module.smartcity.dal.mysql.lawenforcement;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcement.LawEnforcementDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcement.vo.*;

/**
 * 城市管理执法 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface LawEnforcementMapper extends BaseMapperX<LawEnforcementDO> {

    default PageResult<LawEnforcementDO> selectPage(LawEnforcementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LawEnforcementDO>()
                .eqIfPresent(LawEnforcementDO::getLawEnforcementNumber, reqVO.getLawEnforcementNumber())
                .eqIfPresent(LawEnforcementDO::getLocationTheCase, reqVO.getLocationTheCase())
                .betweenIfPresent(LawEnforcementDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LawEnforcementDO::getId));
    }

}