package cn.iocoder.yudao.module.datacenter.dal.mysql.bizmngmattermajor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizmngmattermajor.BizMngMatterMajorDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizmngmattermajor.vo.*;

/**
 * 管理事项大类 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BizMngMatterMajorMapper extends BaseMapperX<BizMngMatterMajorDO> {

    default PageResult<BizMngMatterMajorDO> selectPage(BizMngMatterMajorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BizMngMatterMajorDO>()
                .eqIfPresent(BizMngMatterMajorDO::getMngMatterMajorId, reqVO.getMngMatterMajorId())
                .eqIfPresent(BizMngMatterMajorDO::getMatterMajorCode, reqVO.getMatterMajorCode())
                .likeIfPresent(BizMngMatterMajorDO::getMatterMajorName, reqVO.getMatterMajorName())
                .eqIfPresent(BizMngMatterMajorDO::getMatterMajorDesc, reqVO.getMatterMajorDesc())
                .eqIfPresent(BizMngMatterMajorDO::getSortNum, reqVO.getSortNum())
                .eqIfPresent(BizMngMatterMajorDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(BizMngMatterMajorDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(BizMngMatterMajorDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(BizMngMatterMajorDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(BizMngMatterMajorDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(BizMngMatterMajorDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(BizMngMatterMajorDO::getId));
    }

}