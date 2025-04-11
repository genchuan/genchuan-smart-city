package cn.iocoder.yudao.module.system.dal.mysql.alarmhandlingcategory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.alarmhandlingcategory.AlarmHandlingCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.alarmhandlingcategory.vo.*;

/**
 * 警报处理类别 Mapper
 *
 * @author zcq
 */
@Mapper
public interface AlarmHandlingCategoryMapper extends BaseMapperX<AlarmHandlingCategoryDO> {

    default PageResult<AlarmHandlingCategoryDO> selectPage(AlarmHandlingCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlarmHandlingCategoryDO>()
                .eqIfPresent(AlarmHandlingCategoryDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(AlarmHandlingCategoryDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(AlarmHandlingCategoryDO::getUpdateBy, reqVO.getUpdateBy())
                .orderByDesc(AlarmHandlingCategoryDO::getId));
    }

}