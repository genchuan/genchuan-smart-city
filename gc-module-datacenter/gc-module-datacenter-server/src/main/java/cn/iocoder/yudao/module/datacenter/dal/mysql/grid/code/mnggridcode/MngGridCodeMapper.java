package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.code.mnggridcode;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.mnggridcode.vo.MngGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.mnggridcode.MngGridCodeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理网格编码 Mapper
 *
 * @author zcq
 */
@Mapper
public interface MngGridCodeMapper extends BaseMapperX<MngGridCodeDO> {

    default PageResult<MngGridCodeDO> selectPage(MngGridCodePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MngGridCodeDO>()
                .eqIfPresent(MngGridCodeDO::getMgCodeId, reqVO.getMgCodeId())
                .eqIfPresent(MngGridCodeDO::getMgGridCode, reqVO.getMgGridCode())
                .eqIfPresent(MngGridCodeDO::getMngGridId, reqVO.getMngGridId())
                .eqIfPresent(MngGridCodeDO::getAreaFullCode, reqVO.getAreaFullCode())
                .eqIfPresent(MngGridCodeDO::getSeqCode, reqVO.getSeqCode())
                .betweenIfPresent(MngGridCodeDO::getGenerateTime, reqVO.getGenerateTime())
                .eqIfPresent(MngGridCodeDO::getCodeStatus, reqVO.getCodeStatus())
                .eqIfPresent(MngGridCodeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MngGridCodeDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(MngGridCodeDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(MngGridCodeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MngGridCodeDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MngGridCodeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MngGridCodeDO::getId));
    }

    /**
     * 根据行政区划代码查询最大的顺序码
     */
    default Integer selectMaxSeqCodeByArea(String areaFullCode) {
        // 使用 selectOne 查询最大的顺序码
        MngGridCodeDO maxRecord = selectOne(new LambdaQueryWrapperX<MngGridCodeDO>()
                .eq(MngGridCodeDO::getAreaFullCode, areaFullCode)
                .select(MngGridCodeDO::getSeqCode)
                .orderByDesc(MngGridCodeDO::getSeqCode)
                .last("LIMIT 1"));

        if (maxRecord != null && maxRecord.getSeqCode() != null) {
            try {
                return Integer.valueOf(maxRecord.getSeqCode());
            } catch (NumberFormatException e) {
                // 如果顺序码不是数字，返回null
                return null;
            }
        }
        return null;
    }

    /**
     * 根据编码查询数量（用于唯一性校验）
     */
    default Long selectCountByCode(String mgGridCode, Long excludeId) {
        return selectCount(new LambdaQueryWrapperX<MngGridCodeDO>()
                .eq(MngGridCodeDO::getMgGridCode, mgGridCode)
                .neIfPresent(MngGridCodeDO::getId, excludeId)
        );
    }

    /**
     * 根据管理网格ID查询
     */
    default MngGridCodeDO selectByMngGridId(String mngGridId) {
        return selectOne(MngGridCodeDO::getMngGridId, mngGridId);
    }

    /**
     * 根据编码查询
     */
    default MngGridCodeDO selectByCode(String mgGridCode) {
        return selectOne(MngGridCodeDO::getMgGridCode, mgGridCode);
    }

    /**
     * 根据行政区划代码查询列表
     */
    default List<MngGridCodeDO> selectListByArea(String areaFullCode) {
        return selectList(MngGridCodeDO::getAreaFullCode, areaFullCode);
    }

}