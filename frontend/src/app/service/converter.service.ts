import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, tap, throwError} from "rxjs";
import {API_URL} from "../util/config";

@Injectable({
  providedIn: 'root'
})
export class ConverterService {

  constructor(private http: HttpClient) {}

  docToPdf(data : any):Observable<any> {
    return this.http.post<any>(API_URL + '/api/converter/doc-pdf', data);
  }
}
