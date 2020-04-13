import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class EmployeeService {

  private baseUrl = 'http://localhost:8080/api/';

  constructor(private http:HttpClient) { }

  getEmployeeList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+'employee-list');
  }

  createEmployee(employee: object): Observable<object> {
    return this.http.post(`${this.baseUrl}`+'save-employee', employee);
  }

  getEmployeeByFilter(searchInfo: object): Observable<any> {
    return this.http.post(`${this.baseUrl}`+'employee-list', searchInfo);
  }
}
