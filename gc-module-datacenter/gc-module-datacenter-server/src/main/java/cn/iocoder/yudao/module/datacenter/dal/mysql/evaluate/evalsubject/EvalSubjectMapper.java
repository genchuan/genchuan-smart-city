package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.evalsubject;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject.vo.EvalSubjectPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.evalsubject.EvalSubjectDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价主体管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalSubjectMapper extends BaseMapperX<EvalSubjectDO> {

    default PageResult<EvalSubjectDO> selectPage(EvalSubjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalSubjectDO>()
                .eqIfPresent(EvalSubjectDO::getEvalSubjectId, reqVO.getEvalSubjectId())
                .likeIfPresent(EvalSubjectDO::getEvalSubjectName, reqVO.getEvalSubjectName())
                .eqIfPresent(EvalSubjectDO::getSubjectCode, reqVO.getSubjectCode())
                .eqIfPresent(EvalSubjectDO::getSubjectType, reqVO.getSubjectType())
                .eqIfPresent(EvalSubjectDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(EvalSubjectDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(EvalSubjectDO::getMemberIds, reqVO.getMemberIds())
                .eqIfPresent(EvalSubjectDO::getMemberNames, reqVO.getMemberNames())
                .eqIfPresent(EvalSubjectDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(EvalSubjectDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(EvalSubjectDO::getSubjectStatus, reqVO.getSubjectStatus())
                .eqIfPresent(EvalSubjectDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalSubjectDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalSubjectDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalSubjectDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalSubjectDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalSubjectDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalSubjectDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalSubjectDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalSubjectDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalSubjectDO::getId));
    }

}