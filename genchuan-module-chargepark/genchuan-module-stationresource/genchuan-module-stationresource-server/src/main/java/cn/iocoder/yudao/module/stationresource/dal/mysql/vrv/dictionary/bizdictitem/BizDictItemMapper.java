package cn.iocoder.yudao.module.stationresource.dal.mysql.vrv.dictionary.bizdictitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo.BizDictItemPageReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.vrv.dictionary.bizdictitem.BizDictItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 业务字典项 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BizDictItemMapper extends BaseMapperX<BizDictItemDO> {

    default PageResult<BizDictItemDO> selectPage(BizDictItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BizDictItemDO>()
                .eqIfPresent(BizDictItemDO::getTypeCode, reqVO.getTypeCode())
                .eqIfPresent(BizDictItemDO::getDictKey, reqVO.getDictKey())
                .eqIfPresent(BizDictItemDO::getDictLabel, reqVO.getDictLabel())
                .eqIfPresent(BizDictItemDO::getColor, reqVO.getColor())
                .eqIfPresent(BizDictItemDO::getSort, reqVO.getSort())
                .eqIfPresent(BizDictItemDO::getDescription, reqVO.getDescription())
                .eqIfPresent(BizDictItemDO::getRemark, reqVO.getRemark())
                .eqIfPresent(BizDictItemDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BizDictItemDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(BizDictItemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(BizDictItemDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(BizDictItemDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(BizDictItemDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(BizDictItemDO::getId));
    }


}
