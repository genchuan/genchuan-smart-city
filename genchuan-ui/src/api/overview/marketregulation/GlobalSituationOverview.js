import request from '@/utils/request';

const BASE_URL = '/admin-api/industry';

/**
 * 获得市监案件总览视图
 * @param {Object} params - 请求参数
 * @returns {Promise} - 返回案件总览数据
 */
export const fetchMarketCaseOverview = async (params = {}) => {
  // 模拟数据（作为兜底）
  const mockData = {
    totalCase: 120,
    pendAcceptCase: 30,
    handlCase: 50,
    completedCase: 40,
    overtimeCase: 5,
    caseHandleRate: 66.7
  };

  try {
    const response = await request.get({
      url: `${BASE_URL}/market-case-view/get`,
      params
    });

    console.log("市监案件总览接口响应:", response);

    // 处理接口响应
    if (response && response.code === 0 && response.data) {
      return response.data;
    } else {
      console.warn('市监案件总览接口返回格式异常，使用模拟数据');
      return mockData;
    }
  } catch (error) {
    console.warn('市监案件总览接口调用失败，使用模拟数据:', error.message);
    return mockData;
  }
};

/**
 * 获得监管对象分布视图
 * @param {Object} params - 请求参数
 * @returns {Promise} - 返回监管对象分布数据
 */
export const fetchMarketSupvOverview = async (params = {}) => {
  // 模拟数据（作为兜底）
  const mockData = {
    totalSupvObj: 1200,
    entCount: 500,
    individualCount: 300,
    specEquipCount: 400,
    unsupvdCount: 100,
    supvCoverageRate: 91.67
  };

  try {
    const response = await request.get({
      url: `${BASE_URL}/market-supv-view/get`,
      params
    });

    console.log("监管对象分布接口响应:", response);

    // 处理接口响应
    if (response && response.code === 0 && response.data) {
      return response.data;
    } else {
      console.warn('监管对象分布接口返回格式异常，使用模拟数据');
      return mockData;
    }
  } catch (error) {
    console.warn('监管对象分布接口调用失败，使用模拟数据:', error.message);
    return mockData;
  }
};

/**
 * 获得市监核心指标
 * @param {Object} params - 请求参数
 * @returns {Promise} - 返回核心指标数据
 */
export const fetchMarketCoreMetrics = async (params = {}) => {
  // 模拟数据（作为兜底）
  const mockData = {
    totalEntity: 523440,
    todayNewCase: 34,
    smplQualifiedRate: 97.5,
    warnHandleRate: 88.3,
    goodCreditRate: 72.1
  };

  try {
    const response = await request.get({
      url: `${BASE_URL}/market-core-metrics/get`,
      params
    });

    console.log("市监核心指标接口响应:", response);

    // 处理接口响应
    if (response && response.code === 0 && response.data) {
      return response.data;
    } else {
      console.warn('市监核心指标接口返回格式异常，使用模拟数据');
      return mockData;
    }
  } catch (error) {
    console.warn('市监核心指标接口调用失败，使用模拟数据:', error.message);
    return mockData;
  }
};

/**
 * 获得市监全域数据地图
 * @param {Object} params - 请求参数
 * @returns {Promise} - 返回全域数据地图数据
 */
export const fetchMarketOverviewMap = async (params = {}) => {
  // 模拟数据（作为兜底）
  const mockData = {
    totalSupvObj: 5000,
    handlCaseCount: 120,
    pendWarnCount: 30,
    regionCoverageRate: 95
  };

  try {
    const response = await request.get({
      url: `${BASE_URL}/market-overview/get`,
      params
    });

    console.log("市监全域数据地图接口响应:", response);

    // 处理接口响应
    if (response && response.code === 0 && response.data) {
      return response.data;
    } else {
      console.warn('市监全域数据地图接口返回格式异常，使用模拟数据');
      return mockData;
    }
  } catch (error) {
    console.warn('市监全域数据地图接口调用失败，使用模拟数据:', error.message);
    return mockData;
  }
};

/**
 * 筛选市场监管案件列表
 * @param {Object} params - 筛选参数
 * @returns {Promise} - 返回案件列表
 */
