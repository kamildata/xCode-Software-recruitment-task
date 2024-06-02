import { MatTableDataSource } from '@angular/material/table';
import { CurrencyServiceService } from './currency-service.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { CurrencyDto, HistoryDto } from './models/models';
import { finalize } from 'rxjs';
import { MatPaginator } from '@angular/material/paginator';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  isLoading = false;
  isLoadingTable = false;
  showError = false;
  errorMessage: string;
  showCurrencyValue = false;

  currencyValueForm: FormGroup;
  currencyValue: number;
  historyDataToTable: HistoryDto[];

  displayedColumns: string[] = ['name', 'currency', 'value', 'date'];
  pageSize = 10;
  pageIndex = 0;
  length: number;

  @ViewChild(MatPaginator) pagination!: MatPaginator;

  constructor(
    private formBuilder: FormBuilder,
    private currencyService: CurrencyServiceService
  ) {}

  ngOnInit(): void {
    this.currencyValueForm = this.formBuilder.group({
      name: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(255),
        ],
      ],
      currency: [
        '',
        [Validators.required, Validators.minLength(3), Validators.maxLength(3)],
      ],
    });
  }

  getCurrencyValue() {
    this.showError = false;
    this.isLoading = true;

    this.currencyService
      .getCurrencyValue(this.currencyValueForm.value)
      .pipe(finalize(() => (this.isLoading = false)))
      .subscribe({
        next: (res) => {
          console.log(res);
          this.currencyValue = res.value;
          this.showCurrencyValue = true;
        },
        error: (res) => {
          this.showError = true;
          this.currencyValue = null;
          console.log(res.status === 404);
          if (res.status === 404) {
            this.errorMessage = 'Incorrect currency code';
          } else {
            this.errorMessage = 'Something went wrong. Try again later.';
          }
        },
      });
  }

  getHistoryData(page: number = 0, size: number = 10) {
    this.isLoadingTable = true;
    this.currencyService
      .getHistory(page, size)
      .pipe(finalize(() => (this.isLoadingTable = false)))
      .subscribe({
        next: (res) => {
          this.historyDataToTable = res.content;
          this.length = res.totalElements;
          console.log(this.historyDataToTable);
        },
        error: (res) => {
          this.showError = true;
          this.errorMessage = "Something went wrong. Try again later.'";
        },
      });
  }

  handlePageEvent(e: PageEvent) {
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
    this.length = e.length;
    this.getHistoryData(e.pageIndex, e.pageSize);
  }
}
