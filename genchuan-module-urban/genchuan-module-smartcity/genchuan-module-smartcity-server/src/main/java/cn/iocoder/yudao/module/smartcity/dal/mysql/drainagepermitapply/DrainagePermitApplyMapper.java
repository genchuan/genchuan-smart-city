package cn.iocoder.yudao.module.smartcity.dal.mysql.drainagepermitapply;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagepermitapply.DrainagePermitApplyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagepermitapply.vo.*;

/**
 * 排水许可证申请 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface DrainagePermitApplyMapper extends BaseMapperX<DrainagePermitApplyDO> {

    default PageResult<DrainagePermitApplyDO> selectPage(DrainagePermitApplyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DrainagePermitApplyDO>()
                .likeIfPresent(DrainagePermitApplyDO::getApplyNo, reqVO.getApplyNo())
                .likeIfPresent(DrainagePermitApplyDO::getUserName, reqVO.getUserName())
                .likeIfPresent(DrainagePermitApplyDO::getApplyStatus, reqVO.getApplyStatus())
                .betweenIfPresent(DrainagePermitApplyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DrainagePermitApplyDO::getId));
    }

}