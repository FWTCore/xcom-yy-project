import request from "@/utils/request";

// 实物核对资产报表概要信息
export function physicalOverView(query) {
  return request({
    url: "/asset/board/physical/overview",
    method: "get",
    params: query,
  });
}

export function physicalMetrics(query) {
  return request({
    url: "/asset/board/physical/metrics",
    method: "get",
    params: query,
  });
}

export function getPhysicalList(query) {
  return request({
    url: "/asset/check/physical/list",
    method: "get",
    params: query,
  });
}
