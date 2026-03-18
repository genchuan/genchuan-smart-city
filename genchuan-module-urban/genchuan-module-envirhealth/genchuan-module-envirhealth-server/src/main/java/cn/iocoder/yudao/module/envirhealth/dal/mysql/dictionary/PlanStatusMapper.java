package cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.planstatus.vo.PlanStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.PlanStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 计划状态字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PlanStatusMapper extends BaseMapperX<PlanStatusDO> {

    default PageResult<PlanStatusDO> selectPage(PlanStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PlanStatusDO>()
                .eqIfPresent(PlanStatusDO::getSysPlanStatusId, reqVO.getSysPlanStatusId())
                .likeIfPresent(PlanStatusDO::getName, reqVO.getName())
                .eqIfPresent(PlanStatusDO::getCode, reqVO.getCode())
                .eqIfPresent(PlanStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PlanStatusDO::getSort, reqVO.getSort())
                .betweenIfPresent(PlanStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PlanStatusDO::getId));
    }

}