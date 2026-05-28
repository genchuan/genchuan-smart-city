package cn.iocoder.yudao.module.smartcampus.dal.mysql.archive;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcampus.dal.dataobject.archive.ArchiveDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo.*;

import java.util.List;

/**
 * 学生学籍档案 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ArchiveMapper extends BaseMapperX<ArchiveDO> {

    default PageResult<ArchiveDO> selectPage(ArchivePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ArchiveDO>()
                .eqIfPresent(ArchiveDO::getStudentNo, reqVO.getStudentNo())
                .likeIfPresent(ArchiveDO::getName, reqVO.getName())
                .eqIfPresent(ArchiveDO::getClassId, reqVO.getClassId())
                .eqIfPresent(ArchiveDO::getMajor, reqVO.getMajor())
                .eqIfPresent(ArchiveDO::getLevel, reqVO.getLevel())
                .eqIfPresent(ArchiveDO::getStudyType, reqVO.getStudyType())
                .eqIfPresent(ArchiveDO::getIdCard, reqVO.getIdCard())
                .eqIfPresent(ArchiveDO::getPhone, reqVO.getPhone())
                .eqIfPresent(ArchiveDO::getParentPhone, reqVO.getParentPhone())
                .eqIfPresent(ArchiveDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ArchiveDO::getArchiveTime, reqVO.getArchiveTime())
                .eqIfPresent(ArchiveDO::getProcessStatus, reqVO.getProcessStatus())
                .eqIfPresent(ArchiveDO::getRejectReason, reqVO.getRejectReason())
                .eqIfPresent(ArchiveDO::getChangeReason, reqVO.getChangeReason())
                .eqIfPresent(ArchiveDO::getEvidenceUrl, reqVO.getEvidenceUrl())
                .betweenIfPresent(ArchiveDO::getPunishValidTime, reqVO.getPunishValidTime())
                .eqIfPresent(ArchiveDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ArchiveDO::getExtension, reqVO.getExtension())
                .eqIfPresent(ArchiveDO::getCreator, reqVO.getCreator())
                .eqIfPresent(ArchiveDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(ArchiveDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ArchiveDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(ArchiveDO::getId));
    }

    Integer audit(@Param("ids") List<Long> ids, @Param("processStatus") String processStatus,
                  @Param("rejectReason") String rejectReason, @Param("updater") String updater);

}