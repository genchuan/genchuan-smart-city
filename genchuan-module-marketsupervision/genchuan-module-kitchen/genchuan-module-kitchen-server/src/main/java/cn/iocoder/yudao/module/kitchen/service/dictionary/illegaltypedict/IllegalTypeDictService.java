package cn.iocoder.yudao.module.kitchen.service.dictionary.illegaltypedict;

import java.util.*;

import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo.IllegalTypeDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo.IllegalTypeDictSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegaltypedict.IllegalTypeDictDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 违规类型字典 Service 接口
 *
 * @author 亘川智城
 */
public interface IllegalTypeDictService {

    /**
     * 创建违规类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIllegalTypeDict(@Valid IllegalTypeDictSaveReqVO createReqVO);

    /**
     * 更新违规类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateIllegalTypeDict(@Valid IllegalTypeDictSaveReqVO updateReqVO);

    /**
     * 删除违规类型字典
     *
     * @param id 编号
     */
    void deleteIllegalTypeDict(Long id);

    /**
     * 获得违规类型字典
     *
     * @param id 编号
     * @return 违规类型字典
     */
    IllegalTypeDictDO getIllegalTypeDict(Long id);

    /**
     * 获得违规类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 违规类型字典分页
     */
    PageResult<IllegalTypeDictDO> getIllegalTypeDictPage(IllegalTypeDictPageReqVO pageReqVO);

}
