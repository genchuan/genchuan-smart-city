package cn.iocoder.yudao.module.stationresource.service.stationresource.stationmgmt.debtexpand;

import java.util.*;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.chart.DebtExpandChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.DebtExpandPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.DebtExpandSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops.DebtExpandCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops.UpdateDebtExpandReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.debtexpand.DebtExpandDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 联合追缴拓场配置 Service 接口
 *
 * @author 亘川智城
 */
public interface DebtExpandService {

    /**
     * 创建联合追缴拓场配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDebtExpand(@Valid DebtExpandSaveReqVO createReqVO);

    /**
     * 更新联合追缴拓场配置
     *
     * @param updateReqVO 更新信息
     */
    void updateDebtExpand(@Valid DebtExpandSaveReqVO updateReqVO);

    /**
     * 删除联合追缴拓场配置
     *
     * @param id 编号
     */
    void deleteDebtExpand(Long id);

    /**
    * 批量删除联合追缴拓场配置
    *
    * @param ids 编号
    */
    void deleteDebtExpandListByIds(List<Long> ids);

    /**
     * 获得联合追缴拓场配置
     *
     * @param id 编号
     * @return 联合追缴拓场配置
     */
    DebtExpandDO getDebtExpand(Long id);

    /**
     * 获得联合追缴拓场配置分页
     *
     * @param pageReqVO 分页查询
     * @return 联合追缴拓场配置分页
     */
    PageResult<DebtExpandDO> getDebtExpandPage(DebtExpandPageReqVO pageReqVO);

    Long addDebtExpand(DebtExpandCreateReqVO createReqVO);

    ImportRespVO importDebtExpand(MultipartFile file, boolean updateSupport);

    void updateDebtExpandStatus(List<Long> ids, String 已生效);

    void myUpdateDebtExpand(UpdateDebtExpandReqVO updateReqVO);

    DebtExpandChartRespVO getDebtExpandChart();


}
