import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { Observable,Subject } from "rxjs";
import { FormControl, FormGroup } from '@angular/forms';
import { Search } from '../search';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})

export class EmployeeListComponent implements OnInit {

  constructor(private employeeservice:EmployeeService) {}

  employeesArray: any[] = [];
  dtTrigger: Subject<any> = new Subject();
  employees: Observable<Employee[]>;
  employee: Employee = new Employee();
  employeelist: any;
  searchInfo: Search = new Search();

  ngOnInit(): void {     
    this.employeeservice.getEmployeeList().subscribe(data =>{  
    this.employees = data;  
    this.dtTrigger.next();  
    })  
  }

  form = new FormGroup({
    name : new FormControl(),
    startDate : new FormControl(),
    endDate : new FormControl()
  });

  getEmployeeResults(searchInfo) {
    this.employeeservice.getEmployeeByFilter(searchInfo).subscribe(data =>{
      this.employees = data;
      this.dtTrigger.next();
    })
  }

  searchForm(searchInfo) {
    this.searchInfo.name = this.Name.value;
    this.searchInfo.startDate = this.StartDate.value;
    this.searchInfo.endDate = this.EndDate.value;
    this.getEmployeeResults(this.searchInfo);
  }

  get Name() {
    return this.form.get('name');
  }

  get StartDate() {
    return this.form.get('startDate');
  }

  get EndDate() {
    return this.form.get('endDate');
  }
}
