package cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantinfo;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantinfo.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantinfo.MerchantInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 商户信息 Service 接口
 *
 * @author 亘川智城
 */
public interface MerchantInfoService {

    /**
     * 创建商户信息
     *
     * @param createReqVO 创建信息
     */
    Boolean createMerchantInfo(@Valid MerchantInfoSaveReqVO createReqVO);

    /**
     * 更新商户信息
     *
     * @param updateReqVO 更新信息
     */
    void updateMerchantInfo(@Valid MerchantInfoSaveReqVO updateReqVO);

    /**
     * 删除商户信息
     *
     * @param id 编号
     */
    void deleteMerchantInfo(Long id);

    /**
    * 批量删除商户信息
    *
    * @param ids 编号
    */
    void deleteMerchantInfoListByIds(List<Long> ids);

    /**
     * 获得商户信息
     *
     * @param id 编号
     * @return 商户信息
     */
    MerchantInfoDO getMerchantInfo(Long id);

    /**
     * 获得商户信息分页
     *
     * @param pageReqVO 分页查询
     * @return 商户信息分页
     */
    PageResult<MerchantInfoDO> getMerchantInfoPage(MerchantInfoPageReqVO pageReqVO);

    /**
     * 导入商户信息
     *
     * @param list 商户信息
     */
    Boolean importInfos(List<MerchantInfoImportExcelVO> list, Boolean updateSupport);

    /**
     * 批量更新商户审核
     *
     * @param reqVO 更新信息
     */
    void batchUpdatePlateAuth(@Valid MerchantInfoSaveReqVO reqVO,int index);
}