import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {
  CurrencyDto,
  CurrencyValueDto,
  getHistoryResponse,
} from './models/models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CurrencyServiceService {
  constructor(private httpClient: HttpClient) {}

  getCurrencyValue(currencyDto: CurrencyDto): Observable<CurrencyValueDto> {
    return this.httpClient.post<CurrencyValueDto>(
      'http://localhost:8080/currencies/get-current-currency-value-command',
      currencyDto
    );
  }

  getHistory(
    page: number = 0,
    size: number = 10
  ): Observable<getHistoryResponse> {
    return this.httpClient.get<getHistoryResponse>(
      `http://localhost:8080/currencies/requests?page=${page}&size=${size}`
    );
  }
}
