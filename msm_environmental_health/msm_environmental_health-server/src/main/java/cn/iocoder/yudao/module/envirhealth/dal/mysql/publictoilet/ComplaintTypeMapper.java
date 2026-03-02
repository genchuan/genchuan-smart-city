package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.complainttype.ComplaintTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ComplaintTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 投诉类型字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ComplaintTypeMapper extends BaseMapperX<ComplaintTypeDO> {

    default PageResult<ComplaintTypeDO> selectPage(ComplaintTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ComplaintTypeDO>()
                .eqIfPresent(ComplaintTypeDO::getComplaintTypeId, reqVO.getComplaintTypeId())
                .likeIfPresent(ComplaintTypeDO::getComplaintName, reqVO.getComplaintName())
                .eqIfPresent(ComplaintTypeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(ComplaintTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ComplaintTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ComplaintTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ComplaintTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ComplaintTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ComplaintTypeDO::getId));
    }

}