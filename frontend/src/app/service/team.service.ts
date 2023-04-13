import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, filter, Observable, tap, throwError} from "rxjs";
import {API_URL} from "../util/config";

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  constructor(private http: HttpClient) {}

  addTeam(data : any):Observable<any> {
    return this.http.post<any>(API_URL + '/api/team/add-team', data).pipe(
      catchError(error => {
        let errorMessage = 'An error occurred while add team. Please try again later.';
        if (error.error && error.error.message) {
          errorMessage = error.error.message;
        }
        return throwError(errorMessage);
      })
    );
  }

  addTask(data : any):Observable<any> {
    return this.http.post<any>(API_URL + `/api/teams/${data.id}/tasks/add-task`, data.payload).pipe(
      catchError(error => {
        let errorMessage = 'An error occurred while adding task. Please try again later.';
        if (error.error && error.error.message) {
          errorMessage = error.error.message;
        }
        return throwError(errorMessage);
      })
    );
  }

  updateTeam(data : any):Observable<any> {
    return this.http.patch<any>(API_URL + `/api/team/update-team?id=${data.id}`, data.payload).pipe(
      catchError(error => {
        let errorMessage = 'An error occurred while updating current team. Please try again later.';
        if (error.error && error.error.message) {
          errorMessage = error.error.message;
        }
        return throwError(errorMessage);
      })
    );
  }

  deleteTeam(data : any):Observable<any> {
    return this.http.delete<any>(API_URL + `/api/team/delete-teams?id=${data.id}`).pipe(
      catchError(error => {
        let errorMessage = 'An error occurred while updating current team. Please try again later.';
        if (error.error && error.error.message) {
          errorMessage = error.error.message;
        }
        return throwError(errorMessage);
      })
    );
  }

  getTeams():Observable<any> {
    return this.http.get<any>(API_URL + '/api/team/get-teams').pipe(
      catchError(error => {
        let errorMessage = 'An error occurred while fetching teams. Please try again later.';
        if (error.error && error.error.message) {
          errorMessage = error.error.message;
        }
        return throwError(errorMessage);
      })
    );
  }
}
