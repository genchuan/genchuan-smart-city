package cn.iocoder.yudao.module.smartcity.dal.mysql.reportofillegalconstructions;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.reportofillegalconstructions.ReportOfIllegalConstructionsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.reportofillegalconstructions.vo.*;

/**
 * 违建上报 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface ReportOfIllegalConstructionsMapper extends BaseMapperX<ReportOfIllegalConstructionsDO> {

    default PageResult<ReportOfIllegalConstructionsDO> selectPage(ReportOfIllegalConstructionsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ReportOfIllegalConstructionsDO>()
                .eqIfPresent(ReportOfIllegalConstructionsDO::getReportNumber, reqVO.getReportNumber())
                .eqIfPresent(ReportOfIllegalConstructionsDO::getReportperson, reqVO.getReportperson())
                .betweenIfPresent(ReportOfIllegalConstructionsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ReportOfIllegalConstructionsDO::getId));
    }

}