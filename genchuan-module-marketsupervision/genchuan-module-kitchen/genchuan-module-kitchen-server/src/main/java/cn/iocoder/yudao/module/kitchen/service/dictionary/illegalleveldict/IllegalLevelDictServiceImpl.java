package cn.iocoder.yudao.module.kitchen.service.dictionary.illegalleveldict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegalleveldict.vo.IllegalLevelDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegalleveldict.vo.IllegalLevelDictSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegalleveldict.IllegalLevelDictDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.illegalleveldict.IllegalLevelDictMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.ILLEGAL_LEVEL_DICT_NOT_EXISTS;

/**
 * 违规等级字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class IllegalLevelDictServiceImpl implements IllegalLevelDictService {

    @Resource
    private IllegalLevelDictMapper illegalLevelDictMapper;

    @Override
    public Long createIllegalLevelDict(IllegalLevelDictSaveReqVO createReqVO) {
        // 插入
        IllegalLevelDictDO illegalLevelDict = BeanUtils.toBean(createReqVO, IllegalLevelDictDO.class);
        illegalLevelDictMapper.insert(illegalLevelDict);
        // 返回
        return illegalLevelDict.getId();
    }

    @Override
    public void updateIllegalLevelDict(IllegalLevelDictSaveReqVO updateReqVO) {
        // 校验存在
        validateIllegalLevelDictExists(updateReqVO.getId());
        // 更新
        IllegalLevelDictDO updateObj = BeanUtils.toBean(updateReqVO, IllegalLevelDictDO.class);
        illegalLevelDictMapper.updateById(updateObj);
    }

    @Override
    public void deleteIllegalLevelDict(Long id) {
        // 校验存在
        validateIllegalLevelDictExists(id);
        // 删除
        illegalLevelDictMapper.deleteById(id);
    }

    private void validateIllegalLevelDictExists(Long id) {
        if (illegalLevelDictMapper.selectById(id) == null) {
            throw exception(ILLEGAL_LEVEL_DICT_NOT_EXISTS);
        }
    }

    @Override
    public IllegalLevelDictDO getIllegalLevelDict(Long id) {
        return illegalLevelDictMapper.selectById(id);
    }

    @Override
    public PageResult<IllegalLevelDictDO> getIllegalLevelDictPage(IllegalLevelDictPageReqVO pageReqVO) {
        return illegalLevelDictMapper.selectPage(pageReqVO);
    }

}