export const filterMarketCases = async (params = {}) => {
  // 模拟数据生成函数
  const generateMockCases = (page, size, total) => {
    const cases = [];
    const startIndex = (page - 1) * size;

    for (let i = 0; i < size && startIndex + i < total; i++) {
      const id = startIndex + i + 1;
      const status = id % 4; // 0-3 四种状态
      const caseType = id % 2; // 0-1 两种类型
      const overtimeDays = status === 1 && id % 5 === 0 ? Math.floor(Math.random() * 10) + 1 : 0;

      // 生成随机日期（近30天内）
      const today = new Date();
      const randomDays = Math.floor(Math.random() * 30);
      const acceptDate = new Date(today);
      acceptDate.setDate(today.getDate() - randomDays);

      cases.push({
        caseId: `SC${2024}${(id + 1000).toString().slice(1)}`,
        caseName: `${caseType === 0 ? '食品' : '质量'}安全${status === 0 ? '待受理' : status === 1 ? '办理中' : status === 2 ? '已办结' : '已驳回'}案件${id}`,
        caseType: caseType,
        status: status,
        acceptTime: acceptDate.toISOString().slice(0, 19).replace('T', ' '),
        handleDept: ['市监局一队', '市监局二队', '市监局三队'][id % 3],
        overtimeDays: overtimeDays,
        progress: status === 2 ? 100 : status === 3 ? 0 : Math.floor(Math.random() * 80) + 10
      });
    }

    return cases;
  };

  try {
    const { page = 1, size = 10 } = params;
    const total = 87; // 模拟总条数

    // 实际项目中这里会调用真实接口
    // const response = await request.get({
    //   url: `${BASE_URL}/market-case/list`,
    //   params
    // });

    // 模拟接口延迟
    await new Promise(resolve => setTimeout(resolve, 500));

    console.log("筛选市场监管案件接口参数:", params);

    // 返回模拟数据
    return {
      records: generateMockCases(page, size, total),
      total: total,
      size: size,
      current: page,
      pages: Math.ceil(total / size)
    };

  } catch (error) {
    console.warn('筛选市场监管案件接口调用失败，使用模拟数据:', error.message);
    const { page = 1, size = 10 } = params;
    return {
      records: generateMockCases(page, size, 50),
      total: 50,
      size: size,
      current: page,
      pages: Math.ceil(50 / size)
    };
  }
};

/**
 * 获取案件详情
 * @param {string} caseId - 案件ID
 * @returns {Promise} - 返回案件详情
 */
export const fetchMarketCaseDetail = async (caseId) => {
  try {
    console.log("获取案件详情，ID:", caseId);

    // 模拟接口延迟
    await new Promise(resolve => setTimeout(resolve, 800));

    // 生成模拟的处置日志
    const handleLogs = [];
    const statusMap = ['待受理', '办理中', '已办结', '已驳回'];
    const status = parseInt(caseId.slice(-2)) % 4;

    // 根据状态生成不同的日志
    for (let i = 0; i <= status; i++) {
      const logDate = new Date();
      logDate.setHours(logDate.getHours() - (status - i) * 24);

      handleLogs.push({
        operateTime: logDate.toISOString().slice(0, 19).replace('T', ' '),
        operator: `操作员${i + 1}`,
        content: `${i === 0 ? '受理案件' : i === 1 ? '开始调查' : i === 2 ? '完成处置' : '驳回案件'}，状态变更为${statusMap[i]}`
      });
    }

    // 生成模拟的截止日期
    const acceptDate = new Date();
    acceptDate.setDate(acceptDate.getDate() - 5);
    const dueDate = new Date(acceptDate);
    dueDate.setDate(acceptDate.getDate() + 7);

    // 模拟案件类型
    const caseType = caseId.includes('SC20241') ? 0 : 1;

    // 模拟返回数据
    return {
      caseId: caseId,
      caseName: `${caseType === 0 ? '食品' : '质量'}安全案件详情`,
      caseType: caseType,
      status: status,
      acceptTime: acceptDate.toISOString().slice(0, 19).replace('T', ' '),
      dueTime: dueDate.toISOString().slice(0, 19).replace('T', ' '),
      handleDept: '市监局执法支队',
      handler: '张执法',
      description: `这是一起${caseType === 0 ? '食品' : '质量'}安全违法案件，接到群众举报后，执法人员立即前往现场调查取证，目前案件${statusMap[status]}。`,
      progress: status === 2 ? 100 : status === 3 ? 0 : Math.floor(Math.random() * 80) + 10,
      overtimeDays: status === 1 && parseInt(caseId.slice(-1)) % 3 === 0 ? Math.floor(Math.random() * 5) + 1 : 0,
      handleLogs: handleLogs
    };

  } catch (error) {
    console.error('获取案件详情失败:', error);
    throw new Error('获取案件详情失败');
  }
};

/**
 * 导出案件数据
 * @param {Object} params - 导出参数
 * @returns {Promise} - 返回导出结果
 */
export const exportMarketCaseData = async (params) => {
  try {
    console.log("导出案件数据参数:", params);

    // 模拟导出过程
    await new Promise(resolve => setTimeout(resolve, 2000));

    return {
      fileName: `市监案件数据_${new Date().toLocaleDateString().replace(/\//g, '-')}.xlsx`
    };
  } catch (error) {
    console.error('导出案件数据失败:', error);
    throw new Error('导出案件数据失败');
  }
};

/**
 * 导出监管对象数据
 * @param {Object} params - 导出参数
 * @returns {Promise} - 返回导出结果
 */
export const exportSupvObjData = async (params) => {
  try {
    console.log("导出监管对象数据参数:", params);

    // 模拟导出过程
    await new Promise(resolve => setTimeout(resolve, 2000));

    return {
      fileName: `监管对象数据_${new Date().toLocaleDateString().replace(/\//g, '-')}.xlsx`
    };
  } catch (error) {
    console.error('导出监管对象数据失败:', error);
    throw new Error('导出监管对象数据失败');
  }
};
