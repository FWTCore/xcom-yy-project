import request from "@/utils/request";

// 账实核对
export function metricsList(type, query) {
  return request({
    url: `/asset/check/${type}/metrics/list`,
    method: "get",
    params: query,
  });
}

export function relation(physicalIds, ledgerIds) {
  return request({
    url: "/asset/check/relational",
    method: "post",
    data: {
      physicalIds,
      ledgerIds,
    },
  });
}
