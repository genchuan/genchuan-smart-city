package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.jobtype.JobTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.JobTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 岗位类型字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface JobTypeMapper extends BaseMapperX<JobTypeDO> {

    default PageResult<JobTypeDO> selectPage(JobTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JobTypeDO>()
                .eqIfPresent(JobTypeDO::getSysJobTypeId, reqVO.getSysJobTypeId())
                .likeIfPresent(JobTypeDO::getName, reqVO.getName())
                .eqIfPresent(JobTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(JobTypeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(JobTypeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(JobTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(JobTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(JobTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(JobTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(JobTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(JobTypeDO::getId));
    }

}