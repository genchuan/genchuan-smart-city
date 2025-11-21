// Type definitions
export interface Category {
  id: string;
  name: string;
}

export interface AttributeField {
  fieldName: string;
  fieldCode: string;
  fieldType: string;
  fieldLength: string;
  constraintType: 'required' | 'optional';
  valueRange?: string;
}

export type Requirement =
  | {
  dataReqId: string;
  componentCategoryId: string;
  componentCategoryName: string;
  dataType: 'spatial';
  coordSystem: string;
  accuracyLevel: 'A' | 'B';
  elevationDatum: string;
  summary: string;
  createUser: string;
  createTime: string;
}
  | {
  dataReqId: string;
  componentCategoryId: string;
  componentCategoryName: string;
  dataType: 'attribute';
  fields: AttributeField[];
  summary: string;
  createUser: string;
  createTime: string;
};

// Mock data
const categories: Category[] = [
  { id: 'cat_gas_pipeline', name: '燃气管道（小类）' },
  { id: 'cat_water_pipe', name: '给水管道（小类）' },
];

let requirements: Requirement[] = [
  {
    dataReqId: 'req-001',
    componentCategoryId: 'cat_gas_pipeline',
    componentCategoryName: '燃气管道',
    dataType: 'spatial',
    coordSystem: '2000国家大地坐标系',
    accuracyLevel: 'A',
    elevationDatum: '1985国家高程基准',
    summary: '燃气管道空间数据要求',
    createUser: 'admin',
    createTime: '2025-02-10 09:00:00',
  },
];

// Delay util
const sleep = (ms = 80) => new Promise(r => setTimeout(r, ms));

// APIs
export async function fetchCategoryList(): Promise<Category[]> {
  await sleep();
  return categories;
}

export async function fetchRequirementList(params?: {
  componentCategoryId?: string;
  dataType?: 'spatial' | 'attribute';
}) {
  await sleep();
  return {
    total: requirements.length,
    records: requirements.filter(
      r =>
        (!params?.componentCategoryId ||
          r.componentCategoryId === params.componentCategoryId) &&
        (!params?.dataType || r.dataType === params.dataType)
    ),
  };
}

export async function createRequirement(req: Requirement) {
  await sleep();
  requirements.push(req);
  return true;
}

export async function updateRequirement(req: Requirement) {
  await sleep();
  const idx = requirements.findIndex(r => r.dataReqId === req.dataReqId);
  if (idx !== -1) requirements[idx] = req;
  return true;
}

export async function deleteRequirementById(id: string) {
  await sleep();
  requirements = requirements.filter(r => r.dataReqId !== id);
  return true;
}
