package cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.bizmngmatterext;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterext.vo.BizMngMatterExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatterext.BizMngMatterExtDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理事项扩展管理事项配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BizMngMatterExtMapper extends BaseMapperX<BizMngMatterExtDO> {

    default PageResult<BizMngMatterExtDO> selectPage(BizMngMatterExtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BizMngMatterExtDO>()
                .eqIfPresent(BizMngMatterExtDO::getMngMatterExtId, reqVO.getMngMatterExtId())
                .eqIfPresent(BizMngMatterExtDO::getMatterMajorId, reqVO.getMatterMajorId())
                .likeIfPresent(BizMngMatterExtDO::getMatterMajorName, reqVO.getMatterMajorName())
                .eqIfPresent(BizMngMatterExtDO::getExtMinorCode, reqVO.getExtMinorCode())
                .likeIfPresent(BizMngMatterExtDO::getExtMinorName, reqVO.getExtMinorName())
                .eqIfPresent(BizMngMatterExtDO::getExtMinorDesc, reqVO.getExtMinorDesc())
                .eqIfPresent(BizMngMatterExtDO::getSuggestDeptCode, reqVO.getSuggestDeptCode())
                .likeIfPresent(BizMngMatterExtDO::getSuggestDeptName, reqVO.getSuggestDeptName())
                .eqIfPresent(BizMngMatterExtDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(BizMngMatterExtDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(BizMngMatterExtDO::getId));
    }

}