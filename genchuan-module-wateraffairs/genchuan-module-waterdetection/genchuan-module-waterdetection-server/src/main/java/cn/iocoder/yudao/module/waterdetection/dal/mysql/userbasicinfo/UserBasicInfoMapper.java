package cn.iocoder.yudao.module.waterdetection.dal.mysql.userbasicinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.userbasicinfo.UserBasicInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.userbasicinfo.vo.*;

/**
 * 用户基础信息登记 Mapper
 *
 * @author zcq
 */
@Mapper
public interface UserBasicInfoMapper extends BaseMapperX<UserBasicInfoDO> {

    default PageResult<UserBasicInfoDO> selectPage(UserBasicInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserBasicInfoDO>()
                .eqIfPresent(UserBasicInfoDO::getUserCode, reqVO.getUserCode())
                .likeIfPresent(UserBasicInfoDO::getUserName, reqVO.getUserName())
                .eqIfPresent(UserBasicInfoDO::getIdCardNo, reqVO.getIdCardNo())
                .eqIfPresent(UserBasicInfoDO::getAddress, reqVO.getAddress())
                .eqIfPresent(UserBasicInfoDO::getPhone, reqVO.getPhone())
                .betweenIfPresent(UserBasicInfoDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(UserBasicInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserBasicInfoDO::getId));
    }

}