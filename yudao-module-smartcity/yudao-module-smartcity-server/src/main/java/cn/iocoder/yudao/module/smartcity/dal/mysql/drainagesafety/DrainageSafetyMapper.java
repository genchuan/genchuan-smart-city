package cn.iocoder.yudao.module.smartcity.dal.mysql.drainagesafety;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagesafety.DrainageSafetyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagesafety.vo.*;

/**
 * 排水安全 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface DrainageSafetyMapper extends BaseMapperX<DrainageSafetyDO> {

    default PageResult<DrainageSafetyDO> selectPage(DrainageSafetyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DrainageSafetyDO>()
                .eqIfPresent(DrainageSafetyDO::getWarningNumber, reqVO.getWarningNumber())
                .betweenIfPresent(DrainageSafetyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DrainageSafetyDO::getId));
    }

}