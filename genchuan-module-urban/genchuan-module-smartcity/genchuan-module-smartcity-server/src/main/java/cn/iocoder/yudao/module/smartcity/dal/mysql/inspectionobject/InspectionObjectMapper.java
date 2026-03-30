package cn.iocoder.yudao.module.smartcity.dal.mysql.inspectionobject;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionobject.InspectionObjectDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionobject.vo.*;

/**
 * 双随机行政检查 Mapper
 *
 * @author 朱聪权
 */
@Mapper
public interface InspectionObjectMapper extends BaseMapperX<InspectionObjectDO> {

    default PageResult<InspectionObjectDO> selectPage(InspectionObjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionObjectDO>()
                .likeIfPresent(InspectionObjectDO::getEntName, reqVO.getEntName())
                .likeIfPresent(InspectionObjectDO::getCreditCode, reqVO.getCreditCode())
                .likeIfPresent(InspectionObjectDO::getLegalPerson, reqVO.getLegalPerson())
                .orderByDesc(InspectionObjectDO::getId));
    }

}