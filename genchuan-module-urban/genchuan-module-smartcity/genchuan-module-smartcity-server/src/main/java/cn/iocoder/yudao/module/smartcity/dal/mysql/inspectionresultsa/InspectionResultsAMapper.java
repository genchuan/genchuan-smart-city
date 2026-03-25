package cn.iocoder.yudao.module.smartcity.dal.mysql.inspectionresultsa;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionresultsa.InspectionResultsADO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionresultsa.vo.*;

/**
 * 巡查结果 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface InspectionResultsAMapper extends BaseMapperX<InspectionResultsADO> {

    default PageResult<InspectionResultsADO> selectPage(InspectionResultsAPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionResultsADO>()
                .eqIfPresent(InspectionResultsADO::getNumber, reqVO.getNumber())
                .eqIfPresent(InspectionResultsADO::getPatrolPersonnel, reqVO.getPatrolPersonnel())
                .betweenIfPresent(InspectionResultsADO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectionResultsADO::getId));
    }

}