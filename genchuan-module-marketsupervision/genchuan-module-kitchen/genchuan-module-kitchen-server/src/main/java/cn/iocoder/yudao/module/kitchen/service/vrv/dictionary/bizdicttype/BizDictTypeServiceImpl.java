package cn.iocoder.yudao.module.kitchen.service.vrv.dictionary.bizdicttype;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.BizDictTypePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.BizDictTypeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.vrv.dictionary.bizdicttype.BizDictTypeDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.vrv.dictionary.bizdicttype.BizDictTypeMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.*;

/**
 * 业务字典分类 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class BizDictTypeServiceImpl implements BizDictTypeService {

    @Resource
    private BizDictTypeMapper bizDictTypeMapper;

    @Override
    public Long createBizDictType(BizDictTypeSaveReqVO createReqVO) {
        // 插入
        BizDictTypeDO bizDictType = BeanUtils.toBean(createReqVO, BizDictTypeDO.class);
        bizDictTypeMapper.insert(bizDictType);

        // 返回
        return bizDictType.getId();
    }

    @Override
    public void updateBizDictType(BizDictTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateBizDictTypeExists(updateReqVO.getId());
        // 更新
        BizDictTypeDO updateObj = BeanUtils.toBean(updateReqVO, BizDictTypeDO.class);
        bizDictTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteBizDictType(Long id) {
        // 校验存在
        validateBizDictTypeExists(id);
        // 删除
        bizDictTypeMapper.deleteById(id);
    }

    @Override
        public void deleteBizDictTypeListByIds(List<Long> ids) {
        // 删除
        bizDictTypeMapper.deleteByIds(ids);
        }


    private void validateBizDictTypeExists(Long id) {
        if (bizDictTypeMapper.selectById(id) == null) {
        }
    }

    @Override
    public BizDictTypeDO getBizDictType(Long id) {
        return bizDictTypeMapper.selectById(id);
    }

    @Override
    public PageResult<BizDictTypeDO> getBizDictTypePage(BizDictTypePageReqVO pageReqVO) {
        return bizDictTypeMapper.selectPage(pageReqVO);
    }

}
