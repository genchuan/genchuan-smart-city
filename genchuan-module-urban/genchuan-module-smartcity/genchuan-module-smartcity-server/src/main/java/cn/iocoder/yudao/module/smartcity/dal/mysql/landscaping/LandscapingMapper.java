package cn.iocoder.yudao.module.smartcity.dal.mysql.landscaping;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.landscaping.LandscapingDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.landscaping.vo.*;

/**
 * 园林绿化 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface LandscapingMapper extends BaseMapperX<LandscapingDO> {

    default PageResult<LandscapingDO> selectPage(LandscapingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LandscapingDO>()
                .likeIfPresent(LandscapingDO::getGreenSpaceName, reqVO.getGreenSpaceName())
                .eqIfPresent(LandscapingDO::getGreenbeltType, reqVO.getGreenbeltType())
                .betweenIfPresent(LandscapingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LandscapingDO::getId));
    }

}