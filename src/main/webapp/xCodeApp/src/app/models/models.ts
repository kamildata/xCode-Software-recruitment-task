export interface CurrencyDto {
  name: string;
  currency: string;
}

export interface CurrencyValueDto {
  value: number;
}

export interface HistoryDto {
  currency: string;
  name: string;
  date: Date;
  value: number;
}

export interface getHistoryResponse {
  content: HistoryDto[];
  pageable: Pageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  sort: Sort;
  first: boolean;
  numberOfElements: number;
  empty: boolean;
}

export interface Sort {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
}

export interface Pageable {
  pageNumber: number;
  pageSize: number;
  sort: Sort;
  offset: number;
  unpaged: boolean;
  paged: boolean;
}
