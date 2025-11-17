package cn.iocoder.yudao.module.datacenter.dal.mysql.businessstandard.processexecstd;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.processexecstd.vo.ProcessExecStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessstandard.processexecstd.ProcessExecStdDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 流程执行规范 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ProcessExecStdMapper extends BaseMapperX<ProcessExecStdDO> {

    default PageResult<ProcessExecStdDO> selectPage(ProcessExecStdPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProcessExecStdDO>()
                .eqIfPresent(ProcessExecStdDO::getProcessExecStdId, reqVO.getProcessExecStdId())
                .likeIfPresent(ProcessExecStdDO::getStdName, reqVO.getStdName())
                .eqIfPresent(ProcessExecStdDO::getRelatedProcessId, reqVO.getRelatedProcessId())
                .likeIfPresent(ProcessExecStdDO::getRelatedProcessName, reqVO.getRelatedProcessName())
                .eqIfPresent(ProcessExecStdDO::getProcessSteps, reqVO.getProcessSteps())
                .eqIfPresent(ProcessExecStdDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(ProcessExecStdDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(ProcessExecStdDO::getTimeLimit, reqVO.getTimeLimit())
                .eqIfPresent(ProcessExecStdDO::getExecRequirement, reqVO.getExecRequirement())
                .eqIfPresent(ProcessExecStdDO::getStdFileId, reqVO.getStdFileId())
                .eqIfPresent(ProcessExecStdDO::getStdFilePath, reqVO.getStdFilePath())
                .eqIfPresent(ProcessExecStdDO::getStdStatus, reqVO.getStdStatus())
                .eqIfPresent(ProcessExecStdDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(ProcessExecStdDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ProcessExecStdDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(ProcessExecStdDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ProcessExecStdDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ProcessExecStdDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ProcessExecStdDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ProcessExecStdDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(ProcessExecStdDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(ProcessExecStdDO::getId));
    }

}