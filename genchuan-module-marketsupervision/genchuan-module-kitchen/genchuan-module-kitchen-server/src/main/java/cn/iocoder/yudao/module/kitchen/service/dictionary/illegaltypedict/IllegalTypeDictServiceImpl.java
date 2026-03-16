package cn.iocoder.yudao.module.kitchen.service.dictionary.illegaltypedict;

import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo.IllegalTypeDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo.IllegalTypeDictSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegaltypedict.IllegalTypeDictDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.illegaltypedict.IllegalTypeDictMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.*;

/**
 * 违规类型字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class IllegalTypeDictServiceImpl implements IllegalTypeDictService {

    @Resource
    private IllegalTypeDictMapper illegalTypeDictMapper;

    @Override
    public Long createIllegalTypeDict(IllegalTypeDictSaveReqVO createReqVO) {
        // 插入
        IllegalTypeDictDO illegalTypeDict = BeanUtils.toBean(createReqVO, IllegalTypeDictDO.class);
        illegalTypeDictMapper.insert(illegalTypeDict);
        // 返回
        return illegalTypeDict.getId();
    }

    @Override
    public void updateIllegalTypeDict(IllegalTypeDictSaveReqVO updateReqVO) {
        // 校验存在
        validateIllegalTypeDictExists(updateReqVO.getId());
        // 更新
        IllegalTypeDictDO updateObj = BeanUtils.toBean(updateReqVO, IllegalTypeDictDO.class);
        illegalTypeDictMapper.updateById(updateObj);
    }

    @Override
    public void deleteIllegalTypeDict(Long id) {
        // 校验存在
        validateIllegalTypeDictExists(id);
        // 删除
        illegalTypeDictMapper.deleteById(id);
    }

    private void validateIllegalTypeDictExists(Long id) {
        if (illegalTypeDictMapper.selectById(id) == null) {
            throw exception(ILLEGAL_TYPE_DICT_NOT_EXISTS);
        }
    }

    @Override
    public IllegalTypeDictDO getIllegalTypeDict(Long id) {
        return illegalTypeDictMapper.selectById(id);
    }

    @Override
    public PageResult<IllegalTypeDictDO> getIllegalTypeDictPage(IllegalTypeDictPageReqVO pageReqVO) {
        return illegalTypeDictMapper.selectPage(pageReqVO);
    }

}
