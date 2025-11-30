import request from "@/utils/request";

// 资产台账报表概要信息
export function ledgerOverView(query) {
  return request({
    url: "/asset/board/ledger/overview",
    method: "get",
    params: query,
  });
}

export function ledgerMetrics(query) {
  return request({
    url: "/asset/board/ledger/metrics",
    method: "get",
    params: query,
  });
}

export function ledgerList(query) {
  return request({
    url: "/asset/check/ledger/list",
    method: "get",
    params: query,
  });
}
