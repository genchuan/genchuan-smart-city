package cn.iocoder.yudao.module.smartcity.dal.mysql.oflawenforcementpersonnel;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.oflawenforcementpersonnel.OfLawEnforcementPersonnelDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.oflawenforcementpersonnel.vo.*;

/**
 * 执法人员管理 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface OfLawEnforcementPersonnelMapper extends BaseMapperX<OfLawEnforcementPersonnelDO> {

    default PageResult<OfLawEnforcementPersonnelDO> selectPage(OfLawEnforcementPersonnelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OfLawEnforcementPersonnelDO>()
                .likeIfPresent(OfLawEnforcementPersonnelDO::getFullName, reqVO.getFullName())
                .eqIfPresent(OfLawEnforcementPersonnelDO::getGender, reqVO.getGender())
                .eqIfPresent(OfLawEnforcementPersonnelDO::getIdNumber, reqVO.getIdNumber())
                .betweenIfPresent(OfLawEnforcementPersonnelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OfLawEnforcementPersonnelDO::getId));
    }

}