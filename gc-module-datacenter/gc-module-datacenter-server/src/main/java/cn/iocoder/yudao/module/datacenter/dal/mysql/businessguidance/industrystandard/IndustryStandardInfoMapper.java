package cn.iocoder.yudao.module.datacenter.dal.mysql.businessguidance.industrystandard;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.industrystandard.vo.IndustryStandardInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.industrystandard.IndustryStandardInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 行业规范信息 Mapper
 *
 * @author Gyh
 */
@Mapper
public interface IndustryStandardInfoMapper extends BaseMapperX<IndustryStandardInfoDO> {

    /**
     * 根据规范编号查询
     */
    default IndustryStandardInfoDO selectByStandardNo(String standardNo) {
        return selectOne("standard_no", standardNo);
    }

    /**
     * 检查规范编号是否存在
     */
    default boolean existsByStandardNo(String standardNo) {
        return selectCount("standard_no", standardNo) > 0;
    }

    /**
     * 检查规范编号是否存在（排除自身）
     */
    default boolean existsByStandardNoExcludeSelf(String standardNo, Long excludeId) {
        return selectCount(new LambdaQueryWrapperX<IndustryStandardInfoDO>()
                .eq(IndustryStandardInfoDO::getStandardNo, standardNo)
                .ne(IndustryStandardInfoDO::getId, excludeId)) > 0;
    }

    /**
     * 分页查询行业规范信息 - 修复无限递归问题
     */
    default PageResult<IndustryStandardInfoDO> selectPage(IndustryStandardInfoPageReqVO pageReqVO,
                                                          LambdaQueryWrapperX<IndustryStandardInfoDO> queryWrapper) {
        // ✅ 正确调用父类的selectPage方法，而不是递归调用自身
        return BaseMapperX.super.selectPage(pageReqVO, queryWrapper);
    }

    /**
     * 分页查询行业规范信息（简化版）
     */
    default PageResult<IndustryStandardInfoDO> selectPage(IndustryStandardInfoPageReqVO pageReqVO) {
        return selectPage(pageReqVO, buildQueryWrapper(pageReqVO));
    }

    /**
     * 构建查询条件
     */
    private LambdaQueryWrapperX<IndustryStandardInfoDO> buildQueryWrapper(IndustryStandardInfoPageReqVO pageReqVO) {
        return new LambdaQueryWrapperX<IndustryStandardInfoDO>()
                .likeIfPresent(IndustryStandardInfoDO::getStandardName, pageReqVO.getStandardName())
                .likeIfPresent(IndustryStandardInfoDO::getStandardNo, pageReqVO.getStandardNo())
                .eqIfPresent(IndustryStandardInfoDO::getIndustryField, pageReqVO.getIndustryField())
                .eqIfPresent(IndustryStandardInfoDO::getStandardStatus, pageReqVO.getStandardStatus())
                .likeIfPresent(IndustryStandardInfoDO::getIssueOrganization, pageReqVO.getIssueOrganization())
                .likeIfPresent(IndustryStandardInfoDO::getRelatedBusinessModule, pageReqVO.getRelatedBusinessModule())
                .orderByDesc(IndustryStandardInfoDO::getIssueTime);
    }
}