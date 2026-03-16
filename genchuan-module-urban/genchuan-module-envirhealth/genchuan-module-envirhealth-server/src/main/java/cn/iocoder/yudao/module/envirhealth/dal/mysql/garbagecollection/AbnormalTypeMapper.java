package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.abnormaltype.AbnormalTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.AbnormalTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 垃圾异常类型字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AbnormalTypeMapper extends BaseMapperX<AbnormalTypeDO> {

    default PageResult<AbnormalTypeDO> selectPage(AbnormalTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AbnormalTypeDO>()
                .eqIfPresent(AbnormalTypeDO::getAbnormalTypeId, reqVO.getAbnormalTypeId())
                .likeIfPresent(AbnormalTypeDO::getAbnormalName, reqVO.getAbnormalName())
                .eqIfPresent(AbnormalTypeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(AbnormalTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AbnormalTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AbnormalTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AbnormalTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AbnormalTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AbnormalTypeDO::getId));
    }

}