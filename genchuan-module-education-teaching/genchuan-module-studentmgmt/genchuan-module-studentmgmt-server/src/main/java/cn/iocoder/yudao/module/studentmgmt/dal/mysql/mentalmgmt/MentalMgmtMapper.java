package cn.iocoder.yudao.module.studentmgmt.dal.mysql.mentalmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.MentalMgmtJoinPageRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.MentalMgmtPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.mentalmgmt.MentalMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.violatemgmt.ViolateMgmtDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.util.Objects;

/**
 * 心理管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MentalMgmtMapper extends BaseMapperX<MentalMgmtDO> {

    default PageResult<MentalMgmtDO> selectPage(MentalMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MentalMgmtDO>()
                .eqIfPresent(MentalMgmtDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(MentalMgmtDO::getMentalStatus, reqVO.getMentalStatus())
                .eqIfPresent(MentalMgmtDO::getRiskLevel, reqVO.getRiskLevel())
//                .betweenIfPresent(MentalMgmtDO::getEvaluateTime, reqVO.getEvaluateTime())
//                .betweenIfPresent(MentalMgmtDO::getConsultTime, reqVO.getConsultTime())
//                .betweenIfPresent(MentalMgmtDO::getInterveneTime, reqVO.getInterveneTime())
                .eqIfPresent(MentalMgmtDO::getInterveneContent, reqVO.getInterveneContent())
                .eqIfPresent(MentalMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MentalMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MentalMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(MentalMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(MentalMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MentalMgmtDO::getId));
    }

    default PageResult<MentalMgmtJoinPageRespVO> selectJoinPage(MentalMgmtPageReqVO reqVO) {

        // 1. 构建分页对象
        Page<MentalMgmtJoinPageRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 构建 MPJ 联表 Wrapper
        MPJLambdaWrapper<MentalMgmtDO> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(MentalMgmtDO.class);
        wrapper.selectAs(StudentInfoDO::getName, MentalMgmtJoinPageRespVO::getStudentName);
        wrapper.leftJoin(StudentInfoDO.class, StudentInfoDO::getId, MentalMgmtDO::getStudentId);

        if (null != reqVO.getStudentId()) {
            wrapper.eq(MentalMgmtDO::getStudentId, reqVO.getStudentId());
        }
        if (StringUtils.isNotBlank(reqVO.getMentalStatus())) {
            wrapper.eq(MentalMgmtDO::getMentalStatus, reqVO.getMentalStatus());
        }
        if (StringUtils.isNotBlank(reqVO.getRiskLevel())) {
            wrapper.eq(MentalMgmtDO::getRiskLevel, reqVO.getRiskLevel());
        }
        if (StringUtils.isNotBlank(reqVO.getStatus())) {
            wrapper.eq(MentalMgmtDO::getStatus, reqVO.getStatus());
        }

        if (null != reqVO.getEvaluateTime()) {
            wrapper.between(MentalMgmtDO::getEvaluateTime, reqVO.getEvaluateTime()[0], reqVO.getEvaluateTime()[1]);
        }
        if (null != reqVO.getConsultTime()) {
            wrapper.between(MentalMgmtDO::getConsultTime, reqVO.getConsultTime()[0], reqVO.getConsultTime()[1]);
        }
        if (null != reqVO.getInterveneTime()) {
            wrapper.between(MentalMgmtDO::getInterveneTime, reqVO.getInterveneTime()[0], reqVO.getInterveneTime()[1]);
        }

        wrapper.orderByDesc(MentalMgmtDO::getId);// ===== 主表字段 =====

        // 3. 执行联表分页查询
        IPage<MentalMgmtJoinPageRespVO> resultPage = selectJoinPage(page, MentalMgmtJoinPageRespVO.class, wrapper);

        // 4. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }
}