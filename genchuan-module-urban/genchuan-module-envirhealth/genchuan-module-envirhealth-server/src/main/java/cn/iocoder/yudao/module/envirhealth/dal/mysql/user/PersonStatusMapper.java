package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.personstatus.PersonStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.PersonStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人员状态字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PersonStatusMapper extends BaseMapperX<PersonStatusDO> {

    default PageResult<PersonStatusDO> selectPage(PersonStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PersonStatusDO>()
                .eqIfPresent(PersonStatusDO::getPersonStatusId, reqVO.getPersonStatusId())
                .likeIfPresent(PersonStatusDO::getName, reqVO.getName())
                .eqIfPresent(PersonStatusDO::getStatusCode, reqVO.getStatusCode())
                .eqIfPresent(PersonStatusDO::getSort, reqVO.getSort())
                .betweenIfPresent(PersonStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PersonStatusDO::getId));
    }

}