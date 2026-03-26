package cn.iocoder.yudao.module.smartcity.dal.mysql.environmentalsanitation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.environmentalsanitation.EnvironmentalSanitationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsanitation.vo.*;

/**
 * 市容环卫 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface EnvironmentalSanitationMapper extends BaseMapperX<EnvironmentalSanitationDO> {

    default PageResult<EnvironmentalSanitationDO> selectPage(EnvironmentalSanitationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EnvironmentalSanitationDO>()
                .likeIfPresent(EnvironmentalSanitationDO::getFacilityName, reqVO.getFacilityName())
                .eqIfPresent(EnvironmentalSanitationDO::getFacilityLocation, reqVO.getFacilityLocation())
                .betweenIfPresent(EnvironmentalSanitationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EnvironmentalSanitationDO::getId));
    }

}