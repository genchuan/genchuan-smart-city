package cn.iocoder.yudao.module.smartcity.dal.mysql.institutionalinformationinput;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.institutionalinformationinput.InstitutionalInformationInputDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.institutionalinformationinput.vo.*;

/**
 * 机构信息录入 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface InstitutionalInformationInputMapper extends BaseMapperX<InstitutionalInformationInputDO> {

    default PageResult<InstitutionalInformationInputDO> selectPage(InstitutionalInformationInputPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InstitutionalInformationInputDO>()
                .likeIfPresent(InstitutionalInformationInputDO::getInstitutionName, reqVO.getInstitutionName())
                .eqIfPresent(InstitutionalInformationInputDO::getUnifiedSocialCreditCode, reqVO.getUnifiedSocialCreditCode())
                .betweenIfPresent(InstitutionalInformationInputDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InstitutionalInformationInputDO::getId));
    }

}