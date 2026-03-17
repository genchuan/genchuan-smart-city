package cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.tasktype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.tasktype.vo.TaskTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.tasktype.TaskTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务类型字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TaskTypeMapper extends BaseMapperX<TaskTypeDO> {

    default PageResult<TaskTypeDO> selectPage(TaskTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskTypeDO>()
                .eqIfPresent(TaskTypeDO::getSysTaskTypeId, reqVO.getSysTaskTypeId())
                .likeIfPresent(TaskTypeDO::getName, reqVO.getName())
                .eqIfPresent(TaskTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(TaskTypeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TaskTypeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TaskTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TaskTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TaskTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(TaskTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(TaskTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskTypeDO::getId));
    }

}