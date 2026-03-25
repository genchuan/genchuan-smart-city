package cn.iocoder.yudao.module.smartcity.dal.mysql.personnelhomework;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.personnelhomework.PersonnelHomeworkDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.personnelhomework.vo.*;

/**
 * 人员作业 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface PersonnelHomeworkMapper extends BaseMapperX<PersonnelHomeworkDO> {

    default PageResult<PersonnelHomeworkDO> selectPage(PersonnelHomeworkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PersonnelHomeworkDO>()
                .eqIfPresent(PersonnelHomeworkDO::getJobNumber, reqVO.getJobNumber())
                .eqIfPresent(PersonnelHomeworkDO::getOperatorId, reqVO.getOperatorId())
                .betweenIfPresent(PersonnelHomeworkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PersonnelHomeworkDO::getId));
    }

}