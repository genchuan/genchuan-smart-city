package cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.bizmngcompext;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext.vo.BizMngCompExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompext.BizMngCompExtDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理部件扩展管理部件配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BizMngCompExtMapper extends BaseMapperX<BizMngCompExtDO> {

    default PageResult<BizMngCompExtDO> selectPage(BizMngCompExtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BizMngCompExtDO>()
                .eqIfPresent(BizMngCompExtDO::getMngCompExtId, reqVO.getMngCompExtId())
                .eqIfPresent(BizMngCompExtDO::getMajorId, reqVO.getMajorId())
                .likeIfPresent(BizMngCompExtDO::getMajorName, reqVO.getMajorName())
                .eqIfPresent(BizMngCompExtDO::getExtMinorCode, reqVO.getExtMinorCode())
                .likeIfPresent(BizMngCompExtDO::getExtMinorName, reqVO.getExtMinorName())
                .eqIfPresent(BizMngCompExtDO::getExtMinorDesc, reqVO.getExtMinorDesc())
                .eqIfPresent(BizMngCompExtDO::getSuggestDeptCode, reqVO.getSuggestDeptCode())
                .likeIfPresent(BizMngCompExtDO::getSuggestDeptName, reqVO.getSuggestDeptName())
                .eqIfPresent(BizMngCompExtDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(BizMngCompExtDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(BizMngCompExtDO::getId));
    }

}