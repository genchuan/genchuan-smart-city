package cn.iocoder.yudao.module.smartcity.dal.mysql.dynamicinformationclassification;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.dynamicinformationclassification.DynamicInformationClassificationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationclassification.vo.*;

/**
 * 动态信息分类 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface DynamicInformationClassificationMapper extends BaseMapperX<DynamicInformationClassificationDO> {

    default PageResult<DynamicInformationClassificationDO> selectPage(DynamicInformationClassificationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DynamicInformationClassificationDO>()
                .eqIfPresent(DynamicInformationClassificationDO::getMessageSubject, reqVO.getMessageSubject())
                .eqIfPresent(DynamicInformationClassificationDO::getIndustrySector, reqVO.getIndustrySector())
                .betweenIfPresent(DynamicInformationClassificationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DynamicInformationClassificationDO::getId));
    }

}