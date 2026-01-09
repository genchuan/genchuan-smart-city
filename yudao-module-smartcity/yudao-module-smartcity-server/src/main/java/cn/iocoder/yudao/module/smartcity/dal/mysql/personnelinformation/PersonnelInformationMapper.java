package cn.iocoder.yudao.module.smartcity.dal.mysql.personnelinformation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.personnelinformation.PersonnelInformationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.personnelinformation.vo.*;

/**
 * 人员信息 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface PersonnelInformationMapper extends BaseMapperX<PersonnelInformationDO> {

    default PageResult<PersonnelInformationDO> selectPage(PersonnelInformationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PersonnelInformationDO>()
                .likeIfPresent(PersonnelInformationDO::getFullName, reqVO.getFullName())
                .eqIfPresent(PersonnelInformationDO::getGender, reqVO.getGender())
                .eqIfPresent(PersonnelInformationDO::getIdNumber, reqVO.getIdNumber())
                .betweenIfPresent(PersonnelInformationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PersonnelInformationDO::getId));
    }

}