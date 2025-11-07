package cn.iocoder.yudao.module.smartcity.dal.mysql.inspectiontaska;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectiontaska.InspectionTaskADO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectiontaska.vo.*;

/**
 * 巡查任务 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface InspectionTaskAMapper extends BaseMapperX<InspectionTaskADO> {

    default PageResult<InspectionTaskADO> selectPage(InspectionTaskAPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionTaskADO>()
                .eqIfPresent(InspectionTaskADO::getTaskNumber, reqVO.getTaskNumber())
                .eqIfPresent(InspectionTaskADO::getTask, reqVO.getTask())
                .betweenIfPresent(InspectionTaskADO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectionTaskADO::getId));
    }

}