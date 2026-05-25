package cn.iocoder.yudao.module.enterprisesvc.dal.mysql.enterprisefile;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.enterprisesvc.dal.dataobject.enterprisefile.EnterpriseFileDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile.vo.*;

/**
 * 企业档案 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface EnterpriseFileMapper extends BaseMapperX<EnterpriseFileDO> {

    default EnterpriseFileDO selectByCreditCode(String creditCode) {
        return selectOne(EnterpriseFileDO::getCreditCode, creditCode);
    }

    default PageResult<EnterpriseFileDO> selectPage(EnterpriseFilePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EnterpriseFileDO>()
                .likeIfPresent(EnterpriseFileDO::getEnterpriseName, reqVO.getEnterpriseName())
                .eqIfPresent(EnterpriseFileDO::getCreditCode, reqVO.getCreditCode())
                .eqIfPresent(EnterpriseFileDO::getRegisterAddr, reqVO.getRegisterAddr())
                .eqIfPresent(EnterpriseFileDO::getEnterpriseType, reqVO.getEnterpriseType())
                .eqIfPresent(EnterpriseFileDO::getEnterpriseScale, reqVO.getEnterpriseScale())
                .eqIfPresent(EnterpriseFileDO::getFileStatus, reqVO.getFileStatus())
                .eqIfPresent(EnterpriseFileDO::getStaffCount, reqVO.getStaffCount())
                .eqIfPresent(EnterpriseFileDO::getCheckUser, reqVO.getCheckUser())
                .eqIfPresent(EnterpriseFileDO::getCheckRate, reqVO.getCheckRate())
                .eqIfPresent(EnterpriseFileDO::getHandleUser, reqVO.getHandleUser())
                .eqIfPresent(EnterpriseFileDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(EnterpriseFileDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(EnterpriseFileDO::getCreator, reqVO.getCreator())
                .eqIfPresent(EnterpriseFileDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(EnterpriseFileDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(EnterpriseFileDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(EnterpriseFileDO::getId));
    }

    /**
     * 统计图表基础数据（总数、在园数、退园数、审核通过率）
     */
    Map<String, Object> selectChartStats();

    /**
     * 统计企业类型占比
     */
    List<EnterpriseFileChartRespVO.ChartItemVO> selectTypeRatio();

    /**
     * 统计企业规模占比
     */
    List<EnterpriseFileChartRespVO.ChartItemVO> selectScaleRatio();

}
